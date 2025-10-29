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
      <body className="bg-gray-900 text-white overflow-x-hidden">
        {/* Header sits OUTSIDE of main */}
        <Header />

        {/* main should contain ONE child: {children} */}
        <main className="pt-20 m-0 p-0">
          {children}
        </main>

        <Footer />
      </body>
    </html>
  );
}
