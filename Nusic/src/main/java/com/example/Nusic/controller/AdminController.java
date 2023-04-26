package com.example.Nusic.controller;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.Admin;
import com.example.Nusic.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin)  {
        Admin currAdmin=null;
        try {
            currAdmin=adminService.createAdmin(admin);
            currAdmin.setPassword(null);
            return ResponseEntity.ok(currAdmin);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException |
                OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | DatabaseException | PasswordMismatchException e) {
            throw e;
        } catch (AdminException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> validateAdminByEmail(@RequestBody Admin admin, HttpServletRequest request)  {
        Admin currAdmin=null;
        try {
            currAdmin=adminService.validateAdmin(admin);
            currAdmin.setPassword(null);
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            return ResponseEntity.ok(currAdmin);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        } catch (AdminException e) {
            throw new RuntimeException("Internal Server Error", e);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    break;
                }
            }
        }
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdminDetails(@PathVariable Long id,@RequestBody Admin admin){

        Admin currAdmin=null;
        try {
            currAdmin=adminService.updateAdminDetails(id,admin);
            currAdmin.setPassword(null);

            return ResponseEntity.ok(currAdmin);
        } catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                 EntityNotFoundException | UnknownSqlException e) {
            throw e;
        }catch (AdminException e) {
            throw new RuntimeException(e);
        }

    }
}
