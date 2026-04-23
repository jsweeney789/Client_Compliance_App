package com.skillstorm.models;

import java.time.Instant;

class KycCheck {
    public enum Result{PASS, FAIL, PENDING_REVIEW}
    public enum CheckType{ID_VERIFICATION, SANCTIONS_SCREENING, PEP_SCREENING, ADVERSE_MEDIA_CHECK}

    private CheckType type;   
    private Result result; 
    private Instant date;
    private String notes;
}
