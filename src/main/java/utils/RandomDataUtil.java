package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomDataUtil {
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Emily", "Chris", "Sarah", "David", "Laura", "James", "Olivia"
    };

    private static final String[] LAST_NAMES = {
            "Doe", "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Martinez", "Davis", "Clark"
    };
    private static final String[] FIRST_NAMES_AR = {
            "علي", "فاطمة", "أحمد", "سارة", "يوسف", "مريم", "محمد", "لينا", "عائشة", "إبراهيم"
    };

    private static final String[] LAST_NAMES_AR = {
            "الحسني", "الزيدي", "الشامي", "النجار", "العلي", "الحداد", "الأحمد", "الرفاعي", "الخطيب", "السعود"
    };    private static final String[] ROLES = {"Admin", "User", "Manager", "Viewer"};
    public static String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomUserName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateRandomPassword() {
        String alphanumeric = RandomStringUtils.randomAlphanumeric(20); // 20 alphanumeric characters
        String specialChar = RandomStringUtils.random(1, "!@#$%^&*()-_+=<>?/"); // 1 special character
        String rawPassword = alphanumeric + specialChar;

        // URL encode the password
        return URLEncoder.encode(rawPassword, StandardCharsets.UTF_8);

    }

    public static String generateRandomEmail() {
        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
//        saveCredentials(generateRandomRole(),randomEmail, generateRandomPassword(),true);  // Save email and password
        return randomEmail;

    }

    public static String generateRandomPhone() {
        return "123456" + (int) (Math.random() * 10000);
    }

    public static String generateRandomFullName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    public static String generateRandomFullNameAr() {
        Random random = new Random();
        String firstNameAr = FIRST_NAMES_AR[random.nextInt(FIRST_NAMES_AR.length)];
        String lastNameAr = LAST_NAMES_AR[random.nextInt(LAST_NAMES_AR.length)];
        return firstNameAr + " " + lastNameAr;
    }

    public static String generateRandomBirthDate() {

        int year = 1950 + new Random().nextInt(51); // Random year between 1950 and 2000

        // Set a random month between 1 and 12
        int month = 1 + new Random().nextInt(12);

        // Set a random day between 1 and 28 to avoid invalid dates
        int day = 1 + new Random().nextInt(28);

        // Create a LocalDateTime object from the random year, month, and day
        LocalDateTime randomDate = LocalDateTime.of(year, month, day, 0, 0, 0, 0);

        // Format the date to match the required format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return randomDate.format(formatter) + "Z";
    }

    public static String generateRandomImage() {
        return "image_" + UUID.randomUUID().toString() + ".jpg";
    }

    public static String generateRandomNationalNo() {
        return "NID" + (int) (Math.random() * 1000000);
    }

    public static String generateRandomCreatedDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String generateRandomModifiedDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String generateRandomUserId() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomRoleId() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomRole() {
        Random random = new Random();
        int randomIndex = random.nextInt(ROLES.length);  // Selects a random index from the roles array
        return ROLES[randomIndex];  // Return the role at the selected index
    }
//    public static void saveCredentials(String role, String email, String password, boolean resetFile) {
//        // Define the file name based on the role
//        String fileName = "src/test/resources/TestData/credentials_" + role + ".json";
//
//        // Create the JSON content
//        String jsonContent = String.format("{\n  \"email\": \"%s\",\n  \"password\": \"%s\"\n}", email, password);
//
//        try {
//            // Ensure the directory exists
//            Files.createDirectories(Paths.get("src/test/resources/TestData"));
//
//            // Reset the file (clear existing content) if the flag is true
//            if (resetFile) {
//                Files.write(Paths.get(fileName), "".getBytes());
//                System.out.println("File reset for role: " + role);
//            }
//
//            // Append the new credentials
//            Files.write(Paths.get(fileName), (jsonContent + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            System.out.println("Credentials saved successfully for role: " + role);
//
//        } catch (IOException e) {
//            System.err.println("Error while saving credentials for role: " + role);
//            e.printStackTrace();
//        }
//    }
    public static String[] getCredentials() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/test/resources/TestData/credentials.json"));
            String userName = lines.get(0).split("=")[1];
            String password = lines.get(1).split("=")[1];
            return new String[]{userName, password};
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading credentials.");
            return null;
        }
}
}