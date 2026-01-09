"""
Test suite for Geschäftslogik-Validierung (Business Logic Validation)
Ported from Java/Echo2 code for Artikel, Kontrakte, and Fuhren

Tests cover:
- Artikel-Validierung: Typ-Kombinationen, Einheiten-Divisor, AVV/Basel/OECD-Code
- Kontrakt-Validierung: Pflichtfelder, Gültigkeitsdaten
- Fuhren-Validierung: Pflichtfelder, Datumslogik, Mengenabweichung, AVV-Code-Pflicht
- Status-Workflow bei Fuhren
- skip_validation Parameter
- Validierungs-Endpunkte
"""

import pytest
import requests
import os
from datetime import datetime, timedelta

BASE_URL = os.environ.get("base_url", "https://demobackend.emergentagent.com")


class TestSetup:
    """Setup fixtures and authentication"""
    
    @pytest.fixture(scope="class")
    def auth_token(self):
        """Get authentication token"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200, f"Login failed: {response.text}"
        data = response.json()
        assert data.get("success"), f"Login not successful: {data}"
        return data.get("access_token")
    
    @pytest.fixture(scope="class")
    def auth_headers(self, auth_token):
        """Get headers with auth token"""
        return {
            "Authorization": f"Bearer {auth_token}",
            "Content-Type": "application/json"
        }


class TestArtikelValidierung(TestSetup):
    """
    Artikel-Validierung Tests
    - Typ-Kombinationen (Gefahrgut+Leergut, Produkt+Dienstleistung)
    - Einheiten-Divisor-Prüfung
    - AVV/Basel/OECD-Code-Prüfung
    """
    
    def test_validate_artikel_endpoint_exists(self, auth_headers):
        """Test that /api/artikel/validieren endpoint exists"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={"artbez1": "Test"})
        assert response.status_code == 200, f"Endpoint failed: {response.text}"
        data = response.json()
        assert "valid" in data, "Response should contain 'valid' field"
        assert "errors" in data, "Response should contain 'errors' field"
        assert "warnings" in data, "Response should contain 'warnings' field"
    
    def test_artikel_gefahrgut_leergut_kombination_error(self, auth_headers):
        """Leergut kann kein Gefahrgut sein - should return error"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Gefahrgut_Leergut",
                                    "gefahrgut": True,
                                    "ist_leergut": True
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("Leergut kann kein Gefahrgut" in e for e in data["errors"]), \
            f"Expected error about Leergut+Gefahrgut, got: {data['errors']}"
    
    def test_artikel_gefahrgut_dienstleistung_kombination_error(self, auth_headers):
        """Dienstleistung kann kein Gefahrgut sein - should return error"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Gefahrgut_Dienstleistung",
                                    "gefahrgut": True,
                                    "dienstleistung": True
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("Dienstleistung kann kein Gefahrgut" in e for e in data["errors"]), \
            f"Expected error about Dienstleistung+Gefahrgut, got: {data['errors']}"
    
    def test_artikel_produkt_dienstleistung_kombination_error(self, auth_headers):
        """Artikel kann nur Produkt ODER Dienstleistung sein - should return error"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Produkt_Dienstleistung",
                                    "ist_produkt": True,
                                    "dienstleistung": True
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("nur Produkt, Dienstleistung oder 'End of Waste'" in e for e in data["errors"]), \
            f"Expected error about type combination, got: {data['errors']}"
    
    def test_artikel_einheit_divisor_gleich_muss_1_sein(self, auth_headers):
        """Wenn Einheit = Preiseinheit, muss Divisor 1 sein"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Einheit_Divisor",
                                    "einheit": "kg",
                                    "einheit_preis": "kg",
                                    "mengendivisor": 1000  # Should be 1
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("Divisor 1 sein" in e for e in data["errors"]), \
            f"Expected error about divisor, got: {data['errors']}"
    
    def test_artikel_einheit_divisor_ungleich_darf_nicht_1_sein(self, auth_headers):
        """Wenn Einheit != Preiseinheit, darf Divisor NICHT 1 sein"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Einheit_Divisor_2",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1  # Should NOT be 1
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("DARF der Divisor NICHT 1 sein" in e for e in data["errors"]), \
            f"Expected error about divisor not being 1, got: {data['errors']}"
    
    def test_artikel_produkt_mit_avv_code_error(self, auth_headers):
        """Produkt/Dienstleistung/EndOfWaste dürfen keine AVV-Codes haben"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Produkt_AVV",
                                    "ist_produkt": True,
                                    "avv_code_eingang": "170101"
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("keinen OECD-, Basel- oder AVV-Code" in e for e in data["errors"]), \
            f"Expected error about AVV code, got: {data['errors']}"
    
    def test_artikel_rohstoff_ohne_zolltarifnummer_warning(self, auth_headers):
        """Rohstoff ohne Zolltarifnummer erzeugt Warnung"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Rohstoff_Zoll",
                                    "ist_produkt": False,
                                    "dienstleistung": False,
                                    "end_of_waste": False,
                                    "zolltarifnr": ""  # Empty
                                })
        assert response.status_code == 200
        data = response.json()
        # Should be valid but with warning
        assert data["valid"] == True, f"Should be valid with warning, got errors: {data['errors']}"
        assert any("ohne Zolltarifnummer" in w for w in data["warnings"]), \
            f"Expected warning about Zolltarifnummer, got: {data['warnings']}"
    
    def test_artikel_valid_configuration(self, auth_headers):
        """Valid artikel configuration should pass"""
        response = requests.post(f"{BASE_URL}/api/artikel/validieren", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Valid_Artikel",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1000,
                                    "ist_produkt": False,
                                    "dienstleistung": False,
                                    "gefahrgut": False,
                                    "ist_leergut": False,
                                    "zolltarifnr": "12345678"
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == True, f"Should be valid, got errors: {data['errors']}"


class TestKontraktValidierung(TestSetup):
    """
    Kontrakt-Validierung Tests
    - Pflichtfelder (name1)
    - Gültigkeitsdaten-Prüfung (gueltig_von < gueltig_bis)
    """
    
    def test_validate_kontrakt_endpoint_exists(self, auth_headers):
        """Test that /api/kontrakte/validieren endpoint exists"""
        response = requests.post(f"{BASE_URL}/api/kontrakte/validieren", 
                                headers=auth_headers, 
                                json={"name1": "Test Partner"})
        assert response.status_code == 200, f"Endpoint failed: {response.text}"
        data = response.json()
        assert "valid" in data, "Response should contain 'valid' field"
        assert "errors" in data, "Response should contain 'errors' field"
        assert "warnings" in data, "Response should contain 'warnings' field"
    
    def test_kontrakt_name1_pflichtfeld_error(self, auth_headers):
        """name1 (Vertragspartner) ist Pflichtfeld"""
        response = requests.post(f"{BASE_URL}/api/kontrakte/validieren", 
                                headers=auth_headers, 
                                json={
                                    "name1": "",  # Empty
                                    "vorgang_typ": "EK"
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("Pflichtfeld" in e or "Name1" in e for e in data["errors"]), \
            f"Expected error about name1 being required, got: {data['errors']}"
    
    def test_kontrakt_gueltig_bis_vor_von_error(self, auth_headers):
        """gueltig_bis darf nicht vor gueltig_von liegen"""
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/kontrakte/validieren", 
                                headers=auth_headers, 
                                json={
                                    "name1": "TEST_Kontrakt_Datum",
                                    "gueltig_von": (today + timedelta(days=30)).strftime("%Y-%m-%d"),
                                    "gueltig_bis": today.strftime("%Y-%m-%d")  # Before gueltig_von
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("vor" in e.lower() and "gültig" in e.lower() for e in data["errors"]), \
            f"Expected error about date order, got: {data['errors']}"
    
    def test_kontrakt_valid_configuration(self, auth_headers):
        """Valid kontrakt configuration should pass"""
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/kontrakte/validieren", 
                                headers=auth_headers, 
                                json={
                                    "name1": "TEST_Valid_Kontrakt",
                                    "vorgang_typ": "EK",
                                    "gueltig_von": today.strftime("%Y-%m-%d"),
                                    "gueltig_bis": (today + timedelta(days=365)).strftime("%Y-%m-%d")
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == True, f"Should be valid, got errors: {data['errors']}"


class TestFuhreValidierung(TestSetup):
    """
    Fuhren-Validierung Tests
    - Pflichtfelder (Adressen, Artikel)
    - Datumslogik (Anlieferung >= Abholung)
    - Mengenabweichungs-Warnung
    - AVV-Code-Pflicht für Rohstoffe
    """
    
    @pytest.fixture(scope="class")
    def test_adresse_id(self, auth_headers):
        """Create a test address for Fuhre tests"""
        response = requests.post(f"{BASE_URL}/api/adressen", 
                                headers=auth_headers, 
                                json={
                                    "name1": "TEST_Fuhre_Adresse",
                                    "plz": "12345",
                                    "ort": "Teststadt",
                                    "land": "Deutschland",
                                    "ist_firma": True,
                                    "umsatzsteuer_lkz": "DE",
                                    "umsatzsteuer_id": "123456789"
                                },
                                params={"skip_validation": "true"})
        if response.status_code == 200:
            data = response.json()
            if data.get("success"):
                return data["data"]["id"]
        # Try to find existing
        response = requests.get(f"{BASE_URL}/api/adressen", 
                               headers=auth_headers,
                               params={"suche": "TEST_Fuhre_Adresse"})
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        pytest.skip("Could not create test address")
    
    @pytest.fixture(scope="class")
    def test_artikel_rohstoff_id(self, auth_headers):
        """Create a test Rohstoff article (without AVV code)"""
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Rohstoff_Ohne_AVV",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1000,
                                    "ist_produkt": False,
                                    "dienstleistung": False,
                                    "end_of_waste": False,
                                    "avv_code_eingang": None
                                },
                                params={"skip_validation": "true"})
        if response.status_code == 200:
            data = response.json()
            if data.get("success"):
                return data["data"]["id"]
        # Try to find existing
        response = requests.get(f"{BASE_URL}/api/artikel", 
                               headers=auth_headers,
                               params={"suche": "TEST_Rohstoff_Ohne_AVV"})
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        pytest.skip("Could not create test article")
    
    @pytest.fixture(scope="class")
    def test_artikel_mit_avv_id(self, auth_headers):
        """Create a test article with AVV code"""
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Rohstoff_Mit_AVV",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1000,
                                    "ist_produkt": False,
                                    "dienstleistung": False,
                                    "end_of_waste": False,
                                    "avv_code_eingang": "170101"
                                },
                                params={"skip_validation": "true"})
        if response.status_code == 200:
            data = response.json()
            if data.get("success"):
                return data["data"]["id"]
        # Try to find existing
        response = requests.get(f"{BASE_URL}/api/artikel", 
                               headers=auth_headers,
                               params={"suche": "TEST_Rohstoff_Mit_AVV"})
        if response.status_code == 200:
            data = response.json()
            if data.get("data") and len(data["data"]) > 0:
                return data["data"][0]["id"]
        pytest.skip("Could not create test article with AVV")
    
    def test_validate_fuhre_endpoint_exists(self, auth_headers):
        """Test that /api/fuhren/validieren endpoint exists"""
        response = requests.post(f"{BASE_URL}/api/fuhren/validieren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": "test",
                                    "id_adresse_ziel": "test",
                                    "id_artikel": "test"
                                })
        assert response.status_code == 200, f"Endpoint failed: {response.text}"
        data = response.json()
        assert "valid" in data, "Response should contain 'valid' field"
        assert "errors" in data, "Response should contain 'errors' field"
        assert "warnings" in data, "Response should contain 'warnings' field"
    
    def test_fuhre_pflichtfelder_error(self, auth_headers):
        """Pflichtfelder müssen ausgefüllt sein"""
        response = requests.post(f"{BASE_URL}/api/fuhren/validieren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": "",
                                    "id_adresse_ziel": "",
                                    "id_artikel": ""
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        # Should have errors for all three required fields
        errors_text = " ".join(data["errors"])
        assert "Lieferant" in errors_text or "Start-Adresse" in errors_text, \
            f"Expected error about Lieferant, got: {data['errors']}"
        assert "Abnehmer" in errors_text or "Ziel-Adresse" in errors_text, \
            f"Expected error about Abnehmer, got: {data['errors']}"
        assert "Artikel" in errors_text or "Sorte" in errors_text, \
            f"Expected error about Artikel, got: {data['errors']}"
    
    def test_fuhre_anlieferung_vor_abholung_error(self, auth_headers, test_adresse_id, test_artikel_mit_avv_id):
        """Anlieferungsdatum darf nicht vor Abholdatum liegen"""
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/fuhren/validieren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": test_adresse_id,
                                    "id_adresse_ziel": test_adresse_id,
                                    "id_artikel": test_artikel_mit_avv_id,
                                    "datum_abholung": (today + timedelta(days=5)).strftime("%Y-%m-%d"),
                                    "datum_anlieferung": today.strftime("%Y-%m-%d")  # Before abholung
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, "Should be invalid"
        assert any("vor" in e.lower() and "abhol" in e.lower() for e in data["errors"]), \
            f"Expected error about date order, got: {data['errors']}"
    
    def test_fuhre_mengenabweichung_warning(self, auth_headers, test_adresse_id, test_artikel_mit_avv_id):
        """Abweichung zwischen Lade- und Ablademenge > 5% erzeugt Warnung"""
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/fuhren/validieren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": test_adresse_id,
                                    "id_adresse_ziel": test_adresse_id,
                                    "id_artikel": test_artikel_mit_avv_id,
                                    "datum_abholung": today.strftime("%Y-%m-%d"),
                                    "datum_anlieferung": (today + timedelta(days=1)).strftime("%Y-%m-%d"),
                                    "menge_aufladen": 1000,
                                    "menge_abladen": 800  # 20% deviation
                                })
        assert response.status_code == 200
        data = response.json()
        # Should be valid but with warning
        assert data["valid"] == True, f"Should be valid with warning, got errors: {data['errors']}"
        assert any("abweichung" in w.lower() for w in data["warnings"]), \
            f"Expected warning about Mengenabweichung, got: {data['warnings']}"
    
    def test_fuhre_rohstoff_ohne_avv_error(self, auth_headers, test_adresse_id, test_artikel_rohstoff_id):
        """Rohstoffe ohne AVV-Code sind nicht zulässig"""
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/fuhren/validieren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": test_adresse_id,
                                    "id_adresse_ziel": test_adresse_id,
                                    "id_artikel": test_artikel_rohstoff_id,
                                    "datum_abholung": today.strftime("%Y-%m-%d"),
                                    "datum_anlieferung": (today + timedelta(days=1)).strftime("%Y-%m-%d")
                                })
        assert response.status_code == 200
        data = response.json()
        assert data["valid"] == False, f"Should be invalid, got: {data}"
        assert any("AVV-Code" in e for e in data["errors"]), \
            f"Expected error about AVV-Code, got: {data['errors']}"


class TestFuhreStatusWorkflow(TestSetup):
    """
    Status-Workflow bei Fuhren Tests
    Erlaubte Übergänge: OFFEN->IN_TRANSPORT->GELIEFERT->ABGERECHNET
    """
    
    @pytest.fixture(scope="class")
    def test_fuhre_id(self, auth_headers):
        """Create a test Fuhre for status tests"""
        # First get or create test data
        # Get an address
        response = requests.get(f"{BASE_URL}/api/adressen", headers=auth_headers, params={"limit": 1})
        if response.status_code != 200 or not response.json().get("data"):
            pytest.skip("No addresses available")
        adresse_id = response.json()["data"][0]["id"]
        
        # Get an article with AVV code
        response = requests.get(f"{BASE_URL}/api/artikel", headers=auth_headers, params={"suche": "AVV"})
        if response.status_code != 200 or not response.json().get("data"):
            # Create one
            response = requests.post(f"{BASE_URL}/api/artikel", 
                                    headers=auth_headers, 
                                    json={
                                        "artbez1": "TEST_Status_Artikel",
                                        "einheit": "kg",
                                        "einheit_preis": "t",
                                        "mengendivisor": 1000,
                                        "avv_code_eingang": "170101"
                                    },
                                    params={"skip_validation": "true"})
            if response.status_code != 200:
                pytest.skip("Could not create test article")
            artikel_id = response.json()["data"]["id"]
        else:
            artikel_id = response.json()["data"][0]["id"]
        
        # Create Fuhre
        today = datetime.now()
        response = requests.post(f"{BASE_URL}/api/fuhren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": adresse_id,
                                    "id_adresse_ziel": adresse_id,
                                    "id_artikel": artikel_id,
                                    "datum_abholung": today.strftime("%Y-%m-%d"),
                                    "datum_anlieferung": (today + timedelta(days=1)).strftime("%Y-%m-%d"),
                                    "status": "OFFEN"
                                },
                                params={"skip_validation": "true"})
        if response.status_code == 200 and response.json().get("success"):
            return response.json()["data"]["id"]
        pytest.skip("Could not create test Fuhre")
    
    def test_status_offen_to_in_transport_allowed(self, auth_headers, test_fuhre_id):
        """OFFEN -> IN_TRANSPORT should be allowed"""
        response = requests.put(f"{BASE_URL}/api/fuhren/{test_fuhre_id}", 
                               headers=auth_headers, 
                               json={"status": "IN_TRANSPORT"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Status change should succeed: {data}"
    
    def test_status_in_transport_to_geliefert_allowed(self, auth_headers, test_fuhre_id):
        """IN_TRANSPORT -> GELIEFERT should be allowed"""
        response = requests.put(f"{BASE_URL}/api/fuhren/{test_fuhre_id}", 
                               headers=auth_headers, 
                               json={"status": "GELIEFERT"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Status change should succeed: {data}"
    
    def test_status_geliefert_to_abgerechnet_allowed(self, auth_headers, test_fuhre_id):
        """GELIEFERT -> ABGERECHNET should be allowed"""
        response = requests.put(f"{BASE_URL}/api/fuhren/{test_fuhre_id}", 
                               headers=auth_headers, 
                               json={"status": "ABGERECHNET"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Status change should succeed: {data}"
    
    def test_status_abgerechnet_to_offen_not_allowed(self, auth_headers, test_fuhre_id):
        """ABGERECHNET -> OFFEN should NOT be allowed (end status)"""
        response = requests.put(f"{BASE_URL}/api/fuhren/{test_fuhre_id}", 
                               headers=auth_headers, 
                               json={"status": "OFFEN"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Status change should fail: {data}"
        assert any("nicht erlaubt" in e.lower() for e in data.get("errors", [])), \
            f"Expected error about status transition, got: {data}"


class TestSkipValidationParameter(TestSetup):
    """
    Tests for skip_validation parameter
    Validierung kann bei allen Endpunkten übersprungen werden
    """
    
    def test_artikel_create_with_skip_validation(self, auth_headers):
        """Create invalid artikel with skip_validation=true should succeed"""
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Skip_Validation_Artikel",
                                    "gefahrgut": True,
                                    "ist_leergut": True  # Invalid combination
                                },
                                params={"skip_validation": "true"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Should succeed with skip_validation: {data}"
        # Cleanup
        if data.get("data", {}).get("id"):
            requests.delete(f"{BASE_URL}/api/artikel/{data['data']['id']}", headers=auth_headers)
    
    def test_artikel_create_without_skip_validation_fails(self, auth_headers):
        """Create invalid artikel without skip_validation should fail"""
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_No_Skip_Validation_Artikel",
                                    "gefahrgut": True,
                                    "ist_leergut": True  # Invalid combination
                                })
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Should fail without skip_validation: {data}"
        assert len(data.get("errors", [])) > 0, "Should have validation errors"
    
    def test_artikel_update_with_skip_validation(self, auth_headers):
        """Update artikel with invalid data and skip_validation=true should succeed"""
        # First create a valid artikel
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Update_Skip_Artikel",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1000
                                },
                                params={"skip_validation": "true"})
        assert response.status_code == 200
        artikel_id = response.json()["data"]["id"]
        
        # Update with invalid data
        response = requests.put(f"{BASE_URL}/api/artikel/{artikel_id}", 
                               headers=auth_headers, 
                               json={
                                   "gefahrgut": True,
                                   "ist_leergut": True  # Invalid combination
                               },
                               params={"skip_validation": "true"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Should succeed with skip_validation: {data}"
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/artikel/{artikel_id}", headers=auth_headers)
    
    def test_fuhre_create_with_skip_validation(self, auth_headers):
        """Create Fuhre with missing required fields and skip_validation=true"""
        # Get any address and article
        response = requests.get(f"{BASE_URL}/api/adressen", headers=auth_headers, params={"limit": 1})
        if response.status_code != 200 or not response.json().get("data"):
            pytest.skip("No addresses available")
        adresse_id = response.json()["data"][0]["id"]
        
        response = requests.get(f"{BASE_URL}/api/artikel", headers=auth_headers, params={"limit": 1})
        if response.status_code != 200 or not response.json().get("data"):
            pytest.skip("No articles available")
        artikel_id = response.json()["data"][0]["id"]
        
        today = datetime.now()
        # Create with dates in wrong order (should normally fail)
        response = requests.post(f"{BASE_URL}/api/fuhren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": adresse_id,
                                    "id_adresse_ziel": adresse_id,
                                    "id_artikel": artikel_id,
                                    "datum_abholung": (today + timedelta(days=5)).strftime("%Y-%m-%d"),
                                    "datum_anlieferung": today.strftime("%Y-%m-%d")  # Before abholung
                                },
                                params={"skip_validation": "true"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == True, f"Should succeed with skip_validation: {data}"
        
        # Cleanup
        if data.get("data", {}).get("id"):
            requests.delete(f"{BASE_URL}/api/fuhren/{data['data']['id']}", headers=auth_headers)


class TestValidationIntegrationInCRUD(TestSetup):
    """
    Tests that validation is properly integrated in create/update endpoints
    """
    
    def test_artikel_create_validates_by_default(self, auth_headers):
        """POST /api/artikel should validate by default"""
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Default_Validation",
                                    "einheit": "kg",
                                    "einheit_preis": "kg",
                                    "mengendivisor": 1000  # Invalid: should be 1
                                })
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Should fail validation: {data}"
        assert "errors" in data, "Should have errors field"
    
    def test_artikel_update_validates_by_default(self, auth_headers):
        """PUT /api/artikel/{id} should validate by default"""
        # First create a valid artikel
        response = requests.post(f"{BASE_URL}/api/artikel", 
                                headers=auth_headers, 
                                json={
                                    "artbez1": "TEST_Update_Validation",
                                    "einheit": "kg",
                                    "einheit_preis": "t",
                                    "mengendivisor": 1000
                                },
                                params={"skip_validation": "true"})
        assert response.status_code == 200
        artikel_id = response.json()["data"]["id"]
        
        # Update with invalid data
        response = requests.put(f"{BASE_URL}/api/artikel/{artikel_id}", 
                               headers=auth_headers, 
                               json={
                                   "einheit": "kg",
                                   "einheit_preis": "kg",
                                   "mengendivisor": 1000  # Invalid
                               })
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Should fail validation: {data}"
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/artikel/{artikel_id}", headers=auth_headers)
    
    def test_fuhre_create_validates_by_default(self, auth_headers):
        """POST /api/fuhren should validate by default"""
        response = requests.post(f"{BASE_URL}/api/fuhren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": "",
                                    "id_adresse_ziel": "",
                                    "id_artikel": "",
                                    "datum_abholung": "2025-01-01",
                                    "datum_anlieferung": "2025-01-02"
                                })
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Should fail validation: {data}"
        assert "errors" in data, "Should have errors field"
    
    def test_fuhre_update_validates_status_transition(self, auth_headers):
        """PUT /api/fuhren/{id} should validate status transitions"""
        # Get any address and article
        response = requests.get(f"{BASE_URL}/api/adressen", headers=auth_headers, params={"limit": 1})
        if response.status_code != 200 or not response.json().get("data"):
            pytest.skip("No addresses available")
        adresse_id = response.json()["data"][0]["id"]
        
        response = requests.get(f"{BASE_URL}/api/artikel", headers=auth_headers, params={"suche": "AVV"})
        if response.status_code != 200 or not response.json().get("data"):
            pytest.skip("No articles with AVV available")
        artikel_id = response.json()["data"][0]["id"]
        
        today = datetime.now()
        # Create a Fuhre with status OFFEN
        response = requests.post(f"{BASE_URL}/api/fuhren", 
                                headers=auth_headers, 
                                json={
                                    "id_adresse_start": adresse_id,
                                    "id_adresse_ziel": adresse_id,
                                    "id_artikel": artikel_id,
                                    "datum_abholung": today.strftime("%Y-%m-%d"),
                                    "datum_anlieferung": (today + timedelta(days=1)).strftime("%Y-%m-%d"),
                                    "status": "OFFEN"
                                },
                                params={"skip_validation": "true"})
        if response.status_code != 200 or not response.json().get("success"):
            pytest.skip("Could not create test Fuhre")
        fuhre_id = response.json()["data"]["id"]
        
        # Try invalid status transition: OFFEN -> ABGERECHNET (should skip IN_TRANSPORT and GELIEFERT)
        response = requests.put(f"{BASE_URL}/api/fuhren/{fuhre_id}", 
                               headers=auth_headers, 
                               json={"status": "ABGERECHNET"})
        assert response.status_code == 200
        data = response.json()
        assert data.get("success") == False, f"Should fail status validation: {data}"
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/fuhren/{fuhre_id}", headers=auth_headers)


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
