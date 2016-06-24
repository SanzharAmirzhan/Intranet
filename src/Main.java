import java.awt.*;
import java.util.*;
import models.*;
import java.util.Scanner;

public class Main {

    public static DatabaseManager databaseManager = new DatabaseManager();
    public static Scanner cin = new Scanner(System.in);

    public static void main(String[] args){

        cout("Hello!");

        Auth auth = checkAuth();

        if(auth == null){
            cout("You should create account\nChoose :\n1 - Teacher\n2 - Student");
            cout("3 - OR ENTER AS ADMIN:");
            int type = cin.nextInt();
            if(type == 1){
                cout("Enter firstName and lastName");
                String firstName = (String)cin.next();
                String lastName = (String)cin.next();

                cout("Enter password:");
                String password = (String)cin.next();

                int teacher_id = databaseManager.createTeacher(firstName, lastName, password);
                Teacher teacher = databaseManager.getTeacherById(teacher_id);

                if(teacher == null){
                    cout("Teacher with id not found/ not saved");
                    System.exit(0);
                }
                databaseManager.saveAuth("teacher", teacher.getTeacher_id());
                TeacherView teacherView = new TeacherView(teacher);
                teacherView.init();
            }
            else if(type == 2){
                cout("Enter firstName and lastName");
                String firstName = (String)cin.next();
                String lastName = (String)cin.next();

                cout("Enter password:");
                String password = (String)cin.next();

                int student_id = databaseManager.createStudent(firstName, lastName, password);
                Student student = databaseManager.getStudentById(student_id);

                if(student == null) {
                    cout("Student with id not found/ not saved");
                    System.exit(0);
                }
                databaseManager.saveAuth("student", student.getStudent_id());
                StudentView studentView = new StudentView(student);
                studentView.init();
            }
            else{
                cout("Enter username");
                String name = (String)cin.next();

                cout("Enter password:");
                String password = (String)cin.next();

                Admin admin = databaseManager.getAdmin(name, password);

                if(admin == null){
                    cout("Not Valid username/password");
                    System.exit(0);
                }
                databaseManager.saveAuth("admin", admin.getAdmin_id());
                AdminView adminView = new AdminView(admin);
                adminView.init();
            }
        }
        else{
            if(auth.getWho().equals("teacher")){
                Teacher teacher = databaseManager.getTeacherById(auth.getWho_id());
                if(teacher == null){
                    cout("Teacher with id not found");
                    System.exit(0);
                }
                TeacherView teacherView = new TeacherView(teacher);
                teacherView.init();
            }
            else if(auth.getWho().equals("student")){
                Student student = databaseManager.getStudentById(auth.getWho_id());
                if(student == null){
                    cout("Student with id not found");
                    System.exit(0);
                }
                StudentView studentView = new StudentView(student);
                studentView.init();
            }
            else{
                Admin admin = databaseManager.getAdminById(auth.getId());
                if(admin == null){
                    cout("Admin with id not found");
                    System.exit(0);
                }
                AdminView adminView = new AdminView(admin);
                adminView.init();
            }
        }
    }

    public static Auth checkAuth(){
        return databaseManager.checkAuth();
    }

    public static void cout(String st){
        System.out.println(st);
    }
}
