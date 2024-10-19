import React, {useEffect} from 'react';

const TransactionRequests = ({ transactions, onApprove, onDecline, userId }) => {
    useEffect(() => {
        const parseRequests = (Request) => {
            console.log(Request);
            return Request;
        };

        fetch(`http://localhost:8080/getRequests/` + userId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                data.map(parseRequests);
            })
            .catch(error => console.error('Error:', error));
    }, [userId]);
    return (
        <div className="transaction-requests">
            <div className="transaction-header">
                <h4>Transaction Requests</h4>
            </div>
            <div className="transaction-list">
                {transactions.length > 0 ? (
                    transactions.map((transaction, index) => (
                        <div className="transaction-item" key={index}>
                            <div className="transaction-icon">RL</div>
                            <div className="transaction-details">
                                <p>{transaction.description}</p>
                                <small>{transaction.date}</small>
                            </div>
                            <p className="transaction-amount">€ {transaction.amount}</p>
                            <div className="action-buttons">
                                <button
                                    className="approve-btn"
                                    onClick={() => onApprove(transaction.id)}
                                >
                                    &#10003; {/* Checkmark */}
                                </button>
                                <button
                                    className="decline-btn"
                                    onClick={() => onDecline(transaction.id)}
                                >
                                    &#10005; {/* X symbol */}
                                </button>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>No transaction requests found.</p>
                )}
            </div>
        </div>
    );
};

export default TransactionRequests;
