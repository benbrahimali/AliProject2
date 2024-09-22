import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './MealComponent.css';

const MealComponent = () => {
    const [meals, setMeals] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8096/api/menu-items')
            .then(response => {
                setMeals(response.data);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des plats', error);
            });
    }, []);

    const handleOrder = (meal) => {
        // تخزين معلومات الطبق في Local Storage أو تمريرها مباشرةً
        localStorage.setItem('selectedMeal', JSON.stringify(meal));
        navigate('/reservations'); // الانتقال إلى صفحة الحجوزات
    };

    return (
        <div className="meal-page">
            <div className="meal-header">
                <h1 className="meal-title">Notre Menu</h1>
                <p className="meal-subtitle">Découvrez nos plats délicieux et fraîchement préparés.</p>
            </div>
            <div className="meal-list">
                {meals.map(meal => (
                    <div key={meal.id} className="meal-item">
                        <img src={meal.imageUrl || 'src/assets/the.jpeg'} alt={meal.name} className="meal-image"/>
                        <div className="meal-details">
                            <h3 className="meal-name">{meal.name}</h3>
                            <p className="meal-description">{meal.description}</p>
                            <p className="meal-price">{meal.price} TND</p>
                            <button className="order-btn" onClick={() => handleOrder(meal)}>Commander</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default MealComponent;
