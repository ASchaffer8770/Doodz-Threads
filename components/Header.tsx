"use client";

import { useState, useEffect } from "react";
import Link from "next/link";
import { ShoppingCart, User, ChevronDown, X } from "lucide-react";
import MobileMenu from "@/components/MobileMenu";

export default function Header() {
  const [menuOpen, setMenuOpen] = useState(false);

  // 🔒 Disable body scroll when mobile menu is open
  useEffect(() => {
    if (menuOpen) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "";
    }
  }, [menuOpen]);

  return (
    <header className="fixed inset-x-0 top-0 z-50 bg-gray-800/95 backdrop-blur-sm text-white">
      <nav
        aria-label="Global"
        className="mx-auto flex max-w-7xl items-center justify-between px-6 py-3 lg:px-8"
      >
        {/* ===== Left: Logo + Brand ===== */}
        <div className="flex items-center gap-3 flex-shrink-0">
          <Link href="/" className="flex items-center gap-2">
            <div className="relative flex items-center justify-center h-14 w-14 rounded-full bg-gray-700/70 ring-2 ring-[#18FFEA]/40 shadow-[0_0_14px_3px_#18FFEA20] overflow-hidden">
              <img
                src="/DT_NB.png"
                alt="Doodz Threads Logo"
                className="h-[calc(100%-2px)] w-[calc(100%-2px)] object-contain"
              />
            </div>
            <h1 className="text-xl font-semibold tracking-tight text-white flex items-baseline gap-1">
              Doodz{" "}
              <span className="text-[#18FFEA] font-semibold tracking-tight">
                Threads
              </span>
            </h1>
          </Link>
        </div>

        {/* ===== Center: Desktop Navigation ===== */}
        <div className="hidden lg:flex flex-1 justify-center relative">
          <div className="flex items-center gap-x-16">
            {/* Collections dropdown */}
            <div className="relative group">
              <Link
                href="/collections"
                className="flex items-center gap-1 text-sm font-medium text-gray-100 hover:text-[#18FFEA] transition-colors duration-150"
              >
                Collections
                <ChevronDown
                  size={16}
                  className="transition-transform duration-200 group-hover:rotate-180"
                />
              </Link>
              <div className="absolute left-1/2 -translate-x-1/2 mt-2 hidden opacity-0 group-hover:opacity-100 group-hover:block transition-all duration-200 z-50">
                <div className="rounded-xl bg-gray-900/95 border border-gray-700/50 shadow-xl backdrop-blur-md p-3 w-56 -mt-2">
                  <div className="flex flex-col space-y-2 text-sm text-gray-200">
                    {[
                      { name: "Mens", href: "#" },
                      { name: "Womens", href: "#" },
                      { name: "Kids", href: "#" },
                      { name: "Limited Editions", href: "#" },
                    ].map((item) => (
                      <Link
                        key={item.name}
                        href={item.href}
                        className="rounded-lg px-3 py-2 hover:bg-[#18FFEA]/10 hover:text-[#18FFEA] transition-colors duration-150"
                      >
                        {item.name}
                      </Link>
                    ))}
                  </div>
                </div>
              </div>
            </div>

            {/* Other links */}
            {[
              { name: "Drops", href: "/drops" },
              { name: "Design", href: "/design" },
              { name: "About", href: "/about" },
            ].map((item) => (
              <Link
                key={item.name}
                href={item.href}
                className="text-sm font-medium text-gray-100 hover:text-[#18FFEA] transition-colors duration-150"
              >
                {item.name}
              </Link>
            ))}
          </div>
        </div>

        {/* ===== Right: Cart + Account ===== */}
        <div className="hidden lg:flex flex-shrink-0 items-center gap-6">
          <Link
            href="/cart"
            className="hover:text-[#18FFEA] transition-colors duration-150"
            aria-label="Cart"
          >
            <ShoppingCart size={24} strokeWidth={2} />
          </Link>
          <Link
            href="/account"
            className="hover:text-[#18FFEA] transition-colors duration-150"
            aria-label="Account"
          >
            <User size={24} strokeWidth={2} />
          </Link>
        </div>

        {/* ===== Mobile Toggle ===== */}
        <div className="flex lg:hidden">
          <button
            type="button"
            onClick={() => setMenuOpen(true)}
            className="inline-flex items-center justify-center rounded-md p-2 text-gray-200"
          >
            <span className="sr-only">Open main menu</span>
            <svg
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth={1.5}
              className="h-6 w-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
              />
            </svg>
          </button>
        </div>
      </nav>
      {menuOpen && <MobileMenu onClose={() => setMenuOpen(false)} />}
    </header>
  );
}
