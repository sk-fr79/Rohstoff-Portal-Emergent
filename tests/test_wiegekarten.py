"""
Wiegekarten (Weighing Cards) API Tests
Tests for Fahrzeugwaage / Systec IT 4000 integration

Endpoints tested:
- GET /api/wiegekarten - List all weighing cards with filters
- GET /api/wiegekarten/{id} - Get single weighing card
- POST /api/wiegekarten - Create new weighing card
- PUT /api/wiegekarten/{id} - Update weighing card
- POST /api/wiegekarten/{id}/waegung/{nr} - Save weighing 1 or 2
- POST /api/wiegekarten/{id}/storno - Cancel weighing card
- DELETE /api/wiegekarten/{id} - Delete weighing card (only without weighings)
- POST /api/waage/lesen - Read weight from scale (demo mode)
- GET /api/waage/status - Get scale status
"""

import pytest
import requests
import os
import random
import string

# Use localhost for testing
BASE_URL = "http://localhost:8001"

# Test credentials
TEST_USERNAME = "admin"
TEST_PASSWORD = "Admin123!"


class TestWiegekartenAPI:
    """Wiegekarten (Weighing Cards) API Tests"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup: Login and get auth token"""
        self.session = requests.Session()
        self.session.headers.update({"Content-Type": "application/json"})
        
        # Login
        response = self.session.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": TEST_USERNAME,
            "passwort": TEST_PASSWORD
        })
        assert response.status_code == 200, f"Login failed: {response.text}"
        data = response.json()
        assert data.get("success"), f"Login not successful: {data}"
        
        self.token = data.get("access_token")
        self.session.headers.update({"Authorization": f"Bearer {self.token}"})
        
        yield
        
        # Cleanup: Delete test wiegekarten
        self._cleanup_test_data()
    
    def _cleanup_test_data(self):
        """Delete test-created wiegekarten"""
        try:
            response = self.session.get(f"{BASE_URL}/api/wiegekarten")
            if response.status_code == 200:
                data = response.json()
                for wk in data.get("data", []):
                    # Delete only test wiegekarten (with TEST_ prefix in kennzeichen)
                    if wk.get("kennzeichen", "").startswith("TEST-"):
                        # Try to delete, ignore errors
                        self.session.delete(f"{BASE_URL}/api/wiegekarten/{wk['id']}")
        except:
            pass
    
    def _generate_test_kennzeichen(self):
        """Generate unique test license plate"""
        suffix = ''.join(random.choices(string.digits, k=4))
        return f"TEST-AB {suffix}"
    
    # ==================== WAAGE (SCALE) TESTS ====================
    
    def test_waage_status(self):
        """Test GET /api/waage/status - Get scale status"""
        response = self.session.get(f"{BASE_URL}/api/waage/status")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        assert "data" in data
        
        waage_data = data["data"]
        assert "verbunden" in waage_data
        assert "waage_nr" in waage_data
        assert "demo_modus" in waage_data
        print(f"✓ Waage status: verbunden={waage_data['verbunden']}, demo_modus={waage_data['demo_modus']}")
    
    def test_waage_lesen_demo(self):
        """Test POST /api/waage/lesen - Read weight from scale (demo mode)"""
        response = self.session.post(f"{BASE_URL}/api/waage/lesen")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        assert "data" in data
        
        waage_satz = data["data"]
        assert "brutto_gewicht" in waage_satz
        assert "waage_status" in waage_satz
        
        # Demo mode returns random weight between 5000-35000 kg
        gewicht = waage_satz["brutto_gewicht"]
        assert 5000 <= gewicht <= 35000, f"Weight {gewicht} not in expected range"
        print(f"✓ Waage lesen: {gewicht} kg (demo mode)")
    
    # ==================== WIEGEKARTEN CRUD TESTS ====================
    
    def test_get_wiegekarten_list(self):
        """Test GET /api/wiegekarten - List all weighing cards"""
        response = self.session.get(f"{BASE_URL}/api/wiegekarten")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        assert "data" in data
        assert "pagination" in data
        
        pagination = data["pagination"]
        assert "total" in pagination
        assert "page" in pagination
        print(f"✓ Wiegekarten list: {pagination['total']} total entries")
    
    def test_get_wiegekarten_nur_offene(self):
        """Test GET /api/wiegekarten?nur_offene=true - Filter only open cards"""
        response = self.session.get(f"{BASE_URL}/api/wiegekarten", params={"nur_offene": True})
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        print(f"✓ Wiegekarten nur_offene filter works")
    
    def test_create_wiegekarte_wareneingang(self):
        """Test POST /api/wiegekarten - Create new weighing card (Wareneingang)"""
        kennzeichen = self._generate_test_kennzeichen()
        
        payload = {
            "typ_wiegekarte": "W",
            "ist_lieferant": True,  # Wareneingang
            "kennzeichen": kennzeichen,
            "trailer": "TEST-TR 001",
            "adresse_lieferant": "Test Lieferant GmbH\nTeststraße 1\n12345 Teststadt",
            "artikel_bezeichnung": "Altpapier gemischt"
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten", json=payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        assert "data" in data
        
        wiegekarte = data["data"]
        assert wiegekarte.get("wiegekarten_nr"), "Wiegekarten-Nr should be generated"
        assert wiegekarte["wiegekarten_nr"].startswith("WK-"), "Nr should start with WK-"
        assert wiegekarte["kennzeichen"] == kennzeichen
        assert wiegekarte["ist_lieferant"] is True
        assert wiegekarte["zustand"] == "NEU"
        
        self.created_wiegekarte_id = wiegekarte["id"]
        print(f"✓ Created Wiegekarte: {wiegekarte['wiegekarten_nr']} (Wareneingang)")
        
        return wiegekarte
    
    def test_create_wiegekarte_warenausgang(self):
        """Test POST /api/wiegekarten - Create new weighing card (Warenausgang)"""
        kennzeichen = self._generate_test_kennzeichen()
        
        payload = {
            "typ_wiegekarte": "W",
            "ist_lieferant": False,  # Warenausgang
            "kennzeichen": kennzeichen,
            "adresse_lieferant": "Test Abnehmer AG\nAusgangsweg 5\n54321 Ausgangsort",
            "artikel_bezeichnung": "Schrott sortiert"
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten", json=payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        
        wiegekarte = data["data"]
        assert wiegekarte["ist_lieferant"] is False
        print(f"✓ Created Wiegekarte: {wiegekarte['wiegekarten_nr']} (Warenausgang)")
        
        return wiegekarte
    
    def test_create_wiegekarte_validation_error(self):
        """Test POST /api/wiegekarten - Validation error (missing kennzeichen)"""
        payload = {
            "typ_wiegekarte": "W",
            "ist_lieferant": True,
            # Missing kennzeichen - should fail validation
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten", json=payload)
        assert response.status_code == 422, f"Expected 422, got {response.status_code}"
        print("✓ Validation error correctly returned for missing kennzeichen")
    
    def test_get_wiegekarte_by_id(self):
        """Test GET /api/wiegekarten/{id} - Get single weighing card"""
        # First create a wiegekarte
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        response = self.session.get(f"{BASE_URL}/api/wiegekarten/{wk_id}")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        assert data["data"]["id"] == wk_id
        print(f"✓ Get Wiegekarte by ID: {wk_id}")
    
    def test_get_wiegekarte_not_found(self):
        """Test GET /api/wiegekarten/{id} - Not found"""
        response = self.session.get(f"{BASE_URL}/api/wiegekarten/non-existent-id")
        assert response.status_code == 404, f"Expected 404, got {response.status_code}"
        print("✓ 404 correctly returned for non-existent wiegekarte")
    
    def test_update_wiegekarte(self):
        """Test PUT /api/wiegekarten/{id} - Update weighing card"""
        # First create a wiegekarte
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        update_payload = {
            "trailer": "UPDATED-TR 999",
            "bemerkung1": "Test Bemerkung aktualisiert",
            "strahlung_geprueft": True
        }
        
        response = self.session.put(f"{BASE_URL}/api/wiegekarten/{wk_id}", json=update_payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        
        updated = data["data"]
        assert updated["trailer"] == "UPDATED-TR 999"
        assert updated["bemerkung1"] == "Test Bemerkung aktualisiert"
        assert updated["strahlung_geprueft"] is True
        print(f"✓ Updated Wiegekarte: {wk_id}")
    
    # ==================== WÄGUNG (WEIGHING) TESTS ====================
    
    def test_waegung_1_speichern(self):
        """Test POST /api/wiegekarten/{id}/waegung/1 - Save first weighing"""
        # Create wiegekarte
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        # Read weight from scale (demo)
        waage_response = self.session.post(f"{BASE_URL}/api/waage/lesen")
        gewicht = waage_response.json()["data"]["brutto_gewicht"]
        
        # Save weighing 1
        payload = {
            "gewicht": gewicht,
            "manuell": False
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/1", json=payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        
        updated = data["data"]
        assert updated["waegung1"] is not None
        assert updated["waegung1"]["gewicht"] == gewicht
        assert updated["zustand"] == "WAEGUNG1"
        
        # For Wareneingang: Wägung1 = Brutto
        assert updated["bruttogewicht"] == gewicht
        print(f"✓ Wägung 1 gespeichert: {gewicht} kg (Brutto)")
        
        return updated
    
    def test_waegung_2_speichern_und_netto_berechnung(self):
        """Test POST /api/wiegekarten/{id}/waegung/2 - Save second weighing and verify netto calculation"""
        # Create wiegekarte and save first weighing
        wiegekarte = self.test_waegung_1_speichern()
        wk_id = wiegekarte["id"]
        brutto = wiegekarte["bruttogewicht"]
        
        # Read new weight (tara - empty truck)
        waage_response = self.session.post(f"{BASE_URL}/api/waage/lesen")
        tara = waage_response.json()["data"]["brutto_gewicht"]
        
        # Save weighing 2
        payload = {
            "gewicht": tara,
            "manuell": False
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/2", json=payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        
        updated = data["data"]
        assert updated["waegung2"] is not None
        assert updated["waegung2"]["gewicht"] == tara
        assert updated["zustand"] == "WAEGUNG2"
        
        # For Wareneingang: Wägung2 = Tara, Netto = Brutto - Tara
        assert updated["taragewicht"] == tara
        expected_netto = abs(brutto - tara)
        assert updated["nettogewicht"] == expected_netto, f"Netto should be {expected_netto}, got {updated['nettogewicht']}"
        
        print(f"✓ Wägung 2 gespeichert: Brutto={brutto}, Tara={tara}, Netto={expected_netto} kg")
    
    def test_waegung_manuell(self):
        """Test manual weighing entry"""
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        # Manual weighing
        payload = {
            "gewicht": 25000,
            "manuell": True
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/1", json=payload)
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        updated = data["data"]
        assert updated["waegung1"]["manuell"] is True
        print("✓ Manuelle Wägung gespeichert")
    
    # ==================== STORNO & DELETE TESTS ====================
    
    def test_storno_wiegekarte(self):
        """Test POST /api/wiegekarten/{id}/storno - Cancel weighing card"""
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/storno")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        data = response.json()
        assert data.get("success") is True
        
        # Verify storno
        get_response = self.session.get(f"{BASE_URL}/api/wiegekarten/{wk_id}")
        storniert = get_response.json()["data"]
        assert storniert["storno"] is True
        assert storniert["zustand"] == "STORNO"
        print(f"✓ Wiegekarte storniert: {wk_id}")
    
    def test_storno_prevents_edit(self):
        """Test that cancelled cards cannot be edited"""
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        # Storno
        self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/storno")
        
        # Try to update - should fail
        response = self.session.put(f"{BASE_URL}/api/wiegekarten/{wk_id}", json={"bemerkung1": "Test"})
        assert response.status_code == 400, f"Expected 400, got {response.status_code}"
        print("✓ Stornierte Wiegekarte kann nicht bearbeitet werden")
    
    def test_storno_prevents_waegung(self):
        """Test that cancelled cards cannot be weighed"""
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        # Storno
        self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/storno")
        
        # Try to add weighing - should fail
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/1", json={"gewicht": 10000, "manuell": True})
        assert response.status_code == 400, f"Expected 400, got {response.status_code}"
        print("✓ Stornierte Wiegekarte kann nicht gewogen werden")
    
    def test_delete_wiegekarte_ohne_waegung(self):
        """Test DELETE /api/wiegekarten/{id} - Delete card without weighings"""
        wiegekarte = self.test_create_wiegekarte_wareneingang()
        wk_id = wiegekarte["id"]
        
        response = self.session.delete(f"{BASE_URL}/api/wiegekarten/{wk_id}")
        assert response.status_code == 200, f"Failed: {response.text}"
        
        # Verify deletion
        get_response = self.session.get(f"{BASE_URL}/api/wiegekarten/{wk_id}")
        assert get_response.status_code == 404
        print(f"✓ Wiegekarte ohne Wägung gelöscht: {wk_id}")
    
    def test_delete_wiegekarte_mit_waegung_fails(self):
        """Test DELETE /api/wiegekarten/{id} - Cannot delete card with weighings"""
        # Create and add weighing
        wiegekarte = self.test_waegung_1_speichern()
        wk_id = wiegekarte["id"]
        
        # Try to delete - should fail
        response = self.session.delete(f"{BASE_URL}/api/wiegekarten/{wk_id}")
        assert response.status_code == 400, f"Expected 400, got {response.status_code}"
        
        error_detail = response.json().get("detail", "")
        assert "stornieren" in error_detail.lower() or "wägung" in error_detail.lower()
        print("✓ Wiegekarte mit Wägung kann nicht gelöscht werden (nur stornieren)")
    
    # ==================== WARENAUSGANG GEWICHTSBERECHNUNG ====================
    
    def test_warenausgang_gewichtsberechnung(self):
        """Test weight calculation for Warenausgang (outgoing goods)
        
        Bei Warenausgang:
        - Wägung 1 = Tara (leeres Fahrzeug)
        - Wägung 2 = Brutto (beladenes Fahrzeug)
        - Netto = Brutto - Tara
        """
        # Create Warenausgang wiegekarte
        kennzeichen = self._generate_test_kennzeichen()
        payload = {
            "typ_wiegekarte": "W",
            "ist_lieferant": False,  # Warenausgang
            "kennzeichen": kennzeichen
        }
        
        response = self.session.post(f"{BASE_URL}/api/wiegekarten", json=payload)
        wiegekarte = response.json()["data"]
        wk_id = wiegekarte["id"]
        
        # Wägung 1 = Tara (empty truck)
        tara = 15000
        self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/1", json={"gewicht": tara, "manuell": True})
        
        # Wägung 2 = Brutto (loaded truck)
        brutto = 35000
        response = self.session.post(f"{BASE_URL}/api/wiegekarten/{wk_id}/waegung/2", json={"gewicht": brutto, "manuell": True})
        
        updated = response.json()["data"]
        
        # For Warenausgang: Wägung1=Tara, Wägung2=Brutto
        assert updated["taragewicht"] == tara, f"Tara should be {tara}"
        assert updated["bruttogewicht"] == brutto, f"Brutto should be {brutto}"
        assert updated["nettogewicht"] == brutto - tara, f"Netto should be {brutto - tara}"
        
        print(f"✓ Warenausgang Berechnung: Tara={tara}, Brutto={brutto}, Netto={brutto - tara} kg")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
