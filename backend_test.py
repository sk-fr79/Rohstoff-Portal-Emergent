#!/usr/bin/env python3
"""
Rohstoff Portal Backend API Tests
Tests all backend endpoints as specified in the review request
"""

import requests
import sys
import json
from datetime import datetime
from typing import Optional, Dict, Any

class RohstoffPortalTester:
    def __init__(self, base_url: str = "http://localhost:8001"):
        self.base_url = base_url
        self.token: Optional[str] = None
        self.tests_run = 0
        self.tests_passed = 0
        self.created_adresse_id: Optional[str] = None
        
    def log(self, message: str, level: str = "INFO"):
        """Log test messages"""
        timestamp = datetime.now().strftime("%H:%M:%S")
        print(f"[{timestamp}] {level}: {message}")
        
    def run_test(self, name: str, method: str, endpoint: str, expected_status: int, 
                 data: Optional[Dict] = None, headers: Optional[Dict] = None) -> tuple[bool, Dict]:
        """Run a single API test"""
        url = f"{self.base_url}/{endpoint}"
        
        # Default headers
        test_headers = {'Content-Type': 'application/json'}
        if self.token:
            test_headers['Authorization'] = f'Bearer {self.token}'
        if headers:
            test_headers.update(headers)
            
        self.tests_run += 1
        self.log(f"🔍 Testing {name}...")
        self.log(f"   {method} {url}")
        
        try:
            if method == 'GET':
                response = requests.get(url, headers=test_headers, timeout=10)
            elif method == 'POST':
                response = requests.post(url, json=data, headers=test_headers, timeout=10)
            elif method == 'PUT':
                response = requests.put(url, json=data, headers=test_headers, timeout=10)
            elif method == 'DELETE':
                response = requests.delete(url, headers=test_headers, timeout=10)
            else:
                raise ValueError(f"Unsupported method: {method}")
                
            success = response.status_code == expected_status
            
            if success:
                self.tests_passed += 1
                self.log(f"✅ PASSED - Status: {response.status_code}")
                try:
                    response_data = response.json()
                    if isinstance(response_data, dict) and response_data.get('success'):
                        self.log(f"   Response: success={response_data.get('success')}")
                    return True, response_data
                except:
                    return True, {}
            else:
                self.log(f"❌ FAILED - Expected {expected_status}, got {response.status_code}")
                try:
                    error_data = response.json()
                    self.log(f"   Error: {error_data}")
                    return False, error_data
                except:
                    self.log(f"   Response text: {response.text}")
                    return False, {}
                    
        except requests.exceptions.RequestException as e:
            self.log(f"❌ FAILED - Network Error: {str(e)}", "ERROR")
            return False, {}
        except Exception as e:
            self.log(f"❌ FAILED - Unexpected Error: {str(e)}", "ERROR")
            return False, {}
    
    def test_health_check(self) -> bool:
        """Test health check endpoint"""
        self.log("\n=== 1. HEALTH CHECK TEST ===")
        success, response = self.run_test(
            "Health Check",
            "GET", 
            "api/health",
            200
        )
        
        if success and response.get('status') == 'ok':
            self.log("✅ Health check returned correct status")
            return True
        else:
            self.log("❌ Health check failed or returned wrong status")
            return False
    
    def test_login(self) -> bool:
        """Test login functionality"""
        self.log("\n=== 2. LOGIN TEST ===")
        
        login_data = {
            "benutzername": "admin",
            "passwort": "Admin123!"
        }
        
        success, response = self.run_test(
            "Admin Login",
            "POST",
            "api/auth/login",
            200,
            data=login_data
        )
        
        if success and response.get('success') and response.get('access_token'):
            self.token = response['access_token']
            self.log("✅ Login successful, token received")
            self.log(f"   User: {response.get('user', {}).get('benutzername')}")
            return True
        else:
            self.log("❌ Login failed or no token received")
            return False
    
    def test_auth_me(self) -> bool:
        """Test current user endpoint"""
        self.log("\n=== 3. AUTH ME TEST ===")
        
        if not self.token:
            self.log("❌ No token available for auth test")
            return False
            
        success, response = self.run_test(
            "Get Current User",
            "GET",
            "api/auth/me",
            200
        )
        
        if success and response.get('success') and response.get('user'):
            self.log("✅ Auth me endpoint working")
            return True
        else:
            self.log("❌ Auth me endpoint failed")
            return False
    
    def test_adressen_crud(self) -> bool:
        """Test addresses CRUD operations"""
        self.log("\n=== 4. ADRESSEN CRUD TESTS ===")
        
        if not self.token:
            self.log("❌ No token available for adressen tests")
            return False
        
        # Test GET adressen (list)
        success, response = self.run_test(
            "Get Adressen List",
            "GET",
            "api/adressen",
            200
        )
        
        if not success:
            self.log("❌ Failed to get adressen list")
            return False
        
        self.log(f"✅ Found {len(response.get('data', []))} existing addresses")
        
        # Test POST adressen (create)
        new_adresse = {
            "name1": "Test GmbH",
            "strasse": "Teststraße",
            "hausnummer": "1",
            "plz": "12345",
            "ort": "Teststadt",
            "adresstyp": 1
        }
        
        success, response = self.run_test(
            "Create New Adresse",
            "POST",
            "api/adressen",
            200,
            data=new_adresse
        )
        
        if success and response.get('success') and response.get('data'):
            self.created_adresse_id = response['data'].get('id')
            self.log(f"✅ Created adresse with ID: {self.created_adresse_id}")
            self.log(f"   KDNR: {response['data'].get('kdnr')}")
        else:
            self.log("❌ Failed to create adresse")
            return False
        
        # Test GET single adresse
        if self.created_adresse_id:
            success, response = self.run_test(
                "Get Single Adresse",
                "GET",
                f"api/adressen/{self.created_adresse_id}",
                200
            )
            
            if success and response.get('success'):
                self.log("✅ Successfully retrieved single adresse")
            else:
                self.log("❌ Failed to retrieve single adresse")
                return False
        
        return True
    
    def test_kontrakte_crud(self) -> bool:
        """Test contracts CRUD operations"""
        self.log("\n=== 5. KONTRAKTE CRUD TESTS ===")
        
        if not self.token:
            self.log("❌ No token available for kontrakte tests")
            return False
            
        if not self.created_adresse_id:
            self.log("❌ No adresse_id available for kontrakt creation")
            return False
        
        # Test GET kontrakte (list)
        success, response = self.run_test(
            "Get Kontrakte List",
            "GET",
            "api/kontrakte",
            200
        )
        
        if not success:
            self.log("❌ Failed to get kontrakte list")
            return False
            
        self.log(f"✅ Found {len(response.get('data', []))} existing contracts")
        
        # Test POST kontrakte (create)
        new_kontrakt = {
            "adresse_id": self.created_adresse_id,
            "ist_einkauf": True,
            "bemerkungen_intern": "Test Kontrakt erstellt durch API Test"
        }
        
        success, response = self.run_test(
            "Create New Kontrakt",
            "POST",
            "api/kontrakte",
            200,
            data=new_kontrakt
        )
        
        if success and response.get('success') and response.get('data'):
            kontrakt_id = response['data'].get('id')
            buchungsnummer = response['data'].get('buchungsnummer')
            self.log(f"✅ Created kontrakt with ID: {kontrakt_id}")
            self.log(f"   Buchungsnummer: {buchungsnummer}")
            return True
        else:
            self.log("❌ Failed to create kontrakt")
            return False
    
    def test_dashboard_stats(self) -> bool:
        """Test dashboard statistics"""
        self.log("\n=== 6. DASHBOARD STATS TEST ===")
        
        if not self.token:
            self.log("❌ No token available for dashboard test")
            return False
        
        success, response = self.run_test(
            "Get Dashboard Stats",
            "GET",
            "api/dashboard/stats",
            200
        )
        
        if success and response.get('success') and response.get('data'):
            stats = response['data']
            self.log(f"✅ Dashboard stats retrieved:")
            self.log(f"   Adressen: {stats.get('adressen', 0)}")
            self.log(f"   Artikel: {stats.get('artikel', 0)}")
            self.log(f"   Offene Kontrakte: {stats.get('kontrakte_offen', 0)}")
            return True
        else:
            self.log("❌ Failed to get dashboard stats")
            return False
    
    def run_all_tests(self) -> int:
        """Run all backend tests"""
        self.log("🚀 Starting Rohstoff Portal Backend API Tests")
        self.log(f"   Base URL: {self.base_url}")
        
        # Run tests in sequence
        tests = [
            self.test_health_check,
            self.test_login,
            self.test_auth_me,
            self.test_adressen_crud,
            self.test_kontrakte_crud,
            self.test_dashboard_stats,
        ]
        
        for test in tests:
            try:
                if not test():
                    self.log(f"❌ Test {test.__name__} failed, stopping execution", "ERROR")
                    break
            except Exception as e:
                self.log(f"❌ Test {test.__name__} crashed: {str(e)}", "ERROR")
                break
        
        # Print final results
        self.log(f"\n📊 FINAL RESULTS:")
        self.log(f"   Tests Run: {self.tests_run}")
        self.log(f"   Tests Passed: {self.tests_passed}")
        self.log(f"   Success Rate: {(self.tests_passed/self.tests_run*100):.1f}%" if self.tests_run > 0 else "   Success Rate: 0%")
        
        if self.tests_passed == self.tests_run:
            self.log("🎉 ALL TESTS PASSED!")
            return 0
        else:
            self.log("💥 SOME TESTS FAILED!")
            return 1

def main():
    """Main test execution"""
    # Try to determine the correct base URL
    base_urls = [
        "http://localhost:8001",
        "http://127.0.0.1:8001",
    ]
    
    tester = None
    for url in base_urls:
        try:
            test_tester = RohstoffPortalTester(url)
            # Quick health check to see if this URL works
            response = requests.get(f"{url}/api/health", timeout=5)
            if response.status_code == 200:
                tester = test_tester
                break
        except:
            continue
    
    if not tester:
        print("❌ Could not connect to backend API on any URL")
        return 1
    
    return tester.run_all_tests()

if __name__ == "__main__":
    sys.exit(main())