import React, { useEffect, useState } from "react";

import "./Dashboard.css";
import co2 from "../../assets/CO2-icon.png";
import temprerature from "../../assets/Temperarure-icon.png";
import solimoisture from "../../assets/Soil.moisture-icon.png";
import humidity from "../../assets/Humidity-icon.png";
import { ref, onValue } from "firebase/database";
import { rtdb } from "../../services/firebase";
import { saveUIDataToBackend } from "../../services/api";

// NavBar provided by MainLayout sidebar

interface SensorData {
  epoch: number;
  temp: number;
  rh: number;
  soilmo: number;
  co2?: number;
  time: string;
}
interface MetricCardProps {
  icon: React.ReactNode;
  title: string;
  value: string;
  description: string;
}

const MetricCard: React.FC<MetricCardProps> = ({
  icon,
  title,
  value,
  description,
}) => {
  return (
    <div className="metric-card">
      <div className="metric-header">
        <div className="metric-left">
          <span className="metric-icon">{icon}</span>
          <span className="metric-value">{value}</span>
        </div>
      </div>

      <h4>{title}</h4>

      <p>{description}</p>
    </div>
  );
};

const Dashboard: React.FC = () => {
  const [data, setData] = useState<SensorData | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const res = await getCurrent();
  //       setData(res.data);
  //     } catch (err) {
  //       setError("Failed to load sensor data");
  //     } finally {
  //       setLoading(false);
  //     }
  //   };

  //   fetchData();

  //   // optional: auto-refresh every 5 seconds
  //   const interval = setInterval(fetchData, 5000);
  //   return () => clearInterval(interval);
  // }, []);
  useEffect(() => {
    const envRef = ref(rtdb, "env");

    const unsubscribe = onValue(envRef, (snapshot) => {
      if (snapshot.exists()) {
        setData(snapshot.val() as SensorData);
      } else {
        setData(null);
      }
      setLoading(false);
    });

    return () => unsubscribe();
  }, []);

  useEffect(() => {
    if (!data) return;

    saveUIDataToBackend({
      temperature: data.temp,
      humidity: data.rh,
      soilMoisture: data.soilmo,
      co2: data.co2 ?? 0,
      timestamp: data.time,
    });
  }, [data]);

  if (loading) {
    return <p className="status-text">Loading sensor data...</p>;
  }

  if (error) {
    return <p className="status-text error">{error}</p>;
  }

  if (!data) {
    return <p className="status-text">No data available</p>;
  }

  // const formattedTime =
  //   data.timestamp !== undefined
  //     ? new Date(data.timestamp).toLocaleString()
  //     : "";

  return (
    <>
      <div className="dashboard-page">
        <main className="dashboard-content">
          <div>
            <h3 className="dashboard-card-h3">Real-Time Sensor Values</h3>
            <p className="sub-text-time">
              Last updated: <span className="timestamp">{data.time}</span>
            </p>
            <p className="sub-text">Live monitoring from ESP32</p>
          </div>
          {/* Content */}
          <div className="dashboard-inner">
            <div className="dashboard-card">
              <div className="metrics-grid">
                <MetricCard
                  icon={<img src={temprerature} alt="Temperature" />}
                  title="Temperature"
                  value={`${data.temp}°C`}
                  description="The greenhouse temperature is currently within the optimal range for healthy plant growth."
                />

                <MetricCard
                  icon={<img src={humidity} alt="Humidity" />}
                  title="Humidity"
                  value={`${data.rh}%`}
                  description="Humidity levels are stable and well-suited for maintaining plant health."
                />

                <MetricCard
                  icon={<img src={solimoisture} alt="Soil-Moisture" />}
                  title="Soil Moisture"
                  value={`${data.soilmo}%`}
                  description="Soil moisture is slightly below the recommended level for optimal crop growth."
                />

                <MetricCard
                  icon={<img src={co2} alt="CO₂-Level" />}
                  title="CO₂ Level"
                  value={`${data.co2 ?? 0} ppm`}
                  description="CO₂ concentration is within the normal range for efficient photosynthesis."
                />
              </div>
            </div>
          </div>
        </main>
      </div>
    </>
  );
};

export default Dashboard;
