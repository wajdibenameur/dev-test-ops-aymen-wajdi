import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private _apiUrl: string;

  constructor() {
    // Détermine l'URL basée sur l'environnement
    if (window.location.hostname === 'localhost') {
      this._apiUrl = 'http://localhost:8090/api';
    } else {
      this._apiUrl = 'http://springboot-service:8090/api';
    }
  }

  get apiUrl(): string {
    return this._apiUrl;
  }
}