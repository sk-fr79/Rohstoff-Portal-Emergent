"""
Test Suite for Permission System (Berechtigungssystem)
Tests backend API permission enforcement for different user roles:
- Admin user: Full access to all endpoints
- Waage user: Limited access (read artikel/adressen, full wiegekarten/dashboard, denied fuhren/kontrakte/rechnungen/kreditversicherung)
"""

import pytest
import requests
import os

BASE_URL = "http://localhost:8001"

# Test credentials
ADMIN_USER = {"benutzername": "admin", "passwort": "Admin123!"}
WAAGE_USER = {"benutzername": "waage", "passwort": "Waage!123"}


class TestAuthLogin:
    """Test authentication for both users"""
    
    def test_admin_login_success(self):
        """Admin user can login successfully"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json=ADMIN_USER)
        assert response.status_code == 200, f"Admin login failed: {response.text}"
        data = response.json()
        assert data.get("success") == True, f"Login not successful: {data}"
        assert "access_token" in data, "No access_token in response"
        assert data.get("user", {}).get("ist_admin") == True, "Admin user should have ist_admin=True"
        print(f"✓ Admin login successful, ist_admin={data.get('user', {}).get('ist_admin')}")
    
    def test_waage_login_success(self):
        """Waage user can login successfully"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json=WAAGE_USER)
        assert response.status_code == 200, f"Waage login failed: {response.text}"
        data = response.json()
        assert data.get("success") == True, f"Login not successful: {data}"
        assert "access_token" in data, "No access_token in response"
        assert data.get("user", {}).get("ist_admin") == False, "Waage user should have ist_admin=False"
        print(f"✓ Waage login successful, ist_admin={data.get('user', {}).get('ist_admin')}")


@pytest.fixture
def admin_token():
    """Get admin authentication token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=ADMIN_USER)
    if response.status_code == 200 and response.json().get("success"):
        return response.json()["access_token"]
    pytest.skip("Admin authentication failed")


@pytest.fixture
def waage_token():
    """Get waage user authentication token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=WAAGE_USER)
    if response.status_code == 200 and response.json().get("success"):
        return response.json()["access_token"]
    pytest.skip("Waage authentication failed")


class TestWaageUserDeniedEndpoints:
    """Test that waage user is DENIED access to restricted modules (expect 403)"""
    
    def test_waage_denied_fuhren_read(self, waage_token):
        """Waage user should NOT be able to read /api/fuhren (403)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=headers)
        assert response.status_code == 403, f"Expected 403 for fuhren, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied access to /api/fuhren (403)")
    
    def test_waage_denied_kontrakte_read(self, waage_token):
        """Waage user should NOT be able to read /api/kontrakte (403)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/kontrakte", headers=headers)
        assert response.status_code == 403, f"Expected 403 for kontrakte, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied access to /api/kontrakte (403)")
    
    def test_waage_denied_rechnungen_read(self, waage_token):
        """Waage user should NOT be able to read /api/rechnungen (403)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=headers)
        assert response.status_code == 403, f"Expected 403 for rechnungen, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied access to /api/rechnungen (403)")
    
    def test_waage_denied_kreditversicherungen_read(self, waage_token):
        """Waage user should NOT be able to read /api/kreditversicherungen (403)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/kreditversicherungen", headers=headers)
        assert response.status_code == 403, f"Expected 403 for kreditversicherungen, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied access to /api/kreditversicherungen (403)")


class TestWaageUserReadOnlyEndpoints:
    """Test that waage user can READ but NOT WRITE to artikel/adressen"""
    
    def test_waage_can_read_artikel(self, waage_token):
        """Waage user should be able to READ /api/artikel (200)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/artikel", headers=headers)
        assert response.status_code == 200, f"Expected 200 for artikel read, got {response.status_code}: {response.text}"
        print("✓ Waage can read /api/artikel (200)")
    
    def test_waage_can_read_adressen(self, waage_token):
        """Waage user should be able to READ /api/adressen (200)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/adressen", headers=headers)
        assert response.status_code == 200, f"Expected 200 for adressen read, got {response.status_code}: {response.text}"
        print("✓ Waage can read /api/adressen (200)")
    
    def test_waage_denied_artikel_write(self, waage_token):
        """Waage user should NOT be able to POST to /api/artikel (403 - only read permission)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        test_artikel = {
            "artbez1": "TEST_Waage_Artikel",
            "einheit": "kg"
        }
        response = requests.post(f"{BASE_URL}/api/artikel", headers=headers, json=test_artikel)
        assert response.status_code == 403, f"Expected 403 for artikel write, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied write access to /api/artikel (403)")
    
    def test_waage_denied_adressen_write(self, waage_token):
        """Waage user should NOT be able to POST to /api/adressen (403 - only read permission)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        test_adresse = {
            "name1": "TEST_Waage_Adresse",
            "typ": "LIEFERANT"
        }
        response = requests.post(f"{BASE_URL}/api/adressen", headers=headers, json=test_adresse)
        assert response.status_code == 403, f"Expected 403 for adressen write, got {response.status_code}: {response.text}"
        print("✓ Waage correctly denied write access to /api/adressen (403)")


