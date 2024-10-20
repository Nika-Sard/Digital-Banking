import React, { useState } from 'react';

const CardComponent = ({ accountId, userId }) => {
    const [newAccountId, setNewAccountId] = useState(accountId);

    const updateAccountId = (change) => {
        let updatedAccountId = (parseInt(accountId) + change) % 4;
        if(updatedAccountId === 0) updatedAccountId += 1;
        setNewAccountId(updatedAccountId);
        const newUrl = `http://localhost:3000/?userId=${userId}&accountId=${updatedAccountId}`;
        window.history.pushState({}, '', newUrl);
        window.location.reload();
    };

    return (
        <div className="card-container">
            <div className="card">
                <button onClick={() => updateAccountId(-1)} className="arrow-button left-arrow">
                    <i className="fas fa-chevron-left"></i>
                </button>
                <div className="card-chip"></div>
                <div className="card-details">
                    <p>PULSE</p>
                    <p>{accountId}</p>
                </div>
                <button onClick={() => updateAccountId(1)} className="arrow-button right-arrow">
                    <i className="fas fa-chevron-right"></i>
                </button>
            </div>
        </div>
    );
};

export default CardComponent;
