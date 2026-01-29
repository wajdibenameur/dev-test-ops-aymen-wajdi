import { Product } from "./product";
import { User } from "./user";

export interface Commande {
  id?: number;
  user: User;
  products: Product[];
  status: string;
  dateCommande: string;
  priceTotale: number;
}
