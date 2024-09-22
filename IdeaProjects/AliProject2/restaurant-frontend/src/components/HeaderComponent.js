// src/components/HeaderComponent.js
import React from 'react';
import logoEsprit from '../assets/est.jpg';
import { FaInstagram, FaFacebook } from 'react-icons/fa';

const HeaderComponent = () => {
    return (
        <header className="header d-flex justify-content-between align-items-center p-3">
            <div className="logo-container">
                <img src={logoEsprit} alt="Logo Esprit" style={{ width: '120px' }} />
            </div>
            <div className="contact-info">
                <span className="mr-3">+216 70 250 000</span>
                <a href="https://www.instagram.com/esprit" target="_blank" rel="noopener noreferrer">
                    <FaInstagram size={24} className="mr-2" />
                </a>
                <a href="https://www.facebook.com/esprit" target="_blank" rel="noopener noreferrer">
                    <FaFacebook size={24} />
                </a>
            </div>
        </header>
    );
};

export default HeaderComponent;
