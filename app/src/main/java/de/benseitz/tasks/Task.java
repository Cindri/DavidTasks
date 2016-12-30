package de.benseitz.tasks;

import java.io.Serializable;

/**
 * Created by Ben on 16/12/16.
 */
public class Task implements Serializable {
    private String titel;
    private String notiz;
    private Boolean important;
    private Boolean done;

    public Task(String titel, String notiz, Boolean important, Boolean done) {
        super();
        this.titel = titel;
        this.notiz = notiz;
        this.important = important;
        this.done = done;
    }

    public String getTitel() {
        return titel;
    }

    public String getNotiz() {
        return notiz;
    }

    public boolean getImportant() { return important; }

    public boolean getDone() { return done; }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public void setImportant(boolean important) { this.important = important; }

    public void setDone(boolean done) { this.done = done; }
}
