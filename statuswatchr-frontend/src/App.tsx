import { useEffect, useState } from "react";
import { getWatchrs } from "./api/watchrApi";
import type { Watchr } from "./types/watchr";

function App() {
  const [watchrs, setWatchrs] = useState<Watchr[]>([]);

  useEffect(() => {
    getWatchrs()
      .then(setWatchrs)
      .catch((err) => {
        console.error("Failed to fetch watchrs", err);
      });
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Watchrs</h1>

      {watchrs.map((w) => (
        <div key={w.id}>{w.name}</div>
      ))}
    </div>
  );
}

export default App;