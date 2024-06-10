package africa.semicolon.services.cloud;

import africa.semicolon.config.AppConfig;
import africa.semicolon.exceptions.MediaNotUploadedException;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryServices implements CloudServices{
    private AppConfig appConfig;

    @Override
    public String uploadImage(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary();
        Uploader uploader = cloudinary.uploader();
        try {
            Map<?,?> uploadResponse = uploader.upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id", "adsSpace/adPictures" + file.getName(),
                    "api_key", appConfig.getCloudApiKey(),
                    "api_secret", appConfig.getCloudApiSecret(),
                    "api_name", appConfig.getCloudApiName(),
                    "secure",true
            ));
            return uploadResponse.get("url").toString();
        } catch (IOException e) {
            throw new MediaNotUploadedException("File not uploaded successfully" + e.getMessage());
        }

    }
}
