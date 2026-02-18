export type BackendStatus = "UP" | "DOWN" | "UNKNOWN";

export type WatchrResponse = {
  id: number;
  name: string;
  url: string;
  intervalSeconds: number;
  status: BackendStatus;
  lastCheckedAt: string | null; // Instant serialized as ISO string
  lastError: string | null;
};

// UI types
export type UiStatus = "Up" | "Down" | "Unknown";

export type WatchRow = {
  id: number;
  name: string;
  url: string;
  freq: string; // "60s", "5m", etc
  status: UiStatus;
  lastCheckedAt?: string | null;
  lastError?: string | null;
};