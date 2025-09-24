package com.gugusb.hwics.pojo.dto;

import java.time.LocalDate;

public class PrinterDTO {
    private LocalDate startTime;
    private LocalDate endTime;

    @Override
    public String toString() {
        return "PrinterDTO{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}
