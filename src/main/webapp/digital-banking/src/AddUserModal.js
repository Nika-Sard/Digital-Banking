import React, { useState } from 'react';

const AddUserModal = ({ isOpen, onClose, onAddUser }) => {
    const [userIdToAdd, setUserIdToAdd] = useState('');

    const handleAddUser = () => {
        onAddUser(userIdToAdd);
        setUserIdToAdd('');
        onClose();
    };

    if (!isOpen) {
        return null;
    }

    return (
        <div className="modal-overlay"> {/* Wrap the modal content with the overlay */}
            <div className="modal-content">
                <h4>Add User to Collaborative Account</h4>
                <input
                    type="text"
                    placeholder="Enter User ID"
                    value={userIdToAdd}
                    onChange={(e) => setUserIdToAdd(e.target.value)}
                />
                <div className="modal-actions">
                    <button onClick={handleAddUser}>Add User</button>
                    <button onClick={onClose}>Cancel</button>
                </div>
            </div>
        </div>
    );
};

export default AddUserModal;
