import { useEffect, useState }
    from "react";

import ProblemCard
    from "../components/problem/ProblemCard";

import {
    getProblems
}
    from "../services/problemService";

function HomePage() {

    const [
        problems,
        setProblems
    ] = useState([]);

    useEffect(() => {

        fetchProblems();

    }, []);

    const fetchProblems =
        async () => {

            try {

                const data =
                    await getProblems();

                setProblems(
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
            grid
            gap-4
            "
        >

            {

                problems.map(
                    problem => (

                        <ProblemCard
                            key={
                                problem.id
                            }
                            problem={
                                problem
                            }
                        />

                    )
                )

            }

        </div>

    )

}

export default HomePage;