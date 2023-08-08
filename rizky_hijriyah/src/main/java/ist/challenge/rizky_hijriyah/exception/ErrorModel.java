package ist.challenge.rizky_hijriyah.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorModel {
    private String code;
    private String message;

}
