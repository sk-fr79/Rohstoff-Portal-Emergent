"""
Kreditversicherung API Tests - NEW STRUCTURE
Tests for the redesigned Kreditversicherung module with:
- 1:n relationship: 1 Hauptvertrag (Kopf) with Gesamtlimit + Start/End date
- n Kundenpositionen (Unterverträge) with individual limits per customer
- Auslastung = Summe Kundenlimits / Gesamtlimit * 100%
- Hauptvertrag end date always overrides Untervertrag end date
"""

import pytest
import requests
import os
from datetime import datetime, timedelta

# Get BASE_URL from environment - use localhost for testing
BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', 'http://localhost:8001')
if BASE_URL.endswith('/'):
    BASE_URL = BASE_URL.rstrip('/')

# Test credentials
TEST_USERNAME = "admin"
TEST_PASSWORD = "Admin123!"


class TestKreditversicherungNewStructure:
    """Test suite for new Kreditversicherung structure with kunden_positionen"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup: Login and get auth token"""
        self.session = requests.Session()
        self.session.headers.update({"Content-Type": "application/json"})
        
        # Login
        login_response = self.session.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": TEST_USERNAME,
            "passwort": TEST_PASSWORD
        })
        
        if login_response.status_code != 200:
            pytest.skip(f"Login failed: {login_response.status_code} - {login_response.text}")
        
        token = login_response.json().get("access_token") or login_response.json().get("token")
        if not token:
            pytest.skip(f"No token received from login: {login_response.json()}")
        
        self.session.headers.update({"Authorization": f"Bearer {token}"})
        
        # Store created IDs for cleanup
        self.created_kv_ids = []
        self.created_adresse_ids = []
        
        yield
        
        # Cleanup: Delete test data
        for kv_id in self.created_kv_ids:
            try:
                self.session.delete(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
            except:
                pass
        
        for adresse_id in self.created_adresse_ids:
            try:
                self.session.delete(f"{BASE_URL}/api/adressen/{adresse_id}")
            except:
                pass

    # ============================================================
    # HAUPTVERTRAG (KOPF) CRUD TESTS
    # ============================================================
    
    def test_01_list_kreditversicherungen_with_auslastung(self):
        """GET /api/kreditversicherungen - should return list with auslastung_prozent, summe_kundenlimits, anzahl_kunden"""
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        assert "total" in data
        
        # Check that each item has the new calculated fields
        for item in data["data"]:
            assert "auslastung_prozent" in item, "Missing auslastung_prozent field"
            assert "summe_kundenlimits" in item, "Missing summe_kundenlimits field"
            assert "anzahl_kunden" in item, "Missing anzahl_kunden field"
        
        print(f"✓ GET /api/kreditversicherungen returns {len(data['data'])} items with auslastung fields")

    def test_02_create_hauptvertrag_with_gesamtlimit(self):
        """POST /api/kreditversicherungen - create new Hauptvertrag with gesamtlimit, gueltig_von, gueltig_bis"""
        payload = {
            "versicherungsnummer": "TEST-NEW-KV-001",
            "versicherer_name": "Test Versicherung AG",
            "gesamtlimit": 500000,
            "gueltig_von": "2026-01-01",
            "gueltig_bis": "2026-12-31",
            "aktiv": True,
            "bemerkungen": "Test Hauptvertrag mit neuer Struktur"
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen", json=payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        kv = data["data"]
        assert "id" in kv
        assert kv["versicherungsnummer"] == "TEST-NEW-KV-001"
        assert kv["versicherer_name"] == "Test Versicherung AG"
        assert kv["gesamtlimit"] == 500000
        assert kv["gueltig_von"] == "2026-01-01"
        assert kv["gueltig_bis"] == "2026-12-31"
        assert kv["aktiv"] == True
        assert kv["auslastung_prozent"] == 0.0
        assert kv["summe_kundenlimits"] == 0.0
        assert kv["anzahl_kunden"] == 0
        assert "kunden_positionen" in kv
        assert kv["kunden_positionen"] == []
        
        self.created_kv_ids.append(kv["id"])
        print(f"✓ POST /api/kreditversicherungen created Hauptvertrag with ID: {kv['id']}")
        
        return kv["id"]

    def test_03_get_single_kreditversicherung_with_calculated_fields(self):
        """GET /api/kreditversicherungen/{id} - should return single KV with calculated fields"""
        # First create a KV
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Then get it
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data["data"]["id"] == kv_id
        assert data["data"]["versicherungsnummer"] == "TEST-NEW-KV-001"
        assert "auslastung_prozent" in data["data"]
        assert "summe_kundenlimits" in data["data"]
        assert "anzahl_kunden" in data["data"]
        
        print(f"✓ GET /api/kreditversicherungen/{kv_id} returns correct data with calculated fields")

    def test_04_update_hauptvertrag(self):
        """PUT /api/kreditversicherungen/{id} - update Hauptvertrag"""
        # First create a KV
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Update it
        update_payload = {
            "gesamtlimit": 750000,
            "gueltig_bis": "2027-06-30",
            "bemerkungen": "Updated Hauptvertrag"
        }
        
        response = self.session.put(f"{BASE_URL}/api/kreditversicherungen/{kv_id}", json=update_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data["data"]["gesamtlimit"] == 750000
        assert data["data"]["gueltig_bis"] == "2027-06-30"
        assert data["data"]["bemerkungen"] == "Updated Hauptvertrag"
        
        # Verify with GET
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        assert get_response.status_code == 200
        assert get_response.json()["data"]["gesamtlimit"] == 750000
        
        print(f"✓ PUT /api/kreditversicherungen/{kv_id} updated successfully")

    # ============================================================
    # KUNDENPOSITIONEN (UNTERVERTRÄGE) TESTS
    # ============================================================
    
    def _get_test_adresse_id(self):
        """Helper: Get an existing address ID for testing"""
        response = self.session.get(f"{BASE_URL}/api/adressen?limit=1")
        if response.status_code == 200 and response.json().get("data"):
            return response.json()["data"][0]["id"]
        return None

    def _create_test_adresse(self):
        """Helper: Create a test address"""
        payload = {
            "name1": "TEST_KV_Kunde_New GmbH",
            "strasse": "Teststraße 1",
            "plz": "12345",
            "ort": "Teststadt",
            "land": "DE",
            "ist_kunde": True
        }
        
        response = self.session.post(f"{BASE_URL}/api/adressen", json=payload)
        if response.status_code == 200:
            adresse_id = response.json()["data"]["id"]
            self.created_adresse_ids.append(adresse_id)
            return adresse_id
        return None

    def test_05_add_kundenposition(self):
        """POST /api/kreditversicherungen/{id}/positionen - add Kundenposition with kreditlimit"""
        # Create KV
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Get or create test address
        adresse_id = self._get_test_adresse_id()
        if not adresse_id:
            adresse_id = self._create_test_adresse()
        assert adresse_id is not None, "Failed to get/create test address"
        
        # Add Kundenposition
        position_payload = {
            "adresse_id": adresse_id,
            "kreditlimit": 150000,
            "unterversicherungsnummer": "UV-TEST-001",
            "fakturierungsfrist": 60,
            "gueltig_bis": "2026-06-30",
            "aktiv": True,
            "bemerkungen": "Test Kundenposition"
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        position = data["data"]
        assert "id" in position
        assert position["adresse_id"] == adresse_id
        assert position["kreditlimit"] == 150000
        assert position["unterversicherungsnummer"] == "UV-TEST-001"
        assert position["fakturierungsfrist"] == 60
        assert position["gueltig_bis"] == "2026-06-30"
        assert position["aktiv"] == True
        
        print(f"✓ POST /api/kreditversicherungen/{kv_id}/positionen added Kundenposition")
        
        return kv_id, position["id"], adresse_id

    def test_06_verify_auslastung_calculation(self):
        """Verify auslastung = summe_kundenlimits / gesamtlimit * 100"""
        # Create KV with gesamtlimit 500000
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Get test address
        adresse_id = self._get_test_adresse_id()
        if not adresse_id:
            adresse_id = self._create_test_adresse()
        
        # Add Kundenposition with kreditlimit 150000
        position_payload = {
            "adresse_id": adresse_id,
            "kreditlimit": 150000,
            "aktiv": True
        }
        
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Get KV and verify auslastung
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        assert response.status_code == 200
        
        kv = response.json()["data"]
        
        # Expected: 150000 / 500000 * 100 = 30%
        assert kv["summe_kundenlimits"] == 150000
        assert kv["auslastung_prozent"] == 30.0
        assert kv["anzahl_kunden"] == 1
        
        print(f"✓ Auslastung correctly calculated: {kv['auslastung_prozent']}% (150000/500000)")

    def test_07_update_kundenposition(self):
        """PUT /api/kreditversicherungen/{id}/positionen/{pos_id} - update Kundenposition"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        # Update position
        update_payload = {
            "kreditlimit": 200000,
            "fakturierungsfrist": 90,
            "bemerkungen": "Updated Kundenposition"
        }
        
        response = self.session.put(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen/{pos_id}", json=update_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        assert response.json().get("success") == True
        
        # Verify update
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        assert get_response.status_code == 200
        
        kv = get_response.json()["data"]
        position = next((p for p in kv["kunden_positionen"] if p["id"] == pos_id), None)
        assert position is not None
        assert position["kreditlimit"] == 200000
        assert position["fakturierungsfrist"] == 90
        
        # Verify auslastung updated: 200000 / 500000 * 100 = 40%
        assert kv["summe_kundenlimits"] == 200000
        assert kv["auslastung_prozent"] == 40.0
        
        print(f"✓ PUT /api/kreditversicherungen/{kv_id}/positionen/{pos_id} updated successfully")

    def test_08_delete_kundenposition(self):
        """DELETE /api/kreditversicherungen/{id}/positionen/{pos_id} - remove Kundenposition"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        # Delete position
        response = self.session.delete(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen/{pos_id}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        assert response.json().get("success") == True
        
        # Verify deletion
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        assert get_response.status_code == 200
        
        kv = get_response.json()["data"]
        assert kv["anzahl_kunden"] == 0
        assert kv["summe_kundenlimits"] == 0
        assert kv["auslastung_prozent"] == 0.0
        
        print(f"✓ DELETE /api/kreditversicherungen/{kv_id}/positionen/{pos_id} removed successfully")

    def test_09_duplicate_adresse_rejected(self):
        """POST /api/kreditversicherungen/{id}/positionen - should reject duplicate adresse"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        # Try to add same address again
        position_payload = {
            "adresse_id": adresse_id,
            "kreditlimit": 100000,
            "aktiv": True
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        assert response.status_code == 400, f"Expected 400, got {response.status_code}: {response.text}"
        assert "bereits" in response.json().get("detail", "").lower()
        
        print(f"✓ Duplicate adresse correctly rejected")

    # ============================================================
    # ADRESSEN KREDITLIMITS & KREDITSTATUS TESTS
    # ============================================================
    
    def test_10_get_adresse_kreditlimits(self):
        """GET /api/adressen/{id}/kreditlimits - get all credit limits for address"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        response = self.session.get(f"{BASE_URL}/api/adressen/{adresse_id}/kreditlimits")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        limits_data = data["data"]
        assert limits_data["adresse_id"] == adresse_id
        assert "gesamtlimit" in limits_data
        assert "limits" in limits_data
        assert isinstance(limits_data["limits"], list)
        
        # Should have at least one limit from our test KV
        assert len(limits_data["limits"]) >= 1
        
        # Check limit structure
        limit = limits_data["limits"][0]
        assert "betrag" in limit
        assert "kv_id" in limit
        assert "versicherungsnummer" in limit
        assert "unterversicherungsnummer" in limit
        assert "gueltig_bis" in limit
        assert "ist_aktiv" in limit
        assert "fakturierungsfrist" in limit
        
        print(f"✓ GET /api/adressen/{adresse_id}/kreditlimits - Gesamtlimit: {limits_data['gesamtlimit']}")

    def test_11_get_adresse_kreditstatus(self):
        """GET /api/adressen/{id}/kreditstatus - get credit status with verfuegbar"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        response = self.session.get(f"{BASE_URL}/api/adressen/{adresse_id}/kreditstatus")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        status_data = data["data"]
        assert status_data["adresse_id"] == adresse_id
        assert "gesamtlimit" in status_data
        assert "aktuelle_forderungen" in status_data
        assert "verfuegbar" in status_data
        assert "status" in status_data
        assert status_data["status"] in ["ok", "warnung", "ueberschritten", "kein_limit"]
        assert "limits" in status_data
        
        print(f"✓ GET /api/adressen/{adresse_id}/kreditstatus - Status: {status_data['status']}, Verfügbar: {status_data['verfuegbar']}")

    # ============================================================
    # KREDITPRÜFUNG TESTS
    # ============================================================
    
    def test_12_kreditpruefung_within_limit(self):
        """POST /api/kreditpruefung - check credit within limit returns 'ok'"""
        kv_id, pos_id, adresse_id = self.test_05_add_kundenposition()
        
        # Test with amount within limit
        pruefung_payload = {
            "adresse_ids": [adresse_id],
            "neuer_betrag": 50000
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditpruefung", json=pruefung_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        pruefung = data["data"]
        assert pruefung["geprueft"] == True
        assert pruefung["neuer_betrag"] == 50000
        assert pruefung["status"] == "ok"
        assert "gesamtlimit" in pruefung
        assert "verfuegbar" in pruefung
        assert "betroffene_adressen" in pruefung
        
        print(f"✓ POST /api/kreditpruefung (within limit) - Status: {pruefung['status']}")

    def test_13_kreditpruefung_exceeding_limit(self):
        """POST /api/kreditpruefung - check credit exceeding limit returns 'ueberschritten'"""
        # Create a fresh KV and address to avoid interference from other tests
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Create a new test address specifically for this test
        adresse_id = self._create_test_adresse()
        assert adresse_id is not None, "Failed to create test address"
        
        # Add Kundenposition with small limit (50000)
        position_payload = {
            "adresse_id": adresse_id,
            "kreditlimit": 50000,  # Small limit
            "aktiv": True
        }
        
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Test with amount exceeding limit (50000 limit, 100000 request)
        pruefung_payload = {
            "adresse_ids": [adresse_id],
            "neuer_betrag": 100000
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditpruefung", json=pruefung_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        pruefung = data["data"]
        assert pruefung["geprueft"] == True
        assert pruefung["status"] == "ueberschritten"
        assert pruefung["ueberschreitung"] > 0
        assert pruefung["warnung_text"] is not None
        assert "ACHTUNG" in pruefung["warnung_text"]
        
        print(f"✓ POST /api/kreditpruefung (exceeding) - Status: {pruefung['status']}, Überschreitung: {pruefung['ueberschreitung']}")

    # ============================================================
    # SOFT DELETE TEST
    # ============================================================
    
    def test_14_soft_delete_kreditversicherung(self):
        """DELETE /api/kreditversicherungen/{id} - soft delete (sets aktiv=false)"""
        kv_id = self.test_02_create_hauptvertrag_with_gesamtlimit()
        
        # Delete it
        response = self.session.delete(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        assert response.json().get("success") == True
        
        # Verify it's deactivated (soft delete)
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        if get_response.status_code == 200:
            assert get_response.json()["data"]["aktiv"] == False
        
        # Remove from cleanup list since we already deleted
        if kv_id in self.created_kv_ids:
            self.created_kv_ids.remove(kv_id)
        
        print(f"✓ DELETE /api/kreditversicherungen/{kv_id} - soft deleted (aktiv=false)")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
