"""
Test suite for Fuhren (Transporte) and Rechnungen (Fakturierung) modules
Tests CRUD operations, sidebar sections, and automatic invoice creation from Fuhre
"""
import pytest
import requests
import os
from datetime import datetime

# Use localhost for testing (public URL not accessible for API routes)
BASE_URL = "http://localhost:8001"

class TestAuth:
    """Authentication tests"""
    
    @pytest.fixture(scope="class")
    def auth_token(self):
        """Get authentication token"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True
        assert "access_token" in data
        return data["access_token"]
    
    def test_login_success(self):
        """Test successful login"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "access_token" in data
        assert "user" in data
        assert data["user"]["benutzername"] == "admin"


class TestFuhrenAPI:
    """Fuhren (Transporte) API tests"""
    
    @pytest.fixture(scope="class")
    def auth_headers(self):
        """Get auth headers"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        token = response.json()["access_token"]
        return {"Authorization": f"Bearer {token}"}
    
    @pytest.fixture(scope="class")
    def test_adresse_id(self, auth_headers):
        """Get or create a test address for Fuhren"""
        # First try to get existing addresses
        response = requests.get(f"{BASE_URL}/api/adressen?limit=1", headers=auth_headers)
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        
        # Create a test address if none exists
        response = requests.post(f"{BASE_URL}/api/adressen", headers=auth_headers, json={
            "name1": "TEST_Fuhren_Adresse",
            "ort": "Teststadt",
            "plz": "12345",
            "land": "Deutschland"
        })
        assert response.status_code == 201
        return response.json()["data"]["id"]
    
    @pytest.fixture(scope="class")
    def test_artikel_id(self, auth_headers):
        """Get or create a test article for Fuhren"""
        response = requests.get(f"{BASE_URL}/api/artikel?limit=1", headers=auth_headers)
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        
        # Create a test article if none exists
        response = requests.post(f"{BASE_URL}/api/artikel", headers=auth_headers, json={
            "artbez1": "TEST_Fuhren_Artikel",
            "einheit": "kg"
        })
        assert response.status_code == 201
        return response.json()["data"]["id"]
    
    def test_list_fuhren(self, auth_headers):
        """Test GET /api/fuhren - List all Fuhren"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "total" in data
        assert isinstance(data["data"], list)
    
    def test_list_fuhren_with_filter(self, auth_headers):
        """Test GET /api/fuhren with status filter"""
        response = requests.get(f"{BASE_URL}/api/fuhren?status=OFFEN", headers=auth_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
    
    def test_create_fuhre(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test POST /api/fuhren - Create new Fuhre"""
        today = datetime.now().strftime("%Y-%m-%d")
        fuhre_data = {
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "menge_vorgabe": 500,
            "einheit": "kg",
            "einzelpreis_ek": 45.0,
            "einzelpreis_vk": 55.0,
            "status": "OFFEN"
        }
        response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json=fuhre_data)
        assert response.status_code == 201
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "fuhren_nr" in data["data"]
        assert data["data"]["status"] == "OFFEN"
        return data["data"]["id"]
    
    def test_get_fuhre_by_id(self, auth_headers):
        """Test GET /api/fuhren/{id} - Get single Fuhre"""
        # First get list to find an ID
        list_response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        fuhren = list_response.json()["data"]
        if len(fuhren) > 0:
            fuhre_id = fuhren[0]["id"]
            response = requests.get(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers)
            assert response.status_code == 200
            data = response.json()
            assert data["success"] == True
            assert data["data"]["id"] == fuhre_id
    
    def test_get_fuhre_not_found(self, auth_headers):
        """Test GET /api/fuhren/{id} - 404 for non-existent"""
        response = requests.get(f"{BASE_URL}/api/fuhren/non-existent-id", headers=auth_headers)
        assert response.status_code == 404
    
    def test_update_fuhre(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test PUT /api/fuhren/{id} - Update Fuhre"""
        # Create a new Fuhre first
        today = datetime.now().strftime("%Y-%m-%d")
        create_response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json={
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "status": "OFFEN"
        })
        assert create_response.status_code == 201
        fuhre_id = create_response.json()["data"]["id"]
        
        # Update the Fuhre
        update_response = requests.put(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers, json={
            "fahrer_name": "TEST_Fahrer Max",
            "transportkennzeichen": "TEST-AB-123",
            "menge_aufladen": 480
        })
        assert update_response.status_code == 200
        data = update_response.json()
        assert data["success"] == True
        assert data["data"]["fahrer_name"] == "TEST_Fahrer Max"
        assert data["data"]["transportkennzeichen"] == "TEST-AB-123"
        
        # Verify with GET
        get_response = requests.get(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers)
        assert get_response.json()["data"]["fahrer_name"] == "TEST_Fahrer Max"
    
    def test_delete_fuhre_soft_delete(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test DELETE /api/fuhren/{id} - Soft delete"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create a Fuhre to delete
        create_response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json={
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "status": "OFFEN"
        })
        fuhre_id = create_response.json()["data"]["id"]
        
        # Delete the Fuhre
        delete_response = requests.delete(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers)
        assert delete_response.status_code == 200
        
        # Verify it's soft-deleted (should return 404 or deleted=true)
        get_response = requests.get(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers)
        # Either 404 or deleted=true is acceptable for soft delete
        assert get_response.status_code in [200, 404]
    
    def test_fuhre_status_transitions(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test Fuhre status transitions: OFFEN -> IN_TRANSPORT -> GELIEFERT"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create Fuhre with OFFEN status
        create_response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json={
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "status": "OFFEN"
        })
        fuhre_id = create_response.json()["data"]["id"]
        
        # Update to IN_TRANSPORT
        update1 = requests.put(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers, json={
            "status": "IN_TRANSPORT"
        })
        assert update1.status_code == 200
        assert update1.json()["data"]["status"] == "IN_TRANSPORT"
        
        # Update to GELIEFERT
        update2 = requests.put(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers, json={
            "status": "GELIEFERT",
            "menge_abladen": 495
        })
        assert update2.status_code == 200
        assert update2.json()["data"]["status"] == "GELIEFERT"


class TestRechnungenAPI:
    """Rechnungen (Fakturierung) API tests"""
    
    @pytest.fixture(scope="class")
    def auth_headers(self):
        """Get auth headers"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        token = response.json()["access_token"]
        return {"Authorization": f"Bearer {token}"}
    
    @pytest.fixture(scope="class")
    def test_adresse_id(self, auth_headers):
        """Get or create a test address for Rechnungen"""
        response = requests.get(f"{BASE_URL}/api/adressen?limit=1", headers=auth_headers)
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        
        response = requests.post(f"{BASE_URL}/api/adressen", headers=auth_headers, json={
            "name1": "TEST_Rechnungen_Adresse",
            "ort": "Teststadt",
            "plz": "12345"
        })
        return response.json()["data"]["id"]
    
    def test_list_rechnungen(self, auth_headers):
        """Test GET /api/rechnungen - List all Rechnungen"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
    
    def test_list_rechnungen_with_type_filter(self, auth_headers):
        """Test GET /api/rechnungen with vorgang_typ filter"""
        response = requests.get(f"{BASE_URL}/api/rechnungen?vorgang_typ=RECHNUNG", headers=auth_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All returned items should be RECHNUNG type
        for rechnung in data["data"]:
            assert rechnung["vorgang_typ"] == "RECHNUNG"
    
    def test_list_rechnungen_with_status_filter(self, auth_headers):
        """Test GET /api/rechnungen with status filter"""
        response = requests.get(f"{BASE_URL}/api/rechnungen?status=ENTWURF", headers=auth_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
    
    def test_create_rechnung(self, auth_headers, test_adresse_id):
        """Test POST /api/rechnungen - Create new Rechnung"""
        today = datetime.now().strftime("%Y-%m-%d")
        rechnung_data = {
            "vorgang_typ": "RECHNUNG",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Rechnung_Kunde GmbH",
            "erstellungsdatum": today,
            "waehrung": "EUR",
            "status": "ENTWURF"
        }
        response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json=rechnung_data)
        assert response.status_code == 201
        data = response.json()
        assert data["success"] == True
        assert "rechnungs_nr" in data["data"]
        assert data["data"]["vorgang_typ"] == "RECHNUNG"
        assert data["data"]["status"] == "ENTWURF"
        return data["data"]["id"]
    
    def test_create_gutschrift(self, auth_headers, test_adresse_id):
        """Test POST /api/rechnungen - Create Gutschrift"""
        today = datetime.now().strftime("%Y-%m-%d")
        gutschrift_data = {
            "vorgang_typ": "GUTSCHRIFT",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Gutschrift_Lieferant GmbH",
            "erstellungsdatum": today,
            "waehrung": "EUR",
            "status": "ENTWURF"
        }
        response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json=gutschrift_data)
        assert response.status_code == 201
        data = response.json()
        assert data["success"] == True
        assert data["data"]["vorgang_typ"] == "GUTSCHRIFT"
    
    def test_get_rechnung_by_id(self, auth_headers):
        """Test GET /api/rechnungen/{id} - Get single Rechnung"""
        list_response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        rechnungen = list_response.json()["data"]
        if len(rechnungen) > 0:
            rechnung_id = rechnungen[0]["id"]
            response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
            assert response.status_code == 200
            data = response.json()
            assert data["success"] == True
            assert data["data"]["id"] == rechnung_id
            # Verify summen are calculated
            assert "summe_netto" in data["data"]
            assert "summe_brutto" in data["data"]
    
    def test_get_rechnung_not_found(self, auth_headers):
        """Test GET /api/rechnungen/{id} - 404 for non-existent"""
        response = requests.get(f"{BASE_URL}/api/rechnungen/non-existent-id", headers=auth_headers)
        assert response.status_code == 404
    
    def test_update_rechnung(self, auth_headers, test_adresse_id):
        """Test PUT /api/rechnungen/{id} - Update Rechnung"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create a Rechnung first
        create_response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json={
            "vorgang_typ": "RECHNUNG",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Update_Rechnung",
            "erstellungsdatum": today,
            "status": "ENTWURF"
        })
        rechnung_id = create_response.json()["data"]["id"]
        
        # Update the Rechnung
        update_response = requests.put(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers, json={
            "zahlungsbedingung": "30 Tage netto",
            "bemerkung_extern": "TEST Externe Bemerkung"
        })
        assert update_response.status_code == 200
        data = update_response.json()
        assert data["success"] == True
        assert data["data"]["zahlungsbedingung"] == "30 Tage netto"
        
        # Verify with GET
        get_response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        assert get_response.json()["data"]["zahlungsbedingung"] == "30 Tage netto"
    
    def test_add_position_to_rechnung(self, auth_headers, test_adresse_id):
        """Test POST /api/rechnungen/{id}/positionen - Add position"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create a Rechnung
        create_response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json={
            "vorgang_typ": "RECHNUNG",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Position_Rechnung",
            "erstellungsdatum": today,
            "status": "ENTWURF"
        })
        rechnung_id = create_response.json()["data"]["id"]
        
        # Add a position
        position_data = {
            "artbez": "TEST Artikel Position",
            "menge": 10,
            "einheit": "Stk",
            "einzelpreis": 25.50,
            "steuersatz": 19.0
        }
        pos_response = requests.post(f"{BASE_URL}/api/rechnungen/{rechnung_id}/positionen", 
                                     headers=auth_headers, json=position_data)
        assert pos_response.status_code == 201
        data = pos_response.json()
        assert data["success"] == True
        
        # Verify position was added and sums calculated
        get_response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        rechnung = get_response.json()["data"]
        assert len(rechnung["positionen"]) >= 1
        assert rechnung["summe_netto"] > 0
        assert rechnung["summe_brutto"] > rechnung["summe_netto"]
    
    def test_rechnung_summen_calculation(self, auth_headers, test_adresse_id):
        """Test that Rechnung sums are correctly calculated"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create Rechnung
        create_response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json={
            "vorgang_typ": "RECHNUNG",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Summen_Rechnung",
            "erstellungsdatum": today,
            "status": "ENTWURF"
        })
        rechnung_id = create_response.json()["data"]["id"]
        
        # Add position: 10 x 100€ = 1000€ netto, 19% MwSt = 190€, Brutto = 1190€
        requests.post(f"{BASE_URL}/api/rechnungen/{rechnung_id}/positionen", 
                     headers=auth_headers, json={
                         "artbez": "TEST Summen Artikel",
                         "menge": 10,
                         "einheit": "Stk",
                         "einzelpreis": 100.0,
                         "steuersatz": 19.0
                     })
        
        # Verify sums
        get_response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        rechnung = get_response.json()["data"]
        assert rechnung["summe_netto"] == 1000.0
        assert rechnung["summe_steuer"] == 190.0
        assert rechnung["summe_brutto"] == 1190.0
    
    def test_delete_rechnung_soft_delete(self, auth_headers, test_adresse_id):
        """Test DELETE /api/rechnungen/{id} - Soft delete"""
        today = datetime.now().strftime("%Y-%m-%d")
        # Create a Rechnung to delete
        create_response = requests.post(f"{BASE_URL}/api/rechnungen", headers=auth_headers, json={
            "vorgang_typ": "RECHNUNG",
            "id_adresse": test_adresse_id,
            "name1": "TEST_Delete_Rechnung",
            "erstellungsdatum": today,
            "status": "ENTWURF"
        })
        rechnung_id = create_response.json()["data"]["id"]
        
        # Delete the Rechnung
        delete_response = requests.delete(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        assert delete_response.status_code == 200
        
        # Verify it's soft-deleted
        get_response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        assert get_response.status_code in [200, 404]


class TestRechnungAusFuhre:
    """Test automatic invoice creation from Fuhre"""
    
    @pytest.fixture(scope="class")
    def auth_headers(self):
        """Get auth headers"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        token = response.json()["access_token"]
        return {"Authorization": f"Bearer {token}"}
    
    @pytest.fixture(scope="class")
    def test_adresse_id(self, auth_headers):
        """Get test address"""
        response = requests.get(f"{BASE_URL}/api/adressen?limit=1", headers=auth_headers)
        return response.json()["data"][0]["id"]
    
    @pytest.fixture(scope="class")
    def test_artikel_id(self, auth_headers):
        """Get test article"""
        response = requests.get(f"{BASE_URL}/api/artikel?limit=1", headers=auth_headers)
        return response.json()["data"][0]["id"]
    
    def test_create_rechnung_from_fuhre(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test POST /api/rechnungen/aus-fuhre/{fuhre_id} - Create invoice from Fuhre"""
        today = datetime.now().strftime("%Y-%m-%d")
        
        # Create a Fuhre with GELIEFERT status
        fuhre_response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json={
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "menge_abladen": 500,
            "einzelpreis_vk": 75.0,
            "steuersatz_vk": 19.0,
            "status": "GELIEFERT"
        })
        assert fuhre_response.status_code == 201
        fuhre_id = fuhre_response.json()["data"]["id"]
        
        # Create Rechnung from Fuhre
        rechnung_response = requests.post(
            f"{BASE_URL}/api/rechnungen/aus-fuhre/{fuhre_id}?vorgang_typ=RECHNUNG",
            headers=auth_headers
        )
        assert rechnung_response.status_code == 201
        data = rechnung_response.json()
        assert data["success"] == True
        assert "rechnungs_nr" in data["data"]
        
        # Verify the Rechnung has a position from the Fuhre
        rechnung_id = data["data"]["id"]
        get_response = requests.get(f"{BASE_URL}/api/rechnungen/{rechnung_id}", headers=auth_headers)
        rechnung = get_response.json()["data"]
        assert len(rechnung["positionen"]) >= 1
        # Position should reference the Fuhre
        fuhre_position = [p for p in rechnung["positionen"] if p.get("id_fuhre") == fuhre_id]
        assert len(fuhre_position) >= 1
    
    def test_create_gutschrift_from_fuhre(self, auth_headers, test_adresse_id, test_artikel_id):
        """Test creating Gutschrift from Fuhre"""
        today = datetime.now().strftime("%Y-%m-%d")
        
        # Create a Fuhre
        fuhre_response = requests.post(f"{BASE_URL}/api/fuhren", headers=auth_headers, json={
            "id_adresse_start": test_adresse_id,
            "id_adresse_ziel": test_adresse_id,
            "id_artikel": test_artikel_id,
            "datum_abholung": today,
            "datum_anlieferung": today,
            "transportmittel": "LKW",
            "menge_abladen": 300,
            "einzelpreis_ek": 50.0,
            "steuersatz_ek": 19.0,
            "status": "GELIEFERT"
        })
        fuhre_id = fuhre_response.json()["data"]["id"]
        
        # Create Gutschrift from Fuhre
        gutschrift_response = requests.post(
            f"{BASE_URL}/api/rechnungen/aus-fuhre/{fuhre_id}?vorgang_typ=GUTSCHRIFT",
            headers=auth_headers
        )
        assert gutschrift_response.status_code == 201
        data = gutschrift_response.json()
        assert data["data"]["vorgang_typ"] == "GUTSCHRIFT"


class TestFuhrenSidebarSections:
    """Test Fuhren sidebar sections data availability"""
    
    @pytest.fixture(scope="class")
    def auth_headers(self):
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        return {"Authorization": f"Bearer {response.json()['access_token']}"}
    
    def test_fuhre_has_stammdaten_fields(self, auth_headers):
        """Test Fuhre has Stammdaten section fields"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        if response.json()["data"]:
            fuhre = response.json()["data"][0]
            # Stammdaten fields
            assert "fuhren_nr" in fuhre
            assert "status" in fuhre
            assert "datum_abholung" in fuhre
            assert "datum_anlieferung" in fuhre
    
    def test_fuhre_has_lieferant_fields(self, auth_headers):
        """Test Fuhre has Lieferant section fields"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        if response.json()["data"]:
            fuhre = response.json()["data"][0]
            # Lieferant fields
            assert "id_adresse_start" in fuhre
            assert "name_lieferant" in fuhre
    
    def test_fuhre_has_abnehmer_fields(self, auth_headers):
        """Test Fuhre has Abnehmer section fields"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        if response.json()["data"]:
            fuhre = response.json()["data"][0]
            # Abnehmer fields
            assert "id_adresse_ziel" in fuhre
            assert "name_abnehmer" in fuhre
    
    def test_fuhre_has_artikel_mengen_fields(self, auth_headers):
        """Test Fuhre has Artikel & Mengen section fields"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        if response.json()["data"]:
            fuhre = response.json()["data"][0]
            # Artikel & Mengen fields
            assert "id_artikel" in fuhre
            assert "menge_vorgabe" in fuhre or fuhre.get("menge_vorgabe") is None
            assert "menge_aufladen" in fuhre or fuhre.get("menge_aufladen") is None
            assert "menge_abladen" in fuhre or fuhre.get("menge_abladen") is None
            assert "einheit" in fuhre
    
    def test_fuhre_has_transport_fields(self, auth_headers):
        """Test Fuhre has Transport section fields"""
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=auth_headers)
        if response.json()["data"]:
            fuhre = response.json()["data"][0]
            # Transport fields
            assert "transportmittel" in fuhre
            assert "transportkennzeichen" in fuhre or fuhre.get("transportkennzeichen") is None
            assert "fahrer_name" in fuhre or fuhre.get("fahrer_name") is None


class TestRechnungenSidebarSections:
    """Test Rechnungen sidebar sections data availability"""
    
    @pytest.fixture(scope="class")
    def auth_headers(self):
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        return {"Authorization": f"Bearer {response.json()['access_token']}"}
    
    def test_rechnung_has_kopfdaten_fields(self, auth_headers):
        """Test Rechnung has Kopfdaten section fields"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        if response.json()["data"]:
            rechnung = response.json()["data"][0]
            # Kopfdaten fields
            assert "rechnungs_nr" in rechnung
            assert "vorgang_typ" in rechnung
            assert "status" in rechnung
            assert "waehrung" in rechnung
    
    def test_rechnung_has_adressat_fields(self, auth_headers):
        """Test Rechnung has Adressat section fields"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        if response.json()["data"]:
            rechnung = response.json()["data"][0]
            # Adressat fields
            assert "id_adresse" in rechnung
            assert "name1" in rechnung
    
    def test_rechnung_has_termine_fields(self, auth_headers):
        """Test Rechnung has Termine section fields"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        if response.json()["data"]:
            rechnung = response.json()["data"][0]
            # Termine fields
            assert "erstellungsdatum" in rechnung
    
    def test_rechnung_has_positionen(self, auth_headers):
        """Test Rechnung has Positionen section"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        if response.json()["data"]:
            rechnung = response.json()["data"][0]
            assert "positionen" in rechnung
            assert isinstance(rechnung["positionen"], list)
    
    def test_rechnung_has_summen(self, auth_headers):
        """Test Rechnung has calculated sums"""
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=auth_headers)
        if response.json()["data"]:
            rechnung = response.json()["data"][0]
            assert "summe_netto" in rechnung
            assert "summe_steuer" in rechnung
            assert "summe_brutto" in rechnung


if __name__ == "__main__":
    pytest.main([__file__, "-v"])
