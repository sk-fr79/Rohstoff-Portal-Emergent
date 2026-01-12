"""
Rohstoff Portal - Kontrakt Positionen Tests
Tests for: Position-Dialog, ArtikelSelect, Copy-Function, Summen-Header
Testing the new smart Position management features
"""

import pytest
import requests
import os
import uuid
from datetime import datetime

# Use localhost for backend testing (internal service)
BASE_URL = "http://localhost:8001"

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
# ARTIKEL LOOKUP TESTS (for ArtikelSelect component)
# ============================================================

class TestArtikelLookup:
    """Tests for /api/artikel/lookup endpoint used by ArtikelSelect"""
    
    def test_artikel_lookup_basic(self, auth_headers):
        """Test basic artikel lookup returns data"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/lookup",
            headers=auth_headers,
            params={"limit": 10}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ Artikel lookup returned {len(data['data'])} articles")
    
    def test_artikel_lookup_with_search(self, auth_headers):
        """Test artikel lookup with search filter"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/lookup",
            headers=auth_headers,
            params={"suche": "Blech", "limit": 10}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All results should contain "Blech" in name or number
        for artikel in data["data"]:
            assert "Blech" in (artikel.get("artbez1") or "") or "Blech" in (artikel.get("anr1") or "")
        print(f"✓ Artikel search for 'Blech' returned {len(data['data'])} results")
    
    def test_artikel_lookup_with_artikelgruppe_filter(self, auth_headers):
        """Test artikel lookup with artikelgruppe filter"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/lookup",
            headers=auth_headers,
            params={"artikelgruppe": "Metalle", "limit": 10}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All results should have artikelgruppe "Metalle"
        for artikel in data["data"]:
            assert artikel.get("artikelgruppe") == "Metalle"
        print(f"✓ Artikel filter by 'Metalle' returned {len(data['data'])} results")
    
    def test_artikel_lookup_with_gefahrgut_filter(self, auth_headers):
        """Test artikel lookup with gefahrgut filter"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/lookup",
            headers=auth_headers,
            params={"gefahrgut": True, "limit": 10}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        # All results should be gefahrgut
        for artikel in data["data"]:
            assert artikel.get("gefahrgut") == True
        print(f"✓ Artikel filter by gefahrgut returned {len(data['data'])} results")
    
    def test_artikel_lookup_returns_required_fields(self, auth_headers):
        """Test artikel lookup returns all fields needed by ArtikelSelect"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/lookup",
            headers=auth_headers,
            params={"limit": 1}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        if data["data"]:
            artikel = data["data"][0]
            # Check required fields for ArtikelSelect
            assert "id" in artikel
            assert "anr1" in artikel
            assert "artbez1" in artikel
            assert "einheit" in artikel
            assert "einheit_preis" in artikel
            assert "artikelgruppe" in artikel
            assert "gefahrgut" in artikel
            assert "ist_produkt" in artikel
            assert "dienstleistung" in artikel
            print(f"✓ Artikel lookup returns all required fields")
    
    def test_artikel_gruppen_endpoint(self, auth_headers):
        """Test /api/artikel/gruppen endpoint for filter dropdown"""
        response = requests.get(
            f"{BASE_URL}/api/artikel/gruppen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ Artikelgruppen returned {len(data['data'])} groups: {data['data']}")


# ============================================================
# KONTRAKT POSITIONEN TESTS
# ============================================================

class TestKontraktPositionen:
    """Tests for Kontrakt Position CRUD operations"""
    
    @pytest.fixture
    def test_kontrakt(self, auth_headers):
        """Create a test kontrakt for position tests"""
        test_name = f"TEST_Positionen_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "waehrung_kurz": "EUR",
                "status": "OFFEN",
                "aktiv": True
            }
        )
        assert response.status_code == 200
        kontrakt = response.json()["data"]
        yield kontrakt
        # Cleanup
        requests.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}", headers=auth_headers)
    
    def test_add_position_to_kontrakt(self, auth_headers, test_kontrakt):
        """Test adding a position to a kontrakt"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "position_typ": "ARTIKEL",
                "artbez1": "Test Artikel Position",
                "anr1": "TEST-001",
                "anzahl": 100,
                "einheitkurz": "t",
                "einzelpreis": 250.50,
                "mengen_toleranz_prozent": 10,
                "ueberliefer_ok": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        position = data["data"]
        
        # Verify position data
        assert position["positionsnummer"] == 1
        assert position["artbez1"] == "Test Artikel Position"
        assert position["anzahl"] == 100
        assert position["einzelpreis"] == 250.50
        assert position["gesamtpreis"] == 25050.0  # 100 * 250.50
        print(f"✓ Added position with Gesamtpreis: {position['gesamtpreis']}")
    
    def test_add_multiple_positions(self, auth_headers, test_kontrakt):
        """Test adding multiple positions and verify positionsnummer increments"""
        # Add first position
        response1 = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Position 1",
                "anzahl": 50,
                "einzelpreis": 100
            }
        )
        assert response1.status_code == 200
        pos1 = response1.json()["data"]
        assert pos1["positionsnummer"] == 1
        
        # Add second position
        response2 = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Position 2",
                "anzahl": 75,
                "einzelpreis": 200
            }
        )
        assert response2.status_code == 200
        pos2 = response2.json()["data"]
        assert pos2["positionsnummer"] == 2
        
        print(f"✓ Multiple positions added with correct positionsnummer")
    
    def test_update_position(self, auth_headers, test_kontrakt):
        """Test updating a position"""
        # First add a position
        add_response = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Original Position",
                "anzahl": 100,
                "einzelpreis": 100
            }
        )
        position_id = add_response.json()["data"]["id"]
        
        # Update the position
        update_response = requests.put(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen/{position_id}",
            headers=auth_headers,
            json={
                "artbez1": "Updated Position",
                "anzahl": 200,
                "einzelpreis": 150
            }
        )
        assert update_response.status_code == 200
        
        # Verify update by getting kontrakt
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}",
            headers=auth_headers
        )
        kontrakt = get_response.json()["data"]
        updated_pos = next((p for p in kontrakt["positionen"] if p["id"] == position_id), None)
        
        assert updated_pos is not None
        assert updated_pos["artbez1"] == "Updated Position"
        assert updated_pos["anzahl"] == 200
        assert updated_pos["einzelpreis"] == 150
        assert updated_pos["gesamtpreis"] == 30000.0  # 200 * 150
        print(f"✓ Position updated with new Gesamtpreis: {updated_pos['gesamtpreis']}")
    
    def test_delete_position(self, auth_headers, test_kontrakt):
        """Test deleting a position"""
        # First add a position
        add_response = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Position to Delete",
                "anzahl": 50,
                "einzelpreis": 100
            }
        )
        position_id = add_response.json()["data"]["id"]
        
        # Delete the position
        delete_response = requests.delete(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen/{position_id}",
            headers=auth_headers
        )
        assert delete_response.status_code == 200
        
        # Verify deletion
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}",
            headers=auth_headers
        )
        kontrakt = get_response.json()["data"]
        deleted_pos = next((p for p in kontrakt.get("positionen", []) if p["id"] == position_id), None)
        assert deleted_pos is None
        print(f"✓ Position deleted successfully")
    
    def test_kontrakt_summen_calculation(self, auth_headers, test_kontrakt):
        """Test that kontrakt summen are calculated correctly"""
        # Add multiple positions
        requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={"artbez1": "Pos 1", "anzahl": 100, "einzelpreis": 100}  # 10000
        )
        requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={"artbez1": "Pos 2", "anzahl": 50, "einzelpreis": 200}  # 10000
        )
        requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={"artbez1": "Pos 3", "anzahl": 25, "einzelpreis": 400}  # 10000
        )
        
        # Get kontrakt and check summen
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}",
            headers=auth_headers
        )
        kontrakt = get_response.json()["data"]
        summen = kontrakt.get("summen", {})
        
        assert summen.get("anzahl_positionen") == 3
        assert summen.get("summe_menge") == 175  # 100 + 50 + 25
        assert summen.get("summe_wert") == 30000.0  # 10000 + 10000 + 10000
        print(f"✓ Summen calculated: {summen['anzahl_positionen']} Positionen, {summen['summe_menge']} t, {summen['summe_wert']} €")


