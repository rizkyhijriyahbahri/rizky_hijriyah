package ist.challenge.rizky_hijriyah.repository;

import ist.challenge.rizky_hijriyah.dto.usermanagement.UserCredential;
import ist.challenge.rizky_hijriyah.model.UserManagementModel;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserManagementRepository extends JpaRepository<UserManagementModel, Long> {
    UserManagementModel findByUsername(String username);
}
