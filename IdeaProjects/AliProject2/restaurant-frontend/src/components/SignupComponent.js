import React, { useState } from 'react';
import axios from 'axios';

const SignupComponent = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8381/api/auth/signup', {
                username,
                email,
                password,
            });

            if (response.data.success) {
                setMessage('Inscription réussie! Vous pouvez maintenant vous connecter.');
                window.location.href = '/home';
            } else {
                setMessage(response.data.message);
            }
        } catch (error) {
            setMessage('Erreur lors de l\'inscription. Veuillez réessayer.');
        }
    };

    return (
        <div className="signup-container">
            <h2>S'inscrire</h2>
            <form onSubmit={handleSignup}>
                <input
                    type="text"
                    placeholder="Nom d'utilisateur"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Mot de passe"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">S'inscrire</button>
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default SignupComponent;
