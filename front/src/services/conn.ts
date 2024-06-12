import axios from "axios";

const BASE_URL_BOT = "http://localhost:3000/connection";
const BASE_URL = "http://localhost:8080/instances";

export const getLink = async (ip: string) => {
  const response = await axios.post(BASE_URL_BOT, { ip });
  return response.data;
};

export const getIp = async (id: string) => {
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const response = await axios.get(`${BASE_URL}/${id}/ip`, { headers });

  return response.data;
};
