package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {

    Faker faker = new Faker(new Locale("en"));

    public String firstName = getFirstName(),
            lastName = getLastName(),
            emailAddress = getEmail(),
            currentAddress = getCurrentAddress(),
            gender = getGender(),
            mobileNumber = getMobileNumber(),
            subjects = getSubjects(),
            hobbies = getHobbies(),
            state = getState(),
            city = getCity(state),
            image = getImage(),
            dayOfBirth = getDayOfBirth(),
            monthOfBirth = getMonthOfBirth(),
            yearOfBirth = getYearOfBirth();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[faker.number().numberBetween(0, genders.length - 1)];
    }

    public String getMobileNumber() {
        return faker.number().digits(10);
    }

    public String getDayOfBirth() {
        return String.valueOf(faker.number().numberBetween(10,28));
    }

    public String getMonthOfBirth() {
        String[] months = {"January","February","March","April","May","June","July",
                "August","September","October","November","December"};
        return months[faker.number().numberBetween(0, months.length - 1)];
    }

    public String getYearOfBirth() {
        return String.valueOf(faker.number().numberBetween(1924,2024));
    }

    public String getSubjects() {
        String[] subjects = {"Arts", "History", "English", "Chemistry",
                "Commerce", "Economics", "Maths", "Social Studies", "Physics", "Biology"};
        return subjects[faker.number().numberBetween(0, subjects.length - 1)];
    }

    public String getHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return hobbies[faker.number().numberBetween(0, hobbies.length - 1)];
    }
    public String getImage() {
        String[] images = {"git.png"};
        return images[faker.number().numberBetween(0, images.length - 1)];
    }

    public String getCurrentAddress() {
        return faker.address().streetAddress();
    }

    public String getState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return states[faker.number().numberBetween(0, states.length - 1)];
    }

    public String getCity(String state) {
        String city = "";
        if (state.equals("NCR")) city = faker.options().option("Delhi", "Gurgaon", "Noida");
        if (state.equals("Uttar Pradesh")) city = faker.options().option("Agra", "Lucknow", "Merrut");
        if (state.equals("Haryana")) city = faker.options().option("Karnal", "Panipat");
        if (state.equals("Rajasthan")) city = faker.options().option("Jaipur", "Jaiselmer");
        return city;
    }


}
