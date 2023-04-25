// components/PlaylistList/PlaylistList.js
import React from 'react';
import PlaylistItem from '../PlaylistItem/PlaylistItem';

const PlaylistList = ({ playlists, onPlaylistClick }) => {
    return (
        <ul>
            {playlists.map((playlist) => (
                <PlaylistItem key={playlist.id} playlist={playlist} onClick={onPlaylistClick} />
            ))}
        </ul>
    );
};

export default PlaylistList;
