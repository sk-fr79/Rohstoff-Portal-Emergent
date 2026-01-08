"""
UST-ID Validation Tests
Tests for EU VIES API integration and protokoll functionality
"""
import pytest
import requests
import os
from datetime import datetime

# Use localhost for testing since we're inside the container
BASE_URL = "http://localhost:8001"

class TestUstIdValidation:
    """Tests for UST-ID validation via EU VIES API"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup: Login and get token"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        self.token = data["access_token"]
        self.headers = {
            "Authorization": f"Bearer {self.token}",
            "Content-Type": "application/json"
        }
        
        # Get or create a test address
        response = requests.get(f"{BASE_URL}/api/adressen", headers=self.headers)
        assert response.status_code == 200
        adressen = response.json()["data"]
        
        if adressen:
            self.test_adresse_id = adressen[0]["id"]
        else:
            # Create a test address
            response = requests.post(
                f"{BASE_URL}/api/adressen?skip_validation=true",
                headers=self.headers,
                json={
                    "name1": "TEST_UstId_Validation_GmbH",
                    "land": "Deutschland",
                    "ist_firma": True,
                    "umsatzsteuer_lkz": "DE",
                    "umsatzsteuer_id": "123456789"
                }
            )
            assert response.status_code == 200
            self.test_adresse_id = response.json()["data"]["id"]
    
    def test_validate_ustid_endpoint_exists(self):
        """Test that POST /api/ustid/validate endpoint exists"""
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "123456789"
            }
        )
        # Should not return 404 or 405
        assert response.status_code in [200, 400, 422, 500, 502, 504]
        print(f"UST-ID validate endpoint returned: {response.status_code}")
    
    def test_validate_ustid_with_invalid_ustid(self):
        """Test validation with an invalid UST-ID (expected to be invalid)"""
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "000000000"  # Invalid test UST-ID
            }
        )
        
        # Should return 200 with success=True or success=False
        assert response.status_code == 200
        data = response.json()
        
        # Check response structure
        assert "data" in data
        assert "adresse_id" in data["data"]
        assert "laenderkennzeichen" in data["data"]
        assert "ustid" in data["data"]
        assert "gueltig" in data["data"]
        assert "abfrage_datum" in data["data"]
        
        print(f"Validation result: gueltig={data['data']['gueltig']}")
        print(f"Response: {data}")
    
    def test_validate_ustid_with_german_format(self):
        """Test validation with German UST-ID format (DE + 9 digits)"""
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "123456789"
            }
        )
        
        assert response.status_code == 200
        data = response.json()
        
        # Protokoll should be created regardless of validity
        assert "data" in data
        assert data["data"]["laenderkennzeichen"] == "DE"
        print(f"German UST-ID validation: {data}")
    
    def test_validate_ustid_with_austrian_format(self):
        """Test validation with Austrian UST-ID format (AT + U + 8 digits)"""
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "AT",
                "ustid": "U12345678"
            }
        )
        
        assert response.status_code == 200
        data = response.json()
        assert "data" in data
        print(f"Austrian UST-ID validation: {data}")
    
    def test_validate_ustid_missing_fields(self):
        """Test validation with missing required fields"""
        # Missing ustid
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE"
            }
        )
        assert response.status_code == 422  # Validation error
        
        # Missing laenderkennzeichen
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "ustid": "123456789"
            }
        )
        assert response.status_code == 422
        
        print("Missing fields validation working correctly")
    
    def test_validate_ustid_without_auth(self):
        """Test that validation requires authentication"""
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "123456789"
            }
        )
        assert response.status_code == 401
        print("Authentication required - working correctly")


class TestUstIdProtokoll:
    """Tests for UST-ID validation protokoll (history)"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup: Login and get token"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200
        data = response.json()
        self.token = data["access_token"]
        self.headers = {
            "Authorization": f"Bearer {self.token}",
            "Content-Type": "application/json"
        }
        
        # Get a test address
        response = requests.get(f"{BASE_URL}/api/adressen", headers=self.headers)
        assert response.status_code == 200
        adressen = response.json()["data"]
        self.test_adresse_id = adressen[0]["id"] if adressen else None
    
    def test_get_protokoll_endpoint_exists(self):
        """Test that GET /api/ustid/protokoll/{adresse_id} endpoint exists"""
        if not self.test_adresse_id:
            pytest.skip("No test address available")
        
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/{self.test_adresse_id}",
            headers=self.headers
        )
        assert response.status_code == 200
        data = response.json()
        
        assert data["success"] == True
        assert "data" in data
        assert "count" in data
        assert isinstance(data["data"], list)
        
        print(f"Protokoll count: {data['count']}")
        print(f"Protokoll entries: {len(data['data'])}")
    
    def test_protokoll_after_validation(self):
        """Test that protokoll is created after validation"""
        if not self.test_adresse_id:
            pytest.skip("No test address available")
        
        # Get current protokoll count
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/{self.test_adresse_id}",
            headers=self.headers
        )
        initial_count = response.json()["count"]
        
        # Perform a validation
        response = requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "999888777"  # Test UST-ID
            }
        )
        assert response.status_code == 200
        
        # Check protokoll count increased
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/{self.test_adresse_id}",
            headers=self.headers
        )
        new_count = response.json()["count"]
        
        assert new_count > initial_count, "Protokoll should be created after validation"
        print(f"Protokoll count increased from {initial_count} to {new_count}")
    
    def test_protokoll_contains_required_fields(self):
        """Test that protokoll entries contain all required fields"""
        if not self.test_adresse_id:
            pytest.skip("No test address available")
        
        # First create a protokoll entry
        requests.post(
            f"{BASE_URL}/api/ustid/validate",
            headers=self.headers,
            json={
                "adresse_id": self.test_adresse_id,
                "laenderkennzeichen": "DE",
                "ustid": "111222333"
            }
        )
        
        # Get protokoll
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/{self.test_adresse_id}",
            headers=self.headers
        )
        data = response.json()
        
        if data["count"] > 0:
            entry = data["data"][0]
            
            # Check required fields
            required_fields = [
                "id", "adresse_id", "laenderkennzeichen", "ustid",
                "gueltig", "abfrage_datum"
            ]
            for field in required_fields:
                assert field in entry, f"Missing field: {field}"
            
            # Check optional fields exist (can be None)
            optional_fields = [
                "firmenname", "adresse", "strasse", "plz", "ort",
                "request_identifier", "fehler_code", "abgefragt_von"
            ]
            for field in optional_fields:
                assert field in entry, f"Missing optional field: {field}"
            
            print(f"Protokoll entry fields verified: {list(entry.keys())}")
    
    def test_protokoll_sorted_by_date(self):
        """Test that protokoll is sorted by date (newest first)"""
        if not self.test_adresse_id:
            pytest.skip("No test address available")
        
        # Create multiple protokoll entries
        for i in range(3):
            requests.post(
                f"{BASE_URL}/api/ustid/validate",
                headers=self.headers,
                json={
                    "adresse_id": self.test_adresse_id,
                    "laenderkennzeichen": "DE",
                    "ustid": f"00000000{i}"
                }
            )
        
        # Get protokoll
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/{self.test_adresse_id}",
            headers=self.headers
        )
        data = response.json()
        
        if data["count"] >= 2:
            dates = [entry["abfrage_datum"] for entry in data["data"]]
            # Check descending order (newest first)
            assert dates == sorted(dates, reverse=True), "Protokoll should be sorted by date descending"
            print("Protokoll correctly sorted by date (newest first)")
    
    def test_protokoll_without_auth(self):
        """Test that protokoll requires authentication"""
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/some-id"
        )
        assert response.status_code == 401
        print("Authentication required for protokoll - working correctly")
    
    def test_protokoll_for_nonexistent_address(self):
        """Test protokoll for non-existent address returns empty list"""
        response = requests.get(
            f"{BASE_URL}/api/ustid/protokoll/nonexistent-address-id",
            headers=self.headers
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["count"] == 0
        assert data["data"] == []
        print("Empty protokoll for non-existent address - working correctly")


class TestAdresseSaveAfterSchemaFix:
    """Tests for saving addresses after schema fix (nullish vs optional)"""
    
    @pytest.fixture(autouse=True)
    def setup(self):
        """Setup: Login and get token"""
        response = requests.post(f"{BASE_URL}/api/auth/login", json={
            "benutzername": "admin",
            "passwort": "Admin123!"
        })
        assert response.status_code == 200
        data = response.json()
        self.token = data["access_token"]
        self.headers = {
            "Authorization": f"Bearer {self.token}",
            "Content-Type": "application/json"
        }
    
    def test_create_address_with_minimal_data(self):
        """Test creating address with only required fields"""
        response = requests.post(
            f"{BASE_URL}/api/adressen?skip_validation=true",
            headers=self.headers,
            json={
                "name1": "TEST_Minimal_Address"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        assert data["data"]["name1"] == "TEST_Minimal_Address"
        print(f"Created minimal address with ID: {data['data']['id']}")
    
    def test_create_address_with_null_fields(self):
        """Test creating address with explicit null values"""
        response = requests.post(
            f"{BASE_URL}/api/adressen?skip_validation=true",
            headers=self.headers,
            json={
                "name1": "TEST_Null_Fields_Address",
                "vorname": None,
                "strasse": None,
                "plz": None,
                "ort": None,
                "telefon": None,
                "email": None
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["success"] == True
        print("Address with null fields created successfully")
    
    def test_update_address_with_partial_data(self):
        """Test updating address with partial data"""
        # First create an address
        response = requests.post(
            f"{BASE_URL}/api/adressen?skip_validation=true",
            headers=self.headers,
            json={
                "name1": "TEST_Update_Address",
                "strasse": "Teststraße",
                "plz": "12345",
                "ort": "Teststadt"
            }
        )
        assert response.status_code == 200
        adresse_id = response.json()["data"]["id"]
        
        # Update with partial data
        response = requests.put(
            f"{BASE_URL}/api/adressen/{adresse_id}?skip_validation=true",
            headers=self.headers,
            json={
                "name1": "TEST_Updated_Address"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["data"]["name1"] == "TEST_Updated_Address"
        # Other fields should remain unchanged
        assert data["data"]["strasse"] == "Teststraße"
        print("Partial update working correctly")
    
    def test_save_address_with_ustid(self):
        """Test saving address with UST-ID fields"""
        response = requests.post(
            f"{BASE_URL}/api/adressen?skip_validation=true",
            headers=self.headers,
            json={
                "name1": "TEST_UstId_Address_GmbH",
                "land": "Deutschland",
                "ist_firma": True,
                "umsatzsteuer_lkz": "DE",
                "umsatzsteuer_id": "123456789",
                "steuernummer": "12/345/67890"
            }
        )
        assert response.status_code == 200
        data = response.json()
        assert data["data"]["umsatzsteuer_lkz"] == "DE"
        assert data["data"]["umsatzsteuer_id"] == "123456789"
        print("Address with UST-ID saved successfully")


if __name__ == "__main__":
    pytest.main([__file__, "-v", "--tb=short"])
