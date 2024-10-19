import React, {useEffect, useState} from 'react';

const TransferModal = ({ isOpen, onClose, accountId, account, userId }) => {
    const [receiverId, setReceiverId] = useState('');
    const [amount, setAmount] = useState('');
    const [description, setDescripiton] = useState(' ');
    const [requesterDescription, setRequesterDescription] = useState(' ');
    const handleTransfer = () => {
        let url = 'http://localhost:8080/doTransaction/' + accountId + '/' + receiverId + '/' + amount + '/' + description;
        if (account === "OBSHIAKI") {
            url = 'http://localhost:8080/requestTransaction/' + accountId + '/' + receiverId + '/' + amount + '/' + description + '/' + requesterDescription + ' . Requester: ' + userId;
            console.log(requesterDescription);
        }
        // Implement the transfer logic here (e.g., API call)
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        }).catch(error => console.error('Error:', error));

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
                        value={receiverId}
                        onChange={(e) => setReceiverId(e.target.value)}
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
                    <label>Transaction Purpose:</label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescripiton(e.target.value)}
                    />
                </div>
                {
                    account === "OBSHIAKI" ? (
                        <div>
                            <label>Requester Purpose:</label>
                            <input
                                type="text"
                                value={requesterDescription}
                                onChange={(e) => setRequesterDescription(e.target.value)}
                            />
                        </div>
                    ) : null
                }
                <button onClick={handleTransfer}>Transfer</button>
                <button onClick={onClose}>Cancel</button>
            </div>
        </div>
    );
};

export default TransferModal;
