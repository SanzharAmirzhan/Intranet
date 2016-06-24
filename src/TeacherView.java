import models.*;

import java.util.Scanner;

public class TeacherView {
    public static Scanner cin = new Scanner(System.in);
    private DatabaseManager databaseManager;
    private Teacher teacher;

    public TeacherView(Teacher teacher){
        this.teacher =  teacher;
    }

    public void init(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        cout(this.teacher.getFirstName());
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
