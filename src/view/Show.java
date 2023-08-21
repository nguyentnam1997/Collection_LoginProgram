package view;

public class Show {
    public void welcomeMenu() {
        System.out.println("====== WELCOME ======");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("Please choose: ");
    }

    public void viewOptionAfterLogin() {
        System.out.println("1. Change your username");
        System.out.println("2. Change your email");
        System.out.println("3. Change your password");
        System.out.println("4. Logout");
        System.out.println("0. Quit");
    }

    public void reEnterOrForgetPass() {
        System.out.println("Incorrect password, please choose: ");
        System.out.println("1. Re-enter password.");
        System.out.println("2. Forget password?");
    }
    public void reEnterUserOrBack() {
        System.out.println("Username doesn't exists, please choose: ");
        System.out.println("1. Re-enter username.");
        System.out.println("2. Back to the main menu.");
    }
}
