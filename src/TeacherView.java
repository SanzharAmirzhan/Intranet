import models.*;

import java.util.*;

public class TeacherView {
    public static Scanner cin = new Scanner(System.in);
    private DatabaseManager databaseManager;
    private Teacher teacher = null;

    public TeacherView(Teacher teacher){
        this.teacher =  teacher;
    }

    public void init(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        cout("Hello " +  this.teacher.getFirstName());
        load();
    }

    private void load(){
        cout("Choose actions :");
        cout("1) Show my subjects");
        cout("2) Add attendance");
        cout("3) Show schedule of subject");
        cout("4) Set grade for student");
        cout("5) Log-out");
        cout("6) Exit");

        int type = cin.nextInt();

        if(type == 1){
            showSubjects();
        }
        else if(type == 2){
            addAttendance();
        }
        else if(type == 3){
            showScheduleForSubject();
        }
        else if(type == 4){
            setGradeForStudent();
        }
        else if(type == 5){
            cout("Good bye!");
            this.databaseManager.deleteAuth();
            System.exit(0);
        }
        else if(type == 6){
            System.exit(0);
        }
    }

    private void showSubjects(){
        Set<Subject> subjectSet = this.databaseManager.getTeacherSubjects(this.teacher);
        cout("Subjects: ");
        if(subjectSet.isEmpty()){
            cout("Dont't have any subjects");
        }
        else{
            for(Subject subject : subjectSet){
                cout(subject.getTitle());
            }
        }
        load();
    }

    private void addAttendance(){
        cout("Enter subject");
        String choosedSubject = (String)cin.next();
        Subject subject = this.databaseManager.getSubjectWithName(choosedSubject);

        if(subject == null){
            cout("not valid subject");
            return;
        }

        Set<Student> studentSet = this.databaseManager.getStudentsBySubject(subject);

        if(studentSet == null || studentSet.isEmpty()){
            cout("no students registered");
        }
        else {
            List<Student> studentList = new ArrayList<Student>(0);
            int ind = 0;
            cout("Students :");
            for(Student student : studentSet){
                studentList.add(student);
                cout(ind + ") " + student.getFirstName() + " " + student.getLastName());
                ind++;
            }

            cout("Enter number of student");
            int choosedIndex = cin.nextInt();

            if (choosedIndex >= 0 && choosedIndex < studentList.size()) {
                Student student = studentList.get(choosedIndex);

                cout("Enter date");
                String choosedDate = (String) cin.next();

                cout("Enter was (yes/no)");
                String choosedWas = (String) cin.next();

                boolean choosedWasBool = true;
                if(choosedWas == "yes")
                    choosedWasBool = false;

                this.databaseManager.addAttendanceForStudent(student, subject, choosedDate, choosedWasBool);
            } else {
                cout("not valid number");
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

    private void setGradeForStudent(){
        cout("Enter subject title:");
        String choosedSubject = (String)cin.next();
        Subject subject = this.databaseManager.getSubjectWithName(choosedSubject);

        if(subject == null){
            cout("not valid subject");
            return;
        }

        Set<Student> studentSet = this.databaseManager.getStudentsBySubject(subject);

        if(studentSet == null || studentSet.isEmpty()){
            cout("no students registered");
        }
        else {
            List<Student> studentList = new ArrayList<Student>(0);
            int ind = 0;
            cout("Students :");
            for(Student student : studentSet){
                studentList.add(student);
                cout(ind + ") " + student.getFirstName() + " " + student.getLastName());
                ind++;
            }

            cout("Enter number of student");
            int choosedIndex = cin.nextInt();

            if (choosedIndex >= 0 && choosedIndex < studentList.size()) {
                Student student = studentList.get(choosedIndex);

                Transcript hasTranscript = this.databaseManager.getTranscriptForStudent(student, subject);

                if(hasTranscript != null){
                    cout("student has grade by this subject");
                    cout("Change grade (yes/no)");
                    String ans = (String)cin.next();

                    if(ans == "yes"){
                        cout("Enter mark");
                        String changedGrade = (String) cin.next();

                        this.databaseManager.changeGradeForStudentSubject(hasTranscript, changedGrade);
                    }
                }
                else {
                    cout("Enter mark");
                    String choosedGrade = (String) cin.next();

                    int transcript_ind = this.databaseManager.setGradeForStudentSubject(student, subject, choosedGrade);
                    if(transcript_ind != -1){
                        cout("Saved successfully!");
                    }
                    else{
                        cout("not saved");
                    }
                }
            } else {
                cout("not valid number");
            }
        }
        load();
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
