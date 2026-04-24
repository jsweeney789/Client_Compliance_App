package com.skillstorm.types;

public enum OnboardingCaseStage {
    INITIATED("Initiated"),
    UNDER_REVIEW("Under Review"),
    KYC_IN_PROGRESS("Kyc In Progress"),
    APPROVED("Approved"),
    REJECTED("Rejected");


    private final String caseStage;

    OnboardingCaseStage(String caseStage) {
        this.caseStage = caseStage;
    }

    public String getCaseStage() {
        return caseStage;
    }
}
