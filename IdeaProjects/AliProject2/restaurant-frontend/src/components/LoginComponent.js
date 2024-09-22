import React, { useState } from 'react';
import axios from 'axios';

const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8381/api/auth/login', {
                username,
                password,
            });

            if (response.data.success) {
                setMessage('Connexion réussie!');
                window.location.href = '/home';
            } else {
                setMessage('Nom d\'utilisateur ou mot de passe incorrect');
            }
        } catch (error) {
            setMessage('Erreur lors de la connexion. Veuillez réessayer.');
        }
    };

    return (
        <div className="login-container">
            <div className="login-logo">
                <img src='../assets/esprit.jpeg' alt="Esprit Logo" />
            </div>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Nom d'utilisateur"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Mot de passe"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">Se connecter</button>
            </form>
            {message && <p className="message">{message}</p>}

            <div className="login-footer">
                <a href="/signup">Vous n'avez pas de compte? Inscrivez-vous</a>
            </div>
        </div>
    );
};

export default LoginComponent;
