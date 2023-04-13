package com.example.Nusic.DAO;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PlayListDAO extends DAO{
    public PlayList getPlaylistById(Long id) throws PlayListException {
        try{
            begin();
            PlayList playList=getSession().get(PlayList.class,id);
            Set<Song> songs =playList.getSongs();
            songs.size();
            commit();
            close();
            return playList;
        }catch (HibernateException e){
            throw new PlayListException("Error while fetching playlist "+e.getMessage());
        }
    }


    public PlayList createPlaylist(PlayList playlist, Long userId) throws PlayListException {

        try{
            begin();
            Session session=getSession();
            playlist.setUser(session.get(User.class,userId));
            session.persist(playlist);
            commit();
            close();
            return playlist;
        }catch (HibernateException e){
            throw new PlayListException("Error while creating a playlist"+e.getMessage());
        }catch (Exception e){
            System.out.println("PlayList Exception");
        }
        return null;
    }

    public PlayList updatePlaylist(Long id, PlayList playlist) {
        return null;
    }

    public void deletePlaylist(Long id) throws PlayListException {
        try{
            begin();
            PlayList playList=getSession().get(PlayList.class,id);

            if(playList!=null){
                getSession().remove(playList);
            }
            commit();
            close();
        }catch (HibernateException e){
            throw new PlayListException("Error while deleting the playlist "+e.getMessage());
        }
    }

    public List<PlayList> getAllPlaylists() {

        return null;

    }

    public PlayList addSongToPlayList(Song song, Long playListId) throws PlayListException {
        try{
            begin();
            Session session=getSession();
            PlayList playList=session.get(PlayList.class,playListId);
            Song newSong=session.get(Song.class,song.getId());
            Set<Song> songsSet=playList.getSongs();
            songsSet.add(newSong);
            playList.setSongs(songsSet);
            session.persist(playList);
            commit();
            close();

            return playList;
        }catch (HibernateException e){
            throw new PlayListException("Error while adding song to Playlist:"+e.getMessage());
        }
    }

    public PlayList getPlayListByName(String playlistName) {
        return null;
    }
}
