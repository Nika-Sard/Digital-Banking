import React, { useState } from 'react';

// Sample transaction data
const allTransactions = [
    { id: 1, type: 'Balance Top Up', amount: 800, date: '1 Jul 2024' },
    { id: 2, type: 'Transfer', amount: 150, date: '3 Jul 2024' },
    { id: 3, type: 'Exchange', amount: 200, date: '5 Jul 2024' },
    { id: 4, type: 'Transfer', amount: 50, date: '6 Jul 2024' },
    { id: 5, type: 'Balance Top Up', amount: 1000, date: '7 Jul 2024' },
    { id: 6, type: 'Transfer', amount: 300, date: '8 Jul 2024' },
    { id: 7, type: 'Exchange', amount: 400, date: '9 Jul 2024' },
    { id: 8, type: 'Balance Top Up', amount: 500, date: '10 Jul 2024' },
    // Add more transactions as needed
];

const TransactionHistory = () => {
    const [transactions, setTransactions] = useState(allTransactions); // Use initial transaction data

    return (
        <div className="transaction-history">
            <div className="transaction-header">
                <h3>Transactions</h3>
                <a href="/">View all</a>
            </div>
            <div className="transaction-list">
                {transactions.length > 0 ? (
                    transactions.map((transaction) => (
                        <div key={transaction.id} className="transaction-item">
                            <div className="transaction-icon">RL</div>
                            <div className="transaction-details">
                                <p>{transaction.type}</p>
                                <small>{transaction.date}</small>
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

export default TransactionHistory;
