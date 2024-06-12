import { create } from "zustand";

export const useStore = create((set) => ({
  conn: "",
  setConn: (conn: string) => set({ conn }),
}));
