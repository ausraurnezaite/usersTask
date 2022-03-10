import org.json.simple.JSONObject;

import java.util.Optional;

public class User {
    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private int age;
    private CountryCode countryCode;
    private String email;
    private Integer userId;

    public User(JSONObject employeeObject) {

        this.firstname = (String) employeeObject.get("firstname");
        this.lastname = (String) employeeObject.get("lastname");
        this.city = (String) employeeObject.get("city");
        this.country = (String) employeeObject.get("country");
        this.email = (String) employeeObject.get("email");
        this.countryCode = CountryCode.valueOf(employeeObject.get("countryCode").toString());

        String ageString = (String) employeeObject.get("age");
        this.age = Integer.parseInt(ageString);

        String userIdString = (String) employeeObject.get("userid");
        try {
            this.userId = Integer.parseInt(userIdString);
        } catch (NumberFormatException ex) {
            //ignore
        }
    }

    @Override
    public String toString() {
        String idString = "";
        if (this.userId != null) {
            idString = ", userId=" + userId.toString();

        }
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                ", countryCode='" + countryCode + '\'' +
                ", email='" + email + '\'' +
                idString +
                '}';
    }


    public Integer getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }


    public int getAge() {
        return age;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

/*
	"user": {
		"firstname": "Babita",
		"lastname": "Stuart",
		"city": "Sydney",
		"country": "Gibraltar",
		"age": "55",
		"countryCode": "PM",
		"email": "Babita.Stuart@yopmail.com"
	}
 */


/*
{"user":{"country":"Martinique","firstname":"Viviene","city":"Sucre","countryCode":"GB","age":"11","email":"Viviene.Wattenberg@yopmail.com","lastname":"Wattenberg"}}
 */
