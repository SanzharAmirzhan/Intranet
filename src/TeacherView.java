import models.*;

import java.util.Scanner;

public class TeacherView {
    public static Scanner cin = new Scanner(System.in);
    private Teacher teacher;

    public TeacherView(Teacher teacher){
        this.teacher =  teacher;
    }

    public void init(){
        cout(this.teacher.getFirstName());
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
