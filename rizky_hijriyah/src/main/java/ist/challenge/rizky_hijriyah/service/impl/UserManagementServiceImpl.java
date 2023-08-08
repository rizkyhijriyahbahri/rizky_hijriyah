package ist.challenge.rizky_hijriyah.service.impl;

import ist.challenge.rizky_hijriyah.dto.usermanagement.Message;
import ist.challenge.rizky_hijriyah.dto.usermanagement.UserCredential;
import ist.challenge.rizky_hijriyah.exception.BusinessException;
import ist.challenge.rizky_hijriyah.exception.ErrorModel;
import ist.challenge.rizky_hijriyah.model.UserManagementModel;
import ist.challenge.rizky_hijriyah.repository.UserManagementRepository;
import ist.challenge.rizky_hijriyah.service.UserManagementService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    UserManagementRepository userManagementRepository;

    @Override
    public ResponseEntity<Message> register(UserCredential userCredential, HttpStatus httpStatus) {

        UserManagementModel existingUser = userManagementRepository.findByUsername(userCredential.getUsername());
        if (!(existingUser == null)) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("409");
            errorModel.setMessage("Username sudah terpakai");
            List<ErrorModel> errors = new ArrayList<>();
            errors.add(errorModel);
            throw new BusinessException(errors);
        }
        UserManagementModel username = new UserManagementModel();
        username.setUsername(userCredential.getUsername());
        username.setPassword(userCredential.getPassword());

        userManagementRepository.save(username);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Override
    public ResponseEntity<Message> login(UserCredential userCredential, HttpStatus httpStatus) {
        UserManagementModel user = userManagementRepository.findByUsername(userCredential.getUsername());
        var userName = user.getUsername();
        if (!userCredential.getPassword().equals(user.getPassword()))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }



    }

    @Override
    public List<UserManagementModel> listUsers() {

        List<UserManagementModel> result = userManagementRepository.findAll();
        return result;
    }

    @Override
    public ResponseEntity<UserCredential> editUser(Long id, UserCredential userCredential) {
        UserManagementModel existingUser = userManagementRepository.findByUsername(userCredential.getUsername());
        Optional<UserManagementModel> userToEdit = userManagementRepository.findById(id);
        if (!(existingUser == null) && !existingUser.getId().equals(id)) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("409");
            errorModel.setMessage("Username sudah terpakai");
            List<ErrorModel> errors = new ArrayList<>();
            errors.add(errorModel);
            throw new BusinessException(errors);
        }
        if (userToEdit.isPresent()) {
            UserManagementModel user = userToEdit.get();
            if (userCredential.getPassword().equals(user.getPassword())) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode("400");
                errorModel.setMessage("Password tidak boleh sama dengan password sebelumnya");
                List<ErrorModel> errors = new ArrayList<>();
                errors.add(errorModel);
                throw new BusinessException(errors);
            }
            user.setUsername(userCredential.getUsername());
            user.setPassword(userCredential.getPassword());
            userManagementRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }
}
