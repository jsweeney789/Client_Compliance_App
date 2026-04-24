package com.skillstorm.types;

public enum KycCheckResult {
    PASS("Pass"),
    FAIL("Fail"),
    PENDING_REVIEW("Pending Review");

    private final String result;

    KycCheckResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
