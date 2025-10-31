export default function DropsPage() {
  return (
    <section className="relative min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center overflow-hidden">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />
      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[600px] h-[600px] rounded-full bg-[#18FFEA]/10 blur-[120px] opacity-70" />
      </div>

      {/* Page content */}
      <div className="relative z-10 px-6 max-w-4xl text-center mb-10">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-6">
          Current <span className="text-[#18FFEA]">Drops</span>
        </h1>
        <p className="text-gray-300 text-lg md:text-xl leading-relaxed mb-12">
          Explore our latest releases — limited collections that capture creativity and style.
        </p>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
          {/* Example drop cards */}
          {["Urban Core", "No Signal", "Frostline"].map((drop) => (
            <div
              key={drop}
              className="rounded-xl bg-gray-800/70 border border-gray-700/40 shadow-md hover:shadow-[0_0_20px_#18FFEA30] p-6 transition-all duration-300 hover:-translate-y-1"
            >
              <div className="h-48 bg-gray-700/60 rounded-lg mb-4 flex items-center justify-center">
                <span className="text-gray-400 text-sm">[ Image Placeholder ]</span>
              </div>
              <h2 className="text-xl font-semibold mb-2">{drop}</h2>
              <p className="text-gray-400 text-sm mb-4">
                Limited release. Drops soon.
              </p>
              <button className="border border-[#18FFEA] text-[#18FFEA] font-semibold px-6 py-2 rounded-full hover:bg-[#18FFEA]/10 transition-all duration-200">
                View Drop
              </button>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
