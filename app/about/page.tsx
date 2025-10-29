export default function AboutPage() {
  return (
    <section className="relative min-h-screen bg-gray-900 text-white overflow-hidden flex flex-col items-center justify-center">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />

      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[600px] h-[600px] rounded-full bg-[#18FFEA]/10 blur-[120px] opacity-70" />
      </div>

      {/* Content */}
      <div className="relative z-10 px-6 max-w-4xl text-center py-24 sm:py-32">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-8">
          About <span className="text-[#18FFEA]">Doodz Threads</span>
        </h1>

        <p className="text-gray-300 text-lg md:text-xl leading-relaxed mb-10">
          Doodz Threads is a lifestyle brand born from creativity, authenticity,
          and the drive to wear what defines you. We fuse bold design with
          minimal aesthetic to deliver apparel that feels as good as it looks.
        </p>

        <p className="text-gray-400 text-base md:text-lg leading-relaxed mb-10">
          Our journey started with a simple idea — to give creators and everyday
          dreamers a platform to express themselves through design. Whether
          you’re rocking our limited drops or crafting your own style in the
          design studio, we’re here to make self-expression effortless and
          premium.
        </p>

        {/* Feature grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8 mt-12">
          {[
            {
              title: "Premium Quality",
              desc: "We partner with trusted suppliers to ensure every piece meets the highest standards of comfort and durability.",
            },
            {
              title: "Sustainable Vision",
              desc: "Print-on-demand means zero waste — every item is made only when you order it.",
            },
            {
              title: "Creator-Driven",
              desc: "Our design studio lets anyone bring their ideas to life. Made by creators, for creators.",
            },
          ].map((feature) => (
            <div
              key={feature.title}
              className="bg-gray-800/70 border border-gray-700/40 rounded-xl p-6 shadow-md hover:shadow-[0_0_20px_#18FFEA30] transition-all duration-300 hover:-translate-y-1"
            >
              <h2 className="text-xl font-semibold mb-2 text-[#18FFEA]">
                {feature.title}
              </h2>
              <p className="text-gray-300 text-sm">{feature.desc}</p>
            </div>
          ))}
        </div>

        {/* Closing statement */}
        <p className="mt-16 text-gray-400 text-lg max-w-2xl mx-auto leading-relaxed">
          From concept to print, Doodz Threads celebrates individuality and the
          freedom to create your own look. This isn’t fast fashion — it’s
          personal fashion.
        </p>
      </div>
    </section>
  );
}
