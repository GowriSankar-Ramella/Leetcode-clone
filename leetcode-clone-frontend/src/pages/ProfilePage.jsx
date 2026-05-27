import {
    useEffect,
    useState
}
    from "react";

import {
    getCurrentUser,
    getUserStats
}
    from "../services/userService";

import StatsCard
    from "../components/profile/StatsCard";

function ProfilePage() {

    const [
        user,
        setUser
    ] = useState(null);

    const [
        stats,
        setStats
    ] = useState(null);

    useEffect(() => {

        fetchProfile();

    }, []);


    const fetchProfile =
        async () => {

            try {

                const userData =
                    await getCurrentUser();

                const statsData =
                    await getUserStats();

                setUser(
                    userData
                );

                setStats(
                    statsData
                );

            }
            catch (error) {

                console.log(
                    error
                );

            }

        };


    if (!user || !stats) {

        return (
            <div
                className="
            p-8
            "
            >

                Loading...

            </div>
        );

    }

    return (

        <div
            className="
        p-8
        "
        >

            <div
                className="
            mb-8
            "
            >

                <h1
                    className="
                text-4xl
                font-bold
                "
                >

                    {user.name}

                </h1>

                <p
                    className="
                text-gray-600
                mt-2
                "
                >

                    {user.email}

                </p>

                <p
                    className="
                mt-1
                "
                >

                    Role:
                    {" "}
                    {user.role}

                </p>

            </div>


            <div
                className="
            grid
            grid-cols-3
            gap-6
            "
            >

                <StatsCard
                    title="Total Submissions"
                    value={
                        stats.totalSubmissions
                    }
                />

                <StatsCard
                    title="Accepted"
                    value={
                        stats.acceptedSubmissions
                    }
                />

                <StatsCard
                    title="Acceptance Rate"
                    value={
                        `${stats.acceptanceRate.toFixed(1)}%`
                    }
                />

            </div>

        </div>

    )

}

export default ProfilePage;