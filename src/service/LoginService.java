package service;

import entities.User;
import view.Show;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class LoginService {
    public void LoginAndRegisterProgram(Scanner scanner, Show menu, UserService userService, Map<String, User> userMap) {
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
                        userService.Login(scanner, userMap, menu);
                    }
                    case 2 -> {
                        userService.registerUser(scanner, userMap);
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
