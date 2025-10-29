export default function Home() {
  return (
    <section className="relative flex flex-col items-center justify-center text-center min-h-screen bg-gray-900 text-white overflow-hidden">
    {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black"></div>

      {/* Cyan glow */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[700px] h-[700px] rounded-full bg-[#18FFEA]/10 blur-[140px] opacity-70"></div>
      </div>

      {/* Hero Content */}
      <div className="relative z-10 px-6 max-w-3xl">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-6">
          Wear <span className="text-[#18FFEA]">What Defines You</span>
        </h1>

        <p className="text-gray-300 text-lg md:text-xl leading-relaxed mb-10">
          Doodz Threads brings your imagination to life — custom designs,
          premium materials, and drops that hit different.
        </p>

        <div className="flex flex-col sm:flex-row justify-center items-center gap-4">
          <button className="bg-[#18FFEA] text-gray-900 font-semibold px-8 py-3 rounded-full hover:bg-[#14e6d4] transition-all duration-200 shadow-[0_0_15px_#18FFEA60]">
            Shop the Drop
          </button>
          <button className="border border-[#18FFEA] text-[#18FFEA] font-semibold px-8 py-3 rounded-full hover:bg-[#18FFEA]/10 transition-all duration-200">
            Design Your Own
          </button>
        </div>
      </div>
    </section>
  );
}
