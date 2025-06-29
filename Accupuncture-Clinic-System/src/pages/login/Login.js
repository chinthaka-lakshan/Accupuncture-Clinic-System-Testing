import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        const validUsername = 'kiu';  
        const validPassword = 'kiu@123';  

        if (username === validUsername && password === validPassword) {
            navigate('/home');
        } else {
            alert('Invalid username or password');
        }
    };

    return (
        <div className='login'>
            <div className='container'>
                <div className='top'>
                        <span className='logo'>ZenCare</span>
                </div>
            
                <div className="formContainer">
                    <form onSubmit={handleSubmit}>
                        <div className="formInput">
                            <label>Username</label>
                            <input 
                                type="text" 
                                placeholder="Enter Username" 
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required 
                            />
                        </div>
                        <div className="formInput">
                            <label>Password</label>
                            <div className="passwordContainer">
                                <input 
                                    type={showPassword ? "text" : "password"} 
                                    placeholder="Enter Password" 
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required 
                                />
                                <span 
                                    className="passwordToggleIcon" 
                                    onClick={() => setShowPassword(!showPassword)}
                                >
                                </span>
                            </div>
                        </div>
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;