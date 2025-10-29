export default function CartPage() {
  return (
    <section className="relative min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center overflow-hidden">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />
      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[600px] h-[600px] rounded-full bg-[#18FFEA]/10 blur-[120px] opacity-70" />
      </div>

      {/* Cart content */}
      <div className="relative z-10 w-full max-w-4xl px-6 py-24 sm:py-32 text-center">
        <h1 className="text-5xl md:text-6xl font-extrabold tracking-tight mb-10">
          Your <span className="text-[#18FFEA]">Cart</span>
        </h1>

        {/* Example cart item */}
        <div className="bg-gray-800/70 border border-gray-700/40 shadow-md rounded-xl p-6 mb-8 flex flex-col sm:flex-row items-center justify-between gap-6">
          <div className="flex items-center gap-6">
            <div className="h-24 w-24 rounded-lg bg-gray-700/60 flex items-center justify-center text-gray-400 text-sm">
              [ Image ]
            </div>
            <div className="text-left">
              <h2 className="text-lg font-semibold">Minimal Tee</h2>
              <p className="text-gray-400 text-sm">Size: L | Color: Black</p>
              <p className="text-[#18FFEA] font-semibold mt-1">$35.00</p>
            </div>
          </div>
          <div className="flex items-center gap-4">
            <button className="border border-gray-600 rounded-full px-3 py-1 text-gray-300 hover:text-[#18FFEA] hover:border-[#18FFEA] transition">
              -
            </button>
            <span className="text-gray-200 font-semibold">1</span>
            <button className="border border-gray-600 rounded-full px-3 py-1 text-gray-300 hover:text-[#18FFEA] hover:border-[#18FFEA] transition">
              +
            </button>
          </div>
        </div>

        {/* Cart summary */}
        <div className="bg-gray-800/70 border border-gray-700/40 shadow-md rounded-xl p-6 text-left max-w-md mx-auto">
          <div className="flex justify-between mb-3">
            <span className="text-gray-300">Subtotal</span>
            <span className="text-white font-semibold">$35.00</span>
          </div>
          <div className="flex justify-between mb-3">
            <span className="text-gray-300">Shipping</span>
            <span className="text-gray-400">Calculated at checkout</span>
          </div>
          <div className="flex justify-between border-t border-gray-700 pt-3 mt-4">
            <span className="text-lg font-semibold">Total</span>
            <span className="text-[#18FFEA] font-bold text-lg">$35.00</span>
          </div>
          <button className="w-full mt-6 bg-[#18FFEA] text-gray-900 font-semibold py-3 rounded-full hover:bg-[#14e6d4] transition-all duration-200 shadow-[0_0_15px_#18FFEA60]">
            Checkout
          </button>
        </div>

        {/* Empty state example (uncomment when no items) */}
        {/*
        <div className="bg-gray-800/70 border border-gray-700/40 shadow-md rounded-xl p-10 mt-12">
          <p className="text-gray-400 text-lg">
            Your cart is empty. Time to <span className="text-[#18FFEA] font-semibold">start designing!</span>
          </p>
        </div>
        */}
      </div>
    </section>
  );
}
