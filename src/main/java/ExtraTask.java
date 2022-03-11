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
    final static public int ID_BOUNDARY = 10000;

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

            List<User> usersList = UsersToArrayList(usersJsonArray);

            System.out.println("\n______________original_______________");
            usersList.forEach(System.out::println);

            System.out.println("\n______________order by age ASC_______________");
            printUsersByAgeAsc(usersList);

            System.out.println("\n______________id >" + ID_BOUNDARY + "_______________");
            printUsersWithIdsGreaterThanConst(usersList);

            System.out.println("\n______________youngest_______________");
            youngestUser(usersList);

            System.out.println("\n______________oldest_______________");
            oldestUser(usersList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> UsersToArrayList(JSONArray usersJsonArray) {
        ArrayList<User> usersList = new ArrayList<>();
        UserConverter converter = new UserConverter();

        //adding converted users to list
        usersJsonArray.forEach(user -> {
            JSONObject employeeObject = (JSONObject) ((JSONObject) user).get("user");
            User newUser = converter.convertUser(employeeObject);
            usersList.add(newUser);
        });
        return usersList;
    }

    public static void printUsersByAgeAsc(List<User> usersList) {
        usersList.sort(Comparator.comparingInt(User::getAge));
        usersList.forEach(System.out::println);
    }

    public static void printUsersWithIdsGreaterThanConst(List<User> usersList) {
        List<User> usersListWithIds = usersList.stream()
                .filter(user -> user.getUserId() != null)
                .filter(user -> user.getUserId() > ID_BOUNDARY)
                .collect(Collectors.toList());
        usersListWithIds.forEach(System.out::println);
    }


    public static void youngestUser(List<User> usersList) {
        Optional<User> youngest = usersList.stream().min(Comparator.comparingInt(User::getAge));
        if (youngest.isPresent()) {
            System.out.println(youngest);
        }
    }

    public static void oldestUser(List<User> usersList) {
        Optional<User> oldest = usersList.stream().max(Comparator.comparingInt(User::getAge));
        if (oldest.isPresent()) {
            System.out.println(oldest);
        }
    }


//    public static ArrayList<User> UsersArrayList(JSONArray usersJsonArray) {
//        ArrayList<User> usersList = new ArrayList<>();
////adding users to list
//        usersJsonArray.forEach(user -> {
//            JSONObject employeeObject = (JSONObject) ((JSONObject) user).get("user");
//            User newUser = new User(employeeObject);
//            usersList.add(newUser);
//        });
//        return usersList;
//    }
}

//TODO: First complete step 1, then step 2 and etc...
//TODO: Make application working. There could be problems with gradle, refresh dependencies.
//TODO: Create User class with parameters from user.json file.
//TODO: Set User firstname, lastName... Use Enum for country code.
//TODO: Create User list and add all Users.
//TODO: Order list from youngest user to oldest (Use Custom Comparator).
//TODO: Filter list by userId > 10000. If ID is not existent, remove those users. (Use Stream)
//TODO: Find the Oldest and the Youngest user.


