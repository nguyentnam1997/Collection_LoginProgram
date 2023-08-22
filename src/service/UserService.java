package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    //check điều kiện username
    public static boolean isValidUsername(String username) {
        // Định dạng regex cho username
        String usernameRegex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches() && !username.isEmpty();
    }
    //check điều kiện Password
    public static boolean isValidPassword(String password) {
        // Định dạng regex cho mật khẩu
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.,-_;])(?!.*\\s).{7,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    //check điều kiện email
    public static boolean isValidEmail(String email) {
        // Định dạng regex cho địa chỉ email
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //check tồn tại username
    /*public int checkExistsUsername(int count, String username, ArrayList<User> users) {
        for (User us : users) {
            if (us.getUsername().equalsIgnoreCase(username)) {
                count++;
            }
        }
        return count;
    }*/
    //check tồn tại email
    public String checkExistsEmail(String email, Map<String, User> userMap) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if (entry.getValue().getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already exists, try again!");
                return null;
            }
        }
        return email;
    }
    //đăng ký tài khoản
    public void registerUser(Scanner scanner, Map<String, User> userMap) {
        System.out.println("------ REGISTER -------");
        do {
            //int count = 0;
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            if (!isValidUsername(username)) {
                System.out.println("Incorrect username, please try again!");
                continue;
            }
            if (userMap.containsKey(username)) {
                System.out.println("Username already exists, try again!");
                continue;
            }
            do {
                //count = 0;
                System.out.println("Please input your email:");
                String email = scanner.nextLine();
                if (!isValidEmail(email)) {
                    System.out.println("Incorrect email, please try again!");
                    continue;
                }
                else if (checkExistsEmail(email, userMap) == null) continue;
                else {
                    do {
                        System.out.println("Please input your password:");
                        String password = scanner.nextLine();
                        if (!isValidPassword(password)) {
                            System.out.println("Incorrect password, please try again!");
                            continue;
                        }
                        System.out.println("Register user successful!!!");
                        User user = new User(username, email, password);
                        userMap.put(user.getUsername(), user);
                        break;
                    }
                    while (true);
                }
                break;
            }
            while (true);
            break;
        }
        while (true);
    }
    //đăng nhập
    public void Login(Scanner scanner, Map<String, User> userMap, Show show) {
            if (userMap.size() == 0) {
                System.out.println("No user exists, please register first!");
                registerUser(scanner, userMap);
            }
            else {
                do {
                    int count = 0;
                    System.out.println("Please input your username:");
                    String username = scanner.nextLine();
                    if (!isValidUsername(username)) {
                        System.out.println("Incorrect username, please try again!");
                        continue;
                    }
                    else if (!userMap.containsKey(username)) {
                        show.reEnterUserOrBack();
                        int choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1:
                                continue;
                            case 2:
                                break;
                        }
                    }
                    else {
                        checkPassword(scanner, username, userMap, show);
                    }
                    break;
                }
                while (true);
            }
    }
    //kiểm tra mật khẩu
    public void checkPassword(Scanner scanner, String username, Map<String, User> userMap, Show show) {
        do {
            System.out.println("Please input your password:");
            String password = scanner.nextLine();
            if (userMap.get(username).getPassword().equals(password)) {
                System.out.println("------ Login successful! ------");
                /*show.reEnterOrForgetPass();
                int choosePw = Integer.parseInt(scanner.nextLine());
                switch (choosePw) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        changePassword(scanner, userMap.get(username));
                    }
                }*/
            }
            else {
                System.out.println("Incorrect password, please try again!");
                continue;
            }
            break;
        }
        while (true);
    }
    public void changePassword(Scanner scanner, User user) {
        do {
            System.out.println("Please input your email:");
            String email = scanner.nextLine();
            if (!user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Incorrect email, please try again!");
                continue;
            }
                do {
                    System.out.println("Please input new password: ");
                    String newPassword = scanner.nextLine();
                    if (!isValidPassword(newPassword)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    if (newPassword.equals(user.getPassword())) {
                        System.out.println("This password has been created before, please try again!");
                        continue;
                    }
                        System.out.println("Create new password successful!!!");
                        user.setPassword(newPassword);
                    break;
                }
                while (true);
            break;
        }
        while (true);
    }
    public void LoginProgram(Scanner scanner, Show menu, Map<String, User> userMap) {
        do {
            menu.welcomeMenu();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (choose < 1 || choose > 2) {
                    System.out.println("Invalid value, please try again!");
                    continue;
                }
                switch (choose) {
                    case 1 -> {
                        Login(scanner, userMap, menu);
                    }
                    case 2 -> {
                        registerUser(scanner, userMap);
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Invalid value, please try again!");
            }
        }
        while (true);
    }
}
