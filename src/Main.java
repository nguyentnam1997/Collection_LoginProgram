import entities.User;
import service.LoginService;
import service.UserService;
import view.Show;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        UserService userService = new UserService();
        LoginService loginService = new LoginService();
        Show menu = new Show();
        loginService.LoginAndRegisterProgram(scanner, menu, userService, users);
    }
}