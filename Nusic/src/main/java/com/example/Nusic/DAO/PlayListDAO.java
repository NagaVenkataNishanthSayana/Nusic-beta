package com.example.Nusic.DAO;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
            rollback();
            e.printStackTrace();
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
            rollback();
            e.printStackTrace();
            throw new PlayListException("Error while creating a playlist"+e.getMessage());
        }
    }

    public PlayList updatePlaylist(Long id, PlayList playList) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            PlayList currPlayList=session.get(PlayList.class,id);
            if(playList.getPlayListName()!=null){
                currPlayList.setPlayListName(playList.getPlayListName());
                session.merge(currPlayList);
            }
            commit();
            close();
            return currPlayList;
        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            throw new PlayListException("Error while updating PlayList:"+e.getMessage());
        }
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
            rollback();
            throw new PlayListException("Error while deleting the playlist "+e.getMessage());
        }
    }

    public List<PlayList> getAllPlaylists() throws PlayListException {
        try {
            begin();
            Session session=getSession();
            String hql="FROM PlayList";
            Query query=session.createQuery(hql,PlayList.class).setMaxResults(10);
            List<PlayList> playLists=query.getResultList();
            commit();
            close();
            for (PlayList playList:playLists){
                playList.setSongs(null);
            }
            return playLists;
        }catch (HibernateException e){
            rollback();
            throw new PlayListException("Error while getting all playlists:"+e.getMessage());
        }

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
            rollback();
            throw new PlayListException("Error while adding song to Playlist:"+e.getMessage());
        }
    }

    public PlayList getPlayListByName(String playlistName) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            String hql="From PlayList where playListName=:playListName";
            Query query=session.createQuery(hql,PlayList.class);
            PlayList currPlayList= (PlayList) query.getSingleResult();
            Set<Song> songs=currPlayList.getSongs();
            songs.size();
            commit();
            close();
            return currPlayList;

        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            throw new PlayListException("Error while getting playList by name:"+playlistName);
        }
    }

    public PlayList removeSongPlayList(Long id, Long songsId) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            PlayList playList=session.get(PlayList.class,id);
            Song currSong=session.get(Song.class,songsId);
            Set<Song> songsSet=playList.getSongs();
            songsSet.remove(currSong);
            playList.setSongs(songsSet);
            session.persist(playList);
            commit();
            close();
            return playList;
        }catch (HibernateException e){
            e.printStackTrace();
            rollback();
            throw new PlayListException("Error while removing song from PlayList:"+e.getMessage());
        }
    }
}
