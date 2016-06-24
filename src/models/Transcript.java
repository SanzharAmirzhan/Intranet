package models;

import javax.persistence.*;

@Entity
@Table(name = "TRANSCRIPT")
public class Transcript {

    @Id
    @GeneratedValue @Column(name = "TRANSCRIPT_ID")
    private int transcript_id;

    @Column(name = "GRADE", nullable = false)
    private String grade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Transcript() {
    }

    public Transcript(String grade) {
        this.grade = grade;
    }

    public int getTranscript_id() {
        return transcript_id;
    }

    public void setTranscript_id(int transcript_id) {
        this.transcript_id = transcript_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
