import axios from "axios";
import type { HistoryData } from "../types/HistoryData";
import type { SensorData } from "../types/SensorData";

// Base API URL used by the app. Exported so other modules can derive related endpoints (e.g. WS).
export const API_URL = "http://localhost:8082/api";

export const getHistory = async () => {
  const resp = await axios.get(`${API_URL}/history`);
  const payload = resp.data;
  // normalize possible shapes:
  // - [ {...}, ... ]
  // - { data: [ {...}, ... ] }
  // - { data: {...} }
  if (Array.isArray(payload))
    return { data: payload } as { data: HistoryData[] };
  if (payload && Array.isArray((payload as any).data))
    return { data: (payload as any).data } as { data: HistoryData[] };
  if (
    payload &&
    (payload as any).data &&
    typeof (payload as any).data === "object"
  )
    return { data: [(payload as any).data] } as { data: HistoryData[] };
  return { data: [] } as { data: HistoryData[] };
};

// Try to fetch a dedicated current/latest endpoint; if not available,
// fallback to the last item of the history endpoint consumer-side.
export const getCurrent = async () => {
  try {
    const resp = await axios.get(`${API_URL}/latest`);
    const payload = resp.data;
    // payload might be { data: {...} } or the object itself
    const data =
      payload && (payload as any).data ? (payload as any).data : payload;
    return { data: data as SensorData } as { data: SensorData };
  } catch (err) {
    // fallback to history
    const h = await getHistory();
    const last = h.data && h.data.length ? h.data[h.data.length - 1] : null;
    if (!last) throw new Error("No history data available");
    const mapped: SensorData = {
      temperature: last.temperature,
      humidity: last.humidity,
      soilMoisture: last.soilMoisture,
      co2: (last as any).co2 ?? 0,
    };
    return { data: mapped } as { data: SensorData };
  }
};
