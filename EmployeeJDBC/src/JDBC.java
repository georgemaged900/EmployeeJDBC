import java.sql.*;
import java.util.Scanner;

public class JDBC {
    public static void main(String[] args) {

        String username = "root";
        String password = "toor";
        String url = "jdbc:mysql://localhost/sonoo"; // works on sql workbench

        String columnName;
        int columnID, columnAge;
        String ans = "Y";
        String choice;

        Scanner scanner = new Scanner(System.in);

        while (ans!="N") {
            System.out.println("Choose (I) to insert record in database");
            System.out.println("Choose (D) to delete record in database");
            System.out.println("Choose (S) to query database");


            Scanner scannerChoice = new Scanner(System.in);
            choice = scannerChoice.nextLine();

            try {

                Class.forName("com.mysql.cj.jdbc.Driver"); // load database driver class to connect to.
                Connection con = DriverManager.getConnection(url, username, password); // sonoo is database name

                Statement statement = con.createStatement(); // create statement

                if (choice.equals("S")) {
                    System.out.println("Database Records:");
                    ResultSet resultSelect = statement.executeQuery("select* from emp"); // query database
                    while (resultSelect.next())
                        System.out.println(resultSelect.getInt(1) + " " + resultSelect.getString(2) + " " + resultSelect.getString(3));

                }
                if (choice.equals("I")) {

                    System.out.println("Type employee id then name then age to insert in database");
                    Scanner columnIDScanner = new Scanner(System.in);
                    Scanner columnNameScanner = new Scanner(System.in);
                    Scanner columnAgeScanner = new Scanner(System.in);
                    int id = columnIDScanner.nextInt();
                    String name = columnNameScanner.nextLine();
                    int age = columnAgeScanner.nextInt();
                    String insert = "insert into emp values('" + id + "', '" + name + "'," + age + ")";

                    System.out.println("Inserting record into employee database");
                    statement.executeUpdate(insert);
                    System.out.println("Inserted Record " +" "+ id);

                } else if (choice.equals("D")) {

                    System.out.println("Type employee id to delete from database");
                    Scanner scannerDelete = new Scanner(System.in);
                    int id = scannerDelete.nextInt();
                    String delete = "Delete from emp where id = '" + id + "'";
                    System.out.println("Deleting record");
                    statement.executeUpdate(delete);
                    System.out.println("Deleted record" + " " + id);
                }

                con.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("Continue ? (Y) or (N)");
            ans = scanner.nextLine();
            if (ans.equals("N"))
                break;
        }
    }
}
