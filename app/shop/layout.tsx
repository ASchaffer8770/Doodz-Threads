import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Shop — Doodz Threads",
  description: "Browse our latest collections and limited drops.",
};

export default function ShopLayout({ children }: { children: React.ReactNode }) {
  return (
    <section className="max-w-7xl mx-auto px-6 py-12 bg-gray-50 text-gray-900">
      <header className="text-center mb-10">
        <h1 className="text-4xl font-extrabold mb-2">
          Shop <span className="text-[#18FFEA]">Doodz Threads</span>
        </h1>
        <p className="text-gray-600">
          Minimalist drops. Bold statements. Designed for creators.
        </p>
      </header>
      {children}
    </section>
  );
}
