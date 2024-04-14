package africa.semicolon.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteAdResponse {
    private LocalDateTime dateDeleted = LocalDateTime.now();
}
