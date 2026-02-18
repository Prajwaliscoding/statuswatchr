import { useState } from "react";
import { Button } from "../../../components/ui/button";
import { Input } from "../../../components/ui/input";
import { Label } from "../../../components/ui/label";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogFooter,
} from "../../../components/ui/dialog";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../../../components/ui/select";

export type NewWatchr = {
  name: string;
  url: string;
  frequency: string;
};

export default function CreateWatchrDialog({
  onCreate,
}: {
  onCreate: (watch: NewWatchr) => void;
}) {
  const [open, setOpen] = useState(false);
  const [name, setName] = useState("");
  const [url, setUrl] = useState("");
  const [frequency, setFrequency] = useState("60s");

  function reset() {
    setName("");
    setUrl("");
    setFrequency("60s");
  }

  function looksLikeUrl(value: string) {
    try {
      const u = new URL(value);
      return u.protocol === "http:" || u.protocol === "https:";
    } catch {
      return false;
    }
  }

  const canSubmit =
    name.trim().length > 0 && url.trim().length > 0 && looksLikeUrl(url.trim());

  function handleAdd() {
    if (!canSubmit) return;
    onCreate({ name: name.trim(), url: url.trim(), frequency });
    setOpen(false);
    reset();
  }

  return (
    <Dialog
      open={open}
      onOpenChange={(v) => {
        setOpen(v);
        if (!v) reset();
      }}
    >
      <DialogTrigger asChild>
        <Button>Create</Button>
      </DialogTrigger>

      <DialogContent className="border-zinc-800 bg-zinc-950 text-zinc-100">
        <DialogHeader>
          <DialogTitle>New Watch</DialogTitle>
        </DialogHeader>

        <div className="space-y-4">
          <div className="space-y-2">
            <Label>Name</Label>
            <Input
              className="bg-zinc-950 border-zinc-800"
              placeholder="e.g. Main API"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>

          <div className="space-y-2">
            <Label>URL</Label>
            <Input
              className="bg-zinc-950 border-zinc-800"
              placeholder="https://example.com/health"
              value={url}
              onChange={(e) => setUrl(e.target.value)}
            />
            {url.length > 0 && !looksLikeUrl(url) && (
              <div className="text-xs text-rose-300">
                Enter a valid http/https URL.
              </div>
            )}
          </div>

          <div className="space-y-2">
            <Label>Frequency</Label>
            <Select value={frequency} onValueChange={setFrequency}>
              <SelectTrigger className="bg-zinc-950 border-zinc-800">
                <SelectValue />
              </SelectTrigger>
              <SelectContent className="border-zinc-800 bg-zinc-950 text-zinc-100">
                <SelectItem value="30s">Every 30s</SelectItem>
                <SelectItem value="60s">Every 60s</SelectItem>
                <SelectItem value="5m">Every 5m</SelectItem>
                <SelectItem value="10m">Every 10m</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>

        <DialogFooter className="mt-2">
          <Button
            variant="secondary"
            className="bg-zinc-900 border-zinc-800"
            onClick={() => setOpen(false)}
          >
            Cancel
          </Button>
          <Button onClick={handleAdd} disabled={!canSubmit}>
            Add Watch
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}