import axios from "axios";
export const createVM = async (token: string, data: any) => {
    const response = await axios.post(
        "http://localhost:8080/instances",
        data,
        {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
    return response.data;

}
export const getInstances = async (token: string) => {
    const response = await axios.get(
        "http://localhost:8080/instances",
        {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
    return response.data;
}
export const deleteInstance = async (token: string, id: string) => {
    const response = await axios.get(
        `http://localhost:8080/instances/${id}/delete`,
        {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
    return response.data;
}
