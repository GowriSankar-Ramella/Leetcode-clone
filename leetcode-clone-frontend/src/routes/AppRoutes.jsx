import {
    Routes,
    Route
}
    from "react-router-dom";

import HomePage from "../pages/HomePage";

import LoginPage from "../pages/LoginPage";

import RegisterPage from "../pages/RegisterPage";

import ProfilePage from "../pages/ProfilePage";

import MySubmissionsPage from "../pages/MySubmissionsPage";

import ProtectedRoute from "../components/common/ProtectedRoute";

import ProblemPage from "../pages/ProblemPage";

function AppRoutes() {

    return (

        <Routes>

            <Route
                path="/"
                element={<HomePage />}
            />

            <Route
                path="/login"
                element={<LoginPage />}
            />

            <Route
                path="/register"
                element={<RegisterPage />}
            />

            <Route
                path="/profile"
                element={
                    <ProtectedRoute>
                        <ProfilePage />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/my-submissions"
                element={
                    <ProtectedRoute>
                        <MySubmissionsPage />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/problems/:id"
                element={
                    <ProtectedRoute>

                        <ProblemPage />

                    </ProtectedRoute>
                }
            />

        </Routes>

    )

}

export default AppRoutes;