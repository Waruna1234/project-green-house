import { initializeApp } from "firebase/app";
import { getDatabase } from "firebase/database";

// Add firebase Config here 

const app = initializeApp(firebaseConfig);
export const rtdb = getDatabase(app);
