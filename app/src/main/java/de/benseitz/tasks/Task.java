package de.benseitz.tasks;

import java.io.Serializable;

/**
 * Created by Ben on 16/12/16.
 */
public class Task implements Serializable {
    private String titel;
    private String notiz;

    public Task(String titel, String notiz) {
        super();
        this.titel = titel;
        this.notiz = notiz;
    }

    public String getTitel() {
        return titel;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }
}
