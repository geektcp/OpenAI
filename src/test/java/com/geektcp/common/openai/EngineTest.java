package com.geektcp.common.openai;

import com.geektcp.common.openai.engine.Engine;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class EngineTest {

    String token = System.getenv("OPENAI_TOKEN");
    OpenAiService service = new OpenAiService(token);

    @Test
    void getEngines() {
        List<Engine> engines = service.getEngines();

        assertFalse(engines.isEmpty());
    }

    @Test
    void getEngine() {
        Engine ada = service.getEngine("ada");

        assertEquals("ada", ada.id);
    }
}
