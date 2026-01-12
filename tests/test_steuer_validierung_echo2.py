"""
Rohstoff Portal - Echo2 Steuer-Validierungslogik Tests
Tests for: POST /api/adressen/validieren - Business logic from Java __FS_Adress_Check.java

Test scenarios from review request:
1. Firma im Inland ohne UST-ID sollte Fehler zurückgeben
2. Firma im Inland mit Sonderschalter + Steuernummer sollte OK sein
3. Firma im EU-Ausland ohne UST-ID sollte Fehler zurückgeben
4. Firma im EU-Ausland mit korrekter UST-ID sollte OK sein
5. UST-Länderkürzel passt nicht zum Land sollte Fehler zurückgeben
6. Privatperson im Inland ohne Ausweis/Steuernummer sollte Fehler zurückgeben
7. Privatperson im Ausland ohne Ausweis sollte Fehler zurückgeben
8. Teilweise UST-ID (nur LKZ oder nur ID) sollte Fehler zurückgeben
9. Neue Adresse erstellen validiert automatisch und zeigt Fehler
"""

import pytest
import requests
import os
import uuid

# Use public URL for testing
BASE_URL = "https://commodityportal.preview.emergentagent.com"

# Test credentials (German field names)
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
    pytest.skip(f"Authentication failed - {response.status_code}: {response.text}")


@pytest.fixture(scope="module")
def auth_headers(auth_token):
    """Get headers with auth token"""
    return {"Authorization": f"Bearer {auth_token}"}


# ============================================================
# TEST 1: Firma im Inland ohne UST-ID sollte Fehler zurückgeben
# ============================================================
class TestFirmaInlandOhneUstId:
    """Test: FIRMA im Inland ohne UST-ID sollte Fehler zurückgeben"""
    
    def test_firma_inland_ohne_ustid_fehler(self, auth_headers):
        """
        Scenario: Firma in Deutschland ohne UST-ID und ohne Sonderschalter
        Expected: Validation error requiring 'firma_ohne_ustid' switch + Steuernummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Inland_Ohne_USTID",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - FIRMA in Germany without UST-ID"
        assert len(validierung["fehler"]) > 0, "Should have validation errors"
        
        # Check for specific error message about firma_ohne_ustid
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_ustid_error = any("Firma" in text and ("UST-ID" in text or "Steuernummer" in text) for text in fehler_texte)
        assert found_ustid_error, f"Expected error about 'Firma ohne UST-ID', got: {fehler_texte}"
        
        assert validierung["steuer_status"] == "FIRMA_INLAND"
        print(f"✓ TEST 1 PASSED: FIRMA Inland ohne UST-ID correctly rejected with errors: {fehler_texte}")


# ============================================================
# TEST 2: Firma im Inland mit Sonderschalter + Steuernummer sollte OK sein
# ============================================================
class TestFirmaInlandMitSonderschalter:
    """Test: FIRMA im Inland mit Sonderschalter + Steuernummer sollte OK sein"""
    
    def test_firma_inland_mit_sonderschalter_und_steuernummer_ok(self, auth_headers):
        """
        Scenario: Firma in Deutschland ohne UST-ID aber mit firma_ohne_ustid=True + Steuernummer
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Inland_Sonderschalter",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",  # Steuernummer required with Sonderschalter
                "firma_ohne_ustid": True,  # Sonderschalter active
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - FIRMA with Sonderschalter + Steuernummer. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "FIRMA_INLAND"
        print(f"✓ TEST 2 PASSED: FIRMA Inland mit Sonderschalter + Steuernummer correctly validated as OK")
    
    def test_firma_inland_mit_sonderschalter_ohne_steuernummer_fehler(self, auth_headers):
        """
        Scenario: Firma in Deutschland mit firma_ohne_ustid=True but NO Steuernummer
        Expected: Validation error - Steuernummer required when using Sonderschalter
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_Sonderschalter_Ohne_Steuernr",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,  # Missing!
                "firma_ohne_ustid": True,  # Sonderschalter active
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - Sonderschalter without Steuernummer"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("Steuernummer" in text for text in fehler_texte)
        assert found_error, f"Expected error about Steuernummer, got: {fehler_texte}"
        print(f"✓ TEST 2b PASSED: Sonderschalter ohne Steuernummer correctly rejected: {fehler_texte}")


# ============================================================
# TEST 3: Firma im EU-Ausland ohne UST-ID sollte Fehler zurückgeben
# ============================================================
class TestFirmaEUOhneUstId:
    """Test: FIRMA im EU-Ausland ohne UST-ID sollte Fehler zurückgeben"""
    
    def test_firma_eu_ohne_ustid_fehler(self, auth_headers):
        """
        Scenario: Firma in Austria (EU) without UST-ID
        Expected: Validation error requiring UST-ID for EU companies
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_EU_Ohne_USTID",
                "strasse": "Teststraße",
                "plz": "1010",
                "ort": "Wien",
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
        print(f"✓ TEST 3 PASSED: FIRMA EU ohne UST-ID correctly rejected with errors: {fehler_texte}")


