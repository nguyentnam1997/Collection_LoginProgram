import entities.User;
import service.LoginService;
import service.UserService;
import view.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> userMap = new HashMap<>();
        UserService userService = new UserService();
        LoginService loginService = new LoginService();
        Show menu = new Show();
        loginService.LoginAndRegisterProgram(scanner, menu, userService, userMap);
    }
}