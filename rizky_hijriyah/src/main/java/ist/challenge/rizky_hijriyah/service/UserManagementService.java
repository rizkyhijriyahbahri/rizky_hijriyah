package ist.challenge.rizky_hijriyah.service;

import ist.challenge.rizky_hijriyah.dto.usermanagement.Message;
import ist.challenge.rizky_hijriyah.dto.usermanagement.UserCredential;
import ist.challenge.rizky_hijriyah.model.UserManagementModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserManagementService {

    ResponseEntity<Message> register(UserCredential userCredential, HttpStatus httpStatus);

    ResponseEntity<Message> login(UserCredential userCredential, HttpStatus httpStatus);

    List<UserManagementModel> listUsers();

    ResponseEntity<UserCredential> editUser(Long id, UserCredential userCredential);

}
