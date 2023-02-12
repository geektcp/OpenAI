package com.geektcp.common.openai;

import com.geektcp.common.openai.OpenAiService;
import com.geektcp.common.openai.edit.EditRequest;
import com.geektcp.common.openai.edit.EditResult;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class EditTest {

    String token = System.getenv("OPENAI_TOKEN");
    OpenAiService service = new OpenAiService(token);

    @Test
    void edit() {
        EditRequest request = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input("What day of the wek is it?")
                .instruction("Fix the spelling mistakes")
                .build();

        EditResult result = service.createEdit( request);

        assertNotNull(result.getChoices().get(0).getText());
    }

    @Test
    void editDeprecated() {
        EditRequest request = EditRequest.builder()
                .input("What day of the wek is it?")
                .instruction("Fix the spelling mistakes")
                .build();

        EditResult result = service.createEdit("text-davinci-edit-001", request);

        assertNotNull(result.getChoices().get(0).getText());
    }
}
