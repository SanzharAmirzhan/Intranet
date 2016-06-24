package models;

import javax.persistence.*;

@Entity
@Table(name = "AUTH")
public class Auth {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "WHO")
    private String who;

    @Column(name = "WHO_ID")
    private int who_id;

    public Auth(){

    }

    public Auth(String who, int who_id) {
        this.who = who;
        this.who_id = who_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getWho_id() {
        return who_id;
    }

    public void setWho_id(int who_id) {
        this.who_id = who_id;
    }
}
