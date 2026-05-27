function SubmissionTable({

    submissions

}) {

    return (

        <div
            className="
        overflow-x-auto
        "
        >

            <table
                className="
            w-full
            border
            "
            >

                <thead>

                    <tr
                        className="
                    border-b
                    "
                    >

                        <th
                            className="
                        p-3
                        "
                        >
                            Problem
                        </th>

                        <th>
                            Language
                        </th>

                        <th>
                            Status
                        </th>

                        <th>
                            Passed
                        </th>

                        <th>
                            Date
                        </th>

                    </tr>

                </thead>

                <tbody>

                    {

                        submissions.map(
                            submission => (

                                <tr
                                    key={
                                        submission.id
                                    }
                                    className="
                                border-b
                                text-center
                                "
                                >

                                    <td
                                        className="
                                    p-3
                                    "
                                    >

                                        {
                                            submission.problemId
                                        }

                                    </td>

                                    <td>

                                        {
                                            submission.language
                                        }

                                    </td>

                                    <td>

                                        {
                                            submission.status
                                        }

                                    </td>

                                    <td>

                                        {
                                            submission.passed
                                        }
                                        /
                                        {
                                            submission.total
                                        }

                                    </td>

                                    <td>

                                        {

                                            new Date(
                                                submission.createdAt
                                            )
                                                .toLocaleString()

                                        }

                                    </td>

                                </tr>

                            )
                        )

                    }

                </tbody>

            </table>

        </div>

    )

}

export default SubmissionTable;