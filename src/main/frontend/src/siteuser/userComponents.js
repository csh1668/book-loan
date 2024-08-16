import React, { useState } from 'react';
import { registerUser, loginUser } from './userApi';
import './../App.css';
import './../index.css';

const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
        loginUser(username, password).then(rsp => {
            if (rsp.success){
                const token = rsp.data.accessToken;
                localStorage.setItem('accessToken', token);
                console.log('Login success:', rsp);
                props.onSuccess();
            } else {
                console.error('Login failed:', rsp.message);
            }
            
        })
    };

    return (
        <div className="login-container">
            <h2>로그인</h2>
            <p>사용자 이름:<input
                type="text"
                name="사용자 이름"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            /></p>
            
            <p>비밀번호:<input
                type="password"
                name="비밀번호"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            /></p>
            
            <button onClick={handleLogin}>Login</button>
        </div>
    );
};

const Signup = () => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSignup = () => {
        registerUser(username, password, email);
    };

    return (
        <div className="signup-container">
            <h2>Signup</h2>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleSignup}>Signup</button>
        </div>
    );
};

export { Login, Signup };