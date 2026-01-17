@startuml
interface RequestRepository {
    +boolean exists(String studentId, String topic)
    +int save(Request request)
}

interface NotificationService {
    +void send(String studentId, String message)
}

interface AuditLogger {
    +void log(String message)
}

class RequestService {
    -repository: RequestRepository
    -notifier: NotificationService
    -logger: AuditLogger
    +String process(String studentId, String topic, String text, String channel, boolean urgent)
}

class Request {
    -studentId: String
    -topic: String
    -text: String
    -status: String
}

RequestService --> RequestRepository
RequestService --> NotificationService
RequestService --> AuditLogger

class DatabaseRequestRepository
class EmailNotificationService
class FileAuditLogger

DatabaseRequestRepository ..|> RequestRepository
EmailNotificationService ..|> NotificationService
FileAuditLogger ..|> AuditLogger

@enduml