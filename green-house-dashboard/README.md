# Green House Dashboard

A React + TypeScript + Vite web application for monitoring and managing smart greenhouse systems. This dashboard displays real-time sensor data from your greenhouse, including environmental metrics and sensor readings.

## 📋 Project Overview

The Green House Dashboard is a responsive web application built with modern web technologies that connects to Firebase for real-time data synchronization. It provides an intuitive interface to monitor greenhouse conditions with charts, sensor cards, and real-time updates.

### Key Features

- **Real-time Monitoring**: Display live sensor data from your smart greenhouse system
- **Interactive Charts**: Visualize environmental data with Recharts
- **Responsive Design**: Works on desktop, tablet, and mobile devices
- **Firebase Integration**: Real-time data synchronization with Firebase Realtime Database
- **Multi-page Navigation**: Home, Dashboard, Products, and Contact pages

## 🛠 Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Node.js** (v16 or higher) - [Download here](https://nodejs.org/)
- **npm** (comes with Node.js) or **yarn**
- **Git** (optional, for version control)

## 📦 Installation

Follow these steps to set up the project on your local machine:

### 1. Clone or Download the Project

```bash
# If using git
git clone <repository-url>
cd green-house-dashboard

# Or extract the project folder if downloaded as ZIP
```

### 2. Install Dependencies

Navigate to the project directory and install all required dependencies:

```bash
npm install
```

Or if you prefer yarn:

```bash
yarn install
```

This will install all packages listed in `package.json`:

- React 19.2.0
- TypeScript ~5.9.3
- Vite 7.2.4
- React Router DOM 7.11.0
- Firebase 12.7.0
- Axios 1.13.2
- Recharts 3.6.0
- ESLint and dev dependencies

## 🚀 Running the Project

### Development Server

Start the development server with hot module reload:

```bash
npm run dev
```

Or with yarn:

```bash
yarn dev
```

The application will open in your default browser at `http://localhost:5173`

### Build for Production

Create an optimized production build:

```bash
npm run build
```

This will compile TypeScript and bundle your application using Vite.

### Preview Production Build

Preview the production build locally:

```bash
npm run preview
```

### Lint Code

Check for code quality issues:

```bash
npm run lint
```

## 📂 Project Structure

```
green-house-dashboard/
├── src/
│   ├── components/
│   │   ├── DashBoard.tsx
│   │   ├── EnvironmentChart.tsx
│   │   ├── SensorCard.tsx
│   │   ├── contact/         (Contact page)
│   │   ├── dashBoard/       (Dashboard components)
│   │   ├── footer/          (Footer component)
│   │   ├── home/            (Home page)
│   │   ├── layout/          (Main layout wrapper)
│   │   ├── navbar/          (Navigation bar)
│   │   └── product/         (Product page)
│   ├── routes/
│   │   └── AppRouts.tsx     (Route definitions)
│   ├── services/
│   │   ├── api.ts           (API calls with Axios)
│   │   └── firebase.ts      (Firebase configuration)
│   ├── types/
│   │   ├── HistoryData.ts   (Type definitions)
│   │   └── SensorData.ts    (Sensor data types)
│   ├── styles/
│   │   └── style.css        (Global styles)
│   ├── App.tsx              (Main App component)
│   ├── main.tsx             (Entry point)
│   └── index.css            (Global CSS)
├── public/                  (Static assets)
├── package.json             (Project dependencies)
├── vite.config.ts           (Vite configuration)
├── tsconfig.json            (TypeScript configuration)
└── README.md                (This file)
```

## 🔑 Configuration

### Firebase Setup

The project uses Firebase Realtime Database for data synchronization. The configuration is in [src/services/firebase.ts](src/services/firebase.ts):

```typescript
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_AUTH_DOMAIN",
  databaseURL: "YOUR_DATABASE_URL",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_STORAGE_BUCKET",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID",
  measurementId: "YOUR_MEASUREMENT_ID",
};
```

If you need to use a different Firebase project, update these values in the firebase.ts file.

## 📡 Technologies Used

- **React** - UI library for building components
- **TypeScript** - Statically typed JavaScript
- **Vite** - Ultra-fast build tool and dev server
- **React Router** - Client-side routing
- **Firebase** - Backend and real-time database
- **Axios** - HTTP client for API requests
- **Recharts** - Composable charting library
- **ESLint** - Code quality linter

## 🔄 Available Scripts

| Command           | Description                              |
| ----------------- | ---------------------------------------- |
| `npm run dev`     | Start development server with hot reload |
| `npm run build`   | Create optimized production build        |
| `npm run preview` | Preview production build locally         |
| `npm run lint`    | Run ESLint to check code quality         |

## 🌐 Deployment

To deploy this project, you can use services like:

- **Vercel** (recommended for Vite projects)
- **Netlify**
- **Firebase Hosting**
- **GitHub Pages**

### Deploy to Vercel:

```bash
npm install -g vercel
vercel
```

## 📝 Notes

- The project uses Vite for fast development and optimized builds
- ESLint is configured for code quality and consistency
- Make sure all environment variables and Firebase credentials are properly set up before deployment
- The dashboard is fully responsive and works on all modern browsers

## 🤝 Support

For issues or questions:

1. Check the project structure and ensure all dependencies are installed
2. Verify Firebase configuration is correct
3. Clear `node_modules` and reinstall: `rm -rf node_modules && npm install`

## 📄 License

This project is provided as-is for greenhouse monitoring purposes.
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
globalIgnores(['dist']),
{
files: ['**/*.{ts,tsx}'],
extends: [
// Other configs...
// Enable lint rules for React
reactX.configs['recommended-typescript'],
// Enable lint rules for React DOM
reactDom.configs.recommended,
],
languageOptions: {
parserOptions: {
project: ['./tsconfig.node.json', './tsconfig.app.json'],
tsconfigRootDir: import.meta.dirname,
},
// other options...
},
},
])

```

```
