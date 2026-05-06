package com.skillstorm.models;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.skillstorm.types.OnboardingCaseStage;

@Document(collection="cases")
public class OnboardingCase {
    @Id
    private String id;
    private Instant dateInitiated;
    private String clientId;
    private OnboardingCaseStage stage;
    
    // while these are other models in Java to create consolidated objects, these will be embedded into the case
    // they are relevant to
    private List<CaseNote> notes;
    private List<KycCheck> checks;

    public OnboardingCase(String id, String clientId, OnboardingCaseStage stage, List<CaseNote> notes,
            List<KycCheck> checks) {
        this.id = id;
        
        this.dateInitiated = Instant.now();
        this.clientId = clientId;
        this.stage = stage;
        this.notes = notes;
        this.checks = checks;
    }

    public OnboardingCase(String id, String clientId, OnboardingCaseStage stage) {
        this.id = id;
        this.dateInitiated = Instant.now();
        this.clientId = clientId;
        this.stage = stage;
        this.notes = new ArrayList<CaseNote>();
        this.checks = new ArrayList<KycCheck>();
    }

    public OnboardingCase() {
        this.dateInitiated = Instant.now();
        this.notes = new ArrayList<CaseNote>();
        this.checks = new ArrayList<KycCheck>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public OnboardingCaseStage getStage() {
        return stage;
    }

    public void setStage(OnboardingCaseStage stage) {
        this.stage = stage;
    }

    public List<CaseNote> getNotes() {
        return notes;
    }

    public void setNotes(List<CaseNote> notes) {
        this.notes = notes;
    }

    public List<KycCheck> getChecks() {
        return checks;
    }

    public void setChecks(List<KycCheck> checks) {
        this.checks = checks;
    }

    public Instant getDateInitiated() {
        return dateInitiated;
    }

    public void setDateInitiated(Instant dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    @Override
    public String toString() {
        return "OnboardingCase [id=" + id + ", dateInitiated=" + dateInitiated + ", clientId=" + clientId + ", stage="
                + stage + ", notes=" + notes + ", checks=" + checks + "]";
    }

    
}
