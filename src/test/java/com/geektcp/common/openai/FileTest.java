package com.geektcp.common.openai;

import com.geektcp.common.openai.DeleteResult;
import com.geektcp.common.openai.OpenAiService;
import com.geektcp.common.openai.file.File;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FileTest {
    static String filePath = "src/test/resources/fine-tuning-data.jsonl";

    String token = System.getenv("OPENAI_TOKEN");
    OpenAiService service = new OpenAiService(token);
    static String fileId;

    @Test
    void uploadFile() throws Exception {
        File file = service.uploadFile("fine-tune", filePath);
        fileId = file.getId();

        assertEquals("fine-tune", file.getPurpose());
        assertEquals(filePath, file.getFilename());

        // wait for file to be processed
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    void listFiles() {
        List<File> files = service.listFiles();

        assertTrue(files.stream().anyMatch(file -> file.getId().equals(fileId)));
    }

    @Test
    void retrieveFile() {
        File file = service.retrieveFile(fileId);

        assertEquals(filePath, file.getFilename());
    }

    @Test
    void deleteFile() {
        DeleteResult result = service.deleteFile(fileId);
        assertTrue(result.isDeleted());
    }
}
