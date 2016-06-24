package models;

import javax.persistence.*;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance {

    @Id
    @GeneratedValue @Column(name = "ATTENDANCE_ID")
    private int attendance_id;

    @Column(name = "DATE", nullable = false)
    private String date;

    @Column(name = "WAS", nullable = false)
    private boolean was;


    //-------- Relationships --------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "subjects_attendance", joinColumns = @JoinColumn(name = "ATTENDANCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID"))
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "student_attendance", joinColumns = @JoinColumn(name = "ATTENDANCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private Student student;

    //-------- Getters/Setters --------

    public Attendance() {
    }

    public Attendance(String date, boolean was) {
        this.date = date;
        this.was = was;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(int attendance_id) {
        this.attendance_id = attendance_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isWas() {
        return was;
    }

    public void setWas(boolean was) {
        this.was = was;
    }
}
