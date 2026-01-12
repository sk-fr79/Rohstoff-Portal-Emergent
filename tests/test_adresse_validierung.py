"""
Rohstoff Portal - Adress-Validierung Tests
Tests for: POST /api/adressen/validieren - Business logic from Java __FS_Adress_Check.java

Test scenarios:
1. FIRMA im Inland ohne UST-ID (sollte Fehler zurückgeben)
2. FIRMA im Inland mit korrekter UST-ID (sollte OK sein)
3. FIRMA im EU-Ausland ohne UST-ID (sollte Fehler zurückgeben)
4. PRIVAT im Ausland ohne Ausweis (sollte Fehler zurückgeben)
5. GET /api/laender - Länder-Endpunkt sollte 30 Länder zurückgeben
6. POST /api/adressen - Erstellen einer Adresse mit Validierung
"""

import pytest
import requests
import os
import uuid

BASE_URL = "https://commodity-dash.preview.emergentagent.com"

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


class TestLaenderEndpoint:
    """Tests for GET /api/laender endpoint"""
    
    def test_laender_returns_30_countries(self, auth_headers):
        """Test that /api/laender returns 30 countries"""
        response = requests.get(
            f"{BASE_URL}/api/laender",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        
        laender = data["data"]
        assert len(laender) == 30, f"Expected 30 countries, got {len(laender)}"
        
        # Verify structure of each country
        for land in laender:
            assert "name" in land
            assert "ust_praefix" in land
            assert "ist_eu" in land
            assert "ist_homeland" in land
        
        # Verify Deutschland is homeland
        deutschland = next((l for l in laender if l["name"] == "Deutschland"), None)
        assert deutschland is not None, "Deutschland not found in countries"
        assert deutschland["ist_homeland"] == True
        assert deutschland["ist_eu"] == True
        assert deutschland["ust_praefix"] == "DE"
        
        print(f"✓ Laender endpoint returned {len(laender)} countries")
        print(f"✓ Deutschland: ist_homeland={deutschland['ist_homeland']}, ist_eu={deutschland['ist_eu']}, ust_praefix={deutschland['ust_praefix']}")


class TestFirmaInlandValidierung:
    """Tests for FIRMA (company) in Germany validation"""
    
    def test_firma_inland_ohne_ustid_fehler(self, auth_headers):
        """
        Test: FIRMA im Inland ohne UST-ID sollte Fehler zurückgeben
        Expected: Validation error requiring 'firma_ohne_ustid' switch + Steuernummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Inland_Ohne_USTID",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - FIRMA in Germany without UST-ID"
        assert len(validierung["fehler"]) > 0, "Should have validation errors"
        
        # Check for specific error message about firma_ohne_ustid
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_ustid_error = any("Firma ohne UST-ID" in text or "Steuernummer" in text for text in fehler_texte)
        assert found_ustid_error, f"Expected error about 'Firma ohne UST-ID' or 'Steuernummer', got: {fehler_texte}"
        
        assert validierung["steuer_status"] == "FIRMA_INLAND"
        print(f"✓ FIRMA Inland ohne UST-ID correctly rejected with errors: {fehler_texte}")
    
    def test_firma_inland_mit_ustid_ok(self, auth_headers):
        """
        Test: FIRMA im Inland mit korrekter UST-ID sollte OK sein
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Inland_Mit_USTID",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": "DE",
                "umsatzsteuer_id": "123456789",
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - FIRMA in Germany with UST-ID. Errors: {validierung.get('fehler', [])}"
        assert len(validierung["fehler"]) == 0, f"Should have no errors, got: {validierung['fehler']}"
        assert validierung["steuer_status"] == "FIRMA_INLAND"
        print(f"✓ FIRMA Inland mit UST-ID correctly validated as OK")
    
    def test_firma_inland_ohne_ustid_mit_sonderschalter_und_steuernummer_ok(self, auth_headers):
        """
        Test: FIRMA im Inland ohne UST-ID aber mit Sonderschalter + Steuernummer sollte OK sein
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Inland_Sonderschalter",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",
                "firma_ohne_ustid": True,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - FIRMA with Sonderschalter + Steuernummer. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "FIRMA_INLAND"
        print(f"✓ FIRMA Inland ohne UST-ID mit Sonderschalter + Steuernummer correctly validated as OK")


class TestFirmaEUValidierung:
    """Tests for FIRMA (company) in EU countries validation"""
    
    def test_firma_eu_ohne_ustid_fehler(self, auth_headers):
        """
        Test: FIRMA im EU-Ausland ohne UST-ID sollte Fehler zurückgeben
        Expected: Validation error requiring UST-ID for EU companies
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_EU_Ohne_USTID",
                "ist_firma": True,
                "land": "Österreich",  # EU country
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - FIRMA in EU without UST-ID"
        assert len(validierung["fehler"]) > 0, "Should have validation errors"
        
        # Check for specific error message about EU UST-ID requirement
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_eu_error = any("EU-Ausland" in text or "UST-ID" in text for text in fehler_texte)
        assert found_eu_error, f"Expected error about EU UST-ID requirement, got: {fehler_texte}"
        
        assert validierung["steuer_status"] == "FIRMA_EU"
        print(f"✓ FIRMA EU ohne UST-ID correctly rejected with errors: {fehler_texte}")
    
    def test_firma_eu_mit_ustid_ok(self, auth_headers):
        """
        Test: FIRMA im EU-Ausland mit korrekter UST-ID sollte OK sein
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_EU_Mit_USTID",
                "ist_firma": True,
                "land": "Österreich",
                "umsatzsteuer_lkz": "AT",
                "umsatzsteuer_id": "U12345678",
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - FIRMA in EU with UST-ID. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "FIRMA_EU"
        print(f"✓ FIRMA EU mit UST-ID correctly validated as OK")


class TestPrivatValidierung:
    """Tests for PRIVAT (private person) validation"""
    
    def test_privat_ausland_ohne_ausweis_fehler(self, auth_headers):
        """
        Test: PRIVAT im Ausland ohne Ausweis sollte Fehler zurückgeben
        Expected: Validation error requiring Ausweisnummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Ausland_Ohne_Ausweis",
                "ist_firma": False,  # PRIVAT
                "land": "Österreich",  # Ausland
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "ausweis_nummer": None,
                "ausweis_ablauf": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - PRIVAT abroad without ID"
        assert len(validierung["fehler"]) > 0, "Should have validation errors"
        
        # Check for specific error message about Ausweis requirement
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_ausweis_error = any("Ausweisnummer" in text for text in fehler_texte)
        assert found_ausweis_error, f"Expected error about Ausweisnummer, got: {fehler_texte}"
        
        assert validierung["steuer_status"] == "PRIVAT_AUSLAND"
        print(f"✓ PRIVAT Ausland ohne Ausweis correctly rejected with errors: {fehler_texte}")
    
    def test_privat_ausland_mit_ausweis_ok(self, auth_headers):
        """
        Test: PRIVAT im Ausland mit Ausweis sollte OK sein
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Ausland_Mit_Ausweis",
                "ist_firma": False,  # PRIVAT
                "land": "Österreich",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "ausweis_nummer": "PA123456789",
                "ausweis_ablauf": "31.12.2030",
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - PRIVAT abroad with ID. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "PRIVAT_AUSLAND"
        print(f"✓ PRIVAT Ausland mit Ausweis correctly validated as OK")
    
    def test_privat_inland_ohne_ausweis_oder_steuernummer_fehler(self, auth_headers):
        """
        Test: PRIVAT im Inland ohne Ausweis oder Steuernummer sollte Fehler zurückgeben
        Expected: Validation error requiring Ausweisnummer or Steuernummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Inland_Ohne_Ausweis",
                "ist_firma": False,  # PRIVAT
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "ausweis_nummer": None,
                "ausweis_ablauf": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - PRIVAT inland without ID or Steuernummer"
        assert len(validierung["fehler"]) > 0, "Should have validation errors"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("Ausweisnummer" in text or "Steuernummer" in text for text in fehler_texte)
        assert found_error, f"Expected error about Ausweisnummer or Steuernummer, got: {fehler_texte}"
        
        assert validierung["steuer_status"] == "PRIVAT_INLAND"
        print(f"✓ PRIVAT Inland ohne Ausweis/Steuernummer correctly rejected with errors: {fehler_texte}")
    
    def test_privat_inland_mit_steuernummer_ok(self, auth_headers):
        """
        Test: PRIVAT im Inland mit Steuernummer sollte OK sein
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Inland_Mit_Steuernummer",
                "ist_firma": False,  # PRIVAT
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",
                "ausweis_nummer": None,
                "ausweis_ablauf": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - PRIVAT inland with Steuernummer. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "PRIVAT_INLAND"
        print(f"✓ PRIVAT Inland mit Steuernummer correctly validated as OK")


class TestAdresseErstellenMitValidierung:
    """Tests for POST /api/adressen with validation"""
    
    def test_create_adresse_with_validation_error_rejected(self, auth_headers):
        """
        Test: Creating an address with validation errors should be rejected
        Expected: 400 error with validation details
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": f"TEST_Invalid_Adresse_{uuid.uuid4().hex[:8]}",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        assert response.status_code == 400, f"Expected 400 for invalid address, got {response.status_code}"
        data = response.json()
        assert "detail" in data
        assert "fehler" in data["detail"] or "validierung" in data["detail"]
        print(f"✓ Invalid address correctly rejected with 400: {data['detail'].get('message', data['detail'])}")
    
    def test_create_adresse_with_valid_data_ok(self, auth_headers):
        """
        Test: Creating an address with valid data should succeed
        Expected: 200 with created address
        """
        test_name = f"TEST_Valid_Adresse_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": "DE",
                "umsatzsteuer_id": "123456789",
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        assert response.status_code == 200, f"Expected 200 for valid address, got {response.status_code}: {response.text}"
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == test_name
        assert "kdnr" in data["data"]
        print(f"✓ Valid address created successfully: {data['data']['name1']} (KDNR: {data['data']['kdnr']})")
    
    def test_create_adresse_skip_validation(self, auth_headers):
        """
        Test: Creating an address with skip_validation=True should bypass validation
        Expected: 200 even with invalid data
        """
        test_name = f"TEST_Skip_Validation_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen?skip_validation=true",
            headers=auth_headers,
            json={
                "name1": test_name,
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        assert response.status_code == 200, f"Expected 200 with skip_validation, got {response.status_code}: {response.text}"
        data = response.json()
        assert data["success"] == True
        print(f"✓ Address created with skip_validation: {data['data']['name1']}")


class TestSonderschalterValidierung:
    """Tests for Sonderschalter (special switches) validation"""
    
    def test_beide_sonderschalter_gleichzeitig_fehler(self, auth_headers):
        """
        Test: Both Sonderschalter active at the same time should be an error
        Expected: Validation error
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Beide_Sonderschalter",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",
                "firma_ohne_ustid": True,
                "privat_mit_ustid": True  # Both active - should be error
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - both Sonderschalter active"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("gleichzeitig" in text.lower() for text in fehler_texte)
        assert found_error, f"Expected error about both switches active, got: {fehler_texte}"
        print(f"✓ Both Sonderschalter correctly rejected: {fehler_texte}")
    
    def test_sonderschalter_nur_inland_warnung(self, auth_headers):
        """
        Test: Sonderschalter in non-German country should give warning
        Expected: Warning about Sonderschalter only for Germany
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Sonderschalter_Ausland",
                "ist_firma": True,
                "land": "Österreich",  # Not Germany
                "umsatzsteuer_lkz": "AT",
                "umsatzsteuer_id": "U12345678",
                "steuernummer": None,
                "firma_ohne_ustid": True,  # Should give warning for non-German
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        # Should have warning about Sonderschalter only for Germany
        warnungen_texte = [w["meldung"] for w in validierung["warnungen"]]
        found_warning = any("Deutschland" in text or "Inland" in text for text in warnungen_texte)
        assert found_warning, f"Expected warning about Sonderschalter only for Germany, got: {warnungen_texte}"
        print(f"✓ Sonderschalter in Ausland correctly warned: {warnungen_texte}")


class TestUSTIDValidierung:
    """Tests for UST-ID validation"""
    
    def test_ustid_teilweise_ausgefuellt_fehler(self, auth_headers):
        """
        Test: UST-ID only partially filled should be an error
        Expected: Validation error about incomplete UST-ID
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_Teilweise",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": "DE",  # Only LKZ, no ID
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - UST-ID only partially filled"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("teilweise" in text.lower() for text in fehler_texte)
        assert found_error, f"Expected error about partially filled UST-ID, got: {fehler_texte}"
        print(f"✓ Partially filled UST-ID correctly rejected: {fehler_texte}")
    
    def test_ustid_lkz_stimmt_nicht_mit_land_fehler(self, auth_headers):
        """
        Test: UST-LKZ not matching country should be an error
        Expected: Validation error about mismatched LKZ
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_LKZ_Mismatch",
                "ist_firma": True,
                "land": "Deutschland",  # Germany
                "umsatzsteuer_lkz": "AT",  # Austrian prefix - mismatch!
                "umsatzsteuer_id": "123456789",
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - UST-LKZ doesn't match country"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("stimmt nicht" in text.lower() or "überein" in text.lower() for text in fehler_texte)
        assert found_error, f"Expected error about mismatched LKZ, got: {fehler_texte}"
        print(f"✓ Mismatched UST-LKZ correctly rejected: {fehler_texte}")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
