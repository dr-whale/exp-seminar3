import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private long phoneNumber;
    private char gender;

    public Person(String lastName, String firstName, String middleName, Date birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return lastName + firstName + middleName + dateFormat.format(birthDate) + phoneNumber + gender;
    }

    public String getLastName() {
        return lastName;
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine();

        try {
            String[] inputData = input.split(" ");
            if (inputData.length != 6) {
                throw new IllegalArgumentException("Неверное количество параметров");
            }

            String lastName = inputData[0];
            String firstName = inputData[1];
            String middleName = inputData[2];
            Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(inputData[3]);

            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(inputData[4]);
            } catch (NumberFormatException ex) {
                throw new NumberFormatException("Неверный формат номера телефона");
            }

            char gender = inputData[5].charAt(0);

            Person person = new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            File file = new File(person.getLastName() + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(person.toString() + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + file.getName());
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Ошибка: Неверный формат даты");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат номера телефона");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
