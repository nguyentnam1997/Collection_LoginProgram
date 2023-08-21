package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    //check điều kiện username
    public static boolean isValidUsername(String username) {
        // Định dạng regex cho mật khẩu
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
    public int checkExistsUsername(int count, String username, ArrayList<User> users) {
        for (User us : users) {
            if (us.getUsername().equalsIgnoreCase(username)) {
                count++;
            }
        }
        return count;
    }
    //check tồn tại email
    public int checkExistsEmail(int count, String email, ArrayList<User> users) {
        for (User us : users) {
            if (us.getEmail().equalsIgnoreCase(email)) {
                count++;
            }
        }
        return count;
    }
    //đăng ký tài khoản
    public User registerUser(Scanner scanner, ArrayList<User> users) {
        System.out.println("------ REGISTER -------");
        do {
            int count = 0;
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            if (!isValidUsername(username)) {
                System.out.println("Incorrect username, please try again!");
                continue;
            }
                count = checkExistsUsername(count, username, users);
                if (count != 0) {
                    System.out.println("Username already exists, try again!");
                    continue;
                }
                    do {
                        count = 0;
                        System.out.println("Please input your email:");
                        String email = scanner.nextLine();
                        if (!isValidEmail(email)) {
                            System.out.println("Incorrect email, please try again!");
                        }
                        else {
                            count = checkExistsEmail(count, email, users);
                            if (count != 0) {
                                System.out.println("Email already exists, try again!");
                                continue;
                            }
                            do {
                                System.out.println("Please input your password:");
                                String password = scanner.nextLine();
                                if (!isValidPassword(password)) {
                                    System.out.println("Incorrect password, please try again!");
                                    continue;
                                }
                                    System.out.println("Register user successful!!!");
                                    return new User(username, email, password);
                            }
                            while (true);
                        }
                    }
                    while (true);
        }
        while (true);
    }
    //đăng nhập
    public void Login(Scanner scanner, ArrayList<User> users, Show show, UserService userService) {
            if (users.size() == 0) {
                System.out.println("No user exists, please register first!");
                users.add(registerUser(scanner, users));
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
                    count = checkExistsUsername(count, username, users);
                    if (count == 0) {
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
                        for (User user : users) {
                            if (user.getUsername().equalsIgnoreCase(username)) {
                                checkPassword(scanner, user, show);
                                selectOptionAfterLogin(scanner, user, users, show);
                                break;
                            }
                        }
                    }
                    break;
                }
                while (true);
            }
    }
    //kiểm tra mật khẩu
    public void checkPassword(Scanner scanner, User user, Show show) {
        do {
            System.out.println("Please input your password:");
            String password = scanner.nextLine();
            if (!user.getPassword().equals(password)) {
                show.reEnterOrForgetPass();
                int choosePw = Integer.parseInt(scanner.nextLine());
                switch (choosePw) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        changePassword(scanner, user);
                    }
                }
            }
            else {
                System.out.println("------ Login successful! ------");
            }
            break;
        }
        while (true);
    }
    public void selectOptionAfterLogin(Scanner scanner, User user, ArrayList<User> users, Show show) {
        do {
            show.viewOptionAfterLogin();
            System.out.println("Welcome " + user.getUsername() + " , please choose the options above:");
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
                case 1:
                    changeUsername(scanner, user, users);
                    continue;
                case 2:
                    changeEmail(scanner, user, users);
                    continue;
                case 3:
                    changePassword(scanner, user);
                    continue;
                case 4:
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
            break;
        }
        while (true);
    }
    public void changeUsername(Scanner scanner, User user, ArrayList<User> users) {
        int count = 0;
        do {
            System.out.println("Please re-input your username:");
            String username = scanner.nextLine();
            if (!user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Incorrect username, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("Please input your password:");
                    String password = scanner.nextLine();
                    if (!user.getPassword().equalsIgnoreCase(password)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                        do {
                            count = 0;
                            System.out.println("Please enter your new username:");
                            String newUsername = scanner.nextLine();
                            if (!isValidUsername(newUsername)) {
                                System.out.println("Incorrect username, please try again!");
                                continue;
                            }
                            count = checkExistsUsername(count, newUsername, users);
                            if (count != 0) {
                                System.out.println("Username already exists, please re-enter");
                                continue;
                            }
                            else {
                                user.setUsername(newUsername);
                                System.out.println("Create new username successful!!!");
                            }
                            break;
                        }
                        while (true);
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }
    public void changeEmail(Scanner scanner, User user, ArrayList<User> users) {
        int count = 0;
        do {
            System.out.println("Please re-input your email:");
            String email = scanner.nextLine();
            if (!user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Incorrect email, please try again!");
                continue;
            }
                do {
                    System.out.println("Please input your password:");
                    String password = scanner.nextLine();
                    if (!user.getPassword().equalsIgnoreCase(password)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    else {
                        do {
                            count = 0;
                            System.out.println("Please enter your new email:");
                            String newEmail = scanner.nextLine();
                            count = checkExistsEmail(count, newEmail, users);
                            if (count != 0) {
                                System.out.println("Email already exists, please re-enter");
                                continue;
                            }
                                user.setEmail(newEmail);
                                System.out.println("Create new email successful!!!");
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
}
