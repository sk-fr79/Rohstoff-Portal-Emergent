"""
Test Admin Router - Benutzerverwaltung, Rollen, Abteilungen, Berechtigungen
Tests for user management, roles, departments, and permissions system
"""

import pytest
import requests
import os
import uuid

# Use internal URL for testing
BASE_URL = "http://localhost:8001"

# Test credentials
ADMIN_USERNAME = "admin"
ADMIN_PASSWORD = "Admin123!"


class TestAdminAuth:
    """Test admin authentication and authorization"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert data["user"]["ist_admin"] is True
        return data["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_login_success(self):
        """Test successful admin login"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "access_token" in data
        assert data["user"]["benutzername"] == "admin"
        assert data["user"]["ist_admin"] is True
    
    def test_login_invalid_credentials(self):
        """Test login with invalid credentials"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": "admin", "passwort": "wrongpassword"}
        )
        assert response.status_code == 200  # Returns 200 with success=False
        data = response.json()
        assert data["success"] is False
        assert "error" in data or data.get("error") is not None


class TestRollenEndpoints:
    """Test Rollen (Roles) CRUD endpoints"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        return response.json()["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_get_rollen_list(self, auth_headers):
        """GET /api/admin/rollen - List all roles with benutzer_count"""
        response = requests.get(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        # Check that Administrator role exists and is system role
        admin_rolle = next((r for r in data["data"] if r["name"] == "Administrator"), None)
        assert admin_rolle is not None
        assert admin_rolle["ist_system"] is True
        assert "benutzer_count" in admin_rolle
    
    def test_create_rolle(self, auth_headers):
        """POST /api/admin/rollen - Create new role"""
        unique_name = f"TEST_Rolle_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers,
            json={
                "name": unique_name,
                "beschreibung": "Test-Rolle für automatisierte Tests",
                "farbe": "#FF5733"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert data["data"]["name"] == unique_name
        assert data["data"]["farbe"] == "#FF5733"
        assert data["data"]["ist_system"] is False
        
        # Store for cleanup
        self.__class__.created_rolle_id = data["data"]["id"]
    
    def test_create_duplicate_rolle_fails(self, auth_headers):
        """POST /api/admin/rollen - Duplicate role name should fail"""
        response = requests.post(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers,
            json={
                "name": "Administrator",  # Already exists
                "beschreibung": "Duplicate test"
            }
        )
        assert response.status_code == 400
        assert "existiert bereits" in response.json()["detail"]
    
    def test_delete_system_rolle_fails(self, auth_headers):
        """DELETE /api/admin/rollen/{id} - System role cannot be deleted"""
        # First get the Administrator role ID
        response = requests.get(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers
        )
        admin_rolle = next((r for r in response.json()["data"] if r["name"] == "Administrator"), None)
        
        # Try to delete it
        response = requests.delete(
            f"{BASE_URL}/api/admin/rollen/{admin_rolle['id']}",
            headers=auth_headers
        )
        assert response.status_code == 400
        assert "System-Rolle" in response.json()["detail"]
    
    def test_delete_created_rolle(self, auth_headers):
        """DELETE /api/admin/rollen/{id} - Delete non-system role"""
        if not hasattr(self.__class__, 'created_rolle_id'):
            pytest.skip("No role was created to delete")
        
        response = requests.delete(
            f"{BASE_URL}/api/admin/rollen/{self.__class__.created_rolle_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        assert response.json()["success"] is True


class TestAbteilungenEndpoints:
    """Test Abteilungen (Departments) CRUD endpoints"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        return response.json()["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_get_abteilungen_list(self, auth_headers):
        """GET /api/admin/abteilungen - List all departments"""
        response = requests.get(
            f"{BASE_URL}/api/admin/abteilungen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        # Check that departments have benutzer_count
        if len(data["data"]) > 0:
            assert "benutzer_count" in data["data"][0]
    
    def test_create_abteilung(self, auth_headers):
        """POST /api/admin/abteilungen - Create new department"""
        unique_name = f"TEST_Abteilung_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/admin/abteilungen",
            headers=auth_headers,
            json={
                "name": unique_name,
                "kuerzel": "TST",
                "beschreibung": "Test-Abteilung für automatisierte Tests"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert data["data"]["name"] == unique_name
        assert data["data"]["kuerzel"] == "TST"
        
        # Store for cleanup
        self.__class__.created_abteilung_id = data["data"]["id"]
    
    def test_update_abteilung(self, auth_headers):
        """PUT /api/admin/abteilungen/{id} - Update department"""
        if not hasattr(self.__class__, 'created_abteilung_id'):
            pytest.skip("No department was created to update")
        
        response = requests.put(
            f"{BASE_URL}/api/admin/abteilungen/{self.__class__.created_abteilung_id}",
            headers=auth_headers,
            json={
                "beschreibung": "Aktualisierte Beschreibung"
            }
        )
        assert response.status_code == 200
        assert response.json()["success"] is True
    
    def test_delete_abteilung(self, auth_headers):
        """DELETE /api/admin/abteilungen/{id} - Delete department"""
        if not hasattr(self.__class__, 'created_abteilung_id'):
            pytest.skip("No department was created to delete")
        
        response = requests.delete(
            f"{BASE_URL}/api/admin/abteilungen/{self.__class__.created_abteilung_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        assert response.json()["success"] is True


class TestBenutzerEndpoints:
    """Test Benutzer (Users) CRUD endpoints"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        return response.json()["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_get_benutzer_list(self, auth_headers):
        """GET /api/admin/benutzer - List all users with role and departments"""
        response = requests.get(
            f"{BASE_URL}/api/admin/benutzer",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "data" in data
        assert isinstance(data["data"], list)
        
        # Check admin user exists
        admin_user = next((u for u in data["data"] if u["benutzername"] == "admin"), None)
        assert admin_user is not None
        assert admin_user["ist_admin"] is True
        assert "rolle_name" in admin_user
        assert "abteilung_names" in admin_user
    
    def test_create_benutzer(self, auth_headers):
        """POST /api/admin/benutzer - Create new user"""
        unique_username = f"TEST_user_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/admin/benutzer",
            headers=auth_headers,
            json={
                "benutzername": unique_username,
                "email": f"{unique_username}@test.local",
                "passwort": "TestPass123!",
                "vorname": "Test",
                "nachname": "Benutzer",
                "aktiv": True
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert data["data"]["benutzername"] == unique_username
        assert data["data"]["aktiv"] is True
        assert "passwort_hash" not in data["data"]  # Password hash should not be returned
        
        # Store for further tests
        self.__class__.created_benutzer_id = data["data"]["id"]
        self.__class__.created_benutzer_username = unique_username
    
    def test_create_duplicate_username_fails(self, auth_headers):
        """POST /api/admin/benutzer - Duplicate username should fail"""
        response = requests.post(
            f"{BASE_URL}/api/admin/benutzer",
            headers=auth_headers,
            json={
                "benutzername": "admin",  # Already exists
                "email": "duplicate@test.local",
                "passwort": "TestPass123!"
            }
        )
        assert response.status_code == 400
        assert "bereits" in response.json()["detail"]
    
    def test_update_benutzer(self, auth_headers):
        """PUT /api/admin/benutzer/{id} - Update user"""
        if not hasattr(self.__class__, 'created_benutzer_id'):
            pytest.skip("No user was created to update")
        
        response = requests.put(
            f"{BASE_URL}/api/admin/benutzer/{self.__class__.created_benutzer_id}",
            headers=auth_headers,
            json={
                "vorname": "Updated",
                "nachname": "Name"
            }
        )
        assert response.status_code == 200
        assert response.json()["success"] is True
        
        # Verify update
        response = requests.get(
            f"{BASE_URL}/api/admin/benutzer/{self.__class__.created_benutzer_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        assert response.json()["data"]["vorname"] == "Updated"
    
    def test_passwort_reset(self, auth_headers):
        """POST /api/admin/benutzer/{id}/passwort-reset - Reset user password"""
        if not hasattr(self.__class__, 'created_benutzer_id'):
            pytest.skip("No user was created for password reset")
        
        response = requests.post(
            f"{BASE_URL}/api/admin/benutzer/{self.__class__.created_benutzer_id}/passwort-reset",
            headers=auth_headers,
            json={
                "neues_passwort": "NewPassword123!"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "zurückgesetzt" in data.get("message", "")
    
    def test_delete_benutzer(self, auth_headers):
        """DELETE /api/admin/benutzer/{id} - Delete user"""
        if not hasattr(self.__class__, 'created_benutzer_id'):
            pytest.skip("No user was created to delete")
        
        response = requests.delete(
            f"{BASE_URL}/api/admin/benutzer/{self.__class__.created_benutzer_id}",
            headers=auth_headers
        )
        assert response.status_code == 200
        assert response.json()["success"] is True


class TestBerechtigungenEndpoints:
    """Test Berechtigungen (Permissions) endpoints"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        return response.json()["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_get_berechtigungen_matrix(self, auth_headers):
        """GET /api/admin/berechtigungen/matrix - Get permissions matrix"""
        response = requests.get(
            f"{BASE_URL}/api/admin/berechtigungen/matrix",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert "data" in data
        
        matrix = data["data"]
        assert "module" in matrix
        assert "rollen" in matrix
        assert "abteilungen" in matrix
        assert "berechtigungen" in matrix
        assert "levels" in matrix
        
        # Check levels
        assert "read" in matrix["levels"]
        assert "write" in matrix["levels"]
        assert "full" in matrix["levels"]
        assert "denied" in matrix["levels"]
    
    def test_get_meine_berechtigungen(self, auth_headers):
        """GET /api/admin/meine-berechtigungen - Get own permissions"""
        response = requests.get(
            f"{BASE_URL}/api/admin/meine-berechtigungen",
            headers=auth_headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        assert data["data"]["ist_admin"] is True
        
        # Admin should have full access to all modules
        berechtigungen = data["data"]["berechtigungen"]
        for modul, level in berechtigungen.items():
            assert level == "full", f"Admin should have full access to {modul}"
    
    def test_create_berechtigung(self, auth_headers):
        """POST /api/admin/berechtigungen - Create/update permission"""
        # First get a role ID
        response = requests.get(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers
        )
        rollen = response.json()["data"]
        non_admin_rolle = next((r for r in rollen if r["name"] != "Administrator"), None)
        
        if not non_admin_rolle:
            pytest.skip("No non-admin role found")
        
        # Set permission
        response = requests.post(
            f"{BASE_URL}/api/admin/berechtigungen",
            headers=auth_headers,
            json={
                "modul": "adressen",
                "ziel_typ": "rolle",
                "ziel_id": non_admin_rolle["id"],
                "level": "write"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] is True
        
        # Store for cleanup
        self.__class__.test_rolle_id = non_admin_rolle["id"]
    
    def test_invalid_berechtigung_level_fails(self, auth_headers):
        """POST /api/admin/berechtigungen - Invalid level should fail"""
        response = requests.get(
            f"{BASE_URL}/api/admin/rollen",
            headers=auth_headers
        )
        rollen = response.json()["data"]
        non_admin_rolle = next((r for r in rollen if r["name"] != "Administrator"), None)
        
        if not non_admin_rolle:
            pytest.skip("No non-admin role found")
        
        response = requests.post(
            f"{BASE_URL}/api/admin/berechtigungen",
            headers=auth_headers,
            json={
                "modul": "adressen",
                "ziel_typ": "rolle",
                "ziel_id": non_admin_rolle["id"],
                "level": "invalid_level"
            }
        )
        assert response.status_code == 400


class TestAdminAccessControl:
    """Test that non-admins cannot access admin endpoints"""
    
    @pytest.fixture(scope="class")
    def admin_token(self):
        """Get admin authentication token"""
        response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": ADMIN_USERNAME, "passwort": ADMIN_PASSWORD}
        )
        return response.json()["access_token"]
    
    @pytest.fixture(scope="class")
    def auth_headers(self, admin_token):
        """Get authorization headers"""
        return {"Authorization": f"Bearer {admin_token}"}
    
    def test_unauthenticated_access_fails(self):
        """Admin endpoints should require authentication"""
        response = requests.get(f"{BASE_URL}/api/admin/rollen")
        assert response.status_code == 401
    
    def test_create_non_admin_user_and_test_access(self, auth_headers):
        """Non-admin users should get 403 on admin endpoints"""
        # Create a non-admin user
        unique_username = f"TEST_nonadmin_{uuid.uuid4().hex[:8]}"
        response = requests.post(
            f"{BASE_URL}/api/admin/benutzer",
            headers=auth_headers,
            json={
                "benutzername": unique_username,
                "email": f"{unique_username}@test.local",
                "passwort": "TestPass123!",
                "aktiv": True
            }
        )
        assert response.status_code == 200
        user_id = response.json()["data"]["id"]
        
        # Login as non-admin user
        login_response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": unique_username, "passwort": "TestPass123!"}
        )
        assert login_response.status_code == 200
        non_admin_token = login_response.json()["access_token"]
        
        # Try to access admin endpoint
        response = requests.get(
            f"{BASE_URL}/api/admin/rollen",
            headers={"Authorization": f"Bearer {non_admin_token}"}
        )
        assert response.status_code == 403
        assert "Administrator" in response.json()["detail"]
        
        # Cleanup - delete the test user
        requests.delete(
            f"{BASE_URL}/api/admin/benutzer/{user_id}",
            headers=auth_headers
        )


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
