export default function CollectionsPage() {
  return (
    <section className="relative min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center overflow-hidden">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />
      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[700px] h-[700px] rounded-full bg-[#18FFEA]/10 blur-[140px] opacity-70" />
      </div>

      <div className="relative z-10 text-center px-6 max-w-3xl mb-10">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-6">
          Our <span className="text-[#18FFEA]">Collections</span>
        </h1>
        <p className="text-gray-300 text-lg md:text-xl leading-relaxed mb-10">
          Explore every Doodz Threads drop — from streetwear essentials to
          limited edition releases. Discover what defines your look.
        </p>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8 mt-10">
          {["Mens", "Womens", "Kids", "Limited"].map((col) => (
            <div
              key={col}
              className="rounded-xl border border-gray-700/40 bg-gray-800/50 hover:bg-gray-800 transition-all duration-200 shadow-lg p-8 text-center cursor-pointer"
            >
              <h2 className="text-2xl font-semibold text-[#18FFEA] mb-2">
                {col}
              </h2>
              <p className="text-gray-400 text-sm">
                Shop the latest {col.toLowerCase()} collection.
              </p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
