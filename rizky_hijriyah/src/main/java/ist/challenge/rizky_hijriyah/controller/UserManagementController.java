package ist.challenge.rizky_hijriyah.controller;

import ist.challenge.rizky_hijriyah.dto.usermanagement.Message;
import ist.challenge.rizky_hijriyah.dto.usermanagement.UserCredential;

import ist.challenge.rizky_hijriyah.model.UserManagementModel;
import ist.challenge.rizky_hijriyah.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
@RestController
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/register")
    public ResponseEntity<Message> register(@Valid @RequestBody UserCredential userCredential) {
        return userManagementService.register(userCredential, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody UserCredential userCredential) {
        return userManagementService.login(userCredential, HttpStatus.OK);
    }

    @GetMapping("/list-users")
    public List<UserManagementModel> listUsers() {
        return userManagementService.listUsers();
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<UserCredential> editUser(
            @PathVariable Long id,
            @RequestBody UserCredential userCredential)
    {
        return userManagementService.editUser(id,userCredential);
    }
}
