package ru.Anton.support.core;

public class Request {
    private String studentId;
    private String topic;
    private String text;
    private String status;

    // Геттеры и сеттеры
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
