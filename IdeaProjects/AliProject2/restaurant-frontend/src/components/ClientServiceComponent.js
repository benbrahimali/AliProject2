// src/components/ClientServiceComponent.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ClientServiceComponent = () => {
    const [clients, setClients] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8089/api/clients')
            .then(response => {
                setClients(response.data);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des clients', error);
            });
    }, []);

    return (
        <div className="client-service-page">
            <h2>Service Client</h2>
            <ul>
                {clients.map(client => (
                    <li key={client.id}>{client.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default ClientServiceComponent;
