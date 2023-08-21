package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginService {
    public void LoginAndRegisterProgram(Scanner scanner, Show menu, UserService userService, ArrayList<User> users) {
        do {
            try {
                do {
                    menu.welcomeMenu();
                    int choose = Integer.parseInt(scanner.nextLine());
                    if (choose < 1 || choose > 2) {
                        System.out.println("Invalid value, please try again!");
                        continue;
                    }
                    switch (choose) {
                        case 1 -> {
                            userService.Login(scanner, users, menu, userService);
                        }
                        case 2 -> {
                            users.add(userService.registerUser(scanner, users));
                        }
                    }
                }
                while(true);
            }
            catch (Exception e) {
                System.out.println("Invalid value, please try again!");
            }
        }
        while (true);
    }

}
