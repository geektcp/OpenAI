package com.geektcp.common.openai;

import com.geektcp.common.openai.OpenAiService;
import com.geektcp.common.openai.moderation.ModerationRequest;
import com.geektcp.common.openai.moderation.Moderation;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ModerationTest {

    String token = System.getenv("OPENAI_TOKEN");
    OpenAiService service = new OpenAiService(token);

    @Test
    void createModeration() {
        ModerationRequest moderationRequest = ModerationRequest.builder()
                .input("I want to kill them")
                .model("text-moderation-latest")
                .build();

        Moderation moderationScore = service.createModeration(moderationRequest).getResults().get(0);

        assertTrue(moderationScore.isFlagged());
    }
}
