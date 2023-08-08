package ist.challenge.rizky_hijriyah.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserManagementModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, unique = true)
    private String username;

    @Column(length = 25)
    private String password;
}
