"""
Rohstoff Portal - Backend API Tests
Tests for: Auth, Adressen, Artikel, Kontrakte, Dashboard
"""

import pytest
import requests
import os
import uuid

BASE_URL = "https://rawmat-portal.preview.emergentagent.com"

# Test credentials
TEST_USERNAME = "admin"
TEST_PASSWORD = "Admin123!"


class TestHealthCheck:
    """Health check endpoint tests"""
    
    def test_health_endpoint(self):
        """Test health endpoint returns OK"""
        response = requests.get(f"{BASE_URL}/api/health")
        assert response.status_code == 200
        data = response.json()
        assert data["status"] == "ok"
        assert "timestamp" in data
        assert "version" in data
        print(f"✓ Health check passed: {data}")


class TestAuthentication:
    """Authentication endpoint tests"""
    
    def test_login_success(self):
        """Test successful login with admin credentials"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": TEST_USERNAME,
            "passwort": TEST_PASSWORD
        })
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "access_token" in data
        assert "user" in data
        assert data["user"]["benutzername"] == TEST_USERNAME
        print(f"✓ Login successful for user: {data['user']['benutzername']}")
        return data["access_token"]
    
    def test_login_invalid_credentials(self):
        """Test login with invalid credentials"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "wronguser",
            "passwort": "wrongpassword"
        })
        assert response.status_code == 200  # API returns 200 with success=False
        data = response.json()
        assert data["success"] == False
        assert "error" in data
        print(f"✓ Invalid login correctly rejected: {data['error']}")
    
    def test_login_empty_credentials(self):
        """Test login with empty credentials"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "",
            "passwort": ""
        })
        # Should return validation error or success=False
        assert response.status_code in [200, 422]
        print("✓ Empty credentials handled correctly")
    
    def test_auth_me_without_token(self):
        """Test /auth/me without token"""
        response = requests.get(f"{BASE_URL}/api/auth/me")
        assert response.status_code == 401
        print("✓ Unauthorized access correctly rejected")
    
    def test_auth_me_with_token(self):
        """Test /auth/me with valid token"""
        # First login
        login_response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": TEST_USERNAME,
            "passwort": TEST_PASSWORD
        })
        token = login_response.json()["access_token"]
        
        # Then get user info
        response = requests.get(
            f"{BASE_URL}/api/auth/me",
            headers={"Authorization": f"Bearer {token}"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "user" in data
        print(f"✓ Auth/me returned user: {data['user']['benutzername']}")


@pytest.fixture(scope="class")
def auth_token():
    """Get authentication token for tests"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json={
        "benutzername": TEST_USERNAME,
        "passwort": TEST_PASSWORD
    })
    if response.status_code == 200 and response.json().get("success"):
        return response.json()["access_token"]
    pytest.skip("Authentication failed - skipping authenticated tests")


@pytest.fixture(scope="class")
def auth_headers(auth_token):
    """Get headers with auth token"""
    return {"Authorization": f"Bearer {auth_token}"}