# ============================================================
# TEST 4: Firma im EU-Ausland mit korrekter UST-ID sollte OK sein
# ============================================================
class TestFirmaEUMitUstId:
    """Test: FIRMA im EU-Ausland mit korrekter UST-ID sollte OK sein"""
    
    def test_firma_eu_mit_ustid_ok(self, auth_headers):
        """
        Scenario: Firma in Austria (EU) with correct UST-ID (AT prefix)
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_EU_Mit_USTID",
                "strasse": "Teststraße",
                "plz": "1010",
                "ort": "Wien",
                "ist_firma": True,
                "land": "Österreich",
                "umsatzsteuer_lkz": "AT",  # Correct prefix for Austria
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
        print(f"✓ TEST 4 PASSED: FIRMA EU mit UST-ID correctly validated as OK")


# ============================================================
# TEST 5: UST-Länderkürzel passt nicht zum Land sollte Fehler zurückgeben
# ============================================================
class TestUstLkzMismatch:
    """Test: UST-Länderkürzel passt nicht zum Land sollte Fehler zurückgeben"""
    
    def test_ustid_lkz_stimmt_nicht_mit_land_fehler(self, auth_headers):
        """
        Scenario: Firma in Germany with Austrian UST-ID prefix (AT instead of DE)
        Expected: Validation error about mismatched LKZ
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_LKZ_Mismatch",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
        print(f"✓ TEST 5 PASSED: Mismatched UST-LKZ correctly rejected: {fehler_texte}")
    
    def test_ustid_lkz_austria_in_france_fehler(self, auth_headers):
        """
        Scenario: Firma in France with Austrian UST-ID prefix
        Expected: Validation error about mismatched LKZ
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_LKZ_Mismatch_FR",
                "strasse": "Rue de Test",
                "plz": "75001",
                "ort": "Paris",
                "ist_firma": True,
                "land": "Frankreich",  # France
                "umsatzsteuer_lkz": "AT",  # Austrian prefix - mismatch!
                "umsatzsteuer_id": "U12345678",
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
        print(f"✓ TEST 5b PASSED: Austria LKZ in France correctly rejected: {fehler_texte}")


# ============================================================
# TEST 6: Privatperson im Inland ohne Ausweis/Steuernummer sollte Fehler zurückgeben
# ============================================================
class TestPrivatInlandOhneAusweis:
    """Test: Privatperson im Inland ohne Ausweis/Steuernummer sollte Fehler zurückgeben"""
    
    def test_privat_inland_ohne_ausweis_oder_steuernummer_fehler(self, auth_headers):
        """
        Scenario: Private person in Germany without ID or tax number
        Expected: Validation error requiring Ausweisnummer or Steuernummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Inland_Ohne_Ausweis",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
        print(f"✓ TEST 6 PASSED: PRIVAT Inland ohne Ausweis/Steuernummer correctly rejected: {fehler_texte}")
    
    def test_privat_inland_mit_steuernummer_ok(self, auth_headers):
        """
        Scenario: Private person in Germany with tax number (no ID needed)
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Inland_Mit_Steuernummer",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": False,  # PRIVAT
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",  # Has tax number
                "ausweis_nummer": None,
                "ausweis_ablauf": None,
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - PRIVAT inland with Steuernummer. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "PRIVAT_INLAND"
        print(f"✓ TEST 6b PASSED: PRIVAT Inland mit Steuernummer correctly validated as OK")
    
    def test_privat_inland_mit_ausweis_ok(self, auth_headers):
        """
        Scenario: Private person in Germany with ID (no tax number needed)
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Inland_Mit_Ausweis",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": False,  # PRIVAT
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": None,
                "ausweis_nummer": "PA123456789",  # Has ID
                "ausweis_ablauf": "31.12.2030",
                "firma_ohne_ustid": False,
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - PRIVAT inland with Ausweis. Errors: {validierung.get('fehler', [])}"
        assert validierung["steuer_status"] == "PRIVAT_INLAND"
        print(f"✓ TEST 6c PASSED: PRIVAT Inland mit Ausweis correctly validated as OK")


