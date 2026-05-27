function ResultPanel({

    result

}) {

    if (!result) {

        return null;

    }

    const isAccepted =
        result.status === "Accepted";

    return (

        <div
            className="
        mt-4
        border
        rounded
        p-4
        bg-gray-900
        text-white
        "
        >

            <div
                className="
            flex
            items-center
            gap-2
            text-xl
            font-bold
            "
            >

                {

                    isAccepted
                        ?

                        <span
                            className="
                    text-green-500
                    "
                        >

                            ✅

                        </span>

                        :

                        <span
                            className="
                    text-red-500
                    "
                        >

                            ❌

                        </span>

                }

                {result.status}

            </div>


            {

                result.passed !== null
                &&

                <div
                    className="
                mt-4
                "
                >

                    <span
                        className="
                    font-semibold
                    "
                    >

                        Passed:

                    </span>

                    {" "}

                    {

                        result.passed

                    }

                    /

                    {

                        result.total

                    }

                </div>

            }


            {

                result.failedInput
                &&

                <div
                    className="
                mt-4
                "
                >

                    <h3
                        className="
                    font-semibold
                    "
                    >

                        Failed Input

                    </h3>

                    <pre
                        className="
                    bg-black
                    p-2
                    rounded
                    mt-2
                    "
                    >

                        {

                            result.failedInput

                        }

                    </pre>

                </div>

            }


            {

                result.expected
                &&

                <div
                    className="
                mt-4
                "
                >

                    <h3
                        className="
                    font-semibold
                    "
                    >

                        Expected

                    </h3>

                    <pre
                        className="
                    bg-black
                    p-2
                    rounded
                    mt-2
                    "
                    >

                        {

                            result.expected

                        }

                    </pre>

                </div>

            }


            {

                result.actual
                &&

                <div
                    className="
                mt-4
                "
                >

                    <h3
                        className="
                    font-semibold
                    "
                    >

                        Actual

                    </h3>

                    <pre
                        className="
                    bg-black
                    p-2
                    rounded
                    mt-2
                    "
                    >

                        {

                            result.actual

                        }

                    </pre>

                </div>

            }


            {

                result.error
                &&

                <div
                    className="
                mt-4
                "
                >

                    <h3
                        className="
                    text-red-500
                    font-semibold
                    "
                    >

                        Error

                    </h3>

                    <pre
                        className="
                    bg-black
                    p-2
                    rounded
                    overflow-x-auto
                    mt-2
                    "
                    >

                        {

                            result.error

                        }

                    </pre>

                </div>

            }

        </div>

    )

}

export default ResultPanel;