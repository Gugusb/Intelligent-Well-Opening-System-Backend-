package com.gugusb.hwics.pojo;

public class DFPMix {
    private DFP1 dfp1;
    private DFP2 dfp2;
    private DFP3 dfp3;
    private DFP4 dfp4;
    private DFP5 dfp5;
    private Boolean succeed;
    private DataCheckResult dataCheckResult;

    public DataCheckResult getDataCheckResult() {
        return dataCheckResult;
    }

    public void setDataCheckResult(DataCheckResult dataCheckResult) {
        this.dataCheckResult = dataCheckResult;
    }

    @Override
    public String toString() {
        return "DFPMix{" +
                "dfp1=" + dfp1 +
                ", dfp2=" + dfp2 +
                ", dfp3=" + dfp3 +
                ", dfp4=" + dfp4 +
                ", dfp5=" + dfp5 +
                ", succeed=" + succeed +
                ", dataCheckResult=" + dataCheckResult +
                '}';
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public DFP1 getDfp1() {
        return dfp1;
    }

    public void setDfp1(DFP1 dfp1) {
        this.dfp1 = dfp1;
    }

    public DFP2 getDfp2() {
        return dfp2;
    }

    public void setDfp2(DFP2 dfp2) {
        this.dfp2 = dfp2;
    }

    public DFP3 getDfp3() {
        return dfp3;
    }

    public void setDfp3(DFP3 dfp3) {
        this.dfp3 = dfp3;
    }

    public DFP4 getDfp4() {
        return dfp4;
    }

    public void setDfp4(DFP4 dfp4) {
        this.dfp4 = dfp4;
    }

    public DFP5 getDfp5() {
        return dfp5;
    }

    public void setDfp5(DFP5 dfp5) {
        this.dfp5 = dfp5;
    }
}
