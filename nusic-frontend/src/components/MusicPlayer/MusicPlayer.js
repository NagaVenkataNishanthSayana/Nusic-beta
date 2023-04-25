// components/MusicPlayer/Music Player.js

import React from 'react';

const MusicPlayer = ({ track }) => {
    if (!track) {
        return <p>No track selected</p>;
    }

    return (
        <div>
            <h2>Now Playing: {track.title}</h2>
            <audio src={track.url} controls autoPlay />
        </div>
    );
};

export default MusicPlayer;