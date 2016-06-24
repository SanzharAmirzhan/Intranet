import models.*;

import java.util.*;

public class AdminView {
    public static Scanner cin = new Scanner(System.in);
    private DatabaseManager databaseManager;
    private Admin admin = null;

    public AdminView(Admin admin){
        this.admin = admin;
    }

    public void init(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        cout("Hello Admin - " + this.admin.getName());
        load();
    }

    private void load(){
        cout("Choose actions :");
        cout("1) Add Subject");
        cout("2) Add Schedule");
        cout("3) Change Schedule");
        cout("4) Set teacher for subject");
        cout("5) Show schedule for subject");
        cout("6) Log-out");
        cout("7) Exit");

        int type = cin.nextInt();

        if(type == 1){
            addSubject();
        }
        else if(type == 2){
            addSchedule();
        }
        else if(type == 3){
            changeSchedule();
        }
        else if(type == 4){
            setTeacherForSubject();
        }
        else if(type == 5){
            showScheduleForSubject();
        }
        else if(type == 6){
            cout("Good bye!");
            this.databaseManager.deleteAuth();
            System.exit(0);
        }
        else if (type == 7){
            System.exit(0);
        }
    }

    private void addSubject(){
        cout("Enter title of subject:");
        String title = (String)cin.next();

        cout("Enter description of subject:");
        String description = (String)cin.next();

        int id = this.databaseManager.createSubject(title, description);

        if(id != -1){
            cout("Created successfully!");
            load();
        }
        else{
            cout("Some error/ not created");
        }
    }

    private void addSchedule() {
        cout("Enter title of subject:");
        String title = (String) cin.next();
        Subject subject = this.databaseManager.getSubjectWithName(title);

        if (subject == null) {
            cout("not valid title");
            return;
        }

        Schedule hasSchedule = this.databaseManager.getScheduleForSubject(subject);

        if (hasSchedule != null) {
            cout("subject has schedule");
        } else {
            cout("Enter schedule:");
            String schedule = (String) cin.next();

            int id = this.databaseManager.addScheduleForSubject(subject, schedule);

            if (id != -1) {
                cout("Created successfully!");
                load();
            } else {
                cout("Some error/ not created");
            }
        }
        load();
    }

    private void changeSchedule() {
        cout("Enter title of subject:");
        String title = (String) cin.next();
        Subject subject = this.databaseManager.getSubjectWithName(title);

        if (subject == null) {
            cout("not valid title");
            return;
        }

        cout("Enter new schedule:");
        String scheduleText = (String) cin.next();

        Schedule schedule = this.databaseManager.getScheduleForSubject(subject);

        if(schedule == null){
            cout("Subject doesn't have schedule");
            return;
        }

        this.databaseManager.changeScheduleForSubject(schedule, scheduleText);

        cout("Changed successfully!");
        load();
    }

    private void setTeacherForSubject() {
        cout("Enter subject title:");
        String choosedTitle = (String) cin.next();

        Subject subject = this.databaseManager.getSubjectWithName(choosedTitle);

        if (subject == null) {
            cout("not valid title");
            return;
        }

        Teacher haveTeacher = this.databaseManager.getTeacherForSubject(subject);

        boolean setTeacher = true;

        if(haveTeacher != null){
            cout("Subject has teacher.");
            cout("Change teacher (yes/no)");
            String ans = (String)cin.next();

            setTeacher = false;

            if(ans == "yes"){
                setTeacher = true;
            }
        }

        if(setTeacher == true) {
            List<Teacher> teacherList = this.databaseManager.getAllTeachers();
            int ind = 0;
            cout("Choose teacher number:");
            for (Teacher teacher : teacherList) {
                cout(ind + " " + teacher.getFirstName() + " " + teacher.getLastName());
                ind++;
            }

            int choosedIndex = cin.nextInt();

            if (choosedIndex >= 0 && choosedIndex < teacherList.size()) {
                Teacher teacher = teacherList.get(choosedIndex);
                this.databaseManager.setTeacherForSubject(teacher, subject);
                cout("Success!");
            } else {
                cout("number not valid");
            }
        }
        load();
    }

    private void showScheduleForSubject(){
        cout("Enter title of subject:");
        String title = (String) cin.next();
        Subject subject = this.databaseManager.getSubjectWithName(title);

        if (subject == null) {
            cout("not valid title");
            return;
        }

        Schedule schedule = this.databaseManager.getScheduleForSubject(subject);

        if(schedule != null) {
            cout("Schedule");
            cout(schedule.getSchedule_text());
        }
        else{
            cout("Subject doesn't have schedule");
        }
        load();
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
