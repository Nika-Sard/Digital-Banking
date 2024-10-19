// GradientButton.js
import React from 'react';
import './GradientButton.css'; // Import the CSS file for the button

const GradientButton = ({ children, onClick }) => {
    return (
        <button className="button-64" role="button" onClick={onClick}>
            <span className="text">{children}</span>
        </button>
    );
};

export default GradientButton;
