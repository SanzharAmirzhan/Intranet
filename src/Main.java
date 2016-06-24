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
            cout("4 - OR ENTER AS TEACHER");
            cout("5 - OR ENTER AS STUDENT");
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
                teacherView.init(databaseManager);
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
                studentView.init(databaseManager);
            }
            else if (type == 3){
                cout("username");
                String name = (String)cin.next();

                cout("password:");
                String password = (String)cin.next();

                Admin admin = databaseManager.getAdmin(name, password);

                if(admin == null){
                    cout("Not Valid username/password");
                    System.exit(0);
                }
                cout("success/log-in");
                databaseManager.saveAuth("admin", admin.getAdmin_id());
                AdminView adminView = new AdminView(admin);
                adminView.init(databaseManager);
            }
            else if(type == 4){
                cout("firstName and lastName");
                String firstName = (String)cin.next();
                String lastName = (String)cin.next();

                cout("password:");
                String password = (String)cin.next();

                Teacher teacher = databaseManager.getTeacher(firstName, lastName, password);
                if(teacher == null){
                    cout("Not Valid username/password");
                    System.exit(0);
                }
                cout("success/log-in");
                databaseManager.saveAuth("teacher", teacher.getTeacher_id());
                TeacherView teacherView = new TeacherView(teacher);
                teacherView.init(databaseManager);
            }
            else{
                cout("firstName and lastName");
                String firstName = (String)cin.next();
                String lastName = (String)cin.next();

                cout("password:");
                String password = (String)cin.next();

                Student student = databaseManager.getStudent(firstName, lastName, password);
                if(student == null){
                    cout("Not Valid username/password");
                    System.exit(0);
                }
                cout("success/log-in");
                databaseManager.saveAuth("student", student.getStudent_id());
                StudentView studentView = new StudentView(student);
                studentView.init(databaseManager);
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
                teacherView.init(databaseManager);
            }
            else if(auth.getWho().equals("student")){
                Student student = databaseManager.getStudentById(auth.getWho_id());
                if(student == null){
                    cout("Student with id not found");
                    System.exit(0);
                }
                StudentView studentView = new StudentView(student);
                studentView.init(databaseManager);
            }
            else{
                Admin admin = databaseManager.getAdminById(auth.getId());
                if(admin == null){
                    cout("Admin with id not found");
                    System.exit(0);
                }
                AdminView adminView = new AdminView(admin);
                adminView.init(databaseManager);
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
