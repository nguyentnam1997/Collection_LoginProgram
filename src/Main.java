import entities.User;
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
        Show menu = new Show();
        userService.LoginProgram(scanner, menu, userMap);
    }
}