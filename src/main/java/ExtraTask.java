import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExtraTask {

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/users.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray usersJsonArray = (JSONArray) obj;

/*
//getting all county codes
            List<String> countryCodeList = new ArrayList<>();
            usersJsonArray.forEach(user -> {
                JSONObject employeeObject = (JSONObject) ((JSONObject) user).get("user");
                String county = employeeObject.get("countryCode").toString();
                countryCodeList.add(county);
            });
            System.out.println(countryCodeList);

 */

            List<User> usersList = new ArrayList<>();
//adding users to list
            usersJsonArray.forEach(user -> {
                JSONObject employeeObject = (JSONObject) ((JSONObject) user).get("user");
                User newUser = new User(employeeObject);
                usersList.add(newUser);
            });

            System.out.println("\n______________original_______________");
            usersList.forEach(System.out::println);

            System.out.println("\n______________order by age ASC_______________");
            usersList.sort(Comparator.comparingInt(User::getAge));
            usersList.forEach(System.out::println);


            System.out.println("\n______________id > 10000_______________");
            List<User> usersListWithIdsMoreThan10000 = usersList.stream()
                    .filter(user -> user.getUserId() != null)
                    .filter(user -> user.getUserId() > 10000)
                    .collect(Collectors.toList());
            usersListWithIdsMoreThan10000.forEach(System.out::println);

            System.out.println("\n______________youngest_______________");
            Optional<User> youngest = usersList.stream().min(Comparator.comparingInt(User::getAge));
            if (youngest.isPresent()) {
                System.out.println(youngest);
            }
            System.out.println("\n______________oldest_______________");
            Optional<User> oldest = usersList.stream().max(Comparator.comparingInt(User::getAge));
            if (youngest.isPresent()) {
                System.out.println(oldest);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

//TODO: First complete step 1, then step 2 and etc...
//TODO: Make application working. There could be problems with gradle, refresh dependencies.
//TODO: Create User class with parameters from user.json file.
//TODO: Set User firstname, lastName... Use Enum for country code.
//TODO: Create User list and add all Users.
//TODO: Order list from youngest user to oldest (Use Custom Comparator).
//TODO: Filter list by userId > 10000. If ID is not existent, remove those users. (Use Stream)
//TODO: Find the Oldest and the Youngest user.


