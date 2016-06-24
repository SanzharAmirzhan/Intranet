package models;

import javax.persistence.*;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue @Column(name = "SCHEDULE_ID")
    private int schedule_id;

    @Column(name = "SCHEDULE_TEXT", nullable = false)
    private String schedule_text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Schedule() {
    }

    public Schedule(String schedule_text) {
        this.schedule_text = schedule_text;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSchedule_text() {
        return schedule_text;
    }

    public void setSchedule_text(String schedule_text) {
        this.schedule_text = schedule_text;
    }
}
