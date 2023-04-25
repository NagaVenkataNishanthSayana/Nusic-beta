// components/PlaylistItem/SongList.js
import React from 'react';

const PlaylistItem = ({ playlist, onClick }) => {
    return (
        <li onClick={() => onClick(playlist)}>
            {playlist.name}
        </li>
    );
};

export default PlaylistItem;
