package models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue @Column(name = "STUDENT_ID")
    private int student_id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public Student(){

    }

    public Student(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    //-------- Relationships --------
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRANSCRIPT_ID")
    private Transcript transcript;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "students_subjects", joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID"))
    private Set<Subject> subjectSet = new HashSet<Subject>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Attendance> attendanceSet = new HashSet<Attendance>(0);

    //-------- Getters/Setters --------
    public Set<Attendance> getAttendanceSet() {
        return attendanceSet;
    }

    public void setAttendanceSet(Set<Attendance> attendanceSet) {
        this.attendanceSet = attendanceSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
