import api from "./api";

export const executeCode =
    async (data) => {

        const response =
            await api.post(
                "/execute",
                data
            );

        return response.data;

    }