import models.*;

import java.util.*;

public class StudentView {
    public static Scanner cin = new Scanner(System.in);
    private Student student = null;

    public StudentView(Student student){
        this.student = student;
    }

    public void init(){
        cout("Hello " + this.student.getFirstName());


    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
