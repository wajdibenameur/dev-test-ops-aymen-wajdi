import { Injectable } from "@angular/core";
import { Commande } from "../models/commande";
import { CreateCommandeRequest } from "../models/createCommandeDTO";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class CommandeService {
  private api = `${environment.apiUrl}/orders`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Commande[]>(this.api);
  }

  create(request: CreateCommandeRequest) {
    return this.http.post(`${this.api}/create`, request);
  }

  update(id: number, commande: Commande) {
    return this.http.put(`${this.api}/${id}`, commande);
  }

  delete(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
