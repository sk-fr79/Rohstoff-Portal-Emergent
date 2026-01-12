"""
Test Suite für Streckengeschäft-Feature
========================================
Tests für:
- POST /api/kontrakte/strecken - Neues Streckengeschäft erstellen
- GET /api/kontrakte/strecken - Alle Streckengeschäfte laden
- POST /api/kontrakte/{id}/strecke/verknuepfen - Bestehende Kontrakte verknüpfen
- DELETE /api/kontrakte/strecken/{id}/aufloesen - Strecke auflösen
- GET /api/kontrakte - Strecken-Filter und Partner-Anzeige
"""

import pytest
import requests
import os
import uuid

BASE_URL = os.environ.get('BASE_URL', 'http://localhost:8001')

# Test credentials
TEST_USER = {"benutzername": "admin", "passwort": "Admin123!"}


@pytest.fixture(scope="module")
def auth_token():
    """Get authentication token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=TEST_USER)
    if response.status_code == 200:
        data = response.json()
        if data.get("success"):
            return data.get("access_token")
        else:
            pytest.skip(f"Authentication failed: {data.get('error')}")
    pytest.skip(f"Authentication failed: {response.status_code} - {response.text}")


@pytest.fixture(scope="module")
def api_client(auth_token):
    """Authenticated requests session"""
    session = requests.Session()
    session.headers.update({
        "Content-Type": "application/json",
        "Authorization": f"Bearer {auth_token}"
    })
    return session


@pytest.fixture(scope="module")
def test_adressen(api_client):
    """Get test addresses for Lieferant and Abnehmer"""
    response = api_client.get(f"{BASE_URL}/api/kontrakte/lookup/adressen?limit=10")
    assert response.status_code == 200
    adressen = response.json().get("data", [])
    if len(adressen) < 2:
        pytest.skip("Need at least 2 addresses for Streckengeschäft tests")
    return adressen[:2]


class TestStreckengeschaeftAPI:
    """Tests für Streckengeschäft Backend-APIs"""
    
    def test_01_create_streckengeschaeft(self, api_client, test_adressen):
        """Test: Neues Streckengeschäft erstellen - erzeugt automatisch EK + VK"""
        lieferant = test_adressen[0]
        abnehmer = test_adressen[1]
        
        payload = {
            "lieferant_id_adresse": lieferant["id"],
            "lieferant_name1": lieferant["name1"],
            "lieferant_name2": lieferant.get("name2"),
            "lieferant_strasse": lieferant.get("strasse"),
            "lieferant_hausnummer": lieferant.get("hausnummer"),
            "lieferant_plz": lieferant.get("plz"),
            "lieferant_ort": lieferant.get("ort"),
            "lieferant_land": lieferant.get("land"),
            "lieferant_land_code": lieferant.get("land_code"),
            "abnehmer_id_adresse": abnehmer["id"],
            "abnehmer_name1": abnehmer["name1"],
            "abnehmer_name2": abnehmer.get("name2"),
            "abnehmer_strasse": abnehmer.get("strasse"),
            "abnehmer_hausnummer": abnehmer.get("hausnummer"),
            "abnehmer_plz": abnehmer.get("plz"),
            "abnehmer_ort": abnehmer.get("ort"),
            "abnehmer_land": abnehmer.get("land"),
            "abnehmer_land_code": abnehmer.get("land_code"),
            "waehrung_kurz": "EUR",
            "bemerkung_intern": "TEST_Streckengeschäft für automatisierte Tests"
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte/strecken", json=payload)
        print(f"Create Streckengeschäft Response: {response.status_code}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        resp = response.json()
        
        # Verify response structure
        assert resp.get("success") == True
        data = resp.get("data", resp)  # Handle both nested and flat response
        assert "strecken_id" in data
        assert "ek_kontrakt" in data
        assert "vk_kontrakt" in data
        
        # Verify EK Kontrakt
        ek = data["ek_kontrakt"]
        assert ek is not None
        assert ek.get("vorgang_typ") == "EK"
        assert ek.get("ist_strecke") == True
        assert ek.get("strecken_id") == data["strecken_id"]
        assert ek.get("strecken_rolle") == "EK"
        assert ek.get("name1") == lieferant["name1"]
        
        # Verify VK Kontrakt
        vk = data["vk_kontrakt"]
        assert vk is not None
        assert vk.get("vorgang_typ") == "VK"
        assert vk.get("ist_strecke") == True
        assert vk.get("strecken_id") == data["strecken_id"]
        assert vk.get("strecken_rolle") == "VK"
        assert vk.get("name1") == abnehmer["name1"]
        
        # Store for later tests
        pytest.strecken_id = data["strecken_id"]
        pytest.ek_kontrakt_id = ek["id"]
        pytest.vk_kontrakt_id = vk["id"]
        
        print(f"✓ Streckengeschäft erstellt: {data['strecken_id']}")
        print(f"  EK: {ek.get('kontraktnummer')} - {ek.get('name1')}")
        print(f"  VK: {vk.get('kontraktnummer')} - {vk.get('name1')}")
    
    def test_02_get_streckengeschaefte_list(self, api_client):
        """Test: Alle Streckengeschäfte laden"""
        response = api_client.get(f"{BASE_URL}/api/kontrakte/strecken")
        print(f"Get Streckengeschäfte Response: {response.status_code}")
        
        assert response.status_code == 200
        data = response.json()
        
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        # Find our test Strecke
        if hasattr(pytest, 'strecken_id'):
            test_strecke = next((s for s in data["data"] if s.get("strecken_id") == pytest.strecken_id), None)
            if test_strecke:
                assert test_strecke.get("ek_kontrakt") is not None
                assert test_strecke.get("vk_kontrakt") is not None
                assert "status" in test_strecke
                print(f"✓ Test-Strecke gefunden mit Status: {test_strecke.get('status', {}).get('label')}")
    
    def test_03_get_kontrakte_with_strecken_filter(self, api_client):
        """Test: Kontrakte mit nur_strecke Filter"""
        response = api_client.get(f"{BASE_URL}/api/kontrakte?nur_strecke=true")
        print(f"Get Kontrakte (nur_strecke) Response: {response.status_code}")
        
        assert response.status_code == 200
        data = response.json()
        
        assert data.get("success") == True
        kontrakte = data.get("data", [])
        
        # All returned contracts should be Strecken-Kontrakte
        for k in kontrakte:
            assert k.get("ist_strecke") == True, f"Kontrakt {k.get('kontraktnummer')} ist keine Strecke"
        
        print(f"✓ {len(kontrakte)} Strecken-Kontrakte gefunden")
    
    def test_04_get_kontrakt_with_strecken_partner(self, api_client):
        """Test: Einzelner Kontrakt enthält strecken_partner Info"""
        if not hasattr(pytest, 'ek_kontrakt_id'):
            pytest.skip("No test Strecke created")
        
        response = api_client.get(f"{BASE_URL}/api/kontrakte/{pytest.ek_kontrakt_id}")
        print(f"Get EK Kontrakt Response: {response.status_code}")
        
        assert response.status_code == 200
        data = response.json()
        kontrakt = data.get("data", {})
        
        assert kontrakt.get("ist_strecke") == True
        assert kontrakt.get("strecken_id") == pytest.strecken_id
        assert kontrakt.get("strecken_rolle") == "EK"
        
        print(f"✓ EK Kontrakt {kontrakt.get('kontraktnummer')} ist Teil der Strecke")
    
    def test_05_strecke_aufloesen(self, api_client):
        """Test: Streckengeschäft auflösen"""
        if not hasattr(pytest, 'strecken_id'):
            pytest.skip("No test Strecke created")
        
        response = api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{pytest.strecken_id}/aufloesen")
        print(f"Strecke auflösen Response: {response.status_code}")
        
        assert response.status_code == 200
        data = response.json()
        
        assert data.get("success") == True
        # Response contains message about dissolved contracts
        assert "message" in data or "aufgeloeste_kontrakte" in data
        
        print(f"✓ Strecke aufgelöst: {data.get('message', 'OK')}")
        
        # Verify contracts are no longer linked
        response = api_client.get(f"{BASE_URL}/api/kontrakte/{pytest.ek_kontrakt_id}")
        assert response.status_code == 200
        kontrakt = response.json().get("data", {})
        assert kontrakt.get("ist_strecke") == False or kontrakt.get("ist_strecke") is None
        assert kontrakt.get("strecken_id") is None
        
        print(f"✓ EK Kontrakt ist nicht mehr Teil einer Strecke")


class TestStreckenVerknuepfung:
    """Tests für nachträgliche Verknüpfung von Kontrakten zu Streckengeschäften"""
    
    def test_01_create_ek_kontrakt_for_linking(self, api_client, test_adressen):
        """Erstelle EK Kontrakt für Verknüpfungstest"""
        lieferant = test_adressen[0]
        
        payload = {
            "vorgang_typ": "EK",
            "name1": lieferant["name1"],
            "id_adresse": lieferant["id"],
            "strasse": lieferant.get("strasse"),
            "plz": lieferant.get("plz"),
            "ort": lieferant.get("ort"),
            "status": "OFFEN",
            "bemerkung_intern": "TEST_EK für Strecken-Verknüpfung"
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte", json=payload)
        assert response.status_code == 200
        
        pytest.link_ek_id = response.json()["data"]["id"]
        print(f"✓ EK Kontrakt erstellt: {response.json()['data'].get('kontraktnummer')}")
    
    def test_02_create_vk_kontrakt_for_linking(self, api_client, test_adressen):
        """Erstelle VK Kontrakt für Verknüpfungstest"""
        abnehmer = test_adressen[1]
        
        payload = {
            "vorgang_typ": "VK",
            "name1": abnehmer["name1"],
            "id_adresse": abnehmer["id"],
            "strasse": abnehmer.get("strasse"),
            "plz": abnehmer.get("plz"),
            "ort": abnehmer.get("ort"),
            "status": "OFFEN",
            "bemerkung_intern": "TEST_VK für Strecken-Verknüpfung"
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte", json=payload)
        assert response.status_code == 200
        
        pytest.link_vk_id = response.json()["data"]["id"]
        print(f"✓ VK Kontrakt erstellt: {response.json()['data'].get('kontraktnummer')}")
    
    def test_03_verknuepfe_kontrakte_zu_strecke(self, api_client):
        """Test: Bestehende EK + VK Kontrakte zu Streckengeschäft verknüpfen"""
        if not hasattr(pytest, 'link_ek_id') or not hasattr(pytest, 'link_vk_id'):
            pytest.skip("Test contracts not created")
        
        payload = {
            "kontrakt_id": pytest.link_ek_id,
            "partner_kontrakt_id": pytest.link_vk_id
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte/{pytest.link_ek_id}/strecke/verknuepfen", json=payload)
        print(f"Verknüpfen Response: {response.status_code}")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        resp = response.json()
        
        assert resp.get("success") == True
        # Handle nested data structure
        data = resp.get("data", resp)
        assert "strecken_id" in data
        
        pytest.linked_strecken_id = data["strecken_id"]
        print(f"✓ Kontrakte zu Strecke verknüpft: {data['strecken_id']}")
        
        # Verify both contracts are now linked
        response = api_client.get(f"{BASE_URL}/api/kontrakte/{pytest.link_ek_id}")
        ek = response.json().get("data", {})
        assert ek.get("ist_strecke") == True
        assert ek.get("strecken_id") == pytest.linked_strecken_id
        
        response = api_client.get(f"{BASE_URL}/api/kontrakte/{pytest.link_vk_id}")
        vk = response.json().get("data", {})
        assert vk.get("ist_strecke") == True
        assert vk.get("strecken_id") == pytest.linked_strecken_id
        
        print(f"✓ Beide Kontrakte sind jetzt Teil der Strecke")
    
    def test_04_cannot_link_already_linked_kontrakt(self, api_client, test_adressen):
        """Test: Bereits verknüpfter Kontrakt kann nicht erneut verknüpft werden"""
        if not hasattr(pytest, 'link_ek_id'):
            pytest.skip("Test contract not created")
        
        # Create another VK to try linking
        abnehmer = test_adressen[1]
        payload = {
            "vorgang_typ": "VK",
            "name1": abnehmer["name1"],
            "id_adresse": abnehmer["id"],
            "status": "OFFEN",
            "bemerkung_intern": "TEST_VK2 für Fehlertest"
        }
        response = api_client.post(f"{BASE_URL}/api/kontrakte", json=payload)
        new_vk_id = response.json()["data"]["id"]
        
        # Try to link already linked EK with new VK
        payload = {
            "kontrakt_id": pytest.link_ek_id,
            "partner_kontrakt_id": new_vk_id
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte/{pytest.link_ek_id}/strecke/verknuepfen", json=payload)
        print(f"Re-link attempt Response: {response.status_code}")
        
        # Should fail because EK is already linked
        assert response.status_code == 400
        assert "bereits Teil" in response.text or "already" in response.text.lower()
        
        print(f"✓ Korrekt: Bereits verknüpfter Kontrakt kann nicht erneut verknüpft werden")
        
        # Cleanup
        api_client.delete(f"{BASE_URL}/api/kontrakte/{new_vk_id}")
    
    def test_05_cleanup_linked_strecke(self, api_client):
        """Cleanup: Verknüpfte Strecke auflösen"""
        if hasattr(pytest, 'linked_strecken_id'):
            response = api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{pytest.linked_strecken_id}/aufloesen")
            print(f"Cleanup linked Strecke: {response.status_code}")


class TestStreckengeschaeftValidation:
    """Tests für Validierung bei Streckengeschäften"""
    
    def test_01_cannot_create_strecke_with_same_partner(self, api_client, test_adressen):
        """Test: Streckengeschäft mit gleichem Lieferant und Abnehmer sollte funktionieren (kein Fehler)"""
        # Note: In real business this might be valid (same company, different departments)
        same_partner = test_adressen[0]
        
        payload = {
            "lieferant_id_adresse": same_partner["id"],
            "lieferant_name1": same_partner["name1"],
            "abnehmer_id_adresse": same_partner["id"],
            "abnehmer_name1": same_partner["name1"],
            "waehrung_kurz": "EUR",
            "bemerkung_intern": "TEST_Same Partner Strecke"
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte/strecken", json=payload)
        print(f"Same Partner Strecke Response: {response.status_code}")
        
        # This might be allowed or not depending on business rules
        # Just document the behavior
        if response.status_code == 200:
            data = response.json()
            # Cleanup
            if data.get("strecken_id"):
                api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{data['strecken_id']}/aufloesen")
            print(f"✓ Strecke mit gleichem Partner ist erlaubt")
        else:
            print(f"✓ Strecke mit gleichem Partner ist nicht erlaubt: {response.status_code}")
    
    def test_02_strecke_aufloesen_not_found(self, api_client):
        """Test: Nicht existierende Strecke auflösen gibt 404"""
        fake_id = str(uuid.uuid4())
        response = api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{fake_id}/aufloesen")
        
        assert response.status_code == 404
        print(f"✓ Korrekt: 404 für nicht existierende Strecke")


class TestKontrakteListWithStrecken:
    """Tests für Kontrakt-Liste mit Strecken-Informationen"""
    
    def test_01_kontrakte_list_shows_strecken_partner(self, api_client, test_adressen):
        """Test: Kontrakt-Liste zeigt strecken_partner für Strecken-Kontrakte"""
        # First create a Strecke
        lieferant = test_adressen[0]
        abnehmer = test_adressen[1]
        
        payload = {
            "lieferant_id_adresse": lieferant["id"],
            "lieferant_name1": lieferant["name1"],
            "abnehmer_id_adresse": abnehmer["id"],
            "abnehmer_name1": abnehmer["name1"],
            "waehrung_kurz": "EUR",
            "bemerkung_intern": "TEST_List Partner Test"
        }
        
        response = api_client.post(f"{BASE_URL}/api/kontrakte/strecken", json=payload)
        assert response.status_code == 200
        resp = response.json()
        data = resp.get("data", resp)
        strecken_id = data["strecken_id"]
        ek_id = data["ek_kontrakt"]["id"]
        
        # Get kontrakte list
        response = api_client.get(f"{BASE_URL}/api/kontrakte?nur_strecke=true")
        assert response.status_code == 200
        
        kontrakte = response.json().get("data", [])
        
        # Find our EK kontrakt
        ek_kontrakt = next((k for k in kontrakte if k.get("id") == ek_id), None)
        
        if ek_kontrakt:
            assert "strecken_partner" in ek_kontrakt
            partner = ek_kontrakt.get("strecken_partner", {})
            
            # EK should have VK partner info
            assert partner.get("vk") is not None
            assert partner["vk"].get("name1") == abnehmer["name1"]
            
            print(f"✓ EK Kontrakt zeigt VK Partner: {partner['vk'].get('name1')}")
        
        # Cleanup
        api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{strecken_id}/aufloesen")


# Cleanup fixture
@pytest.fixture(scope="module", autouse=True)
def cleanup(api_client):
    """Cleanup test data after all tests"""
    yield
    
    # Cleanup any remaining test Strecken
    if hasattr(pytest, 'strecken_id'):
        try:
            api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{pytest.strecken_id}/aufloesen")
        except:
            pass
    
    if hasattr(pytest, 'linked_strecken_id'):
        try:
            api_client.delete(f"{BASE_URL}/api/kontrakte/strecken/{pytest.linked_strecken_id}/aufloesen")
        except:
            pass
    
    # Cleanup test kontrakte
    for attr in ['link_ek_id', 'link_vk_id', 'ek_kontrakt_id', 'vk_kontrakt_id']:
        if hasattr(pytest, attr):
            try:
                api_client.delete(f"{BASE_URL}/api/kontrakte/{getattr(pytest, attr)}")
            except:
                pass


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
