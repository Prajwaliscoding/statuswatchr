import { NavLink, Outlet } from "react-router";

export default function AppLayout() {
  const linkClass = ({ isActive }: { isActive: boolean }) =>
    [
      "px-3 py-2 rounded-md text-sm transition",
      isActive
        ? "bg-zinc-800 text-white"
        : "text-zinc-300 hover:bg-zinc-900 hover:text-white",
    ].join(" ");

  return (
    <div className="min-h-screen bg-zinc-950 text-zinc-100">
      <div className="mx-auto max-w-6xl px-4 py-6">
        {/* Top bar */}
        <div className="flex items-center justify-between">
          <div>
            <div className="text-xl font-semibold tracking-tight">
              StatusWatchr
            </div>
            <div className="text-sm text-zinc-400">
              Uptime monitoring for your services.
            </div>
          </div>

          <div className="flex gap-2">
            <NavLink to="/" end className={linkClass}>
              Watchrs
            </NavLink>
            <NavLink to="/incidents" className={linkClass}>
              Incidents
            </NavLink>
          </div>
        </div>

        {/* Content */}
        <div className="mt-6">
          <Outlet />
        </div>

        {/* Footer (tiny) */}
        <div className="mt-10 text-xs text-zinc-500">
          © {new Date().getFullYear()} StatusWatchr
        </div>
      </div>
    </div>
  );
}