import "./globals.css";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export const metadata = {
  title: "Doodz Threads",
  description: "Clothing your way",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className="flex flex-col min-h-screen bg-gray-900 text-white overflow-x-hidden">
        {/* Header */}
        <Header />

        {/* Main content expands to fill remaining space */}
        <main className="flex-grow">
          {children}
        </main>

        {/* Footer stays anchored to bottom */}
        <Footer />
      </body>
    </html>
  );
}
