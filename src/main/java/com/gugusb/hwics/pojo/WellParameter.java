package com.gugusb.hwics.pojo;

public class WellParameter {
    private Float wellDeepth;
    private Float wellTubingID;
    private Float wellCasingID;

    @Override
    public String toString() {
        return "WellParameter{" +
                "wellDeepth=" + wellDeepth +
                ", wellTubingID=" + wellTubingID +
                ", wellCasingID=" + wellCasingID +
                '}';
    }

    public Float getWellDeepth() {
        return wellDeepth;
    }

    public void setWellDeepth(Float wellDeepth) {
        this.wellDeepth = wellDeepth;
    }

    public Float getWellTubingID() {
        return wellTubingID;
    }

    public void setWellTubingID(Float wellTubingID) {
        this.wellTubingID = wellTubingID;
    }

    public Float getWellCasingID() {
        return wellCasingID;
    }

    public void setWellCasingID(Float wellCasingID) {
        this.wellCasingID = wellCasingID;
    }
}
