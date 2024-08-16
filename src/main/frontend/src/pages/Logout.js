import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    const history = useNavigate();

    useEffect(() => {
        const logout = async () => {
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
        };

        logout();
        window.location.href = '/login';
    }, [history]);

    return <div>Logging out...</div>;
};

export default Logout;