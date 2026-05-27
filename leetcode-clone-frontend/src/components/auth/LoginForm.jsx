import { useState } from "react";
import { loginUser } from "../../services/authService";
import useAuth from "../../hooks/useAuth";
import { useNavigate } from "react-router-dom";

function LoginForm() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({

        email: "",
        password: ""

    });

    const { login } = useAuth();

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        })

    }

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response =
                await loginUser(
                    formData
                );

            console.log(
                response
            );

            login(
                response.token
            );


            alert(
                "Login successful"
            );

            navigate(
                "/"
            );

        }
        catch (error) {

            console.log(
                error
            );

            alert(
                "Login failed"
            );

        }

    }

    return (

        <form
            onSubmit={handleSubmit}
            className="flex flex-col gap-4 w-80"
        >

            <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                className="border p-2 rounded"
            />

            <input
                type="password"
                name="password"
                placeholder="Password"
                value={formData.password}
                onChange={handleChange}
                className="border p-2 rounded"
            />

            <button
                className="bg-blue-500 text-white p-2 rounded"
            >
                Login
            </button>

        </form>

    )

}

export default LoginForm;