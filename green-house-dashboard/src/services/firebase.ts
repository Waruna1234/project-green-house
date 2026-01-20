import { initializeApp } from "firebase/app";
import { getDatabase } from "firebase/database";

// Write firebaseConfig
const app = initializeApp(firebaseConfig);
export const rtdb = getDatabase(app);
