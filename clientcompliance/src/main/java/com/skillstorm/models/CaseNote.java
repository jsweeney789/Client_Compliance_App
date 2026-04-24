package com.skillstorm.models;

import java.time.Instant;

public class CaseNote {
    private String authorName;
    private Instant timeStamp;
    private String text;
    
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Instant getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
        result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        CaseNote other = (CaseNote) obj;
        if (authorName == null) {
            if (other.authorName != null)
                return false;
        } else if (!authorName.equals(other.authorName))
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "CaseNote [authorName=" + authorName + ", timeStamp=" + timeStamp + ", text=" + text + "]";
    }

    
}
