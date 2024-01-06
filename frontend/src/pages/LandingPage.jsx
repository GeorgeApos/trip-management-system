// eslint-disable-next-line no-unused-vars
import React from 'react';
import './LandingPage.css';

const LandingPage = () => {
    return (
        <div>
            <h1>Welcome to Trip Management System</h1>
            <p>
                This is a platform to manage your trips efficiently. Please log in or
                register to get started.
            </p>
            <div>
                <button onClick={() => window.location.href = '/login'}>
                    Login
                </button>
                <button onClick={() => window.location.href = '/register'}>
                    Register
                </button>
            </div>
        </div>
    );
};

export default LandingPage;
