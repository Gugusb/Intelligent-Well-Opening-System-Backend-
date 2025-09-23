package com.gugusb.hwics.logger.entity;

import com.gugusb.hwics.logger.enums.EventType;
import com.gugusb.hwics.logger.enums.ProcessType;
import com.gugusb.hwics.logger.util.HashMapConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "log_entry")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "process_type", nullable = false)
    private ProcessType processType;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(nullable = false, length = 500)
    private String message;

    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, Object> details;

    // 构造函数
    public LogEntry() {}

    public LogEntry(LocalDateTime timestamp, ProcessType processType,
                    EventType eventType, String message, Map<String, Object> details) {
        this.timestamp = timestamp;
        this.processType = processType;
        this.eventType = eventType;
        this.message = message;
        this.details = details;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public ProcessType getProcessType() { return processType; }
    public void setProcessType(ProcessType processType) { this.processType = processType; }

    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Map<String, Object> getDetails() { return details; }
    public void setDetails(Map<String, Object> details) { this.details = details; }
}