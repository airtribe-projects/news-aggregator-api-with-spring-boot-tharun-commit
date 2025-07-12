import React, { useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8002/api";

export default function NewsAggregator() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState(null);
  const [news, setNews] = useState([]);
  const [category, setCategory] = useState("technology");
  const [country, setCountry] = useState("us");

  const handleRegister = async () => {
    await axios.post(`${API_BASE}/register`, { username, password });
    alert("Registered successfully");
  };

  const handleLogin = async () => {
    const res = await axios.post(`${API_BASE}/login`, { username, password });
    setToken(res.data.token);
  };

  const handleUpdatePreferences = async () => {
  try {
    await axios.put(
      `${API_BASE}/preferences`,
      { category, country },
      { headers: { Authorization: `Bearer ${token}` } }
    );
    alert("Preferences updated");
  } catch (error) {
    console.error("Update preferences failed", error);
    alert("Failed to update preferences");
  }
};


  const handleGetNews = async () => {
    const res = await axios.get(`${API_BASE}/news`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    setNews(res.data);
  };

  return (
    <div className="min-h-screen bg-gray-100 py-8 px-4 sm:px-6 lg:px-8 font-sans">
      <div className="max-w-4xl mx-auto bg-white shadow-md rounded-lg p-6">
        <h1 className="text-4xl font-bold text-center text-blue-800 mb-8">📰 News Aggregator</h1>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
          <input
            className="border p-3 rounded w-full"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            className="border p-3 rounded w-full"
            placeholder="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button className="bg-blue-600 text-white px-4 py-3 rounded hover:bg-blue-700" onClick={handleRegister}>
            Register
          </button>
          <button className="bg-green-600 text-white px-4 py-3 rounded hover:bg-green-700" onClick={handleLogin}>
            Login
          </button>
        </div>

        {token && (
          <>
            <div className="border-t pt-6 mt-6">
              <h2 className="text-2xl font-semibold text-gray-800 mb-4">Update Preferences</h2>
              <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                <input
                  className="border p-2 rounded"
                  placeholder="Category (e.g. technology)"
                  value={category}
                  onChange={(e) => setCategory(e.target.value)}
                />
                <input
                  className="border p-2 rounded"
                  placeholder="Country (e.g. us)"
                  value={country}
                  onChange={(e) => setCountry(e.target.value)}
                />
                <button className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600" onClick={handleUpdatePreferences}>
                  Save Preferences
                </button>
              </div>

              <div className="text-center">
                <button className="bg-purple-700 text-white px-6 py-2 rounded hover:bg-purple-800" onClick={handleGetNews}>
                  Fetch News
                </button>
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-8">
              {news.map((item, index) => (
                <div key={index} className="border shadow-sm rounded-lg p-4 bg-white hover:shadow-lg transition duration-200">
                  <h3 className="text-xl font-bold text-gray-800 mb-2">{item.title}</h3>
                  <p className="text-sm text-gray-600 mb-3">{item.description}</p>
                  <a
                    href={item.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="text-indigo-600 hover:underline text-sm"
                  >
                    Read more →
                  </a>
                </div>
              ))}
            </div>
          </>
        )}
      </div>
    </div>
  );
}
