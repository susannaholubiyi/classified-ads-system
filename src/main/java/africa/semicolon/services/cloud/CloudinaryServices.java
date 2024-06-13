package africa.semicolon.services.cloud;

import africa.semicolon.config.CloudinaryConfig;
import africa.semicolon.exceptions.MediaNotUploadedException;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CloudinaryServices implements CloudServices{
//    private CloudinaryConfig cloudinaryConfig;
    private final Cloudinary cloudinary;
//    @Autowired
//    public CloudinaryServices(Cloudinary cloudinary) {
//        this.cloudinary = cloudinary;
//    }

    @Override
    public String uploadImage(MultipartFile file) {
        log.info("started request: {}");


        try {
            Uploader uploader = cloudinary.uploader();
            log.info("started request2: {}");
            Map<?,?> uploadResponse = uploader.upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResponse.get("url").toString();
        } catch (IOException e) {
            throw new MediaNotUploadedException("File not uploaded successfully" + e.getMessage());
        }

    }
}
