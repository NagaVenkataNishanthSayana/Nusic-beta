package com.example.Nusic.util;

import com.example.Nusic.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@org.springframework.context.annotation.Configuration

@ComponentScan(basePackages = { "com.example.Nusic" })
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        try {

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/NusicDB?createDatabaseIfNotExist=true&useSSL=false");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "Testuser@1");
            properties.put(Environment.FORMAT_SQL, "true");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.POOL_SIZE, "5");

            return new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(User.class).addAnnotatedClass(Playlist.class).addAnnotatedClass(Song.class).addAnnotatedClass(Album.class).addAnnotatedClass(Admin.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("build SeesionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
