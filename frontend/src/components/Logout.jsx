import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
    const navigate = useNavigate();

    useEffect(() => {
        // Clear authentication header from local storage
        localStorage.removeItem("authHeader");

        // Set a timeout to navigate after 2-3 seconds (adjust as needed)
        const timeoutId = setTimeout(() => {
            // Navigate to the home page
            navigate("/");
        }, 2000); // 2 seconds

        // Cleanup the timeout on component unmount
        return () => clearTimeout(timeoutId);
    }, [navigate]);

    return (
        <div>
            <p>Logging out...</p>
        </div>
    );
};

export default Logout;
