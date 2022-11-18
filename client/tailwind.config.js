/** @type {import('tailwindcss').Config} */

module.exports = {
  content: [
    "./pages/*.js",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./components/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        "dark-grey": "#2C2D31",
        "light-white": "rgba(255, 255, 255, 0.18)",
        "white": "#FFFFFF",
        "black": "#000000",
      },
    },
    
  },
  plugins: [],
}
