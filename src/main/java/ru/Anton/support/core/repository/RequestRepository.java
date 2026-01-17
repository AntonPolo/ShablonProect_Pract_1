package ru.Anton.support.core.repository;

import ru.Anton.support.core.Request;

public interface RequestRepository {
    boolean exists(String studentId, String topic);
    int save(Request request);
}
