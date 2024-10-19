import React, { useEffect, useState } from 'react';
import CardComponent from './CardComponent';
import TransactionHistory from './TransactionHistory';
import TransferModal from './TransferModal';
import TopUpModal from './TopUpModel';
import AddUserModal from './AddUserModal';  // Import the AddUserModal
import TransactionRequests from './TransactionRequests';
import '@fortawesome/fontawesome-free/css/all.min.css';
import './styles.css';

const App = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isTopUpOpen, setIsTopUpOpen] = useState(false);
    const [isAddUserModalOpen, setIsAddUserModalOpen] = useState(false); // Add state for AddUserModal
    const [balance, setBalance] = useState(0);
    const [userId, setUserId] = useState(0);
    const [accountId, setAccountId] = useState(0);
    const [showTransactionRequests, setShowTransactionRequests] = useState(false);
    const [account, setAccount] = useState("OBSHIAKI");

    useEffect(() => {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search);
        const newUserId = params.get('userId');
        const newAccountId = params.get('accountId');

        if (newAccountId !== accountId || newUserId !== userId) {
            setUserId(newUserId);
            setAccountId(newAccountId);
            fetch(`http://localhost:8080/getAccount/` + newAccountId, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    setBalance(data.balance);
                    const isObshiaki = data.isObshiak;
                    if(isObshiaki){
                        setAccount("OBSHIAKI");
                    } else {
                        setAccount("User Account");
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }, []);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const openTopUp = () => {
        setIsTopUpOpen(true);
    };

    const closeTopUp = () => {
        setIsTopUpOpen(false);
    };

    const openAddUserModal = () => {
        setIsAddUserModalOpen(true);
    };

    const closeAddUserModal = () => {
        setIsAddUserModalOpen(false);
    };

    const handleApprove = (transactionId) => {
        const updatedTransactions = transactions.map((transaction) =>
            transaction.id === transactionId
                ? { ...transaction, status: 'approved' }
                : transaction
        );
        setTransactions(updatedTransactions);
        alert(`Transaction ${transactionId} has been approved.`);
    };

    const handleDisapprove = (transactionId) => {
        const updatedTransactions = transactions.map((transaction) =>
            transaction.id === transactionId
                ? { ...transaction, status: 'declined' }
                : transaction
        );
        setTransactions(updatedTransactions);
        alert(`Transaction ${transactionId} has been declined.`);
    };

    const handleAddUser = (userIdToAdd) => {
        // Logic for adding the user to the collaborative account
        alert(`User ${userIdToAdd} has been invited to the collaborative account.`);
    };

    return (
        <div className="app-container">
            <header className="app-header">
                <div className="user-icon">RL</div>
                <h2>{account}</h2>
                <div className="notification-icon">
                    <i className="fas fa-bell"></i>
                </div>
            </header>

            <div className="balance-section">
                <h3>€ {balance}</h3>
            </div>

            <CardComponent accountId={accountId}/>

            <div className="action-buttons">
                {
                    account === "OBSHIAKI" ? (
                        <>
                            <button className="action-button" onClick={openTopUp}>
                                <i className="fas fa-plus-circle"></i>
                                <br/>
                                Top up
                            </button>
                            <button className="action-button" onClick={openAddUserModal}>
                                <i className="fas fa-user-plus"></i>
                                <br/>
                                Add User
                            </button>
                        </>
                    ) : null
                }
                <button className="action-button" onClick={openModal}>
                    <i className="fas fa-arrow-circle-up"></i>
                    <br/>
                    Transfer
                </button>
                <button className="action-button">
                    <i className="fas fa-info-circle"></i>
                    <br/>
                    Details
                </button>
            </div>

            {showTransactionRequests ? (
                <TransactionRequests userId={userId}/>
            ) : (
                <TransactionHistory userId={userId} accountId={accountId}/>
            )}

            <footer className="app-footer">
                <button>
                    <i className="fas fa-home"></i>
                    <br/>
                    Home
                </button>
                <button onClick={() => setShowTransactionRequests(false)}>
                    <i className="fas fa-list-alt"></i>
                    <br/>
                    Transactions
                </button>
                <button onClick={() => setShowTransactionRequests(true)}>
                    <i className="fas fa-envelope"></i>
                    <br/>
                    Requests
                </button>
                <button>
                    <i className="fas fa-cog"></i>
                    <br/>
                    Manage
                </button>
            </footer>

            <TransferModal account={account} accountId={accountId} isOpen={isModalOpen} onClose={closeModal}/>
            <TopUpModal isOpen={isTopUpOpen} onClose={closeTopUp}/>
            <AddUserModal isOpen={isAddUserModalOpen} onClose={closeAddUserModal} onAddUser={handleAddUser}/>
        </div>
    );
};

export default App;
