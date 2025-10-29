"use client";

import { useState, useEffect } from "react";
import Link from "next/link";

export default function AccountPage() {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");

  // 👇 Check for an existing "session" cookie or localStorage token
  useEffect(() => {
    const storedSession =
      typeof window !== "undefined" &&
      (localStorage.getItem("userSession") || document.cookie.includes("sessionToken"));

    if (!storedSession) {
      setIsLogin(false); // default to Sign Up if not logged in
    }
  }, []);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!email || !password) return alert("Please fill in all fields.");
    if (!isLogin && password !== confirm) return alert("Passwords do not match.");

    if (isLogin) {
      alert("Logging in...");
      // 🔹 Placeholder for real login logic
      document.cookie = "sessionToken=doodz_user; path=/;";
      localStorage.setItem("userSession", "active");
    } else {
      alert("Creating account...");
      // 🔹 Placeholder for real signup logic
      document.cookie = "sessionToken=doodz_user; path=/;";
      localStorage.setItem("userSession", "active");
    }

    // redirect or refresh
    window.location.href = "/";
  };

  return (
    <section className="relative min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center overflow-hidden">
      {/* Background gradient */}
      <div className="absolute inset-0 bg-gradient-to-b from-gray-800 via-gray-900 to-black" />
      {/* Cyan glow accent */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-[700px] h-[700px] rounded-full bg-[#18FFEA]/10 blur-[140px] opacity-70" />
      </div>

      <div className="relative z-10 w-full max-w-md bg-gray-800/70 border border-gray-700/40 rounded-xl p-8 shadow-md backdrop-blur-sm">
        <h1 className="text-4xl font-extrabold text-center mb-6">
          {isLogin ? "Welcome Back" : "Create Account"}
        </h1>
        <p className="text-gray-400 text-center mb-8">
          {isLogin
            ? "Log in to access your account."
            : "Join the Doodz community and start creating your style."}
        </p>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm text-gray-300 mb-2">Email</label>
            <input
              type="email"
              className="w-full px-4 py-3 rounded-lg bg-gray-700/70 border border-gray-600 text-white focus:outline-none focus:border-[#18FFEA]"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="you@example.com"
            />
          </div>

          <div>
            <label className="block text-sm text-gray-300 mb-2">Password</label>
            <input
              type="password"
              className="w-full px-4 py-3 rounded-lg bg-gray-700/70 border border-gray-600 text-white focus:outline-none focus:border-[#18FFEA]"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
            />
          </div>

          {!isLogin && (
            <div>
              <label className="block text-sm text-gray-300 mb-2">
                Confirm Password
              </label>
              <input
                type="password"
                className="w-full px-4 py-3 rounded-lg bg-gray-700/70 border border-gray-600 text-white focus:outline-none focus:border-[#18FFEA]"
                value={confirm}
                onChange={(e) => setConfirm(e.target.value)}
                placeholder="••••••••"
              />
            </div>
          )}

          <button
            type="submit"
            className="w-full bg-[#18FFEA] text-gray-900 font-semibold py-3 rounded-full hover:bg-[#14e6d4] transition-all duration-200 shadow-[0_0_15px_#18FFEA60]"
          >
            {isLogin ? "Log In" : "Create Account"}
          </button>
        </form>

        <p className="text-gray-400 text-center mt-6 text-sm">
          {isLogin ? "Don't have an account?" : "Already have an account?"}{" "}
          <button
            onClick={() => setIsLogin(!isLogin)}
            className="text-[#18FFEA] hover:underline"
          >
            {isLogin ? "Sign up" : "Log in"}
          </button>
        </p>

        <div className="mt-8 border-t border-gray-700 pt-6 text-center">
          <p className="text-gray-500 text-sm mb-4">Or continue with</p>
          <div className="flex justify-center gap-4">
            <button className="bg-gray-700/80 hover:bg-gray-700 text-white px-4 py-2 rounded-md border border-gray-600">
              Google
            </button>
            <button className="bg-gray-700/80 hover:bg-gray-700 text-white px-4 py-2 rounded-md border border-gray-600">
              Apple
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}
