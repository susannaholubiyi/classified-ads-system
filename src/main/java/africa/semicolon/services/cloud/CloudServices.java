package africa.semicolon.services.cloud;

import org.springframework.web.multipart.MultipartFile;

public interface CloudServices {
    String uploadImage(MultipartFile file);
}
