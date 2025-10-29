"use client";

import Link from "next/link";
import { X } from "lucide-react";

export default function MobileMenu({ onClose }: { onClose: () => void }) {
  return (
    <div className="fixed inset-0 z-[9999] h-screen bg-gray-900/95 backdrop-blur-lg flex flex-col transition-all duration-300">
      {/* Top bar (matches header height exactly) */}
      <div className="flex items-center justify-between px-6 h-[56px] border-b border-gray-700/50">
        <Link
          href="/"
          onClick={onClose}
          className="flex items-center gap-2"
        >
          <div className="relative flex items-center justify-center h-10 w-10 rounded-full bg-gray-700/70 ring-2 ring-[#18FFEA]/40 overflow-hidden">
            <img
              src="/DT_NB.png"
              alt="Doodz Threads Logo"
              className="h-[calc(100%-2px)] w-[calc(100%-2px)] object-contain"
            />
          </div>
          <span className="text-lg font-semibold text-white">
            Doodz <span className="text-[#18FFEA] font-semibold">Threads</span>
          </span>
        </Link>

        <button
          onClick={onClose}
          className="text-gray-300 hover:text-[#18FFEA] transition"
          aria-label="Close menu"
        >
          <X size={28} />
        </button>
      </div>

      {/* Scrollable list */}
      <div className="flex-1 overflow-y-auto flex flex-col items-center justify-start py-10 space-y-8 text-center">
        {[
          { name: "Collections", href: "/collections" },
          { name: "Drops", href: "/drops" },
          { name: "Design", href: "/design" },
          { name: "About", href: "/about" },
          { name: "Account", href: "/account" },
          { name: "Cart", href: "/cart" },
        ].map((item) => (
          <Link
            key={item.name}
            href={item.href}
            onClick={onClose}
            className="text-2xl font-semibold hover:text-[#18FFEA] transition-colors duration-150"
          >
            {item.name}
          </Link>
        ))}
      </div>
    </div>
  );
}
