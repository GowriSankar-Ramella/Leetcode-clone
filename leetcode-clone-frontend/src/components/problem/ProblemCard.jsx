import { Link } from "react-router-dom";

function ProblemCard({
    problem
}) {

    return (

        <Link
            to={`/problems/${problem.id}`}
        >

            <div
                className="
                border
                rounded
                p-4
                hover:shadow-lg
                "
            >

                <h2
                    className="
                    font-bold
                    "
                >

                    {problem.title}

                </h2>

                <p>

                    {problem.difficulty}

                </p>

            </div>

        </Link>

    )

}

export default ProblemCard;