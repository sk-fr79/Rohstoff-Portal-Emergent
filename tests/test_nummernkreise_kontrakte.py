"""
Test Suite for Nummernkreise and Kontrakte Lookup APIs
Tests:
- Nummernkreise CRUD operations
- Nummernkreise initialization
- Benutzer lookup API for Sachbearbeiter/Händler
- Adressen lookup API for Vertragspartner
- Kontrakt creation with automatic Kontraktnummer from Nummernkreis
"""

import pytest
import requests
import os
import uuid

BASE_URL = "http://localhost:8001"

# Test credentials
ADMIN_USER = "admin"
ADMIN_PASSWORD = "Admin123!"
WAAGE_USER = "waage"
WAAGE_PASSWORD = "Waage!123"


@pytest.fixture(scope="module")
def admin_session():
    """Get authenticated admin session"""
    session = requests.Session()
    session.headers.update({"Content-Type": "application/json"})
    
    response = session.post(f"{BASE_URL}/api/auth/login", json={
        "benutzername": ADMIN_USER,
        "passwort": ADMIN_PASSWORD
    })
    
    if response.status_code != 200:
        pytest.skip(f"Admin login failed: {response.status_code} - {response.text}")
    
    data = response.json()
    token = data.get("token") or data.get("access_token")
    if token:
        session.headers.update({"Authorization": f"Bearer {token}"})
    
    return session


@pytest.fixture(scope="module")
def waage_session():
    """Get authenticated waage session"""
    session = requests.Session()
    session.headers.update({"Content-Type": "application/json"})
    
    response = session.post(f"{BASE_URL}/api/auth/login", json={
        "benutzername": WAAGE_USER,
        "passwort": WAAGE_PASSWORD
    })
    
    if response.status_code != 200:
        pytest.skip(f"Waage login failed: {response.status_code} - {response.text}")
    
    data = response.json()
    token = data.get("token") or data.get("access_token")
    if token:
        session.headers.update({"Authorization": f"Bearer {token}"})
    
    return session


# ========================== NUMMERNKREISE TESTS ==========================

