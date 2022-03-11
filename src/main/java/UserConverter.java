import org.json.simple.JSONObject;

public class UserConverter {

    public UserConverter() {
    }

    public User convertUser(JSONObject employeeObject) {
        User user = new User();

        user.setFirstname(employeeObject.get("firstname").toString());
        user.setLastname(employeeObject.get("lastname").toString());
        user.setCity(employeeObject.get("city").toString());
        user.setCountry(employeeObject.get("country").toString());
        user.setEmail(employeeObject.get("email").toString());
        user.setCountryCode(CountryCode.valueOf(employeeObject.get("countryCode").toString()));
        user.setAge(Integer.parseInt(employeeObject.get("age").toString()));
        try {
            user.setUserId(Integer.parseInt(employeeObject.get("userid").toString()));
        } catch (NumberFormatException | NullPointerException ex) {
            //ignore
        }

        return user;
    }
}