# ============================================================
# KONTRAKT WITH POSITIONS CRUD TESTS
# ============================================================

class TestKontraktWithPositions:
    """Tests for creating/updating Kontrakt with embedded positions"""
    
    def test_create_kontrakt_with_positions(self, auth_headers):
        """Test creating a kontrakt with positions in one request"""
        test_name = f"TEST_WithPositions_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "waehrung_kurz": "EUR",
                "status": "OFFEN",
                "positionen": [
                    {
                        "artbez1": "Artikel 1",
                        "anzahl": 100,
                        "einzelpreis": 150,
                        "einheitkurz": "t"
                    },
                    {
                        "artbez1": "Artikel 2",
                        "anzahl": 200,
                        "einzelpreis": 75,
                        "einheitkurz": "t"
                    }
                ]
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        kontrakt = data["data"]
        
        # Verify positions were created
        assert len(kontrakt.get("positionen", [])) == 2
        assert kontrakt["positionen"][0]["positionsnummer"] == 1
        assert kontrakt["positionen"][1]["positionsnummer"] == 2
        
        # Verify summen
        summen = kontrakt.get("summen", {})
        assert summen.get("anzahl_positionen") == 2
        assert summen.get("summe_menge") == 300  # 100 + 200
        assert summen.get("summe_wert") == 30000.0  # 15000 + 15000
        
        print(f"✓ Created kontrakt with 2 positions, Gesamtwert: {summen['summe_wert']} €")
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}", headers=auth_headers)
    
    def test_update_kontrakt_with_positions(self, auth_headers):
        """Test updating a kontrakt with positions"""
        # Create kontrakt
        test_name = f"TEST_UpdatePos_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": test_name,
                "positionen": [
                    {"artbez1": "Original", "anzahl": 50, "einzelpreis": 100}
                ]
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        pos_id = create_response.json()["data"]["positionen"][0]["id"]
        
        # Update kontrakt with modified positions
        update_response = requests.put(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers,
            json={
                "positionen": [
                    {"id": pos_id, "artbez1": "Updated", "anzahl": 100, "einzelpreis": 200},
                    {"artbez1": "New Position", "anzahl": 50, "einzelpreis": 150}
                ]
            }
        )
        assert update_response.status_code == 200
        
        # Verify
        get_response = requests.get(f"{BASE_URL}/api/kontrakte/{kontrakt_id}", headers=auth_headers)
        kontrakt = get_response.json()["data"]
        
        assert len(kontrakt["positionen"]) == 2
        print(f"✓ Updated kontrakt with positions")
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/kontrakte/{kontrakt_id}", headers=auth_headers)


