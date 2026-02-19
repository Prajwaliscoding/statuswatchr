import axios from "axios";                     // HTTP requesting
import type { Watchr } from "../types/watchr";

const API = axios.create({
  baseURL: "http://localhost:8080",
});

export async function getWatchrs(): Promise<Watchr[]> {
  const response = await API.get("/api/watchrs");
  return response.data;
}