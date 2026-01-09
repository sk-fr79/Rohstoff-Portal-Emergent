"""
Rohstoff Portal - Artikel & Kontrakte Module Tests
Tests for: Artikel CRUD, Kontrakte CRUD with new sidebar layout
Testing the modernized pages with sidebar-menu detail view
"""

import pytest
import requests
import os
import uuid
from datetime import datetime, timedelta

# Use the public preview URL for testing
BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', 'https://resource-portal.preview.emergentagent.com')

# Test credentials
TEST_USERNAME = "admin"
TEST_PASSWORD = "Admin123!"


@pytest.fixture(scope="module")
def auth_token():
    """Get authentication token for tests"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json={
        "benutzername": TEST_USERNAME,
        "passwort": TEST_PASSWORD
    })
    if response.status_code == 200 and response.json().get("success"):
        return response.json()["access_token"]
    pytest.skip("Authentication failed - skipping authenticated tests")


@pytest.fixture(scope="module")
def auth_headers(auth_token):
    """Get headers with auth token"""
    return {"Authorization": f"Bearer {auth_token}"}


# ============================================================
# ARTIKEL MODULE TESTS
# ============================================================

class TestArtikelCRUD:
    """Artikel (Articles) CRUD tests - testing new sidebar layout endpoints"""
    
    def test_get_artikel_list(self, auth_headers):
        """Test getting articles list"""
        response = requests.get(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "pagination" in data
        print(f"✓ Got {len(data['data'])} articles, total: {data['pagination']['total']}")
    
    def test_create_artikel_basic(self, auth_headers):
        """Test creating a basic article"""
        test_name = f"TEST_Artikel_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "artbez2": "Test Beschreibung für Artikel",
                "einheit": "kg",
                "einheit_preis": "t",
                "mengendivisor": 1000,
                "aktiv": True,
                "gefahrgut": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert data["data"]["artbez1"] == test_name
        assert data["data"]["einheit"] == "kg"
        assert data["data"]["aktiv"] == True
        print(f"✓ Created article: {data['data']['artbez1']}")
        return data["data"]["id"]
    
    def test_create_artikel_with_all_fields(self, auth_headers):
        """Test creating article with all fields (Stammdaten, Einheiten, Klassifizierung, AVV-Codes, Zoll)"""
        test_name = f"TEST_ArtikelFull_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                # Stammdaten
                "anr1": "A001",
                "artbez1": test_name,
                "artbez2": "Erweiterte Beschreibung mit Details",
                
                # Einheiten
                "einheit": "kg",
                "einheit_preis": "t",
                "mengendivisor": 1000,
                "genauigkeit_mengen": 3,
                
                # Klassifizierung
                "artikelgruppe": "Metalle",
                "artikelgruppe_fibu": "Rohstoffe",
                
                # Status-Flags
                "aktiv": True,
                "gefahrgut": False,
                "ist_leergut": False,
                "elektro_elektronik": False,
                "ist_produkt": False,
                "dienstleistung": False,
                "end_of_waste": False,
                "end_of_waste_lager": False,
                
                # AVV-Codes
                "avv_code_eingang": "17 04 01",
                "avv_code_ausgang": "17 04 02",
                "eakcode": "17 04 01",
                
                # Zoll
                "zolltarifnr": "7204 10 00",
                "zolltarifnotiz": "Abfälle und Schrott aus Eisen oder Stahl",
                
                # Basel-Code
                "basel_code": "B1010",
                "basel_notiz": "Metallabfälle",
                
                # OECD
                "oecd_code": "GC010",
                "oecd_notiz": "Elektrische und elektronische Baugruppen",
                
                # Anhang 7
                "anhang7_3a_code": "A1",
                "anhang7_3a_text": "Anhang 7 IIIA Text",
                "anhang7_3b_code": "B1",
                "anhang7_3b_text": "Anhang 7 IIIB Text",
                
                # Bemerkungen
                "bemerkung_intern": "Interne Notiz zum Artikel"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["artbez1"] == test_name
        assert data["data"]["artikelgruppe"] == "Metalle"
        assert data["data"]["avv_code_eingang"] == "17 04 01"
        assert data["data"]["zolltarifnr"] == "7204 10 00"
        print(f"✓ Created full article with all fields: {data['data']['artbez1']}")
        return data["data"]["id"]
    
    def test_get_artikel_by_id(self, auth_headers):
        """Test getting article by ID"""
        # First create an article
        test_name = f"TEST_GetById_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "einheit": "kg",
                "aktiv": True
            }
        )
        artikel_id = create_response.json()["data"]["id"]
        
        # Then get it by ID
        response = requests.get(
            f"{BASE_URL}/api/artikel/{artikel_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["artbez1"] == test_name
        assert data["data"]["id"] == artikel_id
        print(f"✓ Got article by ID: {data['data']['artbez1']}")
    
    def test_update_artikel(self, auth_headers):
        """Test updating an article (simulating sidebar edit)"""
        # First create an article
        test_name = f"TEST_Update_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "einheit": "kg",
                "aktiv": True
            }
        )
        artikel_id = create_response.json()["data"]["id"]
        
        # Update it (simulating sidebar edit)
        updated_name = f"{test_name}_UPDATED"
        response = requests.put(
            f"{BASE_URL}/api/artikel/{artikel_id}",
            headers=auth_headers,
            json={
                "artbez1": updated_name,
                "artbez2": "Aktualisierte Beschreibung",
                "artikelgruppe": "Neue Gruppe",
                "gefahrgut": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["artbez1"] == updated_name
        assert data["data"]["artikelgruppe"] == "Neue Gruppe"
        assert data["data"]["gefahrgut"] == True
        print(f"✓ Updated article: {data['data']['artbez1']}")
        
        # Verify update persisted
        get_response = requests.get(
            f"{BASE_URL}/api/artikel/{artikel_id}",
            headers=auth_headers
        )
        assert get_response.json()["data"]["artbez1"] == updated_name
    
    def test_delete_artikel_soft_delete(self, auth_headers):
        """Test soft-deleting an article"""
        # First create an article
        test_name = f"TEST_Delete_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "einheit": "kg",
                "aktiv": True
            }
        )
        artikel_id = create_response.json()["data"]["id"]
        
        # Delete it (soft-delete)
        response = requests.delete(
            f"{BASE_URL}/api/artikel/{artikel_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Soft-deleted article")
        
        # Verify it's deactivated
        get_response = requests.get(
            f"{BASE_URL}/api/artikel/{artikel_id}",
            headers=auth_headers
        )
        assert get_response.json()["data"]["aktiv"] == False
    
    def test_search_artikel(self, auth_headers):
        """Test searching articles"""
        response = requests.get(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            params={"suche": "TEST"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Search returned {len(data['data'])} results")
    
    def test_artikel_gefahrgut_flag(self, auth_headers):
        """Test creating hazardous material article"""
        test_name = f"TEST_Gefahrgut_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "einheit": "kg",
                "aktiv": True,
                "gefahrgut": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["gefahrgut"] == True
        print(f"✓ Created hazardous article: {data['data']['artbez1']}")


# ============================================================
# KONTRAKTE MODULE TESTS
# ============================================================

class TestKontrakteCRUD:
    """Kontrakte (Contracts) CRUD tests - testing new sidebar layout endpoints"""
    
    def test_get_kontrakte_list(self, auth_headers):
        """Test getting contracts list"""
        response = requests.get(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "pagination" in data
        print(f"✓ Got {len(data['data'])} contracts, total: {data['pagination']['total']}")
    
    def test_create_einkaufskontrakt_new_schema(self, auth_headers):
        """Test creating purchase contract with new schema (vorgang_typ instead of ist_einkauf)"""
        test_name = f"TEST_EK_Firma_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "name2": "Einkaufsabteilung",
                "strasse": "Teststraße",
                "hausnummer": "123",
                "plz": "12345",
                "ort": "Teststadt",
                "land": "Deutschland",
                "telefon": "+49 123 456789",
                "email": "einkauf@test.de",
                "gueltig_von": "2024-01-01",
                "gueltig_bis": "2024-12-31",
                "waehrung_kurz": "EUR",
                "zahlungsbedingung": "30 Tage netto",
                "lieferbedingung": "FCA",
                "status": "OFFEN",
                "aktiv": True,
                "bemerkung_intern": "Test Einkaufskontrakt"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        # Check vorgang_typ or ist_einkauf (backend may use either)
        assert data["data"].get("vorgang_typ") == "EK" or data["data"].get("ist_einkauf") == True
        print(f"✓ Created purchase contract: {data['data'].get('buchungsnummer', data['data'].get('kontraktnr', 'N/A'))}")
        return data["data"]["id"]
    
    def test_create_verkaufskontrakt_new_schema(self, auth_headers):
        """Test creating sales contract with new schema"""
        test_name = f"TEST_VK_Firma_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "VK",
                "name1": test_name,
                "plz": "54321",
                "ort": "Verkaufsstadt",
                "land": "Deutschland",
                "gueltig_von": "2024-01-01",
                "gueltig_bis": "2024-12-31",
                "waehrung_kurz": "EUR",
                "status": "OFFEN",
                "aktiv": True,
                "bemerkung_intern": "Test Verkaufskontrakt"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # Check vorgang_typ or ist_einkauf (backend may use either)
        assert data["data"].get("vorgang_typ") == "VK" or data["data"].get("ist_einkauf") == False
        print(f"✓ Created sales contract: {data['data'].get('buchungsnummer', data['data'].get('kontraktnr', 'N/A'))}")
        return data["data"]["id"]
    
    def test_create_kontrakt_with_all_fields(self, auth_headers):
        """Test creating contract with all fields (Kopfdaten, Vertragspartner, Termine, Konditionen)"""
        test_name = f"TEST_KontraktFull_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                # Kopfdaten
                "vorgang_typ": "EK",
                "buchungsnummer": f"TEST-{uuid.uuid4().hex[:6]}",
                "status": "OFFEN",
                "aktiv": True,
                
                # Vertragspartner
                "name1": test_name,
                "name2": "Abteilung Test",
                "strasse": "Hauptstraße",
                "hausnummer": "42",
                "plz": "10115",
                "ort": "Berlin",
                "land": "Deutschland",
                "telefon": "+49 30 123456",
                "telefax": "+49 30 123457",
                "email": "test@firma.de",
                
                # Bearbeiter
                "name_bearbeiter_intern": "Max Mustermann",
                "tel_bearbeiter_intern": "+49 30 999888",
                "fax_bearbeiter_intern": "+49 30 999889",
                
                # Termine
                "erstellungsdatum": datetime.now().strftime("%Y-%m-%d"),
                "gueltig_von": "2024-01-01",
                "gueltig_bis": "2024-12-31",
                
                # Konditionen
                "waehrung_kurz": "EUR",
                "zahlungsbedingung": "14 Tage 2% Skonto, 30 Tage netto",
                "lieferbedingung": "DAP Berlin",
                
                # Bemerkungen
                "bemerkung_extern": "Externe Bemerkung für Partner",
                "bemerkung_intern": "Interne Notiz"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == test_name
        assert data["data"]["waehrung_kurz"] == "EUR"
        print(f"✓ Created full contract with all fields: {data['data'].get('buchungsnummer', 'N/A')}")
        return data["data"]["id"]
    
    def test_get_kontrakt_by_id(self, auth_headers):
        """Test getting contract by ID"""
        # First create a contract
        test_name = f"TEST_GetById_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "aktiv": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Then get it by ID
        response = requests.get(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == test_name
        assert data["data"]["id"] == kontrakt_id
        print(f"✓ Got contract by ID: {data['data']['name1']}")
    
    def test_update_kontrakt(self, auth_headers):
        """Test updating a contract (simulating sidebar edit)"""
        # First create a contract
        test_name = f"TEST_Update_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "status": "OFFEN",
                "aktiv": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Update it (simulating sidebar edit)
        updated_name = f"{test_name}_UPDATED"
        response = requests.put(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers,
            json={
                "name1": updated_name,
                "name2": "Neue Abteilung",
                "ort": "Neue Stadt",
                "status": "AKTIV",
                "zahlungsbedingung": "60 Tage netto"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == updated_name
        assert data["data"]["status"] == "AKTIV"
        print(f"✓ Updated contract: {data['data']['name1']}")
        
        # Verify update persisted
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers
        )
        assert get_response.json()["data"]["name1"] == updated_name
        assert get_response.json()["data"]["status"] == "AKTIV"
    
    def test_delete_kontrakt_soft_delete(self, auth_headers):
        """Test soft-deleting a contract"""
        # First create a contract
        test_name = f"TEST_Delete_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "VK",
                "name1": test_name,
                "aktiv": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Delete it (soft-delete)
        response = requests.delete(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Soft-deleted contract")
        
        # Verify it's marked as deleted
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers
        )
        # Check for deleted flag or aktiv=False
        kontrakt_data = get_response.json()["data"]
        assert kontrakt_data.get("deleted") == True or kontrakt_data.get("aktiv") == False
    
    def test_filter_kontrakte_by_type_ek(self, auth_headers):
        """Test filtering contracts by purchase type (EK)"""
        response = requests.get(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            params={"ist_einkauf": True}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All returned contracts should be purchase contracts
        for kontrakt in data["data"]:
            assert kontrakt.get("ist_einkauf") == True or kontrakt.get("vorgang_typ") == "EK"
        print(f"✓ Filter EK returned {len(data['data'])} contracts")
    
    def test_filter_kontrakte_by_type_vk(self, auth_headers):
        """Test filtering contracts by sales type (VK)"""
        response = requests.get(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            params={"ist_einkauf": False}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All returned contracts should be sales contracts
        for kontrakt in data["data"]:
            assert kontrakt.get("ist_einkauf") == False or kontrakt.get("vorgang_typ") == "VK"
        print(f"✓ Filter VK returned {len(data['data'])} contracts")
    
    def test_add_position_to_kontrakt(self, auth_headers):
        """Test adding a position to a contract"""
        # First create a contract
        test_name = f"TEST_Position_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "aktiv": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Add a position
        response = requests.post(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Test Artikel Position",
                "menge": 1000,
                "einzelpreis": 150.50,
                "steuersatz": 19.0,
                "einheit": "kg"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["positionsnummer"] == 1
        print(f"✓ Added position to contract: Position #{data['data']['positionsnummer']}")
    
    def test_kontrakt_status_transitions(self, auth_headers):
        """Test contract status transitions"""
        # Create a contract
        test_name = f"TEST_Status_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "status": "OFFEN",
                "aktiv": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Update status to AKTIV
        response = requests.put(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers,
            json={"status": "AKTIV"}
        )
        assert response.status_code == 200
        assert response.json()["data"]["status"] == "AKTIV"
        
        # Update status to TEILERFUELLT
        response = requests.put(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers,
            json={"status": "TEILERFUELLT"}
        )
        assert response.status_code == 200
        assert response.json()["data"]["status"] == "TEILERFUELLT"
        
        print(f"✓ Contract status transitions work correctly")


# ============================================================
# INTEGRATION TESTS
# ============================================================

class TestArtikelKontrakteIntegration:
    """Integration tests for Artikel and Kontrakte modules"""
    
    def test_create_artikel_then_use_in_kontrakt_position(self, auth_headers):
        """Test creating an article and using it in a contract position"""
        # Create an article
        artikel_name = f"TEST_Integration_{uuid.uuid4().hex[:8]}"
        artikel_response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": artikel_name,
                "einheit": "kg",
                "einheit_preis": "t",
                "aktiv": True
            }
        )
        assert artikel_response.status_code == 200
        artikel_id = artikel_response.json()["data"]["id"]
        
        # Create a contract
        kontrakt_name = f"TEST_IntegrationKontrakt_{uuid.uuid4().hex[:8]}"
        kontrakt_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": kontrakt_name,
                "aktiv": True
            }
        )
        assert kontrakt_response.status_code == 200
        kontrakt_id = kontrakt_response.json()["data"]["id"]
        
        # Add the article as a position
        position_response = requests.post(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}/positionen",
            headers=auth_headers,
            json={
                "artikel_id": artikel_id,
                "artbez1": artikel_name,
                "menge": 500,
                "einzelpreis": 200.00,
                "einheit": "kg"
            }
        )
        assert position_response.status_code == 200
        print(f"✓ Integration test passed: Article used in contract position")


# ============================================================
# ERROR HANDLING TESTS
# ============================================================

class TestErrorHandling:
    """Error handling tests"""
    
    def test_get_nonexistent_artikel(self, auth_headers):
        """Test getting non-existent article returns 404"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/nonexistent-id-12345",
            headers=auth_headers
        )
        assert response.status_code == 404
        print(f"✓ Non-existent article correctly returns 404")
    
    def test_get_nonexistent_kontrakt(self, auth_headers):
        """Test getting non-existent contract returns 404"""
        response = requests.get(
            f"{BASE_URL}/api/kontrakte/nonexistent-id-12345",
            headers=auth_headers
        )
        assert response.status_code == 404
        print(f"✓ Non-existent contract correctly returns 404")
    
    def test_create_artikel_without_required_field(self, auth_headers):
        """Test creating article without required field returns validation error"""
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                # Missing artbez1 which is required
                "einheit": "kg"
            }
        )
        assert response.status_code == 422  # Validation error
        print(f"✓ Missing required field correctly returns 422")
    
    def test_create_kontrakt_without_required_field(self, auth_headers):
        """Test creating contract without required field returns validation error"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                # Missing name1 which is required
                "vorgang_typ": "EK"
            }
        )
        assert response.status_code == 422  # Validation error
        print(f"✓ Missing required field correctly returns 422")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
