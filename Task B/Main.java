import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
//        System.out.println(string);
        Parser parser = new Parser();
        Tree tree = parser.parse(string);
//        System.out.println(tree.toString()); "((PPP->PPP’)->PPP)->PPP"
        parser.compute(tree);
    }
}
