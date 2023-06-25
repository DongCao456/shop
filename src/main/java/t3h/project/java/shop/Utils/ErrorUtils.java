package t3h.project.java.shop.Utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorUtils {

    public static List<String> getErrorMessages(BindingResult bindingResult){
        List<String> messages=new ArrayList<>();
        List<ObjectError> objectErrors=bindingResult.getAllErrors();
        for (ObjectError error:objectErrors
             ) {
            messages.add(error.getDefaultMessage());
        }
        return messages;
    }


    public static List<String> errorsOf(String error){
        List<String> messages=new ArrayList<>();
        messages.add(error);
        return messages;
    }
}
