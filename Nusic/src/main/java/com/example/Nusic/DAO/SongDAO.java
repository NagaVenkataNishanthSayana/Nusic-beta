package com.example.Nusic.DAO;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAO extends DAO{


    public SongDAO() {
    }


    public List<Song> getAllSongs() throws SongException {
        try{
            begin();
            List<Song> songs = getSession().createNativeQuery(
                            "select * from Songs ",Song.class
                    )
                    .getResultList();
            for(Song s:songs){
                System.out.println(s);
            }
            commit();
            close();
            return songs;
        }catch (HibernateException e){
            rollback();
            throw new SongException("Exception while retrieving all song: " + e.getMessage());

        }

    }

    public Song addSong(Long albumId, Song song) throws SongException {

        try{
            begin();
            Session session=getSession();
            song.setAlbum( session.get(Album.class,String.valueOf(albumId)));

            session.persist(song);
            commit();
            close();
            return song;
        }catch (HibernateException e){
            rollback();
            throw new SongException("Exception while adding song: " + e.getMessage());

        }
    }

    public Song getSongById(Long id) throws SongException {
        try{
            begin();
            Song song = getSession().get(Song.class,id);
            commit();
            close();
            return song;
        }catch (HibernateException e){
            rollback();
            throw new SongException("Exception while retrieving all song: " + e.getMessage());

        }
    }

    public Song deleteSongFromAlbum(Long id, Song song) {

        return null;
    }

    public Song getSongByName(String songName) throws SongException {
        try{
            begin();
            String hql="FROM Song as s WHERE s.email= :email";
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Song> cr = cb.createQuery(Song.class);
            Root<Song> root = cr.from(Song.class);
            cr.select(root).where(cb.like(root.get("songName"), "%"+songName+"%"));
            Query<Song> query = getSession().createQuery(cr);
            Song song=query.getSingleResult();
            commit();
            close();

            return song;

        }catch (HibernateException e){
            throw new SongException("Error while fetching Song By Name:"+e.getMessage());
        }
    }
}
