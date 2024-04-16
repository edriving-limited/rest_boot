package com.edriving.restboot.jersey.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RequestLoggingMessage {
    private final Map<String, Optional<?>> values = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        values.put(key, Optional.ofNullable(value));
    }

    public <T> T get(String key) {
        return values.containsKey(key) ? (T) values.get(key).get() : null;
    }

    public Map<String, ?> getValues() {
        return values;
    }

    public String getRequestId() {
        return get("requestId");
    }

    public void setRequestId(String requestId) {
        put("requestId", requestId);
    }

    public String getPath() {
        return get("path");
    }

    public void setPath(String path) {
        put("path", path);
    }

    public String getMethod() {
        return get("method");
    }

    public void setMethod(String method) {
        put("method", method);
    }

    public String getPathTemplate() {
        return get("pathTemplate");
    }

    public void setPathTemplate(String pathTemplate) {
        put("pathTemplate", pathTemplate);
    }

    public String getQuery() {
        return get("query");
    }

    public void setQuery(String query) {
        put("query", query);
    }

    public Long getRequestTimestamp() {
        return get("requestTimestamp");
    }

    public void setRequestTimestamp(Long requestTimestamp) {
        put("requestTimestamp", requestTimestamp);
    }

    public String getRequestTime() {
        return get("requestTime");
    }

    public void setRequestTime(String requestTime) {
        put("requestTime", requestTime);
    }

    public Integer getHttpStatus() {
        return get("httpStatus");
    }

    public void setHttpStatus(Integer status) {
        put("httpStatus", status);
    }

    public Long getElapsedTime() {
        return get("elapsedTime");
    }

    public void setElapsedTime(Long elapsedTime) {
        put("elapsedTime", elapsedTime);
    }

    public Long getResponseTimestamp() {
        return get("responseTimestamp");
    }

    public void setResponseTimestamp(Long responseTimestamp) {
        put("responseTimestamp", responseTimestamp);
    }

    public String getResponseTime() {
        return get("responseTime");
    }

    public void setResponseTime(String responseTime) {
        put("responseTime", responseTime);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Optional<?>> entry : values.entrySet()) {
            result.put(entry.getKey(), entry.getValue().orElse(null));
        }

        return result;
    }
}
