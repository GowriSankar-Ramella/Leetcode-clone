function StatsCard({

    title,
    value

}) {

    return (

        <div
            className="
        border
        rounded
        p-6
        shadow
        bg-white
        "
        >

            <h2
                className="
            text-gray-500
            "
            >

                {title}

            </h2>

            <p
                className="
            text-3xl
            font-bold
            mt-2
            "
            >

                {value}

            </p>

        </div>

    )

}

export default StatsCard;