import {
    useEffect,
    useState
}
    from "react";

import SubmissionTable
    from "../components/submission/SubmissionTable";

import {
    getMySubmissions
}
    from "../services/submissionService";

function MySubmissionsPage() {

    const [
        submissions,
        setSubmissions
    ] = useState([]);

    useEffect(() => {

        fetchSubmissions();

    }, []);

    const fetchSubmissions =
        async () => {

            try {

                const data =
                    await getMySubmissions();

                setSubmissions(
                    data
                );

            }
            catch (error) {

                console.log(
                    error
                );

            }

        };

    return (

        <div
            className="
        p-8
        "
        >

            <h1
                className="
            text-3xl
            font-bold
            mb-6
            "
            >

                My Submissions

            </h1>

            <SubmissionTable
                submissions={
                    submissions
                }
            />

        </div>

    )

}

export default MySubmissionsPage;