package com.skillstorm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.skillstorm.models.CaseNote;
import com.skillstorm.models.KycCheck;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.types.KycCheckResult;
import com.skillstorm.types.KycCheckType;
import com.skillstorm.types.OnboardingCaseStage;

import tools.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OnboardingClassIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Single OnboardingCase create test")
    public void onboardingCaseCreateTest() throws Exception {
        CaseNote noteExample = new CaseNote();
        noteExample.setAuthorName("John Smith");
        noteExample.setText("Some Notes");
        noteExample.setTimeStamp(Instant.now().truncatedTo(ChronoUnit.MILLIS));

        KycCheck checkExample = new KycCheck();
        checkExample.setResult(KycCheckResult.PASS);
        checkExample.setType(KycCheckType.ID_VERIFICATION);
        checkExample.setNotes("Different Notes");
        checkExample.setDate(Instant.now().truncatedTo(ChronoUnit.MILLIS));

        OnboardingCase caseExample = new OnboardingCase();
        caseExample.setClientId("exampleClientID");
        caseExample.setStage(OnboardingCaseStage.INITIATED);
        caseExample.getChecks().add(checkExample);
        caseExample.getNotes().add(noteExample);


        // Create Test
        MvcResult createResult = mockMvc.perform(post("/cases").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(caseExample)))
            .andExpect(status().isCreated())
            .andReturn();
        OnboardingCase resultCase = objectMapper.readValue(createResult.getResponse().getContentAsString(), OnboardingCase.class);
        assertNotNull(resultCase.getId());
        assertEquals(caseExample.getStage(), resultCase.getStage());
        assertTrue(compareChecksList(caseExample.getChecks(), resultCase.getChecks()));
        assertTrue(compareNotesList(caseExample.getNotes(), resultCase.getNotes()));
        
        // Read Tests
        MvcResult readResult = mockMvc.perform(get("/cases/{id}", resultCase.getId()).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        // checking that all things are equal on reading from DB
        resultCase = objectMapper.readValue(readResult.getResponse().getContentAsString(), OnboardingCase.class);
        assertNotNull(resultCase.getId());
        assertEquals(caseExample.getStage(), resultCase.getStage());
        assertTrue(compareChecksList(caseExample.getChecks(), resultCase.getChecks()));
        assertTrue(compareNotesList(caseExample.getNotes(), resultCase.getNotes()));
        // testing read fail
        mockMvc.perform(get("/cases/{id}", "123ABC"))
            .andExpect(status().isNotFound());


        // Update Tests
        OnboardingCaseStage oldStage = resultCase.getStage();
        resultCase.setStage(OnboardingCaseStage.REJECTED);
        MvcResult updateResult = mockMvc.perform(put("/cases/{id}", resultCase.getId()).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(resultCase)))
            .andExpect(status().isOk())
            .andReturn();
        resultCase = objectMapper.readValue(updateResult.getResponse().getContentAsString(), OnboardingCase.class);
        assertNotNull(resultCase.getId());
        assertNotEquals(oldStage, resultCase.getStage());
        assertTrue(compareChecksList(caseExample.getChecks(), resultCase.getChecks()));
        assertTrue(compareNotesList(caseExample.getNotes(), resultCase.getNotes()));


        // Delete test (and teardown)
        mockMvc.perform(delete("/cases/{id}", resultCase.getId()))
            .andExpect(status().isNoContent());
        
        // test delete fail
        mockMvc.perform(delete("/cases/{id}", "123ABC"))
            .andExpect(status().isNotFound());
    }

    /**
     * Private helper method to compare any 2 lists of KycChecks to ensure that onboarding cases have matching sets
     * @param list1 - first list of Kyc Checks
     * @param list2 - second list of Kyc Checks
     * @return - true if lists are equal, false otherwise
     */
    private boolean compareChecksList(List<KycCheck> list1, List<KycCheck> list2) {
        boolean listsEqual = true;
        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            if (!(list1.get(i).equals(list2.get(i)))) {
                System.out.println("Notes mismatch at index " + i);
                System.out.println("Expected: " + list1.get(i));
                System.out.println("Actual:   " + list2.get(i));
                listsEqual = false;
            }
        }
        return listsEqual;
    }

    /**
     * Private helper method to compare any 2 lists of caseNotes to ensure that onboarding cases have matching sets
     * @param list1 - first list of Kyc Checks
     * @param list2 - second list of Kyc Checks
     * @return - true if lists are equal, false otherwise
     */
    private boolean compareNotesList(List<CaseNote> list1, List<CaseNote> list2) {
        boolean listsEqual = true;
        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                System.out.println("Notes mismatch at index " + i);
                System.out.println("Expected: " + list1.get(i));
                System.out.println("Actual:   " + list2.get(i));
                listsEqual = false;
            }
        }
        return listsEqual;
    }
}
