package com.gugusb.hwics.logger.enums;

public enum ProcessType {
    FOAMING("泡排"),
    GAS_LIFT("气举"),
    SWABBING("抽汲"),
    SYSTEM("系统");

    private final String displayName;

    ProcessType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}