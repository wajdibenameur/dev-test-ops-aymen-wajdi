import { Injectable } from "@angular/core";
import { Product } from "../models/product";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class ProductService {
  private api = `${environment.apiUrl}/products`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Product[]>(this.api);
  }

  create(product: Product) {
    return this.http.post(`${this.api}/create`, product);
  }

  update(id: number, product: Product) {
    return this.http.put<Product>(`${this.api}/${id}`, product);
  }

  delete(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
