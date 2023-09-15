/** @type {import('tailwindcss').Config} */

module.exports = {
  content: [
    "./pages/*.js",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./components/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    screens: {
      'xs': '256px',
      'sm': '640px',
      // => @media (min-width: 640px) { ... }

      'md': '768px',
      // => @media (min-width: 768px) { ... }

      'lg': '1024px',
      // => @media (min-width: 1024px) { ... }

      'xl': '1280px',
      // => @media (min-width: 1280px) { ... }

      '2xl': '1536px',
      // => @media (min-width: 1536px) { ... }
    },
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
        "button-hover": "#3547CC",

        "white": "#FFFFFF",
        "black": "#000000",
      },
    },
    
  },
  plugins: [],
}