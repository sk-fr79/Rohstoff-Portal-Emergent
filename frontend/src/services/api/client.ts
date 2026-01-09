import axios from 'axios';
import { useAuthStore } from '@/store/authStore';

const API_URL = import.meta.env.VITE_API_URL || '/api';

// Axios Instance
export const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

// Request Interceptor - Token hinzufügen
api.interceptors.request.use(
  (config) => {
    const { accessToken } = useAuthStore.getState();
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor - Token Refresh
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    
    // Token abgelaufen - Refresh versuchen
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      
      try {
        const { refreshToken, setTokens, logout } = useAuthStore.getState();
        
        if (!refreshToken) {
          logout();
          window.location.href = '/login';
          return Promise.reject(error);
        }
        
        const response = await axios.post(`${API_URL}/auth/refresh`, {
          refreshToken,
        });
        
        if (response.data.success) {
          setTokens(response.data.accessToken, response.data.refreshToken);
          originalRequest.headers.Authorization = `Bearer ${response.data.accessToken}`;
          return api(originalRequest);
        }
      } catch (refreshError) {
        useAuthStore.getState().logout();
        window.location.href = '/login';
        return Promise.reject(refreshError);
      }
    }
    
    return Promise.reject(error);
  }
);

// API-Funktionen
export const authApi = {
  login: (benutzername: string, passwort: string) =>
    api.post('/auth/login', { benutzername, passwort }),
  
  logout: () => api.post('/auth/logout'),
  
  me: () => api.get('/auth/me'),
};

export const adressenApi = {
  getAll: (params?: Record<string, unknown>) => api.get('/adressen', { params }),
  search: (params?: Record<string, unknown>) => api.get('/adressen', { params }),
  getById: (id: string) => api.get(`/adressen/${id}`),
  create: (data: unknown) => api.post('/adressen', data),
  update: (id: string, data: unknown) => api.put(`/adressen/${id}`, data),
  delete: (id: string) => api.delete(`/adressen/${id}`),
};

export const artikelApi = {
  list: (params?: Record<string, unknown>) => api.get('/artikel', { params }),
  search: (params?: Record<string, unknown>) => api.get('/artikel', { params }),
  getById: (id: string) => api.get(`/artikel/${id}`),
  create: (data: unknown) => api.post('/artikel', data),
  update: (id: string, data: unknown) => api.put(`/artikel/${id}`, data),
  delete: (id: string) => api.delete(`/artikel/${id}`),
};

export const kontrakteApi = {
  list: (params?: Record<string, unknown>) => api.get('/kontrakte', { params }),
  search: (params?: Record<string, unknown>) => api.get('/kontrakte', { params }),
  getById: (id: string) => api.get(`/kontrakte/${id}`),
  create: (data: unknown) => api.post('/kontrakte', data),
  update: (id: string, data: unknown) => api.put(`/kontrakte/${id}`, data),
  delete: (id: string) => api.delete(`/kontrakte/${id}`),
  abschliessen: (id: string) => api.post(`/kontrakte/${id}/abschliessen`),
  addPosition: (id: string, data: unknown) => api.post(`/kontrakte/${id}/positionen`, data),
};
