"""
Kreditversicherung API Tests
Tests for:
- CRUD operations for Kreditversicherungen
- Positionen (Limits) management
- Adressen (Customers) linking
- Kreditlimits and Kreditstatus queries
- Kreditpruefung (credit check)
"""

import pytest
import requests
import os
from datetime import datetime, timedelta

# Get BASE_URL from environment - use the public URL
BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', 'http://localhost:8001')
if BASE_URL.endswith('/'):
    BASE_URL = BASE_URL.rstrip('/')

# Test credentials
TEST_USERNAME = "admin"
TEST_PASSWORD = "Admin123!"


class TestKreditversicherungAPI:
    """Test suite for Kreditversicherung endpoints"""
    
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
        
        token = login_response.json().get("token")
        if not token:
            pytest.skip("No token received from login")
        
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
    # BASIC CRUD TESTS
    # ============================================================
    
    def test_01_get_kreditversicherungen_empty_or_list(self):
        """GET /api/kreditversicherungen - should return list (possibly empty)"""
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ GET /api/kreditversicherungen returns {len(data['data'])} items")

    def test_02_create_kreditversicherung(self):
        """POST /api/kreditversicherungen - create new KV"""
        payload = {
            "versicherungsnummer": "TEST-KV-001",
            "versicherer_name": "Test Versicherung AG",
            "fakturierungsfrist": 30,
            "aktiv": True,
            "bemerkungen": "Test Kreditversicherung"
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen", json=payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        kv = data["data"]
        assert "id" in kv
        assert kv["versicherungsnummer"] == "TEST-KV-001"
        assert kv["versicherer_name"] == "Test Versicherung AG"
        assert kv["fakturierungsfrist"] == 30
        assert kv["aktiv"] == True
        
        self.created_kv_ids.append(kv["id"])
        print(f"✓ POST /api/kreditversicherungen created KV with ID: {kv['id']}")
        
        return kv["id"]

    def test_03_get_single_kreditversicherung(self):
        """GET /api/kreditversicherungen/{id} - get single KV"""
        # First create a KV
        kv_id = self.test_02_create_kreditversicherung()
        
        # Then get it
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data["data"]["id"] == kv_id
        assert data["data"]["versicherungsnummer"] == "TEST-KV-001"
        print(f"✓ GET /api/kreditversicherungen/{kv_id} returns correct data")

    def test_04_update_kreditversicherung(self):
        """PUT /api/kreditversicherungen/{id} - update KV"""
        # First create a KV
        kv_id = self.test_02_create_kreditversicherung()
        
        # Update it
        update_payload = {
            "versicherungsnummer": "TEST-KV-001-UPDATED",
            "fakturierungsfrist": 45
        }
        
        response = self.session.put(f"{BASE_URL}/api/kreditversicherungen/{kv_id}", json=update_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data["data"]["versicherungsnummer"] == "TEST-KV-001-UPDATED"
        assert data["data"]["fakturierungsfrist"] == 45
        
        # Verify with GET
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        assert get_response.status_code == 200
        assert get_response.json()["data"]["versicherungsnummer"] == "TEST-KV-001-UPDATED"
        
        print(f"✓ PUT /api/kreditversicherungen/{kv_id} updated successfully")

    def test_05_get_nonexistent_kreditversicherung(self):
        """GET /api/kreditversicherungen/{id} - should return 404 for non-existent"""
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/NONEXISTENT-ID")
        
        assert response.status_code == 404, f"Expected 404, got {response.status_code}"
        print("✓ GET non-existent KV returns 404")

    # ============================================================
    # POSITIONEN (LIMITS) TESTS
    # ============================================================
    
    def test_06_add_position_to_kreditversicherung(self):
        """POST /api/kreditversicherungen/{id}/positionen - add limit position"""
        # First create a KV
        kv_id = self.test_02_create_kreditversicherung()
        
        # Add a position
        today = datetime.now().strftime("%Y-%m-%d")
        next_year = (datetime.now() + timedelta(days=365)).strftime("%Y-%m-%d")
        
        position_payload = {
            "gueltig_ab": today,
            "gueltig_bis": next_year,
            "betrag": 50000.00,
            "betrag_anfrage": 75000.00,
            "aktiv": True,
            "bemerkungen": "Test Limit"
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert data["data"]["betrag"] == 50000.00
        assert "id" in data["data"]
        
        print(f"✓ POST /api/kreditversicherungen/{kv_id}/positionen added position")
        
        return kv_id, data["data"]["id"]

    def test_07_get_positionen(self):
        """GET /api/kreditversicherungen/{id}/positionen - get positions"""
        kv_id, pos_id = self.test_06_add_position_to_kreditversicherung()
        
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert len(data["data"]) >= 1
        
        print(f"✓ GET /api/kreditversicherungen/{kv_id}/positionen returns {len(data['data'])} positions")

    # ============================================================
    # ADRESSEN (CUSTOMERS) LINKING TESTS
    # ============================================================
    
    def _create_test_adresse(self):
        """Helper: Create a test address"""
        payload = {
            "name1": "TEST_KV_Kunde GmbH",
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

    def test_08_add_adresse_to_kreditversicherung(self):
        """POST /api/kreditversicherungen/{id}/adressen - link customer"""
        # Create KV
        kv_id = self.test_02_create_kreditversicherung()
        
        # Create test address
        adresse_id = self._create_test_adresse()
        assert adresse_id is not None, "Failed to create test address"
        
        # Link address to KV
        link_payload = {
            "adresse_id": adresse_id
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen", json=link_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data["data"]["adresse_id"] == adresse_id
        
        print(f"✓ POST /api/kreditversicherungen/{kv_id}/adressen linked address {adresse_id}")
        
        return kv_id, adresse_id

    def test_09_get_kv_adressen(self):
        """GET /api/kreditversicherungen/{id}/adressen - get linked addresses"""
        kv_id, adresse_id = self.test_08_add_adresse_to_kreditversicherung()
        
        response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert len(data["data"]) >= 1
        
        # Check that our address is in the list
        adresse_ids = [a["adresse_id"] for a in data["data"]]
        assert adresse_id in adresse_ids
        
        print(f"✓ GET /api/kreditversicherungen/{kv_id}/adressen returns {len(data['data'])} addresses")

    # ============================================================
    # KREDITLIMITS & KREDITSTATUS TESTS
    # ============================================================
    
    def test_10_get_adresse_kreditlimits(self):
        """GET /api/adressen/{id}/kreditlimits - get credit limits for address"""
        # Create KV with position and linked address
        kv_id = self.test_02_create_kreditversicherung()
        
        # Add position
        today = datetime.now().strftime("%Y-%m-%d")
        position_payload = {
            "gueltig_ab": today,
            "betrag": 100000.00,
            "aktiv": True
        }
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Create and link address
        adresse_id = self._create_test_adresse()
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen", json={"adresse_id": adresse_id})
        
        # Get kreditlimits for address
        response = self.session.get(f"{BASE_URL}/api/adressen/{adresse_id}/kreditlimits")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert data["data"]["adresse_id"] == adresse_id
        assert "gesamtlimit" in data["data"]
        assert "limits" in data["data"]
        
        print(f"✓ GET /api/adressen/{adresse_id}/kreditlimits - Gesamtlimit: {data['data']['gesamtlimit']}")

    def test_11_get_adresse_kreditstatus(self):
        """GET /api/adressen/{id}/kreditstatus - get credit status for address"""
        # Create KV with position and linked address
        kv_id = self.test_02_create_kreditversicherung()
        
        # Add position
        today = datetime.now().strftime("%Y-%m-%d")
        position_payload = {
            "gueltig_ab": today,
            "betrag": 100000.00,
            "aktiv": True
        }
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Create and link address
        adresse_id = self._create_test_adresse()
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen", json={"adresse_id": adresse_id})
        
        # Get kreditstatus for address
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
        
        print(f"✓ GET /api/adressen/{adresse_id}/kreditstatus - Status: {status_data['status']}, Verfügbar: {status_data['verfuegbar']}")

    # ============================================================
    # KREDITPRUEFUNG TESTS
    # ============================================================
    
    def test_12_kreditpruefung(self):
        """POST /api/kreditpruefung - check credit for new amount"""
        # Create KV with position and linked address
        kv_id = self.test_02_create_kreditversicherung()
        
        # Add position with 100k limit
        today = datetime.now().strftime("%Y-%m-%d")
        position_payload = {
            "gueltig_ab": today,
            "betrag": 100000.00,
            "aktiv": True
        }
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Create and link address
        adresse_id = self._create_test_adresse()
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen", json={"adresse_id": adresse_id})
        
        # Test credit check with amount within limit
        pruefung_payload = {
            "adresse_ids": [adresse_id],
            "neuer_betrag": 50000.00
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditpruefung", json=pruefung_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        pruefung = data["data"]
        assert pruefung["geprueft"] == True
        assert pruefung["neuer_betrag"] == 50000.00
        assert "status" in pruefung
        assert "gesamtlimit" in pruefung
        assert "verfuegbar" in pruefung
        
        print(f"✓ POST /api/kreditpruefung - Status: {pruefung['status']}, Limit: {pruefung['gesamtlimit']}")

    def test_13_kreditpruefung_ueberschreitung(self):
        """POST /api/kreditpruefung - check credit exceeding limit"""
        # Create KV with position and linked address
        kv_id = self.test_02_create_kreditversicherung()
        
        # Add position with small limit
        today = datetime.now().strftime("%Y-%m-%d")
        position_payload = {
            "gueltig_ab": today,
            "betrag": 10000.00,  # Small limit
            "aktiv": True
        }
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/positionen", json=position_payload)
        
        # Create and link address
        adresse_id = self._create_test_adresse()
        self.session.post(f"{BASE_URL}/api/kreditversicherungen/{kv_id}/adressen", json={"adresse_id": adresse_id})
        
        # Test credit check with amount exceeding limit
        pruefung_payload = {
            "adresse_ids": [adresse_id],
            "neuer_betrag": 50000.00  # Exceeds 10k limit
        }
        
        response = self.session.post(f"{BASE_URL}/api/kreditpruefung", json=pruefung_payload)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        pruefung = data["data"]
        assert pruefung["status"] == "ueberschritten"
        assert pruefung["ueberschreitung"] > 0
        assert pruefung["warnung_text"] is not None
        
        print(f"✓ POST /api/kreditpruefung (exceeding) - Status: {pruefung['status']}, Überschreitung: {pruefung['ueberschreitung']}")

    # ============================================================
    # DELETE TESTS
    # ============================================================
    
    def test_14_delete_kreditversicherung(self):
        """DELETE /api/kreditversicherungen/{id} - soft delete KV"""
        # Create a KV
        kv_id = self.test_02_create_kreditversicherung()
        
        # Delete it
        response = self.session.delete(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        # Verify it's deactivated (soft delete)
        get_response = self.session.get(f"{BASE_URL}/api/kreditversicherungen/{kv_id}")
        if get_response.status_code == 200:
            assert get_response.json()["data"]["aktiv"] == False
        
        # Remove from cleanup list since we already deleted
        if kv_id in self.created_kv_ids:
            self.created_kv_ids.remove(kv_id)
        
        print(f"✓ DELETE /api/kreditversicherungen/{kv_id} - soft deleted")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
