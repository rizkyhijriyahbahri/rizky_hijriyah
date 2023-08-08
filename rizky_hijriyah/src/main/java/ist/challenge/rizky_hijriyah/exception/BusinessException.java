package ist.challenge.rizky_hijriyah.exception;

import lombok.Data;

import java.util.List;
@Data
public class BusinessException extends RuntimeException{

    private List<ErrorModel> errors;

    public BusinessException(List<ErrorModel> errors){this.errors = errors;}
}
