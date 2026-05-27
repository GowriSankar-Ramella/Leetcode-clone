function PublicTestCases({

    testCases

}) {

    return (

        <div
            className="
        mt-6
        "
        >

            <h2
                className="
            text-xl
            font-bold
            mb-4
            "
            >

                Examples

            </h2>

            {

                testCases.map(
                    (
                        testCase,
                        index
                    ) => (

                        <div
                            key={index}
                            className="
                    border
                    rounded
                    p-4
                    mb-4
                    bg-gray-100
                    "
                        >

                            <h3
                                className="
                        font-semibold
                        "
                            >

                                Example {index + 1}

                            </h3>

                            <p>

                                <strong>
                                    Input:
                                </strong>

                                {" "}

                                {testCase.input}

                            </p>

                            <p>

                                <strong>
                                    Output:
                                </strong>

                                {" "}

                                {testCase.expectedOutput}

                            </p>

                        </div>

                    ))

            }

        </div>

    )

}

export default PublicTestCases;