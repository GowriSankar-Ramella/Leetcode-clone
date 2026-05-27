import api from "./api";

export const getProblems = async () => {

    const response =
        await api.get(
            "/problems"
        );

    return response.data;

}

export const getProblemById =
    async (id) => {

        const response =
            await api.get(
                `/problems/${id}`
            );

        return response.data;

    }

export const getStarterCode =
    async (
        problemId,
        language
    ) => {

        const response =
            await api.get(
                `/problems/${problemId}/starter-codes/${language}`
            );

        return response.data.starterCode;

    }

export const getPublicTestCases =
    async (problemId) => {

        const response =
            await api.get(
                `/problems/${problemId}/public-testcases`
            );

        return response.data;

    }