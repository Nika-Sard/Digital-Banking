import React, {useEffect, useState} from 'react';

const TransactionRequests = ({ userId }) => {
    const [requests, setRequests] = useState([]);
    useEffect(() => {
        const parseRequests = (request) => {
            console.log(request);
            const transaction = request.transaction;
            request.message = 'Receiver: ' + transaction.receiverId + '. ' + request.message;
            return request;
        };

        fetch(`http://localhost:8080/getRequests/` + userId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                setRequests(data.map(parseRequests));
            })
            .catch(error => console.error('Error:', error));
    }, [userId]);

    const onApprove = (requestId) => {
        console.log(requestId);
        const url = 'http://localhost:8080/approveRequest/' + requestId;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        }).catch(error => console.error('Error:', error));
        setRequests(requests.filter((request) => request !== requestId));
    };

    const onDecline = (requestId) => {
        const url = 'http://localhost:8080/rejectRequest/' + requestId;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        }).catch(error => console.error('Error:', error));
        setRequests(requests.filter((request) => request !== requestId));
    };

    return (
        <div className="transaction-requests">
            <div className="transaction-header">
                <h4>Transaction Requests</h4>
            </div>
            <div className="transaction-list">
                {requests.length > 0 ? (
                    requests.map((request, index) => (
                        <div className="transaction-item" key={index}>
                            <div className="transaction-icon">RL</div>
                            <div className="transaction-details">
                                <p>{request.message}</p>
                            </div>
                            <p className="transaction-amount">€ {request.amount}</p>
                            <div className="action-buttons">
                                <button
                                    className="approve-btn"
                                    onClick={() => onApprove(request.requestId)}
                                >
                                    &#10003; {/* Checkmark */}
                                </button>
                                <button
                                    className="decline-btn"
                                    onClick={() => onDecline(request.requestId)}
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
