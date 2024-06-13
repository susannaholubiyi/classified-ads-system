package africa.semicolon.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Getter
@Configuration
public class CloudinaryConfig {
//    @Value("${cloudinary.api.name}")
    private String cloudApiName = "dbd4aummb";
//    @Value("${cloudinary.api.key}")
    private String cloudApiKey = "762645381836436";
//    @Value("${cloudinary.api.secret}")
    private String cloudApiSecret = "yL60VQ5aN6UmWHaBJ4y0DaJyv_4";

    @Bean
    public Cloudinary cloudinary(){
        Map<?, ?> config = ObjectUtils.asMap(
                "cloud_name", cloudApiName,
                "api_key", cloudApiKey,
                "api_secret", cloudApiSecret
        );
        return new Cloudinary(config);
    }
}
