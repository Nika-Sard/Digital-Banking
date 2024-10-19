import React, {useEffect, useState} from 'react';
import CardComponent from './CardComponent';
import TransactionHistory from './TransactionHistory';
import TransferModal from './TransferModal'; // Import the modal
import '@fortawesome/fontawesome-free/css/all.min.css';
import './styles.css';
import TopUpModal from "./TopUpModel";

const App = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isTopUpOpen, setIsTopUpOpen] = useState(false);
    const [balance, setBalance] = useState(0);
    const [userId, setUserId] = useState(0);
    const [accountId, setAccountId] = useState(0);

    useEffect(() => {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search);
        setUserId(params.get('userId'));
        setAccountId(params.get('accountId'));
    }, []);

    useEffect(() => {
        fetch(`http://localhost:8080/getAccount/` + userId + '/' + accountId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                setBalance(data.balance);
            })
            .catch(error => console.error('Error:', error));
    }, [userId, accountId]);

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

    return (
        <div className="app-container">
            <header className="app-header">
                <div className="user-icon">RL</div>
                <h2>OBSHIAKI</h2>
                <div className="notification-icon">
                    <i className="fas fa-bell"></i>
                </div>
            </header>

            <div className="balance-section">
                <h3>€ {balance}</h3>
            </div>

            <CardComponent accountId = {accountId} />

            <div className="action-buttons">
                <button className="action-button" onClick={openTopUp}><i className="fas fa-plus-circle"></i><br />Top up</button>
                <button className="action-button"><i className="fas fa-sync-alt"></i><br />Exchange</button>
                <button className="action-button" onClick={openModal}><i className="fas fa-arrow-circle-up"></i><br />Transfer</button>
                <button className="action-button"><i className="fas fa-info-circle"></i><br />Details</button>
            </div>

            <TransactionHistory userId={userId} accountId={accountId}/>

            <footer className="app-footer">
                <button><i className="fas fa-home"></i><br />Home</button>
                <button><i className="fas fa-list-alt"></i><br />Transactions</button>
                <button><i className="fas fa-chart-line"></i><br />Reports</button>
                <button><i className="fas fa-cog"></i><br />Manage</button>
            </footer>

            {/* Transfer Modal */}
            <TransferModal isOpen={isModalOpen} onClose={closeModal} />
            <TopUpModal isOpen={isTopUpOpen} onClose={closeTopUp}/>
        </div>
    );
};

export default App;
