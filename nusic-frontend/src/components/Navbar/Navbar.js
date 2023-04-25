// components/Navbar/Navbar.js
import React from 'react';

const Navbar = ({ onProfileClick, onLogoutClick }) => {
    return (
        <nav>
            <ul>
                <li>
                    <button onClick={onProfileClick}>Profile</button>
                </li>
                <li>
                    <button onClick={onLogoutClick}>Logout</button>
                </li>
            </ul>
        </nav>
    );
};

export default Navbar;
