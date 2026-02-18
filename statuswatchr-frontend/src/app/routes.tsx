import { Routes, Route } from "react-router";
import AppLayout from "./AppLayout";
import WatchListPage from "../features/watchrs/pages/WatchrListPage";

function IncidentsPage() {
  return (
    <div className="rounded-lg border border-zinc-800 bg-zinc-950 p-6 text-zinc-300">
      Incidents page (next).
    </div>
  );
}

export function AppRoutes() {
  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route path="/" element={<WatchListPage />} />
        <Route path="/incidents" element={<IncidentsPage />} />
        <Route
          path="*"
          element={<div className="p-6 text-zinc-300">404 Not Found</div>}
        />
      </Route>
    </Routes>
  );
}