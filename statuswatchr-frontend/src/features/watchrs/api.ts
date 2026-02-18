import { api } from "@/shared/api/client";
import type { WatchrResponse } from "./types";

export function fetchWatchrs() {
  return api<WatchrResponse[]>("/api/watchrs");
}