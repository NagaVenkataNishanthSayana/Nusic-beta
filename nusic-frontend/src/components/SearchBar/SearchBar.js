// components/SearchBar/SearchBar.js
import React, { useState } from 'react';

const SearchBar = ({ onSearch }) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchBy, setSearchBy] = useState('song');

    const handleSearch = (e) => {
        e.preventDefault();
        onSearch(searchTerm, searchBy);
    };

    return (
        <form onSubmit={handleSearch}>
            <input
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                placeholder="Search"
            />
            <select value={searchBy} onChange={(e) => setSearchBy(e.target.value)}>
                <option value="song">Song</option>
                <option value="album">Album</option>
            </select>
            <button type="submit">Search</button>
        </form>
    );
};

export default SearchBar;
