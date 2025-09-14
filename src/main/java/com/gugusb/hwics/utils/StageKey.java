package com.gugusb.hwics.utils;

import java.util.Objects;

public class StageKey {
    private final Integer x;
    private final Integer y;
    private final Integer z;

    public StageKey(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = null;
    }

    public StageKey(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    // 重写equals和hashCode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StageKey)) return false;
        StageKey key = (StageKey) o;
        if(z == null) return x.equals(key.x) && y.equals(key.y);
        else return z.equals(key.z) && x.equals(key.x) && y.equals(key.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
