package com.skillstorm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.skillstorm.models.CaseNote;
import com.skillstorm.models.KycCheck;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.repositories.OnboardingCaseRepository;
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
@TestMethodOrder(MethodOrderer.Random.class)
public class OnboardingCaseIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OnboardingCaseRepository repo;


    @BeforeEach
    void cleanDb() {
        repo.deleteAll();
    }

    

    @Test
    @DisplayName("Create Test on OnboardingCase")
    public void createCaseTest() throws Exception {
        OnboardingCase input = buildCaseExample();
        OnboardingCase result = createCase(input);

        assertNotNull(result.getId());
        assertEquals(input.getStage(), result.getStage());
        assertTrue(compareChecksList(input.getChecks(), result.getChecks()));
        assertTrue(compareNotesList(input.getNotes(), result.getNotes()));
    }

    @Test
    @DisplayName("Read Test on OnboardingCase")
    public void readCaseTest() throws Exception {
        OnboardingCase input = buildCaseExample();
        OnboardingCase created = createCase(input);

        MvcResult readResult = mockMvc.perform(
                get("/cases/{id}", created.getId()))
            .andExpect(status().isOk())
            .andReturn();

        OnboardingCase result = objectMapper.readValue(
            readResult.getResponse().getContentAsString(),
            OnboardingCase.class
        );

        assertEquals(created.getId(), result.getId());
    }

    @Test
    @DisplayName("Update Test on OnboardingCase")
    public void updateCaseTest() throws Exception {
        OnboardingCase created = createCase(buildCaseExample());

        created.setStage(OnboardingCaseStage.REJECTED);

        MvcResult updateResult = mockMvc.perform(
                put("/cases/{id}", created.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(created)))
            .andExpect(status().isOk())
            .andReturn();

        OnboardingCase updated = objectMapper.readValue(
            updateResult.getResponse().getContentAsString(),
            OnboardingCase.class
        );

        assertEquals(OnboardingCaseStage.REJECTED, updated.getStage());
    }

    @Test
    @DisplayName("Delete Test on OnboardingCase")
    public void deleteCaseTest() throws Exception {
        OnboardingCase created = createCase(buildCaseExample());

        mockMvc.perform(delete("/cases/{id}", created.getId()))
            .andExpect(status().isNoContent());

        mockMvc.perform(get("/cases/{id}", created.getId()))
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

    private OnboardingCase buildCaseExample() {
        CaseNote note = new CaseNote();
        note.setAuthorName("John Smith");
        note.setText("Some Notes");
        note.setTimeStamp(Instant.now().truncatedTo(ChronoUnit.MILLIS));

        KycCheck check = new KycCheck();
        check.setResult(KycCheckResult.PASS);
        check.setType(KycCheckType.ID_VERIFICATION);
        check.setNotes("Different Notes");
        check.setDate(Instant.now().truncatedTo(ChronoUnit.MILLIS));

        OnboardingCase c = new OnboardingCase();
        c.setClientId("exampleClientID");
        c.setStage(OnboardingCaseStage.INITIATED);
        c.getChecks().add(check);
        c.getNotes().add(note);

        return c;
    }

    private OnboardingCase createCase(OnboardingCase input) throws Exception {
        MvcResult result = mockMvc.perform(
                post("/cases")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(input)))
            .andExpect(status().isCreated())
            .andReturn();

        return objectMapper.readValue(
            result.getResponse().getContentAsString(),
            OnboardingCase.class
        );
    }
}
