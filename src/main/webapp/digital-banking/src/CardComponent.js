import React from 'react';

const CardComponent = () => {
    return (
        <div className="card-container">
            <div className="card">
                <div className="card-chip"></div>
                <div className="card-details">
                    <p>PULSE</p>
                    <p>**** 7995</p>
                </div>
                <div className="visa-logo">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/0/04/Visa.svg" alt="Visa Logo" />
                </div>
            </div>
        </div>
    );
};

export default CardComponent;
