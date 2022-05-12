package ir.amitis.taskManagement.customValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MobileNumberValidator implements ConstraintValidator<MobileNumber,String> {

    @Override
    public void initialize(MobileNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String phone = "";
        if (s.startsWith("+98")) {
            phone = s.replace("+98", "09");
        }else if  (s.startsWith("0098")) {
            phone = s.replace("0098", "09");
        }

        if (phone.startsWith("09") && phone.length() == 11) {
            return true;
        }else {
            return false;
        }
    }
}

