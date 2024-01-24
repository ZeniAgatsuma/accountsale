import static java.lang.System.out;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeni.accountssale.presistance.entity.impl.Customer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    
    public static void main(String[] args) {
        List<Customer> customers = generateCustomers(10);
        
        // Виведемо створених користувачів
        for (Customer customer : customers) {
            out.println(customer);
        }
        
        writeCustomersToJsonFile(customers, "customers.json");
    }
    
    public static List<Customer> generateCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        Faker faker = new Faker();
        
        for (int i = 0; i < count; i++) {
            UUID customerId = UUID.randomUUID();
            String password = generateValidPassword();
            String email = faker.internet().emailAddress();
            String customername = faker.name().username();
            String payment = PaymentMethodGenerator.generatePaymentMethod();
            
            Customer customer = Customer.builder()
                    .id(customerId)
                    .nickname(customername)
                    .password(password)
                    .email(email)
                    .paymentMethod(payment)
                    .build();
            
            customers.add(customer);
        }
        
        return customers;
    }
    
    public static void writeCustomersToJsonFile(List<Customer> customers, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            
            gson.toJson(customers, writer);
            
            System.out.println("Колекцію користувачів збережено в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String generateValidPassword() {
        Faker faker = new Faker();
        String password;
        
        do {
            password = faker.internet().password(9, 31, true, true, true);
        } while (!isValidPassword(password));
        
        return password;
    }
    
    private static boolean isValidPassword(String password) {
        // Додайте свої власні критерії валідації для паролю
        return password.length() >= 8 &&
                password.length() <= 32 &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@#$%^&+=].*");
    }
    
    public class PaymentMethodGenerator {
        
        private static final List<String> paymentMethods = Arrays.asList(
                "Credit Card",
                "Debit Card",
                "PayPal",
                "Bitcoin",
                "Apple Pay",
                "Google Pay"
        );
        
        public static String generatePaymentMethod() {
            Random random = new Random();
            int index = random.nextInt(paymentMethods.size());
            return paymentMethods.get(index);
        }
        
        public static void main(String[] args) {
            Faker faker = new Faker();
            
            for (int i = 0; i < 5; i++) {
                String paymentMethod = generatePaymentMethod();
                System.out.println(paymentMethod);
            }
        }
    }
    
}