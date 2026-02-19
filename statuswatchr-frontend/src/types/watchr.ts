export type WatchrStatus = "UP" | "DOWN" | "UNKNOWN";

export type Watchr = {
  id: number;
  name: string;
  url: string;
  intervalSeconds: number;
  status: WatchrStatus;
  lastCheckedAt: string;   // Instant becomes ISO string in JSON
  lastError: string | null; // could be null sometimes
};