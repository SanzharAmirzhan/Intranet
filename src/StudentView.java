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
        cout("2) Show Attendance");
        cout("3) Add new Subject");
        cout("4) Show my subjects");
        cout("5) Show schedule for subject");
        cout("6) Show whole schedule");
        cout("7) Log-out");
        cout("8) Exit");

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
            showScheduleForSubject();
        }
        else if(type == 6){
            showWholeSchedule();
        }
        else if(type == 7){
            cout("Good bye!");
            this.databaseManager.deleteAuth();
            System.exit(0);
        }
        else if(type == 8){
            System.exit(0);
        }
    }

    private void showTranscript(){
        Set<Transcript> transcriptSet = this.databaseManager.getTranscriptsForStudent(this.student);
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

            if(subject != null) {

                boolean add = true;

                Set<Subject> subjectSet = this.databaseManager.getStudentSubjects(this.student);
                for(Subject sub : subjectSet){
                    if(sub.getSubject_id() == subject.getSubject_id()){
                        add = false;
                        break;
                    }
                }

                if(add == true) {
                    this.databaseManager.addSubjectForStudent(this.student, subject);
                    cout("subject added!");
                }
                else{
                    cout("subject added before");
                }
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

    private void showWholeSchedule(){
        Set<Subject> subjectSet = this.databaseManager.getStudentSubjects(this.student);

        if(subjectSet == null || subjectSet.isEmpty()){
            cout("no subjects yet");
        }
        else {
            cout("Whole Schedule: ");
            for (Subject subject : subjectSet) {
                Schedule schedule = this.databaseManager.getScheduleForSubject(subject);
                cout(subject.getTitle() + " -- Schedule: " + schedule.getSchedule_text());
            }
        }
        load();
    }

    // ----- Other ------
    public static void cout(String st){
        System.out.println(st);
    }
}
