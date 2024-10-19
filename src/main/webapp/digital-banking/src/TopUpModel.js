import React, { useState } from 'react';

const TopUpModal = ({ isOpen, onClose }) => {
    const [amount, setAmount] = useState('');

    const handleTopUp = () => {
        // Implement the top-up logic here (e.g., API call)
        console.log(`Topping up €${amount}`);

        // Close the modal after topping up
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h3>Top Up</h3>
                <div>
                    <label>Amount (€):</label>
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                        min="0"
                    />
                </div>
                <button onClick={handleTopUp}>Top Up</button>
                <button onClick={onClose}>Cancel</button>
            </div>
        </div>
    );
};

export default TopUpModal;
