import { Link, useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

function Navbar() {

    const {
        isAuthenticated,
        logout
    } = useAuth();

    const navigate =
        useNavigate();

    const handleLogout = () => {

        logout();

        navigate(
            "/login",
            {
                replace: true
            }
        );

    }

    return (

        <nav
            className="
            flex
            justify-between
            items-center
            px-8
            py-4
            border-b
            "
        >

            <Link
                to="/"
                className="font-bold text-xl"
            >

                LeetCode Clone

            </Link>

            <div
                className="
                flex
                gap-6
                items-center
                "
            >

                <Link to="/">
                    Home
                </Link>

                {isAuthenticated ? (

                    <>

                        <Link
                            to="/profile"
                        >
                            Profile
                        </Link>

                        <Link
                            to="/my-submissions"
                        >
                            Submissions
                        </Link>

                        <button
                            onClick={
                                handleLogout
                            }
                        >

                            Logout

                        </button>

                    </>

                ) : (

                    <>

                        <Link
                            to="/login"
                        >
                            Login
                        </Link>

                        <Link
                            to="/register"
                        >
                            Register
                        </Link>

                    </>

                )}

            </div>

        </nav>

    );

}

export default Navbar;