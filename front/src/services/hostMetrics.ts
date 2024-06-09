import axios from "axios";

export const getHostMetrics = async () => {
  const response = await axios.get("http://127.0.0.1:8080/host");
  console.log("******RESPONSE********", response);
  return response.data;
};


