package vay.enterwind.kesah.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by novay on 14/07/18.
 */

public class ApiError {

    String message;
    Map<String, List<String>> errors;

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

}
