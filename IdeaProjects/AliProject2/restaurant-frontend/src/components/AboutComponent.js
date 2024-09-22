import React from 'react';
import './AboutComponent.css';
import restaurantImage1 from '../assets/about-2.jpg'; // Remplacez par vos images de restaurant
import restaurantImage2 from '../assets/about-1.jpg';
import restaurantImage3 from '../assets/about-4.jpg';
import specialDish1 from '../assets/the.jpeg'; // Remplacez par vos images de plats
import specialDish2 from '../assets/tbn.jpeg';
import specialDish3 from '../assets/tbg.jpeg';

const AboutComponent = () => {
    return (
        <div className="about-container">
            <h2 className="about-title">À Propos de Nous</h2>
            <div className="description-box">
                <h3 className="welcome-title">Bienvenue au Restaurant Universitaire Esprit</h3>
                <p className="about-description">
                    Nous sommes dédiés à offrir des plats de qualité supérieure et un service exceptionnel dans une ambiance chaleureuse et accueillante.
                </p>
            </div>

            <h3 className="gallery-title">Galerie de Notre Restaurant</h3>
            <div className="gallery">
                <img src={restaurantImage1} alt="Restaurant image 1" className="gallery-image"/>
                <img src={restaurantImage2} alt="Restaurant image 2" className="gallery-image"/>
                <img src={restaurantImage3} alt="Restaurant image 3" className="gallery-image"/>
            </div>

            <h3 className="special-dishes-title">Nos Plats Spéciaux</h3>
            <div className="gallery">
                <img src={specialDish1} alt="Plat spécial 1" className="gallery-image"/>
                <img src={specialDish2} alt="Plat spécial 2" className="gallery-image"/>
                <img src={specialDish3} alt="Plat spécial 3" className="gallery-image"/>
            </div>
        </div>

    );
};

export default AboutComponent;
