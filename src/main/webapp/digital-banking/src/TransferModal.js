import React, { useState } from 'react';

const TransferModal = ({ isOpen, onClose }) => {
    const [accountNumber, setAccountNumber] = useState('');
    const [amount, setAmount] = useState('');
    const [description, setDescripiton] = useState('');

    const handleTransfer = () => {
        // Implement the transfer logic here (e.g., API call)
        console.log(`Transferring €${amount} to account ${accountNumber}`);

        // Close the modal after transferring
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h3>Transfer Money</h3>
                <div>
                    <label>Account Number:</label>
                    <input
                        type="text"
                        value={accountNumber}
                        onChange={(e) => setAccountNumber(e.target.value)}
                    />
                </div>
                <div>
                    <label>Amount:</label>
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                    />
                </div>
                <div>
                    <label>Description:</label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescripiton(e.target.value)}
                    />
                </div>
                <button onClick={handleTransfer}>Transfer</button>
                <button onClick={onClose}>Cancel</button>
            </div>
        </div>
    );
};

export default TransferModal;
