// src/components/ContactComponent.js
import React, { useState } from 'react';
import axios from 'axios';

const ContactComponent = () => {
    const [comment, setComment] = useState('');

    const handleCommentSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/contact', { comment })
            .then(response => {
                alert('Commentaire envoyé avec succès!');
            })
            .catch(error => {
                alert('Erreur lors de l’envoi du commentaire.');
            });
    };

    return (
        <div className="contact-page">
            <h2>Contactez-nous</h2>
            <form onSubmit={handleCommentSubmit}>
        <textarea
            placeholder="Votre commentaire"
            value={comment}
            onChange={(e) => setComment(e.target.value)}
        />
                <button type="submit">Envoyer</button>
            </form>
            <div className="contact-info">
                <p>Suivez-nous sur <a href="https://www.instagram.com/esprit" target="_blank" rel="noopener noreferrer">Instagram</a> et <a href="https://www.facebook.com/esprit" target="_blank" rel="noopener noreferrer">Facebook</a></p>
                <p>Appelez-nous au +216 70 250 000</p>
            </div>
        </div>
    );
};

export default ContactComponent;
