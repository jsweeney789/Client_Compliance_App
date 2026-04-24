package com.skillstorm.models;

import java.time.Instant;

import com.skillstorm.types.KycCheckResult;
import com.skillstorm.types.KycCheckType;

public class KycCheck {
    

    private KycCheckType type;   
    private KycCheckResult result; 
    private Instant date;
    private String notes;

    public KycCheckType getType() {
        return type;
    }
    public void setType(KycCheckType type) {
        this.type = type;
    }
    public KycCheckResult getResult() {
        return result;
    }
    public void setResult(KycCheckResult result) {
        this.result = result;
    }
    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KycCheck other = (KycCheck) obj;
        if (type != other.type)
            return false;
        if (result != other.result)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "KycCheck [type=" + type + ", result=" + result + ", date=" + date + ", notes=" + notes + "]";
    }


}
