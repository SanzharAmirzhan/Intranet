import models.*;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class AdminView {
    public static Scanner cin = new Scanner(System.in);
    private DatabaseManager databaseManager;
    private Admin admin = null;

    public AdminView(Admin admin){
        this.admin = admin;
    }

    public void init(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        cout(this.admin.getName());
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
