package ru.Anton.support;


public class RequestProcessor {
    public String process(String studentId, String topic, String text, String channel, boolean urgentFlag) {
        if (studentId == null || topic.isEmpty() || text.isEmpty()) {
            throw new IllegalArgumentException("Bad request");
        }

        // Жёсткая зависимость от конкретной БД
        DatabaseClient db = new DatabaseClient("jdbc:...", "user", "pass");
        // Жёсткая зависимость от файла
        FileLogger logger = new FileLogger("c:/logs/app.log");

        if (urgentFlag) {
            logger.write("URGENT: " + studentId);
        }

        // Уязвимость SQL-инъекции + жёсткая логика
        int existing = db.query("select count(*) from requests where student_id=" + studentId + " and topic='" + topic + "'");
        if (existing > 0) {
            logger.write("Duplicate request: " + studentId);
            return "Already exists";
        }

        int id = db.insert("insert into requests(student_id, topic, text, status) values (...)");

        // Жёсткая привязка к каналам
        if ("email".equals(channel)) {
            SmtpClient smtp = new SmtpClient("smtp.server", 25);
            smtp.send(studentId + "@mail.ru", "Support", "Created request #" + id);
        } else if ("messenger".equals(channel)) {
            MessengerApiClient msg = new MessengerApiClient("token123");
            msg.send(studentId, "Created request #" + id);
        } else {
            SmtpClient smtp = new SmtpClient("smtp.server", 25);
            smtp.send(studentId + "@mail.ru", "Support", "Created request #" + id);
        }

        logger.write("Created request id=" + id);

        // Жёсткая бизнес-логика в методе
        if (topic.contains("password")) {
            return "Reset instruction sent";
        } else if (topic.contains("schedule")) {
            return "We will check schedule";
        } else {
            return "Request accepted";
        }
    }
}

// Фиктивные классы — нужны только для компиляции (не реализуют логику)
class DatabaseClient {
    public DatabaseClient(String url, String user, String pass) {}
    public int query(String sql) { return 0; }
    public int insert(String sql) { return 1; }
}
class FileLogger {
    public FileLogger(String path) {}
    public void write(String msg) {}
}
class SmtpClient {
    public SmtpClient(String host, int port) {}
    public void send(String to, String subject, String body) {}
}
class MessengerApiClient {
    public MessengerApiClient(String token) {}
    public void send(String userId, String message) {}
}