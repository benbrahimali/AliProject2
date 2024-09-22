import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Importer Router, Routes, Route
import HeaderComponent from './components/HeaderComponent';
import HomeComponent from './components/HomeComponent';
import LoginComponent from './components/LoginComponent';
import SignupComponent from './components/SignupComponent';
import ReservationComponent from './components/ReservationComponent';
import ContactComponent from './components/ContactComponent';
import ClientServiceComponent from './components/ClientServiceComponent';
import MealComponent from './components/MealComponent';
import AboutComponent from './components/AboutComponent'; // Importer le composant About
import './assets/styles.css';

const App = () => {
    return (
        <Router>
            <HeaderComponent />
            <Routes>
                <Route path="/" element={<LoginComponent />} />
                <Route path="/signup" element={<SignupComponent />} />
                <Route path="/home" element={<HomeComponent />} />
                <Route path="/meals" element={<MealComponent />} />
                <Route path="/reservations" element={<ReservationComponent />} />
                <Route path="/contact" element={<ContactComponent />} />
                <Route path="/client-service" element={<ClientServiceComponent />} />
                <Route path="/about" element={<AboutComponent />} /> {/* Route pour About */}
            </Routes>
        </Router>
    );
};

export default App;
