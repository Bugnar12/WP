package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Session implements HttpSessionListener {
    private int sessionCount;

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setMaxInactiveInterval(120);
        synchronized (this){
            sessionCount++;
        }

        String sessionId = session.getId();
        Date currentDate = new Date();
        String message = new StringBuffer("New Session created on ")
                .append(currentDate.toString())
                .append("\nID: ")
                .append(sessionId)
                .append("\n")
                .append("There are now")
                .append(sessionCount)
                .append(" active sessions")
                .toString();

        System.out.println(message);
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        HttpSession session = se.getSession();
        String id = session.getId();
        synchronized (this){
            sessionCount--;
        }

        String message = new StringBuffer("Session destroyed\nID: ")
                .append(id)
                .append("\n")
                .append("There are now ")
                .append(sessionCount)
                .append(" active sessions")
                .toString();

        System.out.println(message);
    }
}
