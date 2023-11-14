package block1.task1;

public class UserInfo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide user information: Name, Surname.");
            return;
        }

        String name = args[0];
        String surname = args[1];
        System.out.println("Hello! Nice to see you " + name + " " + surname + "!");
    }
}
