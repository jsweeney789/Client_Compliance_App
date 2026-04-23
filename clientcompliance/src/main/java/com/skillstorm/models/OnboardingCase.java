package com.skillstorm.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cases")
public class OnboardingCase {
    @Id
    private String id;

    private String clientId;
    private String stage;
    
    // while these are other models in Java to create consolidated objects, these will be embedded into the case
    // they are relevant to
    private List<CaseNote> notes;
    private List<KycCheck> checks;

    public OnboardingCase(String id, String clientId, String stage) {
        this.id = id;
        this.clientId = clientId;
        this.stage = stage;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
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

    
}
