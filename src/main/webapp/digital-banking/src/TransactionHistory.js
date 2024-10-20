import React, {useEffect, useState} from 'react';

export default function TransactionHistory({userId, accountId}) {
    const [transactions, setTransactions] = useState([]); // Use initial transaction data

    useEffect(() => {
        const parseTransaction = (transaction) => {
            console.log(transaction);
            if(accountId !== transaction.receiverId) {
                transaction.amount *= -1;
            }
            transaction.message = 'Sender: ' + transaction.senderId + ' Receiver: ' + transaction.receiverId + '. Message: ' + transaction.message;
            return transaction;
        };
        fetch(`http://localhost:8080/getTransactions/` + accountId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                setTransactions(data.map(parseTransaction));
            })
                .catch(error => console.error('Error:', error));
    }, [accountId]);

    return (
        <div className="transaction-history">
                <div className="transaction-header">
                    <h4>Transaction History</h4>
                </div>
            <div className="transaction-list">
                {transactions.length > 0 ? (
                    transactions.map((transaction) => (
                        <div key={transaction.transactionId} className="transaction-item">
                            <div className="transaction-icon">T</div>
                            <div className="transaction-details">
                                <small>{transaction.message}</small>
                            </div>
                            <p className="transaction-amount">€ {transaction.amount}</p>
                        </div>
                    ))
                ) : (
                    <p>No transactions found.</p>
                )}
            </div>
        </div>
    );
};
