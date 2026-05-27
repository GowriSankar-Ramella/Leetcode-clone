function RunSubmitButtons({

    onRun,
    onSubmit,
    loadingAction

}) {

    return (

        <div
            className="
        flex
        gap-4
        "
        >

            <button
                onClick={onRun}
                disabled={
                    loadingAction !== null
                }
                className="
            bg-green-500
            text-white
            px-4
            py-2
            rounded
            disabled:opacity-50
            "
            >

                {

                    loadingAction === "RUN"

                        ?

                        "Running..."

                        :

                        "Run"

                }

            </button>


            <button
                onClick={onSubmit}
                disabled={
                    loadingAction !== null
                }
                className="
            bg-blue-500
            text-white
            px-4
            py-2
            rounded
            disabled:opacity-50
            "
            >

                {

                    loadingAction === "SUBMIT"

                        ?

                        "Submitting..."

                        :

                        "Submit"

                }

            </button>

        </div>

    )

}

export default RunSubmitButtons;