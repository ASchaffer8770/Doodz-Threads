import Link from "next/link";
import { Instagram, Twitter, Facebook, Mail } from "lucide-react";

export default function Footer() {
  return (
    <footer className="bg-gray-950 text-gray-300 border-t border-[#18FFEA]/30">
      <div className="max-w-7xl mx-auto px-6 py-12 lg:px-8">
        {/* ===== Top Section ===== */}
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-10">
          {/* Brand */}
          <div className="flex items-center gap-3">
            <div className="relative flex items-center justify-center h-12 w-12 rounded-full bg-gray-800 ring-2 ring-[#18FFEA]/40 shadow-[0_0_10px_2px_#18FFEA20] overflow-hidden">
              <img
                src="/DT_NB.png"
                alt="Doodz Threads Logo"
                className="h-[calc(100%-4px)] w-[calc(100%-4px)] object-contain"
              />
            </div>
            <h2 className="text-lg font-semibold text-white tracking-tight">
              Doodz <span className="text-[#18FFEA]">Threads</span>
            </h2>
          </div>

          {/* Navigation Links */}
          <nav className="flex flex-wrap gap-6 text-sm font-medium">
            {["Collections", "Drops", "Design", "About"].map((item) => (
              <Link
                key={item}
                href="#"
                className="hover:text-[#18FFEA] transition-colors duration-150"
              >
                {item}
              </Link>
            ))}
          </nav>

          {/* Social Icons */}
          <div className="flex items-center gap-5">
            <Link
              href="#"
              aria-label="Instagram"
              className="hover:text-[#18FFEA] transition-colors duration-150"
            >
              <Instagram size={20} />
            </Link>
            <Link
              href="#"
              aria-label="Twitter"
              className="hover:text-[#18FFEA] transition-colors duration-150"
            >
              <Twitter size={20} />
            </Link>
            <Link
              href="#"
              aria-label="Facebook"
              className="hover:text-[#18FFEA] transition-colors duration-150"
            >
              <Facebook size={20} />
            </Link>
            <Link
              href="#"
              aria-label="Email"
              className="hover:text-[#18FFEA] transition-colors duration-150"
            >
              <Mail size={20} />
            </Link>
          </div>
        </div>

        {/* ===== Divider ===== */}
        <div className="border-t border-gray-800 my-10"></div>

        {/* ===== Bottom Section ===== */}
        <div className="flex flex-col md:flex-row items-center justify-between gap-4 text-sm text-gray-500">
          <p>© {new Date().getFullYear()} Doodz Threads. All rights reserved.</p>
          <div className="flex gap-6">
            <Link href="#" className="hover:text-[#18FFEA]">
              Privacy Policy
            </Link>
            <Link href="#" className="hover:text-[#18FFEA]">
              Terms of Service
            </Link>
          </div>
        </div>
      </div>
    </footer>
  );
}
