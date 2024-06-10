package africa.semicolon.service.cloud;

import africa.semicolon.services.cloud.CloudServices;
import africa.semicolon.utils.GlobalHelpers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class CloudServiceTest {

    @Autowired
    private CloudServices cloudServices;
    @Test
    public void testUploadImage(){
        Path path = Path.of(GlobalHelpers.filePath);
        try {
            InputStream inputStream = Files.newInputStream(path);
            MultipartFile file = new MockMultipartFile("image", inputStream);
            String response = cloudServices.uploadImage(file);
            assertNotNull(response);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
