// components/App.js
import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import SearchBar from './components/SearchBar/SearchBar';
import PlaylistList from './components/PlaylistList/PlaylistList';
import SongList from './components/SongList/SongList';
import MusicPlayer from './components/MusicPlayer/MusicPlayer';
import Login from './components/Login/Login';
import SignUp from './components/SignUp/SignUp';
import { Container, Typography, Box, Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, List, ListItem, ListItemText, ListItemSecondaryAction } from '@mui/material';
import { AddCircle, Edit, Delete, CloudUpload } from '@mui/icons-material';
import axios from 'axios';


const App = () => {
    const [playlists, setPlaylists] = useState([{ id: '1', name: 'Playlist 1', songs: [{id:"1",songPath:"Song Path",songName:"Smoke",artists:"Noct"},{id:"1",songPath:"Song Path",songName:"Smoke",artists:"Noct"}] }]); // Example data: [{ id: '1', name: 'Playlist 1', songs: [...] }]
    const [selectedPlaylist, setSelectedPlaylist] = useState(null);
    const [selectedTrack, setSelectedTrack] = useState(null);

    const handleSearch = (searchTerm, searchBy) => {
        // Perform search and update state with results
    };

    const handlePlaylistClick = (playlist) => {
        setSelectedPlaylist(playlist);
    };

    const handleTrackClick = (track) => {
        setSelectedTrack(track);
    };

    const handleProfileClick = () => {
        console.log('Navigate to profile page');
        // Implement the logic to navigate to the user profile page
    };

    const handleLogoutClick = () => {
        console.log('Perform user logout');
        // Implement the logic to log out the user
    };

    const handleLogin = (email, password) => {
        console.log('Perform user login');
        // Implement the logic to log in the user
    };

    const handleSignUp = (email, password) => {
        console.log('Perform user sign-up');
        // Implement the logic to sign up the user
    };

    return (
        <Router>
            <Navbar onProfileClick={handleProfileClick} onLogoutClick={handleLogoutClick} />
            <Switch>
                <Route exact path="/login">
                    <Login onLogin={handleLogin} />
                </Route>
                <Route exact path="/signup">
                    <SignUp onSignUp={handleSignUp} />
                </Route>
                <Route path="/">
                    <SearchBar onSearch={handleSearch} />
                    <PlaylistList playlists={playlists} onPlaylistClick={handlePlaylistClick} />
                    {selectedPlaylist && <SongList songs={selectedPlaylist.songs} onTrackClick={handleTrackClick} />}
                    <MusicPlayer track={selectedTrack} />
                </Route>
            </Switch>
        </Router>
    );
};

export default App;