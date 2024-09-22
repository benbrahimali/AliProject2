// src/components/HomeComponent.js
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import backgroundImage from '../assets/OIP.jpeg';
import '../assets/HomeStyles.css'; // Assurez-vous d'avoir un fichier CSS pour cette page

const HomeComponent = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        // Suppression des informations d'authentification
        localStorage.removeItem('authToken'); // Si vous stockez un token par exemple
        navigate("/"); // Redirection vers la page de connexion
    };

    return (
        <div className="home-page" style={{ backgroundImage: `url(${backgroundImage})`, backgroundSize: 'cover', backgroundPosition: 'center', height: '100vh' }}>
            <div className="header-content">
                <div className="logout-btn-container">
                    <button onClick={handleLogout} className="logout-btn">Déconnexion</button>
                </div>
                <h1 className="title">Bienvenue au Restaurant Universitaire Esprit</h1>
                <p className="description">
                    Découvrez nos plats délicieux et profitez de notre service de réservation en ligne. Nous offrons une expérience culinaire unique dans une ambiance moderne et confortable.
                </p>
                <div className="home-search">
                    <input type="text" placeholder="Rechercher un plat..." className="search-bar" />
                    <button className="search-btn">Rechercher</button>
                </div>
                <div className="home-links">
                    <Link to="/meals" className="link-btn">Voir les plats</Link>
                    <Link to="/reservations" className="link-btn">Faire une réservation</Link>
                    <Link to="/contact" className="link-btn">Contactez-nous</Link>
                    <Link to="/about" className="link-btn">En savoir plus</Link>
                </div>
            </div>
            <div className="footer-content">
                <p>Suivez-nous sur <a href="https://www.instagram.com/esprit" target="_blank" rel="noopener noreferrer">Instagram</a> et <a href="https://www.facebook.com/esprit" target="_blank" rel="noopener noreferrer">Facebook</a></p>
                <p>Appelez-nous au +216 70 250 000</p>
            </div>
        </div>
    );
};

export default HomeComponent;
