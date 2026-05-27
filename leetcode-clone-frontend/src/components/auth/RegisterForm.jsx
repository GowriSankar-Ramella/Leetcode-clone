import { useState } from "react";
import { registerUser } from "../../services/authService";


function RegisterForm() {

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: ""
    });

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
                await registerUser(
                    formData
                );

            console.log(
                response
            );

            alert(
                "Registration successful"
            );

        }
        catch (error) {

            console.log(
                error
            );

            alert(
                "Registration failed"
            );

        }

    }

    return (

        <form
            onSubmit={handleSubmit}
            className="flex flex-col gap-4 w-80"
        >

            <input
                type="text"
                name="name"
                placeholder="Name"
                value={formData.name}
                onChange={handleChange}
                className="border p-2 rounded"
            />

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
                Register
            </button>

        </form>

    )

}

export default RegisterForm;