# ============================================================
# POSITION VALIDATION TESTS
# ============================================================

class TestPositionValidation:
    """Tests for position validation rules"""
    
    @pytest.fixture
    def test_kontrakt(self, auth_headers):
        """Create a test kontrakt"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "vorgang_typ": "EK",
                "name1": f"TEST_Validation_{uuid.uuid4().hex[:8]}",
                "status": "OFFEN"
            }
        )
        kontrakt = response.json()["data"]
        yield kontrakt
        requests.delete(f"{BASE_URL}/api/kontrakte/{kontrakt['id']}", headers=auth_headers)
    
    def test_position_toleranz_validation(self, auth_headers, test_kontrakt):
        """Test that mengen_toleranz_prozent is validated (0-100)"""
        # Valid toleranz
        response = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Test",
                "anzahl": 100,
                "einzelpreis": 100,
                "mengen_toleranz_prozent": 15
            }
        )
        assert response.status_code == 200
        print(f"✓ Valid toleranz (15%) accepted")
    
    def test_position_gesamtpreis_auto_calculation(self, auth_headers, test_kontrakt):
        """Test that gesamtpreis is automatically calculated"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte/{test_kontrakt['id']}/positionen",
            headers=auth_headers,
            json={
                "artbez1": "Auto Calc Test",
                "anzahl": 123.45,
                "einzelpreis": 67.89
            }
        )
        assert response.status_code == 200
        position = response.json()["data"]
        
        expected = round(123.45 * 67.89, 2)
        assert position["gesamtpreis"] == expected
        print(f"✓ Gesamtpreis auto-calculated: {position['gesamtpreis']} (expected: {expected})")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
