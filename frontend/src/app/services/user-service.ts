import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { User } from "../models/user";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class UserService {
  private api = `${environment.apiUrl}/users`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<User[]>(this.api);
  }

  getById(id: number) {
    return this.http.get<User>(`${this.api}/${id}`);
  }

  create(user: User) {
    return this.http.post(`${this.api}/create`, user);
  }

  update(id: number, user: User) {
    return this.http.put<User>(`${this.api}/${id}`, user);
  }

  delete(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