class TestNummernkreiseAPI:
    """Tests for Nummernkreise (Number Range) management"""
    
    def test_get_nummernkreise_list(self, admin_session):
        """Test GET /api/system/nummernkreise - List all number ranges"""
        response = admin_session.get(f"{BASE_URL}/api/system/nummernkreise")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        print(f"✓ Found {len(data['data'])} Nummernkreise")
        
        # Check structure of first item if exists
        if data["data"]:
            nk = data["data"][0]
            assert "id" in nk
            assert "modul" in nk
            assert "bezeichnung" in nk
            print(f"  First Nummernkreis: {nk.get('bezeichnung')} ({nk.get('modul')})")
    
    def test_get_nummernkreise_filtered_by_modul(self, admin_session):
        """Test GET /api/system/nummernkreise?modul=kontrakte - Filter by module"""
        response = admin_session.get(f"{BASE_URL}/api/system/nummernkreise", params={"modul": "kontrakte"})
        
        assert response.status_code == 200
        
        data = response.json()
        assert data.get("success") == True
        
        # All returned items should be for kontrakte module
        for nk in data["data"]:
            assert nk.get("modul") == "kontrakte", f"Expected modul=kontrakte, got {nk.get('modul')}"
        
        print(f"✓ Found {len(data['data'])} Nummernkreise for 'kontrakte' module")
    
    def test_initialize_default_nummernkreise(self, admin_session):
        """Test POST /api/system/nummernkreise/initialisieren - Initialize default number ranges"""
        response = admin_session.post(f"{BASE_URL}/api/system/nummernkreise/initialisieren")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "message" in data
        
        print(f"✓ Initialization result: {data.get('message')}")
    
    def test_get_naechste_nummer_preview(self, admin_session):
        """Test POST /api/system/nummernkreise/naechste-nummer - Preview next number"""
        response = admin_session.post(
            f"{BASE_URL}/api/system/nummernkreise/naechste-nummer",
            params={"modul": "kontrakte", "filter_typ": "EK"}
        )
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "nummer" in data
        
        nummer = data.get("nummer")
        print(f"✓ Next EK Kontraktnummer preview: {nummer}")
        
        # Should start with EK prefix (based on actual Nummernkreis data)
        assert nummer.startswith("EK"), f"Expected EK prefix, got {nummer}"
    
    def test_get_naechste_nummer_vk(self, admin_session):
        """Test next number for VK (Verkauf) contracts"""
        response = admin_session.post(
            f"{BASE_URL}/api/system/nummernkreise/naechste-nummer",
            params={"modul": "kontrakte", "filter_typ": "VK"}
        )
        
        assert response.status_code == 200
        
        data = response.json()
        assert data.get("success") == True
        
        nummer = data.get("nummer")
        print(f"✓ Next VK Kontraktnummer preview: {nummer}")
        
        # Should start with VK prefix (based on actual Nummernkreis data)
        assert nummer.startswith("VK"), f"Expected VK prefix, got {nummer}"
    
    def test_get_single_nummernkreis(self, admin_session):
        """Test GET /api/system/nummernkreise/{id} - Get single number range"""
        # First get list to find an ID
        list_response = admin_session.get(f"{BASE_URL}/api/system/nummernkreise")
        assert list_response.status_code == 200
        
        data = list_response.json()
        if not data["data"]:
            pytest.skip("No Nummernkreise available to test")
        
        nk_id = data["data"][0]["id"]
        
        # Get single item
        response = admin_session.get(f"{BASE_URL}/api/system/nummernkreise/{nk_id}")
        
        assert response.status_code == 200
        
        single_data = response.json()
        assert single_data.get("success") == True
        assert single_data["data"]["id"] == nk_id
        
        print(f"✓ Retrieved Nummernkreis: {single_data['data'].get('bezeichnung')}")
    
    def test_update_nummernkreis(self, admin_session):
        """Test PUT /api/system/nummernkreise/{id} - Update number range"""
        # First get list to find an ID
        list_response = admin_session.get(f"{BASE_URL}/api/system/nummernkreise")
        assert list_response.status_code == 200
        
        data = list_response.json()
        if not data["data"]:
            pytest.skip("No Nummernkreise available to test")
        
        nk_id = data["data"][0]["id"]
        original_beschreibung = data["data"][0].get("beschreibung", "")
        
        # Update with new description
        new_beschreibung = f"Test Update {uuid.uuid4().hex[:8]}"
        response = admin_session.put(
            f"{BASE_URL}/api/system/nummernkreise/{nk_id}",
            json={"beschreibung": new_beschreibung}
        )
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        updated_data = response.json()
        assert updated_data.get("success") == True
        assert updated_data["data"]["beschreibung"] == new_beschreibung
        
        print(f"✓ Updated Nummernkreis description to: {new_beschreibung}")
        
        # Restore original
        admin_session.put(
            f"{BASE_URL}/api/system/nummernkreise/{nk_id}",
            json={"beschreibung": original_beschreibung}
        )


# ========================== BENUTZER LOOKUP TESTS ==========================

class TestBenutzerLookupAPI:
    """Tests for Benutzer (User) lookup for Sachbearbeiter/Händler selection"""
    
    def test_get_benutzer_lookup(self, admin_session):
        """Test GET /api/kontrakte/lookup/benutzer - Get users for dropdown"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/benutzer")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        print(f"✓ Found {len(data['data'])} Benutzer for selection")
        
        # Check structure
        if data["data"]:
            user = data["data"][0]
            assert "id" in user
            assert "name" in user
            print(f"  First user: {user.get('name')} (email: {user.get('email')})")
    
    def test_benutzer_lookup_has_required_fields(self, admin_session):
        """Test that benutzer lookup returns all required fields"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/benutzer")
        
        assert response.status_code == 200
        
        data = response.json()
        
        for user in data["data"]:
            # Required fields for dropdown
            assert "id" in user, "Missing 'id' field"
            assert "name" in user, "Missing 'name' field"
            # Optional but expected fields
            assert "email" in user or user.get("email") is None
            assert "telefon" in user or user.get("telefon") is None
        
        print(f"✓ All {len(data['data'])} users have required fields")
    
    def test_benutzer_lookup_with_waage_user(self, waage_session):
        """Test that non-admin users can also access benutzer lookup"""
        response = waage_session.get(f"{BASE_URL}/api/kontrakte/lookup/benutzer")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        print(f"✓ Waage user can access benutzer lookup ({len(data['data'])} users)")


# ========================== ADRESSEN LOOKUP TESTS ==========================