class TestDashboard:
    """Dashboard endpoint tests"""
    
    def test_dashboard_stats_without_auth(self):
        """Test dashboard stats without authentication"""
        response = requests.get(f"{BASE_URL}/api/dashboard/stats")
        assert response.status_code == 401
        print("✓ Dashboard stats correctly requires authentication")
    
    def test_dashboard_stats_with_auth(self, auth_headers):
        """Test dashboard stats with authentication"""
        response = requests.get(
            f"{BASE_URL}/api/dashboard/stats",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "adressen" in data["data"]
        assert "artikel" in data["data"]
        assert "kontrakte_offen" in data["data"]
        print(f"✓ Dashboard stats: Adressen={data['data']['adressen']}, Artikel={data['data']['artikel']}, Kontrakte={data['data']['kontrakte_offen']}")


class TestAdressen:
    """Adressen (Addresses) CRUD tests"""
    
    def test_get_adressen_without_auth(self):
        """Test getting addresses without authentication"""
        response = requests.get(f"{BASE_URL}/api/adressen")
        assert response.status_code == 401
        print("✓ Adressen correctly requires authentication")
    
    def test_get_adressen_with_auth(self, auth_headers):
        """Test getting addresses with authentication"""
        response = requests.get(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert "pagination" in data
        print(f"✓ Got {len(data['data'])} addresses, total: {data['pagination']['total']}")
    
    def test_create_adresse(self, auth_headers):
        """Test creating a new address"""
        test_name = f"TEST_Firma_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "name2": "Test Abteilung",
                "strasse": "Teststraße",
                "hausnummer": "123",
                "plz": "12345",
                "ort": "Teststadt",
                "telefon": "+49 123 456789",
                "email": "test@example.com",
                "adresstyp": 1,  # Kunde
                "aktiv": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert data["data"]["name1"] == test_name
        assert "kdnr" in data["data"]  # Auto-generated customer number
        print(f"✓ Created address: {data['data']['name1']} with KDNR: {data['data']['kdnr']}")
        return data["data"]["id"]
    
    def test_search_adressen(self, auth_headers):
        """Test searching addresses"""
        response = requests.get(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            params={"suche": "TEST"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Search returned {len(data['data'])} results")
    
    def test_get_adresse_by_id(self, auth_headers):
        """Test getting address by ID"""
        # First create an address
        test_name = f"TEST_GetById_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        adresse_id = create_response.json()["data"]["id"]
        
        # Then get it by ID
        response = requests.get(
            f"{BASE_URL}/api/adressen/{adresse_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == test_name
        print(f"✓ Got address by ID: {data['data']['name1']}")
    
    def test_update_adresse(self, auth_headers):
        """Test updating an address"""
        # First create an address
        test_name = f"TEST_Update_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        adresse_id = create_response.json()["data"]["id"]
        
        # Update it
        updated_name = f"{test_name}_UPDATED"
        response = requests.put(
            f"{BASE_URL}/api/adressen/{adresse_id}",
            headers=auth_headers,
            json={
                "name1": updated_name,
                "ort": "Neue Stadt"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == updated_name
        assert data["data"]["ort"] == "Neue Stadt"
        print(f"✓ Updated address: {data['data']['name1']}")
    
    def test_delete_adresse(self, auth_headers):
        """Test deleting (soft-delete) an address"""
        # First create an address
        test_name = f"TEST_Delete_{uuid.uuid4().hex[:8]}"
        create_response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "adresstyp": 1,
                "aktiv": True
            }
        )
        adresse_id = create_response.json()["data"]["id"]
        
        # Delete it
        response = requests.delete(
            f"{BASE_URL}/api/adressen/{adresse_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Deleted (deactivated) address")
        
        # Verify it's deactivated
        get_response = requests.get(
            f"{BASE_URL}/api/adressen/{adresse_id}",
            headers=auth_headers
        )
        assert get_response.json()["data"]["aktiv"] == False


class TestArtikel:
    """Artikel (Articles) CRUD tests"""
    
    def test_get_artikel_without_auth(self):
        """Test getting articles without authentication"""
        response = requests.get(f"{BASE_URL}/api/artikel")
        assert response.status_code == 401
        print("✓ Artikel correctly requires authentication")
    
    def test_get_artikel_with_auth(self, auth_headers):
        """Test getting articles with authentication"""
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
    
    def test_create_artikel(self, auth_headers):
        """Test creating a new article"""
        test_name = f"TEST_Artikel_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "artbez2": "Test Beschreibung",
                "einheit": "kg",
                "einheit_preis": "t",
                "mengendivisor": 1000,
                "aktiv": True,
                "gefahrgut": False,
                "eakcode": "17 04 01"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert data["data"]["artbez1"] == test_name
        print(f"✓ Created article: {data['data']['artbez1']}")
        return data["data"]["id"]
    
    def test_create_artikel_gefahrgut(self, auth_headers):
        """Test creating a hazardous material article"""
        test_name = f"TEST_Gefahrgut_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/artikel",
            headers=auth_headers,
            json={
                "artbez1": test_name,
                "einheit": "kg",
                "einheit_preis": "t",
                "aktiv": True,
                "gefahrgut": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["gefahrgut"] == True
        print(f"✓ Created hazardous article: {data['data']['artbez1']}")
    
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


class TestKontrakte:
    """Kontrakte (Contracts) CRUD tests"""
    
    @pytest.fixture(scope="class")
    def test_adresse_id(self, auth_headers):
        """Create a test address for contract tests"""
        test_name = f"TEST_KontraktAdresse_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/adressen",
            headers=auth_headers,
            json={
                "name1": test_name,
                "adresstyp": 2,  # Lieferant
                "aktiv": True
            }
        )
        return response.json()["data"]["id"]
    
    def test_get_kontrakte_without_auth(self):
        """Test getting contracts without authentication"""
        response = requests.get(f"{BASE_URL}/api/kontrakte")
        assert response.status_code == 401
        print("✓ Kontrakte correctly requires authentication")
    
    def test_get_kontrakte_with_auth(self, auth_headers):
        """Test getting contracts with authentication"""
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
    
    def test_create_einkaufskontrakt(self, auth_headers, test_adresse_id):
        """Test creating a purchase contract (Einkaufskontrakt)"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "adresse_id": test_adresse_id,
                "ist_einkauf": True,
                "gueltig_von": "2024-01-01T00:00:00",
                "gueltig_bis": "2024-12-31T23:59:59",
                "bemerkungen_intern": "Test Einkaufskontrakt"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert data["data"]["ist_einkauf"] == True
        assert data["data"]["buchungsnummer"].startswith("EKK-")
        print(f"✓ Created purchase contract: {data['data']['buchungsnummer']}")
        return data["data"]["id"]
    
    def test_create_verkaufskontrakt(self, auth_headers, test_adresse_id):
        """Test creating a sales contract (Verkaufskontrakt)"""
        response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "adresse_id": test_adresse_id,
                "ist_einkauf": False,
                "bemerkungen_intern": "Test Verkaufskontrakt"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["ist_einkauf"] == False
        assert data["data"]["buchungsnummer"].startswith("VKK-")
        print(f"✓ Created sales contract: {data['data']['buchungsnummer']}")
        return data["data"]["id"]
    
    def test_filter_kontrakte_einkauf(self, auth_headers):
        """Test filtering contracts by purchase type"""
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
            assert kontrakt["ist_einkauf"] == True
        print(f"✓ Filter Einkauf returned {len(data['data'])} contracts")
    
    def test_filter_kontrakte_verkauf(self, auth_headers):
        """Test filtering contracts by sales type"""
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
            assert kontrakt["ist_einkauf"] == False
        print(f"✓ Filter Verkauf returned {len(data['data'])} contracts")
    
    def test_get_kontrakt_by_id(self, auth_headers, test_adresse_id):
        """Test getting contract by ID"""
        # First create a contract
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "adresse_id": test_adresse_id,
                "ist_einkauf": True
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
        assert data["data"]["id"] == kontrakt_id
        print(f"✓ Got contract by ID: {data['data']['buchungsnummer']}")
    
    def test_add_position_to_kontrakt(self, auth_headers, test_adresse_id):
        """Test adding a position to a contract"""
        # First create a contract
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "adresse_id": test_adresse_id,
                "ist_einkauf": True
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
    
    def test_abschliessen_kontrakt(self, auth_headers, test_adresse_id):
        """Test closing a contract"""
        # First create a contract
        create_response = requests.post(
            f"{BASE_URL}/api/kontrakte",
            headers=auth_headers,
            json={
                "adresse_id": test_adresse_id,
                "ist_einkauf": True
            }
        )
        kontrakt_id = create_response.json()["data"]["id"]
        
        # Close it
        response = requests.post(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}/abschliessen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print(f"✓ Contract closed successfully")
        
        # Verify it's closed
        get_response = requests.get(
            f"{BASE_URL}/api/kontrakte/{kontrakt_id}",
            headers=auth_headers
        )
        assert get_response.json()["data"]["abgeschlossen"] == True


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
