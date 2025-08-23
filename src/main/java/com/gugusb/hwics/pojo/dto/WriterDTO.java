package com.gugusb.hwics.pojo.dto;

public class WriterDTO {
    private String dataType;
    private Boolean dataBoolean;
    private Short dataShort;
    private Float dataFloat;
    private String place;
    private Integer pageIndex;

    @Override
    public String toString() {
        return "WriterDTO{" +
                "dataType='" + dataType + '\'' +
                ", dataBoolean=" + dataBoolean +
                ", dataShort=" + dataShort +
                ", dataFloat=" + dataFloat +
                ", place='" + place + '\'' +
                ", pageIndex=" + pageIndex +
                '}';
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getDataBoolean() {
        return dataBoolean;
    }

    public void setDataBoolean(Boolean dataBoolean) {
        this.dataBoolean = dataBoolean;
    }

    public Short getDataShort() {
        return dataShort;
    }

    public void setDataShort(Short dataShort) {
        this.dataShort = dataShort;
    }

    public Float getDataFloat() {
        return dataFloat;
    }

    public void setDataFloat(Float dataFloat) {
        this.dataFloat = dataFloat;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
