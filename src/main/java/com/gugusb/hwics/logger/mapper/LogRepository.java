package com.gugusb.hwics.logger.mapper;

import com.gugusb.hwics.logger.entity.LogEntry;
import com.gugusb.hwics.logger.enums.EventType;
import com.gugusb.hwics.logger.enums.ProcessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findByProcessTypeOrderByTimestampDesc(ProcessType processType);

    List<LogEntry> findByEventTypeOrderByTimestampDesc(EventType eventType);

    @Query("SELECT l FROM LogEntry l WHERE l.timestamp BETWEEN :start AND :end ORDER BY l.timestamp DESC")
    List<LogEntry> findByTimestampBetween(@Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end);

    @Query("SELECT l FROM LogEntry l WHERE l.processType = :processType AND l.timestamp BETWEEN :start AND :end ORDER BY l.timestamp DESC")
    List<LogEntry> findByProcessTypeAndTimestampBetween(
            @Param("processType") ProcessType processType,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    Page<LogEntry> findAllByOrderByTimestampDesc(Pageable pageable);

    List<LogEntry> findAllByOrderByTimestampDesc();
}