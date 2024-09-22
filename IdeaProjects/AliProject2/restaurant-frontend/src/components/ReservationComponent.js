import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../assets/ReservationStyles.css';

const ReservationComponent = () => {
    const [clientName, setClientName] = useState('');
    const [arrivalTime, setArrivalTime] = useState('');
    const [departureTime, setDepartureTime] = useState('');
    const [numberOfPeople, setNumberOfPeople] = useState(1);
    const [tableNumber, setTableNumber] = useState('');
    const [message, setMessage] = useState('');
    const [reservations, setReservations] = useState([]);
    const [selectedMeal, setSelectedMeal] = useState(null);

    useEffect(() => {
        const meal = JSON.parse(localStorage.getItem('selectedMeal'));
        if (meal) {
            setSelectedMeal(meal);
        }
        fetchReservations();
    }, []);

    const fetchReservations = async () => {
        try {
            const response = await axios.get('http://localhost:8088/api/reservations');
            setReservations(response.data);
        } catch (error) {
            console.error('Erreur lors de la récupération des réservations', error);
        }
    };

    const handleReservation = async (e) => {
        e.preventDefault();
        try {
            const formattedArrivalTime = new Date(arrivalTime).toISOString();
            const formattedDepartureTime = new Date(departureTime).toISOString();

            const response = await axios.post('http://localhost:8088/api/reservations', {
                clientName,
                arrivalTime: formattedArrivalTime,
                departureTime: formattedDepartureTime,
                numberOfPeople,
                tableNumber,
                mealName: selectedMeal.name, // إضافة اسم الطبق المحجوز
            });

            if (response.status === 201) {
                setMessage('Réservation réussie !');
                fetchReservations();
                resetForm();
            } else {
                setMessage('Une erreur s\'est produite lors de la réservation.');
            }
        } catch (error) {
            handleError(error);
        }
    };

    const handleError = (error) => {
        if (error.response && error.response.data) {
            setMessage(`Erreur : ${error.response.data.message || 'Une erreur s\'est produite.'}`);
        } else {
            setMessage('Une erreur s\'est produite. Veuillez réessayer.');
        }
    };

    const resetForm = () => {
        setClientName('');
        setArrivalTime('');
        setDepartureTime('');
        setNumberOfPeople(1);
        setTableNumber('');
    };

    return (
        <div className="reservation-container">
            <h2>Faites votre réservation</h2>
            {selectedMeal && (
                <div>
                    <h3>Plat sélectionné: {selectedMeal.name}</h3>
                </div>
            )}
            <form onSubmit={handleReservation}>
                <label>
                    Nom du client:
                    <input type="text" value={clientName} onChange={(e) => setClientName(e.target.value)} required />
                </label>
                <label>
                    Heure d'arrivée:
                    <input type="datetime-local" value={arrivalTime} onChange={(e) => setArrivalTime(e.target.value)} required />
                </label>
                <label>
                    Heure de départ:
                    <input type="datetime-local" value={departureTime} onChange={(e) => setDepartureTime(e.target.value)} required />
                </label>
                <label>
                    Nombre de personnes:
                    <input type="number" value={numberOfPeople} onChange={(e) => setNumberOfPeople(e.target.value)} min="1" required />
                </label>
                <label>
                    Numéro de table (facultatif):
                    <input type="number" value={tableNumber} onChange={(e) => setTableNumber(e.target.value)} />
                </label>
                <button type="submit">Réserver</button>
            </form>

            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default ReservationComponent;
