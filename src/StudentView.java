import models.*;

import java.util.*;

public class StudentView {
    public static Scanner cin = new Scanner(System.in);
    private DatabaseManager databaseManager = null;
    private Student student = null;

    public StudentView(Student student){
        this.student = student;
    }

    public void init(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
        cout("Hello " + this.student.getFirstName());
        load();
    }

    private void load(){
        cout("Choose actions :");
        cout("1) Show Transcript");
        cout("2) Show attendance");
        cout("3) Add new Subject");
        cout("4) Show my subjects");
        cout("5) Log-out");
        cout("6) Exit");

        int type = cin.nextInt();

        if(type == 1){
            showTranscript();
        }
        else if(type == 2){
            showAttendance();
        }
        else if(type == 3){
            addSubject();
        }
        else if(type == 4){
            showMySubjects();
        }
        else if(type == 5){
            cout("Good bye!");
            this.databaseManager.deleteAuth();
            System.exit(0);
        }
        else{
            System.exit(0);
        }
        dashLine();
    }

    private void showTranscript(){
        Set<Transcript> transcriptSet = this.databaseManager.getTranscriptForStudent(this.student);
        cout("Transcript :");
        if (transcriptSet.size() == 0){
            cout("Is Empty.");
        }
        else{
            for(Transcript transcript : transcriptSet){
                cout(transcript.getSubject().getTitle() + " " + transcript.getGrade());
            }
        }
        load();
    }

    private void showAttendance(){
        Set<Attendance> attendanceSet = this.databaseManager.getAttendanceForStudent(this.student);
        cout("Attendance :");
        if(attendanceSet.size() == 0){
            cout("Is Empty");
        }
        else{
            for(Attendance attendance : attendanceSet){
                String was = (attendance.isWas() == true) ? "yes" : "no";
                cout(attendance.getSubject().getTitle() + " " + attendance.getDate() + " " + was);
            }
        }
        load();
    }

    private void addSubject(){
        List<Subject> subjectList = this.databaseManager.getAllSubjects();
        if(subjectList.size() == 0){
            cout("Don't have subjects");
        }
        else{
            cout("Choose subject : ");
            for(Subject subject : subjectList){
                cout(subject.getTitle());
            }

            String choosedSubject = (String) cin.next();
            Subject subject = this.databaseManager.getSubjectWithName(choosedSubject);

            if(subject != null){
                this.databaseManager.addSubjectForStudent(this.student, subject);
                cout("subject added!");
            }
            else{
                cout(choosedSubject + " subject does not exist");
            }
        }
        load();
    }

    private void showMySubjects(){
        Set<Subject> subjectSet = this.databaseManager.getStudentSubjects(student);
        cout("Subjects :");
        if(subjectSet.isEmpty()){
            cout("is empty.");
        }
        else {
            for (Subject subject : subjectSet) {
                cout(subject.getTitle());
            }
        }
        load();
    }

    private void dashLine(){
        cout("\n\n");
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
