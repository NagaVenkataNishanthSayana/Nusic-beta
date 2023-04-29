package com.example.Nusic.controller;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.User;
import com.example.Nusic.service.PlayListService;
import com.example.Nusic.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins ={"http://localhost:3000","https://localhost:3000"} , methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE },allowCredentials = "true")
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
            currUser.setPassword(null);
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

    @CrossOrigin(origins = "http://localhost:3000",exposedHeaders = "Set-Cookie", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<Object> validateUserByEmail(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        User currUser = null;
        try {
            currUser = userService.validateUser(user);
            currUser.setPassword(null);
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            Map<String,Object> map=new HashMap<>();
            map.put("SESSION_ID",sessionId);
            map.put("user",currUser);
            System.out.println(sessionId);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
            headers.add("Access-Control-Allow-Credentials", "true");
//            headers.add("Set-Cookie","SESSION_ID="+sessionId+"; Path=/");
//            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
            session.setAttribute("USER", user);

            return ResponseEntity.ok(map);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                 EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        } catch (UserException e) {
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSION_ID")) {
                    cookie.setMaxAge(0);
                    break;
                }
            }
        }
        session.invalidate();
        return ResponseEntity.ok().build();
    }

        @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id,@RequestBody User user){
        User currUser=null;

            try {
                currUser=userService.updateUserDetails(id,user);
                currUser.setPassword(null);
                return ResponseEntity.ok(currUser);
            }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                    EntityNotFoundException | UnknownSqlException e) {
                throw e;
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
    }
}
