package com.gugusb.hwics.pojo;

import org.springframework.data.util.Pair;

import java.util.List;

public class DataCheckResult {
    List<Pair<String, String>> checkResults;
    Integer suceedCount;
    Integer failedCount;

    public DataCheckResult(List<Pair<String, String>> checkResults, Integer suceedCount, Integer failedCount) {
        this.checkResults = checkResults;
        this.suceedCount = suceedCount;
        this.failedCount = failedCount;
    }

    @Override
    public String toString() {
        return "DataCheckResult{" +
                "checkResults=" + checkResults +
                ", suceedCount=" + suceedCount +
                ", failedCount=" + failedCount +
                '}';
    }

    public List<Pair<String, String>> getCheckResults() {
        return checkResults;
    }

    public void setCheckResults(List<Pair<String, String>> checkResults) {
        this.checkResults = checkResults;
    }

    public Integer getSuceedCount() {
        return suceedCount;
    }

    public void setSuceedCount(Integer suceedCount) {
        this.suceedCount = suceedCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }
}