class TestAdressenLookupAPI:
    """Tests for Adressen (Address) lookup for Vertragspartner selection"""
    
    def test_get_adressen_lookup(self, admin_session):
        """Test GET /api/kontrakte/lookup/adressen - Get addresses for dropdown"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/adressen")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        print(f"✓ Found {len(data['data'])} Adressen for selection")
        
        # Check structure
        if data["data"]:
            addr = data["data"][0]
            assert "id" in addr
            assert "name1" in addr
            print(f"  First address: {addr.get('name1')} ({addr.get('plz')} {addr.get('ort')})")
    
    def test_adressen_lookup_with_search(self, admin_session):
        """Test GET /api/kontrakte/lookup/adressen?suche=... - Search addresses"""
        # First get all to find a search term
        all_response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/adressen")
        assert all_response.status_code == 200
        
        all_data = all_response.json()
        if not all_data["data"]:
            pytest.skip("No addresses available to test search")
        
        # Use first address name as search term
        search_term = all_data["data"][0].get("name1", "")[:5]
        
        response = admin_session.get(
            f"{BASE_URL}/api/kontrakte/lookup/adressen",
            params={"suche": search_term, "limit": 50}
        )
        
        assert response.status_code == 200
        
        data = response.json()
        assert data.get("success") == True
        
        print(f"✓ Search for '{search_term}' returned {len(data['data'])} results")
    
    def test_adressen_lookup_has_required_fields(self, admin_session):
        """Test that adressen lookup returns all required fields for Kontrakt form"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/adressen")
        
        assert response.status_code == 200
        
        data = response.json()
        
        for addr in data["data"]:
            # Required fields for Kontrakt form
            assert "id" in addr, "Missing 'id' field"
            assert "name1" in addr, "Missing 'name1' field"
            # Address fields
            assert "strasse" in addr or addr.get("strasse") is None
            assert "plz" in addr or addr.get("plz") is None
            assert "ort" in addr or addr.get("ort") is None
            # Contact fields
            assert "telefon" in addr or addr.get("telefon") is None
            assert "email" in addr or addr.get("email") is None
            # Ansprechpartner array
            assert "ansprechpartner" in addr
        
        print(f"✓ All {len(data['data'])} addresses have required fields")
    
    def test_adressen_lookup_includes_ansprechpartner(self, admin_session):
        """Test that adressen lookup includes ansprechpartner array"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/adressen")
        
        assert response.status_code == 200
        
        data = response.json()
        
        addresses_with_ap = 0
        for addr in data["data"]:
            assert "ansprechpartner" in addr
            assert isinstance(addr["ansprechpartner"], list)
            if addr["ansprechpartner"]:
                addresses_with_ap += 1
        
        print(f"✓ {addresses_with_ap}/{len(data['data'])} addresses have Ansprechpartner")


# ========================== KONTRAKT CREATION WITH NUMMERNKREIS ==========================

class TestKontraktWithNummernkreis:
    """Tests for Kontrakt creation with automatic number from Nummernkreis"""
    
    def test_create_ek_kontrakt_auto_nummer(self, admin_session):
        """Test creating EK Kontrakt with automatic Kontraktnummer"""
        # Create minimal EK Kontrakt
        kontrakt_data = {
            "vorgang_typ": "EK",
            "name1": f"TEST_Lieferant_{uuid.uuid4().hex[:8]}",
            "status": "OFFEN",
            "waehrung_kurz": "EUR"
        }
        
        response = admin_session.post(f"{BASE_URL}/api/kontrakte", json=kontrakt_data)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        kontrakt = data["data"]
        assert "kontraktnummer" in kontrakt
        assert kontrakt["kontraktnummer"].startswith("EKK"), f"Expected EKK prefix, got {kontrakt['kontraktnummer']}"
        
        print(f"✓ Created EK Kontrakt with auto-nummer: {kontrakt['kontraktnummer']}")
        
        # Cleanup
        if kontrakt.get("id"):
            admin_session.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}")
    
    def test_create_vk_kontrakt_auto_nummer(self, admin_session):
        """Test creating VK Kontrakt with automatic Kontraktnummer"""
        # Create minimal VK Kontrakt
        kontrakt_data = {
            "vorgang_typ": "VK",
            "name1": f"TEST_Kunde_{uuid.uuid4().hex[:8]}",
            "status": "OFFEN",
            "waehrung_kurz": "EUR"
        }
        
        response = admin_session.post(f"{BASE_URL}/api/kontrakte", json=kontrakt_data)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        kontrakt = data["data"]
        assert "kontraktnummer" in kontrakt
        assert kontrakt["kontraktnummer"].startswith("VKK"), f"Expected VKK prefix, got {kontrakt['kontraktnummer']}"
        
        print(f"✓ Created VK Kontrakt with auto-nummer: {kontrakt['kontraktnummer']}")
        
        # Cleanup
        if kontrakt.get("id"):
            admin_session.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}")
    
    def test_create_kontrakt_with_sachbearbeiter(self, admin_session):
        """Test creating Kontrakt with Sachbearbeiter from Benutzer lookup"""
        # First get a benutzer
        benutzer_response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/benutzer")
        assert benutzer_response.status_code == 200
        
        benutzer_data = benutzer_response.json()
        if not benutzer_data["data"]:
            pytest.skip("No Benutzer available")
        
        benutzer = benutzer_data["data"][0]
        
        # Create Kontrakt with Sachbearbeiter
        kontrakt_data = {
            "vorgang_typ": "EK",
            "name1": f"TEST_Partner_{uuid.uuid4().hex[:8]}",
            "id_sachbearbeiter": benutzer["id"],
            "sachbearbeiter_name": benutzer["name"],
            "sachbearbeiter_email": benutzer.get("email"),
            "status": "OFFEN"
        }
        
        response = admin_session.post(f"{BASE_URL}/api/kontrakte", json=kontrakt_data)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        kontrakt = data["data"]
        
        assert kontrakt.get("id_sachbearbeiter") == benutzer["id"]
        assert kontrakt.get("sachbearbeiter_name") == benutzer["name"]
        
        print(f"✓ Created Kontrakt with Sachbearbeiter: {benutzer['name']}")
        
        # Cleanup
        if kontrakt.get("id"):
            admin_session.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}")
    
    def test_create_kontrakt_with_vertragspartner(self, admin_session):
        """Test creating Kontrakt with Vertragspartner from Adressen lookup"""
        # First get an address
        adressen_response = admin_session.get(f"{BASE_URL}/api/kontrakte/lookup/adressen")
        assert adressen_response.status_code == 200
        
        adressen_data = adressen_response.json()
        if not adressen_data["data"]:
            pytest.skip("No Adressen available")
        
        adresse = adressen_data["data"][0]
        
        # Create Kontrakt with Vertragspartner from Adresse
        kontrakt_data = {
            "vorgang_typ": "VK",
            "id_adresse": adresse["id"],
            "name1": adresse["name1"],
            "name2": adresse.get("name2"),
            "strasse": adresse.get("strasse"),
            "hausnummer": adresse.get("hausnummer"),
            "plz": adresse.get("plz"),
            "ort": adresse.get("ort"),
            "land": adresse.get("land"),
            "telefon": adresse.get("telefon"),
            "email": adresse.get("email"),
            "status": "OFFEN"
        }
        
        response = admin_session.post(f"{BASE_URL}/api/kontrakte", json=kontrakt_data)
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        kontrakt = data["data"]
        
        assert kontrakt.get("id_adresse") == adresse["id"]
        assert kontrakt.get("name1") == adresse["name1"]
        
        print(f"✓ Created Kontrakt with Vertragspartner: {adresse['name1']}")
        
        # Cleanup
        if kontrakt.get("id"):
            admin_session.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}")


# ========================== KONTRAKTE LIST AND DETAIL ==========================

class TestKontrakteAPI:
    """Tests for Kontrakte list and detail endpoints"""
    
    def test_get_kontrakte_list(self, admin_session):
        """Test GET /api/kontrakte - List all contracts"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        assert "pagination" in data
        
        print(f"✓ Found {len(data['data'])} Kontrakte (total: {data['pagination'].get('total')})")
    
    def test_get_kontrakte_filtered_by_typ(self, admin_session):
        """Test GET /api/kontrakte?typ=EK - Filter by type"""
        response = admin_session.get(f"{BASE_URL}/api/kontrakte", params={"typ": "EK"})
        
        assert response.status_code == 200
        
        data = response.json()
        
        for kontrakt in data["data"]:
            assert kontrakt.get("vorgang_typ") == "EK"
        
        print(f"✓ Found {len(data['data'])} EK Kontrakte")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
