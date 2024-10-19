import React, { useState, useEffect } from 'react';
import './BankingCard.css';

// List of account members (users who share the account)
const members = ['Alice', 'Bob', 'Charlie'];

// Initial transaction data
const initialTransactions = [
    { id: 1, date: 'Oct 10, 2024', merchant: 'Grocery Store', amount: -50, approvals: [], declines: [], status: 'Pending' },
    { id: 2, date: 'Oct 8, 2024', merchant: 'Salary', amount: 5000, approvals: [], declines: [], status: 'Pending' },
    { id: 3, date: 'Oct 6, 2024', merchant: 'Electric Bill', amount: -100, approvals: [], declines: [], status: 'Pending' },
];

const BankingCard = ({ currentUser }) => {
    const [transactions, setTransactions] = useState(initialTransactions);
    const [newTransaction, setNewTransaction] = useState({ merchant: '', amount: '' });

    useEffect(() => {
        fetch(`http://localhost:8080/Transactions/${localStorage.getItem("username")}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                setTransactions(data);
            })
            .catch(error => console.error('Error:', error));
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewTransaction({ ...newTransaction, [name]: value });
    };

    // Handle form submission for new transactions
    const handleSubmitTransaction = (e) => {
        e.preventDefault();

        const transaction = {
            id: transactions.length + 1, // Generate a unique ID
            date: new Date().toLocaleDateString(), // Set today's date
            merchant: newTransaction.merchant,
            amount: parseFloat(newTransaction.amount),
            approvals: [currentUser], // The user who created the transaction automatically approves
            declines: [],
            status: 'Pending', // Set initial status to 'Pending'
        };

        // Add the new transaction to the list of transactions
        setTransactions([...transactions, transaction]);

        // Reset the form
        setNewTransaction({ merchant: '', amount: '' });
    };

    // Handle approval for a transaction
    const handleApprove = (transactionId) => {
        setTransactions((prevTransactions) =>
            prevTransactions.map((transaction) => {
                if (transaction.id === transactionId) {
                    const updatedApprovals = [...transaction.approvals, currentUser];

                    if (updatedApprovals.length === members.length) {
                        return { ...transaction, approvals: updatedApprovals, status: 'Approved' };
                    }

                    return { ...transaction, approvals: updatedApprovals };
                }
                return transaction;
            })
        );
    };

    // Handle decline for a transaction
    const handleDecline = (transactionId) => {
        setTransactions((prevTransactions) =>
            prevTransactions.map((transaction) =>
                transaction.id === transactionId
                    ? { ...transaction, declines: [...transaction.declines, currentUser], status: 'Cancelled' }
                    : transaction
            )
        );
    };

    return (
        <div className="banking-card">
            <div className="account-overview">
                <h2>Collaborative Account - {currentUser}'s View</h2>
                <p>Account Balance: $5,000</p>
            </div>

            <div className="transaction-history">
                <h3>Pending Transactions</h3>
                <ul>
                    {transactions.map((transaction) => (
                        <li key={transaction.id}>
                            <div>
                                <span>{transaction.date}</span>
                                <span>{transaction.merchant}</span>
                                <span>{transaction.amount < 0 ? `-$${Math.abs(transaction.amount)}` : `$${transaction.amount}`}</span>
                            </div>
                            <div>Status: {transaction.status}</div>
                            {transaction.status === 'Pending' && (
                                <div className="approval-section">
                                    <p>Approvals: {transaction.approvals.join(', ') || 'None yet'}</p>
                                    <p>Declines: {transaction.declines.join(', ') || 'None yet'}</p>

                                    {!transaction.approvals.includes(currentUser) && !transaction.declines.includes(currentUser) && (
                                        <div>
                                            <button
                                                className="action-btn"
                                                onClick={() => handleApprove(transaction.id)}
                                            >
                                                Approve as {currentUser}
                                            </button>
                                            <button
                                                className="action-btn decline"
                                                onClick={() => handleDecline(transaction.id)}
                                            >
                                                Decline as {currentUser}
                                            </button>
                                        </div>
                                    )}

                                    {transaction.approvals.includes(currentUser) && (
                                        <p>You have approved this transaction.</p>
                                    )}
                                    {transaction.declines.includes(currentUser) && (
                                        <p>You have declined this transaction.</p>
                                    )}
                                </div>
                            )}
                        </li>
                    ))}
                </ul>
            </div>

            <div className="transaction-form">
                <h3>Make a New Transaction</h3>
                <form onSubmit={handleSubmitTransaction}>
                    <label>
                        Merchant:
                        <input
                            type="text"
                            name="merchant"
                            value={newTransaction.merchant}
                            onChange={handleInputChange}
                            required
                        />
                    </label>
                    <label>
                        Amount:
                        <input
                            type="number"
                            name="amount"
                            value={newTransaction.amount}
                            onChange={handleInputChange}
                            required
                        />
                    </label>
                    <button type="submit">Submit Transaction</button>
                </form>
            </div>
        </div>
    );
};

export default BankingCard;
