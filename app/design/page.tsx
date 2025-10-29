"use client";

import { useState } from "react";

export default function DesignStudioPage() {
  const [selectedColor, setSelectedColor] = useState("#ffffff");
  const [selectedType, setSelectedType] = useState("tshirt");

  return (
    <section className="relative min-h-screen bg-gray-900 text-white overflow-hidden flex flex-col items-center justify-center">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />

      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[700px] h-[700px] rounded-full bg-[#18FFEA]/10 blur-[140px] opacity-70" />
      </div>

      {/* Content */}
      <div className="relative z-10 px-6 w-full max-w-6xl py-24 sm:py-32 text-center">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-6">
          Design <span className="text-[#18FFEA]">Studio</span>
        </h1>
        <p className="text-gray-300 text-lg md:text-xl leading-relaxed mb-10 max-w-2xl mx-auto">
          Create your own custom pieces in real time — choose your style, pick your color, and upload your artwork.
        </p>

        {/* Mock customization area */}
        <div className="bg-gray-800/70 border border-gray-700/40 rounded-xl shadow-md p-8 mt-8 flex flex-col lg:flex-row items-center justify-between gap-10 backdrop-blur-sm">
          {/* Preview */}
          <div className="flex flex-col items-center justify-center w-full lg:w-1/2">
            <div className="relative w-64 h-64 flex items-center justify-center bg-gray-700/70 rounded-xl shadow-inner">
              <div
                className="absolute inset-0 rounded-xl transition-all duration-300"
                style={{ backgroundColor: selectedColor, opacity: 0.9 }}
              ></div>
              <div className="z-10 text-gray-900 font-bold text-lg opacity-70">
                [ Your Design Here ]
              </div>
            </div>
            <p className="text-gray-400 mt-4 text-sm">
              Preview of your selected {selectedType}
            </p>
          </div>

          {/* Controls */}
          <div className="w-full lg:w-1/2 flex flex-col items-center lg:items-start text-left space-y-6">
            <div>
              <h2 className="text-xl font-semibold mb-2 text-[#18FFEA]">
                Apparel Type
              </h2>
              <div className="flex flex-wrap gap-3">
                {["tshirt", "hoodie", "crewneck"].map((type) => (
                  <button
                    key={type}
                    onClick={() => setSelectedType(type)}
                    className={`px-4 py-2 rounded-full border transition-all duration-200 ${
                      selectedType === type
                        ? "border-[#18FFEA] text-[#18FFEA]"
                        : "border-gray-600 text-gray-300 hover:border-[#18FFEA]/50 hover:text-[#18FFEA]"
                    }`}
                  >
                    {type.charAt(0).toUpperCase() + type.slice(1)}
                  </button>
                ))}
              </div>
            </div>

            <div>
              <h2 className="text-xl font-semibold mb-2 text-[#18FFEA]">
                Pick a Base Color
              </h2>
              <div className="flex gap-3 flex-wrap">
                {["#ffffff", "#000000", "#f87171", "#60a5fa", "#34d399"].map((color) => (
                  <button
                    key={color}
                    onClick={() => setSelectedColor(color)}
                    className={`w-10 h-10 rounded-full border-2 transition-transform duration-150 ${
                      selectedColor === color
                        ? "border-[#18FFEA] scale-110"
                        : "border-gray-600 hover:scale-105"
                    }`}
                    style={{ backgroundColor: color }}
                  ></button>
                ))}
              </div>
            </div>

            <div className="w-full lg:w-auto mt-4">
              <button className="bg-[#18FFEA] text-gray-900 font-semibold px-8 py-3 rounded-full hover:bg-[#14e6d4] transition-all duration-200 shadow-[0_0_15px_#18FFEA60]">
                Upload Artwork
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
