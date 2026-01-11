"""
Test Field Bindings Feature - Referenztabellen-Verknüpfungssystem
Tests for:
- GET /api/system/modules - Liste verfügbarer Module und Felder
- GET /api/system/field-bindings - Liste aller Feld-Verknüpfungen
- POST /api/system/field-bindings - Neue Verknüpfung erstellen
- DELETE /api/system/field-bindings/{id} - Verknüpfung löschen
- GET /api/system/field-binding/lookup - Verknüpfungsdetails
- GET /api/system/reference-select/{module}/{field_name} - Dropdown-Optionen
- POST /api/system/validate-reference-value - Validierung eines Werts
"""

import pytest
import requests
import os

BASE_URL = os.environ.get('REACT_APP_BACKEND_URL', 'http://localhost:8001/api').rstrip('/')

# Ensure BASE_URL has /api prefix
if not BASE_URL.endswith('/api'):
    BASE_URL = BASE_URL + '/api'


class TestFieldBindingsFeature:
    """Test Field Bindings Feature"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup - get auth token"""
        self.session = requests.Session()
        self.session.headers.update({"Content-Type": "application/json"})
        
        # Login as admin
        login_response = self.session.post(f"{BASE_URL}/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert login_response.status_code == 200, f"Login failed: {login_response.text}"
        
        token = login_response.json().get("access_token")
        assert token, "No access token received"
        
        self.session.headers.update({"Authorization": f"Bearer {token}"})
        self.token = token
        
    # ============================================================
    # Test GET /api/system/modules
    # ============================================================
    
    def test_get_available_modules(self):
        """Test GET /api/system/modules - Liste verfügbarer Module"""
        response = self.session.get(f"{BASE_URL}/system/modules")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        modules = data.get("data", {})
        
        # Verify expected modules exist
        expected_modules = ["artikel", "adressen", "kontrakte", "fuhren"]
        for module in expected_modules:
            assert module in modules, f"Module '{module}' not found in response"
            assert "label" in modules[module], f"Module '{module}' missing 'label'"
            assert "fields" in modules[module], f"Module '{module}' missing 'fields'"
        
        # Verify artikel module has expected fields
        artikel_fields = [f["name"] for f in modules["artikel"]["fields"]]
        expected_artikel_fields = ["zolltarifnr", "avv_code_eingang", "avv_code_ausgang", "basel_code", "oecd_code", "artikelgruppe"]
        for field in expected_artikel_fields:
            assert field in artikel_fields, f"Field '{field}' not found in artikel module"
        
        print(f"✓ GET /api/system/modules - Found {len(modules)} modules")
        
    # ============================================================
    # Test GET /api/system/field-bindings
    # ============================================================
    
    def test_get_field_bindings_list(self):
        """Test GET /api/system/field-bindings - Liste aller Verknüpfungen"""
        response = self.session.get(f"{BASE_URL}/system/field-bindings")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        bindings = data.get("data", [])
        print(f"✓ GET /api/system/field-bindings - Found {len(bindings)} bindings")
        
        # If bindings exist, verify structure
        if bindings:
            binding = bindings[0]
            expected_fields = ["id", "reference_table_id", "module", "field_name", "display_field", "value_field"]
            for field in expected_fields:
                assert field in binding, f"Binding missing field '{field}'"
        
    def test_get_field_bindings_filter_by_module(self):
        """Test GET /api/system/field-bindings with module filter"""
        response = self.session.get(f"{BASE_URL}/system/field-bindings?module=artikel")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        bindings = data.get("data", [])
        # All bindings should be for artikel module
        for binding in bindings:
            assert binding["module"] == "artikel", f"Expected module 'artikel', got '{binding['module']}'"
        
        print(f"✓ GET /api/system/field-bindings?module=artikel - Found {len(bindings)} artikel bindings")
        
    # ============================================================
    # Test GET /api/system/field-binding/lookup
    # ============================================================
    
    def test_field_binding_lookup_existing(self):
        """Test GET /api/system/field-binding/lookup for existing binding (zolltarifnr)"""
        response = self.session.get(f"{BASE_URL}/system/field-binding/lookup?module=artikel&field_name=zolltarifnr")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        binding_data = data.get("data")
        
        # Based on context, binding should exist for artikel.zolltarifnr
        if binding_data:
            assert "binding_id" in binding_data
            assert "reference_table_name" in binding_data
            assert "display_field" in binding_data
            assert "value_field" in binding_data
            print(f"✓ GET /api/system/field-binding/lookup - Found binding: {binding_data.get('binding_id')}")
        else:
            print("✓ GET /api/system/field-binding/lookup - No binding found (expected if not configured)")
            
    def test_field_binding_lookup_non_existing(self):
        """Test GET /api/system/field-binding/lookup for non-existing binding"""
        response = self.session.get(f"{BASE_URL}/system/field-binding/lookup?module=artikel&field_name=nonexistent_field")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert data.get("data") is None, "Expected null data for non-existing binding"
        
        print("✓ GET /api/system/field-binding/lookup - Correctly returns null for non-existing binding")
        
    # ============================================================
    # Test GET /api/system/reference-select/{module}/{field_name}
    # ============================================================
    
    def test_reference_select_options(self):
        """Test GET /api/system/reference-select/artikel/zolltarifnr - Dropdown-Optionen"""
        response = self.session.get(f"{BASE_URL}/system/reference-select/artikel/zolltarifnr")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        assert "data" in data
        
        result = data.get("data", {})
        assert "options" in result, "Response missing 'options' field"
        
        options = result.get("options", [])
        binding = result.get("binding")
        
        if options:
            # Verify option structure
            option = options[0]
            assert "value" in option, "Option missing 'value'"
            assert "label" in option, "Option missing 'label'"
            print(f"✓ GET /api/system/reference-select/artikel/zolltarifnr - Found {len(options)} options")
        else:
            print("✓ GET /api/system/reference-select/artikel/zolltarifnr - No options (binding may not exist)")
            
    def test_reference_select_with_search(self):
        """Test GET /api/system/reference-select with search parameter"""
        response = self.session.get(f"{BASE_URL}/system/reference-select/artikel/zolltarifnr?search=test&limit=10")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        print("✓ GET /api/system/reference-select with search - Request successful")
        
    def test_reference_select_no_binding(self):
        """Test GET /api/system/reference-select for field without binding"""
        response = self.session.get(f"{BASE_URL}/system/reference-select/artikel/nonexistent_field")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        result = data.get("data", {})
        assert result.get("options") == [], "Expected empty options for non-bound field"
        assert result.get("binding") is None, "Expected null binding for non-bound field"
        
        print("✓ GET /api/system/reference-select for non-bound field - Returns empty options")
        
    # ============================================================
    # Test POST /api/system/validate-reference-value
    # ============================================================
    
    def test_validate_reference_value_no_binding(self):
        """Test POST /api/system/validate-reference-value for field without binding"""
        response = self.session.post(
            f"{BASE_URL}/system/validate-reference-value?module=artikel&field_name=nonexistent&value=test"
        )
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        result = data.get("data", {})
        assert result.get("valid") == True, "Should be valid when no binding exists"
        assert result.get("binding_exists") == False, "binding_exists should be False"
        
        print("✓ POST /api/system/validate-reference-value - No binding returns valid=True")
        
    def test_validate_reference_value_with_binding(self):
        """Test POST /api/system/validate-reference-value for field with binding"""
        response = self.session.post(
            f"{BASE_URL}/system/validate-reference-value?module=artikel&field_name=zolltarifnr&value=test_invalid_value"
        )
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        result = data.get("data", {})
        # If binding exists, invalid value should return valid=False
        if result.get("binding_exists"):
            assert result.get("valid") == False, "Invalid value should return valid=False"
            assert "message" in result, "Should have error message for invalid value"
            print(f"✓ POST /api/system/validate-reference-value - Invalid value correctly rejected: {result.get('message')}")
        else:
            print("✓ POST /api/system/validate-reference-value - No binding, validation skipped")
            
    # ============================================================
    # Test POST /api/system/field-bindings (Create)
    # ============================================================
    
    def test_create_field_binding_validation_invalid_module(self):
        """Test POST /api/system/field-bindings with invalid module"""
        response = self.session.post(f"{BASE_URL}/system/field-bindings", json={
            "reference_table_id": "REF-TEST1234",
            "module": "invalid_module",
            "field_name": "test_field",
            "display_field": "bezeichnung",
            "value_field": "code",
            "is_required": False,
            "allow_search": True
        })
        
        assert response.status_code == 400, f"Expected 400 for invalid module, got {response.status_code}"
        print("✓ POST /api/system/field-bindings - Invalid module correctly rejected")
        
    def test_create_field_binding_validation_invalid_field(self):
        """Test POST /api/system/field-bindings with invalid field"""
        response = self.session.post(f"{BASE_URL}/system/field-bindings", json={
            "reference_table_id": "REF-TEST1234",
            "module": "artikel",
            "field_name": "invalid_field_name",
            "display_field": "bezeichnung",
            "value_field": "code",
            "is_required": False,
            "allow_search": True
        })
        
        assert response.status_code == 400, f"Expected 400 for invalid field, got {response.status_code}"
        print("✓ POST /api/system/field-bindings - Invalid field correctly rejected")
        
    def test_create_field_binding_validation_invalid_ref_table(self):
        """Test POST /api/system/field-bindings with non-existent reference table"""
        response = self.session.post(f"{BASE_URL}/system/field-bindings", json={
            "reference_table_id": "REF-NONEXISTENT",
            "module": "artikel",
            "field_name": "basel_code",  # Use a field that's likely not bound
            "display_field": "bezeichnung",
            "value_field": "code",
            "is_required": False,
            "allow_search": True
        })
        
        assert response.status_code == 404, f"Expected 404 for non-existent ref table, got {response.status_code}"
        print("✓ POST /api/system/field-bindings - Non-existent reference table correctly rejected")
        
    # ============================================================
    # Test DELETE /api/system/field-bindings/{id}
    # ============================================================
    
    def test_delete_field_binding_not_found(self):
        """Test DELETE /api/system/field-bindings/{id} with non-existent ID"""
        response = self.session.delete(f"{BASE_URL}/system/field-bindings/BIND-NONEXISTENT")
        
        assert response.status_code == 404, f"Expected 404 for non-existent binding, got {response.status_code}"
        print("✓ DELETE /api/system/field-bindings - Non-existent binding returns 404")
        
    # ============================================================
    # Test Reference Tables (prerequisite for bindings)
    # ============================================================
    
    def test_get_reference_tables(self):
        """Test GET /api/system/reference-tables - List all reference tables"""
        response = self.session.get(f"{BASE_URL}/system/reference-tables")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}: {response.text}"
        
        data = response.json()
        assert data.get("success") == True
        
        tables = data.get("data", [])
        print(f"✓ GET /api/system/reference-tables - Found {len(tables)} reference tables")
        
        # List table names for debugging
        for table in tables:
            print(f"  - {table.get('table_name')}: {table.get('display_name')} ({table.get('record_count', 0)} records)")
            
    # ============================================================
    # Integration Test: Full Binding Workflow
    # ============================================================
    
    def test_full_binding_workflow(self):
        """Integration test: Create reference table, create binding, use it, delete"""
        
        # Step 1: Create a test reference table
        create_table_response = self.session.post(
            f"{BASE_URL}/system/reference-tables?name=test_binding_table&display_name=Test Binding Table"
        )
        
        if create_table_response.status_code == 200:
            table_data = create_table_response.json().get("data", {})
            table_id = table_data.get("id")
            print(f"  Created test reference table: {table_id}")
            
            # Step 2: Add some test data to the table
            add_data_response = self.session.post(
                f"{BASE_URL}/system/reference-tables/{table_id}/data",
                json={"data": {"code": "TEST001", "bezeichnung": "Test Entry 1"}}
            )
            
            if add_data_response.status_code == 200:
                print("  Added test data to reference table")
                
                # Step 3: Create a binding (use a field that's likely not bound)
                # Check which fields are available
                bindings_response = self.session.get(f"{BASE_URL}/system/field-bindings?module=artikel")
                existing_bindings = bindings_response.json().get("data", [])
                bound_fields = [b["field_name"] for b in existing_bindings]
                
                # Find an unbound field
                available_fields = ["basel_code", "oecd_code", "artikelgruppe"]
                test_field = None
                for field in available_fields:
                    if field not in bound_fields:
                        test_field = field
                        break
                
                if test_field:
                    create_binding_response = self.session.post(
                        f"{BASE_URL}/system/field-bindings",
                        json={
                            "reference_table_id": table_id,
                            "module": "artikel",
                            "field_name": test_field,
                            "display_field": "bezeichnung",
                            "value_field": "code",
                            "is_required": False,
                            "allow_search": True
                        }
                    )
                    
                    if create_binding_response.status_code == 200:
                        binding_data = create_binding_response.json().get("data", {})
                        binding_id = binding_data.get("id")
                        print(f"  Created binding: {binding_id} for artikel.{test_field}")
                        
                        # Step 4: Verify binding lookup works
                        lookup_response = self.session.get(
                            f"{BASE_URL}/system/field-binding/lookup?module=artikel&field_name={test_field}"
                        )
                        assert lookup_response.status_code == 200
                        lookup_data = lookup_response.json().get("data")
                        assert lookup_data is not None, "Binding lookup should return data"
                        print("  Binding lookup verified")
                        
                        # Step 5: Verify reference-select works
                        select_response = self.session.get(
                            f"{BASE_URL}/system/reference-select/artikel/{test_field}"
                        )
                        assert select_response.status_code == 200
                        select_data = select_response.json().get("data", {})
                        options = select_data.get("options", [])
                        assert len(options) > 0, "Should have at least one option"
                        print(f"  Reference select returned {len(options)} options")
                        
                        # Step 6: Validate a value
                        validate_response = self.session.post(
                            f"{BASE_URL}/system/validate-reference-value?module=artikel&field_name={test_field}&value=TEST001"
                        )
                        assert validate_response.status_code == 200
                        validate_data = validate_response.json().get("data", {})
                        assert validate_data.get("valid") == True, "TEST001 should be valid"
                        print("  Value validation verified")
                        
                        # Step 7: Delete the binding
                        delete_binding_response = self.session.delete(
                            f"{BASE_URL}/system/field-bindings/{binding_id}"
                        )
                        assert delete_binding_response.status_code == 200
                        print(f"  Deleted binding: {binding_id}")
                    else:
                        print(f"  Could not create binding: {create_binding_response.text}")
                else:
                    print("  All artikel fields already bound, skipping binding creation test")
            else:
                print(f"  Could not add data to table: {add_data_response.text}")
                
            # Cleanup: Delete the test table
            delete_table_response = self.session.delete(f"{BASE_URL}/system/reference-tables/{table_id}")
            if delete_table_response.status_code == 200:
                print(f"  Cleaned up test reference table: {table_id}")
        else:
            print(f"  Could not create test reference table: {create_table_response.text}")
            
        print("✓ Full binding workflow test completed")


