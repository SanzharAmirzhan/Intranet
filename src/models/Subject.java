package models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID")
    private int subject_id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;


    public Subject() {
    }

    public Subject(String title) {
        this.title = title;
    }

    public Subject(String title, String description) {

        this.title = title;
        this.description = description;
    }


    //-------- Relationships --------
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "subjectSet")
    private Set<Student> studentSet = new HashSet<Student>(0);

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "subjects_teacher", joinColumns = @JoinColumn(name = "SUBJECT_ID"),
                                    inverseJoinColumns = @JoinColumn(name = "TEACHER_ID"))
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private Set<Attendance> attendanceSet = new HashSet<Attendance>(0);

    //-------- Getters/Setters --------
    public Set<Attendance> getAttendanceSet() {
        return attendanceSet;
    }

    public void setAttendanceSet(Set<Attendance> attendanceSet) {
        this.attendanceSet = attendanceSet;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