class TestWaageUserFullAccessEndpoints:
    """Test that waage user has FULL access to wiegekarten"""
    
    def test_waage_can_read_wiegekarten(self, waage_token):
        """Waage user should be able to READ /api/wiegekarten (200)"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/wiegekarten", headers=headers)
        assert response.status_code == 200, f"Expected 200 for wiegekarten read, got {response.status_code}: {response.text}"
        print("✓ Waage can read /api/wiegekarten (200)")


class TestAdminUserFullAccess:
    """Test that admin user has FULL access to all endpoints"""
    
    def test_admin_can_read_fuhren(self, admin_token):
        """Admin user should be able to read /api/fuhren (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/fuhren", headers=headers)
        assert response.status_code == 200, f"Expected 200 for fuhren, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/fuhren (200)")
    
    def test_admin_can_read_kontrakte(self, admin_token):
        """Admin user should be able to read /api/kontrakte (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/kontrakte", headers=headers)
        assert response.status_code == 200, f"Expected 200 for kontrakte, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/kontrakte (200)")
    
    def test_admin_can_read_rechnungen(self, admin_token):
        """Admin user should be able to read /api/rechnungen (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/rechnungen", headers=headers)
        assert response.status_code == 200, f"Expected 200 for rechnungen, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/rechnungen (200)")
    
    def test_admin_can_read_kreditversicherungen(self, admin_token):
        """Admin user should be able to read /api/kreditversicherungen (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/kreditversicherungen", headers=headers)
        assert response.status_code == 200, f"Expected 200 for kreditversicherungen, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/kreditversicherungen (200)")
    
    def test_admin_can_read_artikel(self, admin_token):
        """Admin user should be able to read /api/artikel (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/artikel", headers=headers)
        assert response.status_code == 200, f"Expected 200 for artikel, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/artikel (200)")
    
    def test_admin_can_read_adressen(self, admin_token):
        """Admin user should be able to read /api/adressen (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/adressen", headers=headers)
        assert response.status_code == 200, f"Expected 200 for adressen, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/adressen (200)")
    
    def test_admin_can_read_wiegekarten(self, admin_token):
        """Admin user should be able to read /api/wiegekarten (200)"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/wiegekarten", headers=headers)
        assert response.status_code == 200, f"Expected 200 for wiegekarten, got {response.status_code}: {response.text}"
        print("✓ Admin can read /api/wiegekarten (200)")


class TestWaageUserPermissionsEndpoint:
    """Test that waage user's permissions are correctly returned"""
    
    def test_waage_meine_berechtigungen(self, waage_token):
        """Verify waage user's permissions via /api/admin/meine-berechtigungen"""
        headers = {"Authorization": f"Bearer {waage_token}"}
        response = requests.get(f"{BASE_URL}/api/admin/meine-berechtigungen", headers=headers)
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True, f"Request not successful: {data}"
        
        permissions = data.get("data", {})
        berechtigungen = permissions.get("berechtigungen", {})
        
        print(f"Waage user permissions: {berechtigungen}")
        print(f"ist_admin: {permissions.get('ist_admin')}")
        
        # Verify ist_admin is False
        assert permissions.get("ist_admin") == False, "Waage should not be admin"
        
        # Expected permissions for waage user
        expected_read = ["artikel", "adressen"]
        expected_full = ["dashboard", "wiegekarten"]
        expected_denied = ["fuhren", "kontrakte", "rechnungen", "kreditversicherungen"]
        
        for modul in expected_read:
            level = berechtigungen.get(modul)
            assert level == "read", f"Expected 'read' for {modul}, got '{level}'"
            print(f"✓ {modul}: {level}")
        
        for modul in expected_full:
            level = berechtigungen.get(modul)
            assert level == "full", f"Expected 'full' for {modul}, got '{level}'"
            print(f"✓ {modul}: {level}")
        
        for modul in expected_denied:
            level = berechtigungen.get(modul)
            assert level == "denied" or level is None, f"Expected 'denied' or None for {modul}, got '{level}'"
            print(f"✓ {modul}: {level} (denied)")


class TestAdminUserPermissionsEndpoint:
    """Test that admin user's permissions show full access"""
    
    def test_admin_meine_berechtigungen(self, admin_token):
        """Verify admin user's permissions via /api/admin/meine-berechtigungen"""
        headers = {"Authorization": f"Bearer {admin_token}"}
        response = requests.get(f"{BASE_URL}/api/admin/meine-berechtigungen", headers=headers)
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True, f"Request not successful: {data}"
        
        permissions = data.get("data", {})
        
        # Admin should have ist_admin=True
        assert permissions.get("ist_admin") == True, "Admin should have ist_admin=True"
        print(f"✓ Admin ist_admin: {permissions.get('ist_admin')}")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
