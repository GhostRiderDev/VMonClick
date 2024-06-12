import axios from "axios";

const BASE_URL = "http://localhost:8080/instances";

export const stopInstance = async (id: string) => {
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const response = await axios.post(`${BASE_URL}/${id}/stop`, {}, { headers });

  return response.data;
};

export const startInstance = async (id: string) => {
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const response = await axios.post(`${BASE_URL}/${id}/start`, {}, { headers });

  return response.data;
};
