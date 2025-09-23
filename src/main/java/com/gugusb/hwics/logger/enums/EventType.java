package com.gugusb.hwics.logger.enums;

public enum EventType {
    START("启动"),
    END("结束"),
    HEARTBEAT("心跳"),
    WARNING("警告"),
    ERROR("错误"),
    PARAM_CHANGE("参数变更");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
