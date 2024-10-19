import React from 'react';

const CardComponent = ({accountId}) => {

    return (
        <div className="card-container">
            <div className="card">
                <div className="card-chip"></div>
                <div className="card-details">
                    <p>PULSE</p>
                    <p>{accountId}</p>
                </div>
            </div>
        </div>
    );
};

export default CardComponent;
