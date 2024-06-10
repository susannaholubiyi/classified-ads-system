package africa.semicolon.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Configuration
public class AppConfig {
    @Value("${cloudinary.api.name}")
    private String cloudApiName;
    @Value("${cloudinary.api.key}")
    private String cloudApiKey;
    @Value("${cloudinary.api.secret}")
    private String cloudApiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Map<?, ?> map = ObjectUtils.asMap(
                "cloud_name", cloudApiName,
                "api_key", cloudApiKey,
                "api_secret", cloudApiSecret
        );
        return new Cloudinary(map);
    }
}
