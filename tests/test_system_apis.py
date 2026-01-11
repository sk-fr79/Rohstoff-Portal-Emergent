"""
Test Suite for API-Konfiguration & Referenztabellen-System
Tests CRUD operations for API configurations and reference tables
"""

import pytest
import requests
import os
import uuid

# Get BASE_URL from environment
BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', '').rstrip('/')
if not BASE_URL:
    # Fallback for local testing
    BASE_URL = "http://localhost:8001"

# Test credentials
ADMIN_CREDENTIALS = {"benutzername": "admin", "passwort": "Admin123!"}
NON_ADMIN_CREDENTIALS = {"benutzername": "waage", "passwort": "Waage!123"}


@pytest.fixture(scope="module")
def admin_token():
    """Get admin authentication token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=ADMIN_CREDENTIALS)
    assert response.status_code == 200, f"Admin login failed: {response.text}"
    data = response.json()
    return data.get("access_token") or data.get("token")


@pytest.fixture(scope="module")
def non_admin_token():
    """Get non-admin authentication token"""
    response = requests.post(f"{BASE_URL}/api/auth/login", json=NON_ADMIN_CREDENTIALS)
    if response.status_code != 200:
        pytest.skip("Non-admin user 'waage' not available")
    data = response.json()
    return data.get("access_token") or data.get("token")


@pytest.fixture
def admin_headers(admin_token):
    """Headers with admin auth"""
    return {"Authorization": f"Bearer {admin_token}", "Content-Type": "application/json"}


@pytest.fixture
def non_admin_headers(non_admin_token):
    """Headers with non-admin auth"""
    return {"Authorization": f"Bearer {non_admin_token}", "Content-Type": "application/json"}


# ============================================================
# API CONFIGURATION CRUD TESTS
# ============================================================

class TestApiConfigList:
    """Tests for GET /api/system/apis - List all API configurations"""
    
    def test_list_apis_as_admin(self, admin_headers):
        """Admin can list all API configurations"""
        response = requests.get(f"{BASE_URL}/api/system/apis", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ Admin can list APIs - found {len(data['data'])} configurations")
    
    def test_list_apis_as_non_admin(self, non_admin_headers):
        """Non-admin should get 403 Forbidden"""
        response = requests.get(f"{BASE_URL}/api/system/apis", headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied access to API list")
    
    def test_list_apis_unauthorized(self):
        """Unauthenticated request should get 401"""
        response = requests.get(f"{BASE_URL}/api/system/apis")
        assert response.status_code == 401
        print("✓ Unauthenticated request correctly denied")
    
    def test_list_apis_with_search(self, admin_headers):
        """Admin can search API configurations"""
        response = requests.get(f"{BASE_URL}/api/system/apis?search=test", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print("✓ Search parameter works correctly")
    
    def test_list_apis_filter_active(self, admin_headers):
        """Admin can filter by is_active status"""
        response = requests.get(f"{BASE_URL}/api/system/apis?is_active=true", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print("✓ Active filter works correctly")


class TestApiConfigCreate:
    """Tests for POST /api/system/apis - Create new API configuration"""
    
    def test_create_api_config_minimal(self, admin_headers):
        """Create API config with minimal required fields"""
        unique_name = f"TEST_API_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "base_url": "https://api.example.com/v1"
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert data["data"]["name"] == unique_name
        assert data["data"]["base_url"] == "https://api.example.com/v1"
        assert "id" in data["data"]
        print(f"✓ Created API config with ID: {data['data']['id']}")
        
        # Cleanup
        api_id = data["data"]["id"]
        requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
    
    def test_create_api_config_full(self, admin_headers):
        """Create API config with all fields (Zolltarif-like preset)"""
        unique_name = f"TEST_Zolltarif_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "description": "Zolltarifnummern API für HS-Codes",
            "api_type": "REST",
            "base_url": "https://www.zolltarifnummern.de/api/suggestions",
            "auth_type": "api_key",
            "auth_config": {
                "api_key": "test-api-key-12345",
                "api_key_name": "X-API-Key",
                "api_key_location": "header"
            },
            "request_config": {
                "method": "GET",
                "headers": {"Accept": "application/json"},
                "query_params": {"lang": "de"}
            },
            "response_mapping": {
                "data_path": "suggestions",
                "field_mappings": [
                    {"source_path": "code", "target_field": "code", "is_primary_key": True, "data_type": "string"},
                    {"source_path": "description", "target_field": "bezeichnung", "is_primary_key": False, "data_type": "string"}
                ]
            },
            "reference_table": {
                "enabled": True,
                "table_name": f"ref_test_zolltarif_{uuid.uuid4().hex[:6]}",
                "display_name": "Test Zolltarifnummern",
                "update_strategy": "merge",
                "track_history": False
            },
            "is_active": True
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name"] == unique_name
        assert data["data"]["api_type"] == "REST"
        assert data["data"]["auth_type"] == "api_key"
        assert data["data"]["reference_table"]["enabled"] == True
        print(f"✓ Created full API config with reference table: {data['data']['id']}")
        
        # Cleanup
        api_id = data["data"]["id"]
        requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
    
    def test_create_api_config_invalid_url(self, admin_headers):
        """Create API config with invalid URL should fail"""
        payload = {
            "name": "TEST_Invalid_URL",
            "base_url": "not-a-valid-url"
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        assert response.status_code == 422  # Validation error
        print("✓ Invalid URL correctly rejected")
    
    def test_create_api_config_duplicate_name(self, admin_headers):
        """Create API config with duplicate name should fail"""
        unique_name = f"TEST_Duplicate_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "base_url": "https://api.example.com/v1"
        }
        # Create first
        response1 = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        assert response1.status_code == 200
        api_id = response1.json()["data"]["id"]
        
        # Try to create duplicate
        response2 = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        assert response2.status_code == 400
        assert "existiert bereits" in response2.json().get("detail", "")
        print("✓ Duplicate name correctly rejected")
        
        # Cleanup
        requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
    
    def test_create_api_config_as_non_admin(self, non_admin_headers):
        """Non-admin cannot create API config"""
        payload = {
            "name": "TEST_NonAdmin",
            "base_url": "https://api.example.com/v1"
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied create access")


class TestApiConfigGetSingle:
    """Tests for GET /api/system/apis/{id} - Get single API configuration"""
    
    @pytest.fixture
    def test_api(self, admin_headers):
        """Create a test API for retrieval tests"""
        unique_name = f"TEST_GetSingle_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "description": "Test API for GET single tests",
            "base_url": "https://api.test.com/v1",
            "auth_type": "api_key",
            "auth_config": {
                "api_key": "secret-key-12345",
                "api_key_name": "Authorization"
            }
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        api_data = response.json()["data"]
        yield api_data
        # Cleanup
        requests.delete(f"{BASE_URL}/api/system/apis/{api_data['id']}", headers=admin_headers)
    
    def test_get_api_config_by_id(self, admin_headers, test_api):
        """Admin can get single API config by ID"""
        response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["id"] == test_api["id"]
        assert data["data"]["name"] == test_api["name"]
        print(f"✓ Retrieved API config: {data['data']['name']}")
    
    def test_get_api_config_credentials_masked(self, admin_headers, test_api):
        """API credentials should be masked in response"""
        response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        auth_config = data["data"].get("auth_config", {})
        if auth_config.get("api_key"):
            assert auth_config["api_key"].startswith("***")
            print("✓ API key is correctly masked")
        else:
            print("✓ No API key in response (encrypted)")
    
    def test_get_api_config_not_found(self, admin_headers):
        """Get non-existent API config returns 404"""
        response = requests.get(f"{BASE_URL}/api/system/apis/NONEXISTENT-ID", headers=admin_headers)
        assert response.status_code == 404
        print("✓ Non-existent API correctly returns 404")
    
    def test_get_api_config_as_non_admin(self, non_admin_headers, test_api):
        """Non-admin cannot get API config"""
        response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}", headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied access")


class TestApiConfigUpdate:
    """Tests for PUT /api/system/apis/{id} - Update API configuration"""
    
    @pytest.fixture
    def test_api(self, admin_headers):
        """Create a test API for update tests"""
        unique_name = f"TEST_Update_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "description": "Original description",
            "base_url": "https://api.original.com/v1",
            "is_active": True
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        api_data = response.json()["data"]
        yield api_data
        # Cleanup
        requests.delete(f"{BASE_URL}/api/system/apis/{api_data['id']}", headers=admin_headers)
    
    def test_update_api_config_description(self, admin_headers, test_api):
        """Admin can update API config description"""
        payload = {"description": "Updated description"}
        response = requests.put(f"{BASE_URL}/api/system/apis/{test_api['id']}", json=payload, headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        # Verify update persisted
        get_response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}", headers=admin_headers)
        assert get_response.json()["data"]["description"] == "Updated description"
        print("✓ Description updated and persisted")
    
    def test_update_api_config_deactivate(self, admin_headers, test_api):
        """Admin can deactivate API config"""
        payload = {"is_active": False}
        response = requests.put(f"{BASE_URL}/api/system/apis/{test_api['id']}", json=payload, headers=admin_headers)
        assert response.status_code == 200
        
        # Verify update persisted
        get_response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}", headers=admin_headers)
        assert get_response.json()["data"]["is_active"] == False
        print("✓ API config deactivated successfully")
    
    def test_update_api_config_not_found(self, admin_headers):
        """Update non-existent API config returns 404"""
        payload = {"description": "Test"}
        response = requests.put(f"{BASE_URL}/api/system/apis/NONEXISTENT-ID", json=payload, headers=admin_headers)
        assert response.status_code == 404
        print("✓ Non-existent API correctly returns 404")
    
    def test_update_api_config_as_non_admin(self, non_admin_headers, test_api):
        """Non-admin cannot update API config"""
        payload = {"description": "Hacked"}
        response = requests.put(f"{BASE_URL}/api/system/apis/{test_api['id']}", json=payload, headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied update access")


class TestApiConfigDelete:
    """Tests for DELETE /api/system/apis/{id} - Delete API configuration"""
    
    def test_delete_api_config(self, admin_headers):
        """Admin can delete API config"""
        # Create API to delete
        unique_name = f"TEST_Delete_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "base_url": "https://api.delete.com/v1"
        }
        create_response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        api_id = create_response.json()["data"]["id"]
        
        # Delete
        response = requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        
        # Verify deleted
        get_response = requests.get(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert get_response.status_code == 404
        print("✓ API config deleted and verified")
    
    def test_delete_api_config_not_found(self, admin_headers):
        """Delete non-existent API config returns 404"""
        response = requests.delete(f"{BASE_URL}/api/system/apis/NONEXISTENT-ID", headers=admin_headers)
        assert response.status_code == 404
        print("✓ Non-existent API correctly returns 404")
    
    def test_delete_api_config_as_non_admin(self, non_admin_headers, admin_headers):
        """Non-admin cannot delete API config"""
        # Create API as admin
        unique_name = f"TEST_DeleteNonAdmin_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "base_url": "https://api.nodelete.com/v1"
        }
        create_response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        api_id = create_response.json()["data"]["id"]
        
        # Try to delete as non-admin
        response = requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied delete access")
        
        # Cleanup as admin
        requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)


# ============================================================
# REFERENCE TABLES TESTS
# ============================================================

class TestReferenceTables:
    """Tests for GET /api/system/reference-tables - List reference tables"""
    
    def test_list_reference_tables_as_admin(self, admin_headers):
        """Admin can list all reference tables"""
        response = requests.get(f"{BASE_URL}/api/system/reference-tables", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ Admin can list reference tables - found {len(data['data'])} tables")
    
    def test_list_reference_tables_as_non_admin(self, non_admin_headers):
        """Non-admin should get 403 Forbidden"""
        response = requests.get(f"{BASE_URL}/api/system/reference-tables", headers=non_admin_headers)
        assert response.status_code == 403
        print("✓ Non-admin correctly denied access to reference tables")
    
    def test_list_reference_tables_with_search(self, admin_headers):
        """Admin can search reference tables"""
        response = requests.get(f"{BASE_URL}/api/system/reference-tables?search=test", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print("✓ Search parameter works correctly for reference tables")


class TestApiSyncLogs:
    """Tests for GET /api/system/apis/{id}/logs - Get sync logs"""
    
    @pytest.fixture
    def test_api(self, admin_headers):
        """Create a test API for sync log tests"""
        unique_name = f"TEST_SyncLogs_{uuid.uuid4().hex[:8]}"
        payload = {
            "name": unique_name,
            "base_url": "https://api.synctest.com/v1"
        }
        response = requests.post(f"{BASE_URL}/api/system/apis", json=payload, headers=admin_headers)
        api_data = response.json()["data"]
        yield api_data
        # Cleanup
        requests.delete(f"{BASE_URL}/api/system/apis/{api_data['id']}", headers=admin_headers)
    
    def test_get_sync_logs(self, admin_headers, test_api):
        """Admin can get sync logs for an API"""
        response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}/logs", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert "data" in data
        assert isinstance(data["data"], list)
        print(f"✓ Retrieved sync logs - found {len(data['data'])} entries")
    
    def test_get_sync_logs_with_limit(self, admin_headers, test_api):
        """Admin can limit sync logs"""
        response = requests.get(f"{BASE_URL}/api/system/apis/{test_api['id']}/logs?limit=5", headers=admin_headers)
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print("✓ Limit parameter works correctly for sync logs")


# ============================================================
# INTEGRATION TESTS
# ============================================================

class TestApiConfigIntegration:
    """Integration tests for complete API config workflow"""
    
    def test_full_crud_workflow(self, admin_headers):
        """Test complete Create -> Read -> Update -> Delete workflow"""
        # CREATE
        unique_name = f"TEST_CRUD_{uuid.uuid4().hex[:8]}"
        create_payload = {
            "name": unique_name,
            "description": "Integration test API",
            "base_url": "https://api.integration.com/v1",
            "api_type": "REST",
            "auth_type": "bearer",
            "is_active": True
        }
        create_response = requests.post(f"{BASE_URL}/api/system/apis", json=create_payload, headers=admin_headers)
        assert create_response.status_code == 200
        api_id = create_response.json()["data"]["id"]
        print(f"✓ CREATE: API config created with ID {api_id}")
        
        # READ
        read_response = requests.get(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert read_response.status_code == 200
        assert read_response.json()["data"]["name"] == unique_name
        print("✓ READ: API config retrieved successfully")
        
        # UPDATE
        update_payload = {"description": "Updated integration test API", "is_active": False}
        update_response = requests.put(f"{BASE_URL}/api/system/apis/{api_id}", json=update_payload, headers=admin_headers)
        assert update_response.status_code == 200
        
        # Verify update
        verify_response = requests.get(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert verify_response.json()["data"]["description"] == "Updated integration test API"
        assert verify_response.json()["data"]["is_active"] == False
        print("✓ UPDATE: API config updated and verified")
        
        # DELETE
        delete_response = requests.delete(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert delete_response.status_code == 200
        
        # Verify deletion
        verify_delete = requests.get(f"{BASE_URL}/api/system/apis/{api_id}", headers=admin_headers)
        assert verify_delete.status_code == 404
        print("✓ DELETE: API config deleted and verified")
        
        print("\n✓✓✓ Full CRUD workflow completed successfully ✓✓✓")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