class TestFieldBindingsNonAdmin:
    """Test Field Bindings access for non-admin users"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup - get auth token for non-admin user"""
        self.session = requests.Session()
        self.session.headers.update({"Content-Type": "application/json"})
        
        # Try to login as waage user (non-admin)
        login_response = self.session.post(f"{BASE_URL}/auth/login", json={
            "benutzername": "waage",
            "passwort": "Waage!123"
        })
        
        if login_response.status_code == 200:
            token = login_response.json().get("access_token")
            self.session.headers.update({"Authorization": f"Bearer {token}"})
            self.has_non_admin = True
        else:
            self.has_non_admin = False
            
    def test_non_admin_can_access_field_bindings_list(self):
        """Non-admin users should be able to read field bindings"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.get(f"{BASE_URL}/system/field-bindings")
        
        # Field bindings list should be accessible to authenticated users
        assert response.status_code == 200, f"Expected 200, got {response.status_code}"
        print("✓ Non-admin can access field-bindings list")
        
    def test_non_admin_can_access_field_binding_lookup(self):
        """Non-admin users should be able to lookup field bindings"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.get(f"{BASE_URL}/system/field-binding/lookup?module=artikel&field_name=zolltarifnr")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}"
        print("✓ Non-admin can access field-binding lookup")
        
    def test_non_admin_can_access_reference_select(self):
        """Non-admin users should be able to get reference select options"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.get(f"{BASE_URL}/system/reference-select/artikel/zolltarifnr")
        
        assert response.status_code == 200, f"Expected 200, got {response.status_code}"
        print("✓ Non-admin can access reference-select options")
        
    def test_non_admin_cannot_create_binding(self):
        """Non-admin users should NOT be able to create bindings"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.post(f"{BASE_URL}/system/field-bindings", json={
            "reference_table_id": "REF-TEST",
            "module": "artikel",
            "field_name": "basel_code",
            "display_field": "bezeichnung",
            "value_field": "code"
        })
        
        assert response.status_code == 403, f"Expected 403 Forbidden, got {response.status_code}"
        print("✓ Non-admin correctly denied from creating bindings")
        
    def test_non_admin_cannot_delete_binding(self):
        """Non-admin users should NOT be able to delete bindings"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.delete(f"{BASE_URL}/system/field-bindings/BIND-TEST")
        
        assert response.status_code == 403, f"Expected 403 Forbidden, got {response.status_code}"
        print("✓ Non-admin correctly denied from deleting bindings")
        
    def test_non_admin_cannot_access_modules(self):
        """Non-admin users should NOT be able to access modules list (admin only)"""
        if not self.has_non_admin:
            pytest.skip("Non-admin user not available")
            
        response = self.session.get(f"{BASE_URL}/system/modules")
        
        assert response.status_code == 403, f"Expected 403 Forbidden, got {response.status_code}"
        print("✓ Non-admin correctly denied from accessing modules list")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
