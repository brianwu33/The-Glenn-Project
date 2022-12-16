/** @type {import('tailwindcss').Config} */

module.exports = {
  content: [
    "./pages/*.js",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./components/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    extend: {
      fontFamily: {
        "default": 'Courier New, monospace', 
      },
      colors: {
        "dark-grey": "#2C2D31",
        "light-white": "rgba(255, 255, 255, 0.18)",
        "black-back": "#171617EB",
        "card-bg": "#484A50",

        "button-blue": "#3B4FE3",

        "white": "#FFFFFF",
        "black": "#000000",
      },
    },
    
  },
  plugins: [],
}
