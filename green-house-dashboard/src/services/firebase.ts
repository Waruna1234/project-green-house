import { initializeApp } from "firebase/app";
import { getDatabase } from "firebase/database";

// const firebaseConfig = {
//   apiKey: "YOUR_API_KEY",
//   authDomain: "your-project.firebaseapp.com",
//   databaseURL: "https://your-project-default-rtdb.firebaseio.com",
//   projectId: "your-project-id",
//   storageBucket: "your-project.appspot.com",
//   messagingSenderId: "XXXX",
//   appId: "XXXX",
// };

const firebaseConfig = {
  apiKey: "AIzaSyAZ9ZS8U5h8QWFiRD0yNGtT-ScfZFlg1n4",
  authDomain: "smart-greenhouse-system-94981.firebaseapp.com",
  databaseURL:
    "https://smart-greenhouse-system-94981-default-rtdb.firebaseio.com",
  projectId: "smart-greenhouse-system-94981",
  storageBucket: "smart-greenhouse-system-94981.firebasestorage.app",
  messagingSenderId: "62775788655",
  appId: "1:62775788655:web:7e21952da8200efa17b99a",
  measurementId: "G-XC1FLY3HGZ",
};

const app = initializeApp(firebaseConfig);
export const rtdb = getDatabase(app);
