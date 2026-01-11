"""
Test Suite for Profile and Settings Endpoints
- GET/PUT /api/profil - User profile management
- POST /api/profil/passwort - Password change
- GET/PUT /api/admin/systemeinstellungen - System settings (admin only)
"""

import pytest
import requests
import os

BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', '').rstrip('/')

# Test credentials
ADMIN_USER = {"benutzername": "admin", "passwort": "Admin123!"}
WAAGE_USER = {"benutzername": "waage", "passwort": "Waage!123"}


class TestAuthentication:
    """Test login functionality"""
    
    def test_admin_login(self):
        """Admin user can login successfully"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json=ADMIN_USER)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "access_token" in data
        assert data["user"]["ist_admin"] == True
    
    def test_waage_login(self):
        """Waage user can login successfully"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json=WAAGE_USER)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "access_token" in data


def get_admin_token():
    """Helper to get admin token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=ADMIN_USER)
    return response.json().get("access_token")


def get_waage_token():
    """Helper to get waage user token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=WAAGE_USER)
    return response.json().get("access_token")


class TestProfilEndpoints:
    """Test profile endpoints - GET/PUT /api/profil"""
    
    def test_get_profil_admin(self):
        """GET /api/profil - Admin can retrieve own profile"""
        token = get_admin_token()
        response = requests.get(
            f"{BASE_URL}/api/profil",
            headers={"Authorization": f"Bearer {token}"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        
        # Verify profile data structure
        profile = data["data"]
        assert "id" in profile
        assert "benutzername" in profile
        assert "email" in profile
        assert "vorname" in profile
        assert "nachname" in profile
        assert "kuerzel" in profile
        assert "ist_admin" in profile
        assert profile["benutzername"] == "admin"
        assert profile["ist_admin"] == True
    
    def test_get_profil_waage(self):
        """GET /api/profil - Waage user can retrieve own profile"""
        token = get_waage_token()
        response = requests.get(
            f"{BASE_URL}/api/profil",
            headers={"Authorization": f"Bearer {token}"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["benutzername"] == "waage"
    
    def test_get_profil_unauthorized(self):
        """GET /api/profil - Unauthorized without token"""
        response = requests.get(f"{BASE_URL}/api/profil")
        assert response.status_code == 401
    
    def test_update_profil_admin(self):
        """PUT /api/profil - Admin can update own profile"""
        token = get_admin_token()
        
        # Update profile data
        update_data = {
            "vorname": "TestVorname",
            "nachname": "TestNachname",
            "telefon": "+49 123 456789",
            "position": "Test Position"
        }
        
        response = requests.put(
            f"{BASE_URL}/api/profil",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json=update_data
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        # Verify update by fetching profile again
        get_response = requests.get(
            f"{BASE_URL}/api/profil",
            headers={"Authorization": f"Bearer {token}"}
        )
        profile = get_response.json()["data"]
        assert profile["vorname"] == "TestVorname"
        assert profile["nachname"] == "TestNachname"
        assert profile["telefon"] == "+49 123 456789"
        assert profile["position"] == "Test Position"
    
    def test_update_profil_waage(self):
        """PUT /api/profil - Waage user can update own profile"""
        token = get_waage_token()
        
        update_data = {
            "telefon": "+49 987 654321"
        }
        
        response = requests.put(
            f"{BASE_URL}/api/profil",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json=update_data
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
    
    def test_update_profil_empty_data(self):
        """PUT /api/profil - Returns error with empty data"""
        token = get_admin_token()
        
        response = requests.put(
            f"{BASE_URL}/api/profil",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json={}
        )
        assert response.status_code == 400


class TestPasswortEndpoints:
    """Test password change endpoint - POST /api/profil/passwort"""
    
    def test_change_password_wrong_current(self):
        """POST /api/profil/passwort - Fails with wrong current password"""
        token = get_admin_token()
        
        response = requests.post(
            f"{BASE_URL}/api/profil/passwort",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json={
                "aktuelles_passwort": "WrongPassword123!",
                "neues_passwort": "NewPassword123!"
            }
        )
        assert response.status_code == 400
        data = response.json()
        assert "detail" in data
        assert "falsch" in data["detail"].lower() or "wrong" in data["detail"].lower()
    
    def test_change_password_success(self):
        """POST /api/profil/passwort - Successfully changes password"""
        # Use waage user for this test to avoid breaking admin login
        token = get_waage_token()
        
        # Change password
        response = requests.post(
            f"{BASE_URL}/api/profil/passwort",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json={
                "aktuelles_passwort": "Waage!123",
                "neues_passwort": "NewWaage!123"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        # Verify new password works
        login_response = requests.post(
            f"{BASE_URL}/api/auth/login",
            json={"benutzername": "waage", "passwort": "NewWaage!123"}
        )
        assert login_response.status_code == 200
        
        # Restore original password
        new_token = login_response.json()["access_token"]
        restore_response = requests.post(
            f"{BASE_URL}/api/profil/passwort",
            headers={
                "Authorization": f"Bearer {new_token}",
                "Content-Type": "application/json"
            },
            json={
                "aktuelles_passwort": "NewWaage!123",
                "neues_passwort": "Waage!123"
            }
        )
        assert restore_response.status_code == 200
    
    def test_change_password_unauthorized(self):
        """POST /api/profil/passwort - Unauthorized without token"""
        response = requests.post(
            f"{BASE_URL}/api/profil/passwort",
            json={
                "aktuelles_passwort": "test",
                "neues_passwort": "test123"
            }
        )
        assert response.status_code == 401


class TestSystemeinstellungenEndpoints:
    """Test system settings endpoints - GET/PUT /api/admin/systemeinstellungen"""
    
    def test_get_systemeinstellungen_admin(self):
        """GET /api/admin/systemeinstellungen - Admin can retrieve settings"""
        token = get_admin_token()
        
        response = requests.get(
            f"{BASE_URL}/api/admin/systemeinstellungen",
            headers={"Authorization": f"Bearer {token}"}
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        
        # Verify settings structure
        settings = data["data"]
        assert "firmenname" in settings
        assert "waehrung" in settings
        assert "sprache" in settings
        assert "zeitzone" in settings
    
    def test_get_systemeinstellungen_non_admin(self):
        """GET /api/admin/systemeinstellungen - Non-admin gets 403"""
        token = get_waage_token()
        
        response = requests.get(
            f"{BASE_URL}/api/admin/systemeinstellungen",
            headers={"Authorization": f"Bearer {token}"}
        )
        assert response.status_code == 403
    
    def test_get_systemeinstellungen_unauthorized(self):
        """GET /api/admin/systemeinstellungen - Unauthorized without token"""
        response = requests.get(f"{BASE_URL}/api/admin/systemeinstellungen")
        assert response.status_code == 401
    
    def test_update_systemeinstellungen_admin(self):
        """PUT /api/admin/systemeinstellungen - Admin can update settings"""
        token = get_admin_token()
        
        update_data = {
            "firmenname": "Test Firma GmbH",
            "waehrung": "EUR",
            "sprache": "de"
        }
        
        response = requests.put(
            f"{BASE_URL}/api/admin/systemeinstellungen",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json=update_data
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        # Verify update by fetching settings again
        get_response = requests.get(
            f"{BASE_URL}/api/admin/systemeinstellungen",
            headers={"Authorization": f"Bearer {token}"}
        )
        settings = get_response.json()["data"]
        assert settings["firmenname"] == "Test Firma GmbH"
    
    def test_update_systemeinstellungen_non_admin(self):
        """PUT /api/admin/systemeinstellungen - Non-admin gets 403"""
        token = get_waage_token()
        
        response = requests.put(
            f"{BASE_URL}/api/admin/systemeinstellungen",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json={"firmenname": "Hacked"}
        )
        assert response.status_code == 403


if __name__ == "__main__":
    pytest.main([__file__, "-v"])
