import { useEffect, useMemo, useState } from "react";
import CreateWatchrDialog, { type NewWatchr } from "../components/CreateWatchrDialog";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Badge } from "@/components/ui/badge";

import { fetchWatchrs } from "@/features/watchrs/api";
import type { BackendStatus, UiStatus, WatchRow, WatchrResponse } from "../types";

function secondsToLabel(seconds: number): string {
  if (!Number.isFinite(seconds) || seconds <= 0) return "-";
  if (seconds % 3600 === 0) return `${seconds / 3600}h`;
  if (seconds % 60 === 0) return `${seconds / 60}m`;
  return `${seconds}s`;
}

function mapStatus(s: BackendStatus): UiStatus {
  switch (s) {
    case "UP":
      return "Up";
    case "DOWN":
      return "Down";
    case "UNKNOWN":
    default:
      return "Unknown";
  }
}

function mapToRow(w: WatchrResponse): WatchRow {
  return {
    id: w.id,
    name: w.name,
    url: w.url,
    freq: secondsToLabel(w.intervalSeconds),
    status: mapStatus(w.status),
    lastCheckedAt: w.lastCheckedAt,
    lastError: w.lastError,
  };
}

function StatusBadge({ status }: { status: UiStatus }) {
  if (status === "Up")
    return (
      <Badge className="bg-emerald-600/20 text-emerald-300 border-emerald-700/40">
        Up
      </Badge>
    );
  if (status === "Unknown")
    return (
      <Badge className="bg-amber-600/20 text-amber-300 border-amber-700/40">
        Unknown
      </Badge>
    );
  return (
    <Badge className="bg-rose-600/20 text-rose-300 border-rose-700/40">
      Down
    </Badge>
  );
}

export default function WatchrListPage() {
  const [items, setItems] = useState<WatchRow[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  async function load() {
    try {
      setError(null);
      setLoading(true);

      const data = await fetchWatchrs();
      setItems(data.map(mapToRow));
    } catch (e) {
      setError(e instanceof Error ? e.message : "Failed to load watchrs");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
  }, []);

  // For now: still local create (we will wire POST next)
  const addWatchr = (w: NewWatchr) => {
    setItems((prev) => [
      {
        id: Date.now(),
        name: w.name,
        url: w.url,
        freq: w.frequency, // your dialog returns "60s"/"5m"
        status: "Unknown",
      },
      ...prev,
    ]);
  };

  const subtitle = useMemo(() => {
    if (loading) return "Loading your watchrs...";
    if (error) return "Couldn’t load watchrs. Check backend + CORS.";
    return "Add endpoints to monitor and see their latest status.";
  }, [loading, error]);

  return (
    <Card className="border-zinc-800 bg-zinc-950">
      <CardHeader className="flex flex-row items-center justify-between gap-3">
        <div>
          <CardTitle>Watchrs</CardTitle>
          <p className="text-sm text-zinc-400 mt-1">{subtitle}</p>
        </div>

        <div className="flex items-center gap-2">
          <Button variant="secondary" onClick={load} disabled={loading}>
            {loading ? "Refreshing..." : "Refresh"}
          </Button>
          <CreateWatchrDialog onCreate={addWatchr} />
        </div>
      </CardHeader>

      <CardContent>
        {error && (
          <div className="mb-4 rounded-md border border-rose-900/40 bg-rose-950/20 p-3 text-sm text-rose-200">
            {error}
          </div>
        )}

        <div className="rounded-md border border-zinc-800">
          <Table>
            <TableHeader>
              <TableRow className="border-zinc-800">
                <TableHead className="text-zinc-200">Name</TableHead>
                <TableHead className="hidden md:table-cell text-zinc-200">URL</TableHead>
                <TableHead className="text-zinc-200">Frequency</TableHead>
                <TableHead className="text-zinc-200">Status</TableHead>
              </TableRow>
            </TableHeader>

            <TableBody>
              {items.map((w) => (
                <TableRow key={w.id} className="border-zinc-800">
                  <TableCell className="font-medium text-zinc-100">
                    {w.name}
                  </TableCell>

                  <TableCell className="hidden md:table-cell text-zinc-400">
                    {w.url}
                  </TableCell>

                  <TableCell className="text-zinc-300">{w.freq}</TableCell>

                  <TableCell>
                    <StatusBadge status={w.status} />
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </CardContent>
    </Card>
  );
}