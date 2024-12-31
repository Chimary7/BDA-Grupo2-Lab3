/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      spacing: {
        '9/10': '95%',
        '48r' : '48rem',
        '88': '22rem',
      },

      colors: {
        'color-primary': '#EDE7F6',
        'color-secondary': '#D1C4E9',
        'color-tertiary': '#B39DDB',
        'color-quaternary': '#9575CD',
        'color-quinary': '#512DA8',
      },
    },
  },
  plugins: [],
}