# ============================================================
# TEST 7: Privatperson im Ausland ohne Ausweis sollte Fehler zurückgeben
# ============================================================
class TestPrivatAuslandOhneAusweis:
    """Test: Privatperson im Ausland ohne Ausweis sollte Fehler zurückgeben"""
    
    def test_privat_ausland_ohne_ausweis_fehler(self, auth_headers):
        """
        Scenario: Private person abroad (Austria) without ID
        Expected: Validation error requiring Ausweisnummer
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Ausland_Ohne_Ausweis",
                "strasse": "Teststraße",
                "plz": "1010",
                "ort": "Wien",
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
        
        # Note: steuer_status might be PRIVAT_EU or PRIVAT_AUSLAND depending on implementation
        print(f"✓ TEST 7 PASSED: PRIVAT Ausland ohne Ausweis correctly rejected: {fehler_texte}")
    
    def test_privat_ausland_mit_ausweis_ok(self, auth_headers):
        """
        Scenario: Private person abroad (Austria) with ID
        Expected: Validation passes
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Ausland_Mit_Ausweis",
                "strasse": "Teststraße",
                "plz": "1010",
                "ort": "Wien",
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
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == True, f"Should be valid - PRIVAT abroad with ID. Errors: {validierung.get('fehler', [])}"
        print(f"✓ TEST 7b PASSED: PRIVAT Ausland mit Ausweis correctly validated as OK")


# ============================================================
# TEST 8: Teilweise UST-ID (nur LKZ oder nur ID) sollte Fehler zurückgeben
# ============================================================
class TestTeilweiseUstId:
    """Test: Teilweise UST-ID (nur LKZ oder nur ID) sollte Fehler zurückgeben"""
    
    def test_ustid_nur_lkz_fehler(self, auth_headers):
        """
        Scenario: Only UST-LKZ filled, no UST-ID number
        Expected: Validation error about incomplete UST-ID
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_Nur_LKZ",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
        print(f"✓ TEST 8a PASSED: Only LKZ (no ID) correctly rejected: {fehler_texte}")
    
    def test_ustid_nur_id_fehler(self, auth_headers):
        """
        Scenario: Only UST-ID number filled, no LKZ
        Expected: Validation error about incomplete UST-ID
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_USTID_Nur_ID",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,  # No LKZ
                "umsatzsteuer_id": "123456789",  # Only ID
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
        print(f"✓ TEST 8b PASSED: Only ID (no LKZ) correctly rejected: {fehler_texte}")


