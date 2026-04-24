package com.skillstorm.types;

public enum KycCheckType {
    ID_VERIFICATION("ID Verification"),
    SANCTIONS_SCREENING("Sanctions Screening"),
    PEP_SCREENING("PEP Screening"),
    ADVERSE_MEDIA_CHECK("Adverse Media Check");

    private final String checkType;

    KycCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckType() {
        return checkType;
    }
}
