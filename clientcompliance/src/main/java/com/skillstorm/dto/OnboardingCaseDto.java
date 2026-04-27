package com.skillstorm.dto;

import java.util.List;

import com.skillstorm.models.CaseNote;
import com.skillstorm.models.KycCheck;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.types.OnboardingCaseStage;

public record OnboardingCaseDto(
    String id,
    String clientId,
    OnboardingCaseStage stage,
    List<CaseNote> notes,
    List<KycCheck> checks
) {
    public static OnboardingCase convertToOnboardingCase(String id, String clientId, OnboardingCaseStage stage, List<CaseNote> notes, List<KycCheck> checks) {
        return new OnboardingCase(id, clientId, stage, notes, checks);
    }
}
