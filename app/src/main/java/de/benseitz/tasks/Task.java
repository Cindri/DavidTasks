package de.benseitz.tasks;

import java.io.Serializable;

/**
 * Created by Ben on 16/12/16.
 */
public class Task implements Serializable {
    private String titel;
    private String notiz;
    private Boolean wichtig;

    public Task(String titel, String notiz, Boolean wichtig) {
        super();
        this.titel = titel;
        this.notiz = notiz;
        this.wichtig = wichtig;
    }

    public String getTitel() {
        return titel;
    }

    public String getNotiz() {
        return notiz;
    }

    public boolean getWichtig() { return wichtig; }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public void setWichtig(Boolean wichtig) { this.wichtig = wichtig; }
}
