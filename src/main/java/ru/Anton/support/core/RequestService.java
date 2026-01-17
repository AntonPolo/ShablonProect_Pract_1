package ru.Anton.support.core;

import ru.Anton.support.core.audit.AuditLogger;
import ru.Anton.support.core.notification.NotificationService;
import ru.Anton.support.core.repository.RequestRepository;

public class RequestService {
    private final RequestRepository repository;
    private final NotificationService notifier;
    private final AuditLogger logger;

    public RequestService(RequestRepository repository, NotificationService notifier, AuditLogger logger) {
        this.repository = repository;
        this.notifier = notifier;
        this.logger = logger;
    }
}
