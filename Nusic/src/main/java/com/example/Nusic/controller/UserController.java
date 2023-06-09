package com.example.Nusic.controller;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.User;
import com.example.Nusic.service.PlayListService;
import com.example.Nusic.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayListService playListService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User currUser=null;
        try {
            currUser=userService.saveUser(user);
            return ResponseEntity.ok(currUser);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                 EntityNotFoundException | UnknownSqlException |DatabaseException | PasswordMismatchException e) {
            throw e;
        }catch (UserException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }

    @PostMapping("/{id}/playlists")
    public ResponseEntity<PlayList> createPlaylist(@PathVariable Long id,@RequestBody PlayList playlist) {
        PlayList playList=null;
        try {
            playList= playListService.createPlaylist(playlist,id);
            return ResponseEntity.ok(playList);
        } catch(DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch(PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> validateUserByEmail(@RequestBody User user) {
        User currUser = null;
        try {
            currUser = userService.validateUser(user);
            return ResponseEntity.ok(currUser);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                 EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        } catch (UserException e) {
            throw new RuntimeException("Internal Server Error", e);
        }
    }

        @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id,@RequestBody User user){
        User currUser=null;

            try {
                currUser=userService.updateUserDetails(id,user);
                return ResponseEntity.ok(currUser);
            }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                    EntityNotFoundException | UnknownSqlException e) {
                throw e;
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
    }
}