# ============================================================
# TEST 9: Neue Adresse erstellen validiert automatisch und zeigt Fehler
# ============================================================
class TestAdresseErstellenMitValidierung:
    """Test: POST /api/adressen validates automatically and shows errors"""
    
    def test_create_adresse_with_validation_error_rejected(self, auth_headers):
        """
        Scenario: Creating an address with validation errors (Firma without UST-ID)
        Expected: 400 error with validation details
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": f"TEST_Invalid_Adresse_{uuid.uuid4().hex[:8]}",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
        assert response.status_code == 400, f"Expected 400 for invalid address, got {response.status_code}: {response.text}"
        data = response.json()
        assert "detail" in data
        assert "fehler" in data["detail"] or "validierung" in data["detail"]
        print(f"✓ TEST 9a PASSED: Invalid address correctly rejected with 400: {data['detail'].get('message', data['detail'])}")
    
    def test_create_adresse_with_valid_data_ok(self, auth_headers):
        """
        Scenario: Creating an address with valid data (Firma with UST-ID)
        Expected: 200 with created address
        """
        test_name = f"TEST_Valid_Adresse_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
        print(f"✓ TEST 9b PASSED: Valid address created successfully: {data['data']['name1']} (KDNR: {data['data']['kdnr']})")


# ============================================================
# ADDITIONAL TESTS: Sonderschalter-Logik
# ============================================================
class TestSonderschalterLogik:
    """Additional tests for Sonderschalter (special switches) logic"""
    
    def test_beide_sonderschalter_gleichzeitig_fehler(self, auth_headers):
        """
        Scenario: Both Sonderschalter active at the same time
        Expected: Validation error
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Beide_Sonderschalter",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
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
    
    def test_firma_mit_ustid_und_sonderschalter_fehler(self, auth_headers):
        """
        Scenario: Firma with UST-ID but also firma_ohne_ustid=True
        Expected: Validation error - contradictory settings
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Firma_USTID_Mit_Sonderschalter",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": "DE",
                "umsatzsteuer_id": "123456789",  # Has UST-ID
                "steuernummer": None,
                "firma_ohne_ustid": True,  # But also has Sonderschalter - contradiction!
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - UST-ID with firma_ohne_ustid"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("UST-ID" in text and "nicht gesetzt" in text for text in fehler_texte)
        assert found_error, f"Expected error about contradictory settings, got: {fehler_texte}"
        print(f"✓ Firma with UST-ID + Sonderschalter correctly rejected: {fehler_texte}")
    
    def test_privat_mit_firma_sonderschalter_fehler(self, auth_headers):
        """
        Scenario: Private person with firma_ohne_ustid=True
        Expected: Validation error - wrong Sonderschalter for private person
        """
        response = requests.post(
            f"{BASE_URL}/api/adressen/validieren",
            headers=auth_headers,
            json={
                "name1": "TEST_Privat_Mit_Firma_Sonderschalter",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": False,  # PRIVAT
                "land": "Deutschland",
                "umsatzsteuer_lkz": None,
                "umsatzsteuer_id": None,
                "steuernummer": "12/345/67890",
                "ausweis_nummer": "PA123456789",
                "firma_ohne_ustid": True,  # Wrong Sonderschalter for PRIVAT!
                "privat_mit_ustid": False
            }
        )
        assert response.status_code == 200
        data = response.json()
        
        validierung = data["validierung"]
        assert validierung["ist_gueltig"] == False, "Should be invalid - PRIVAT with firma_ohne_ustid"
        
        fehler_texte = [f["meldung"] for f in validierung["fehler"]]
        found_error = any("PRIVAT" in text and "FIRMA ohne UST-ID" in text for text in fehler_texte)
        assert found_error, f"Expected error about wrong Sonderschalter, got: {fehler_texte}"
        print(f"✓ PRIVAT with firma_ohne_ustid correctly rejected: {fehler_texte}")


# ============================================================
# ADDITIONAL TESTS: Default-Werte für Sperren
# ============================================================
class TestDefaultSperren:
    """Test that wareneingang_sperren and warenausgang_sperren default to True"""
    
    def test_create_adresse_default_sperren_true(self, auth_headers):
        """
        Scenario: Create address without specifying sperren values
        Expected: wareneingang_sperren and warenausgang_sperren should default to True
        """
        test_name = f"TEST_Default_Sperren_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Berlin",
                "ist_firma": True,
                "land": "Deutschland",
                "umsatzsteuer_lkz": "DE",
                "umsatzsteuer_id": "123456789",
                "adresstyp": 1,
                "aktiv": True
                # NOT specifying wareneingang_sperren or warenausgang_sperren
            }
        )
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        data = response.json()
        assert data["success"] == True
        
        # Check default values
        created_adresse = data["data"]
        assert created_adresse.get("wareneingang_sperren") == True, \
            f"wareneingang_sperren should default to True, got: {created_adresse.get('wareneingang_sperren')}"
        assert created_adresse.get("warenausgang_sperren") == True, \
            f"warenausgang_sperren should default to True, got: {created_adresse.get('warenausgang_sperren')}"
        
        print(f"✓ Default Sperren correctly set to True: wareneingang_sperren={created_adresse.get('wareneingang_sperren')}, warenausgang_sperren={created_adresse.get('warenausgang_sperren')}")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
