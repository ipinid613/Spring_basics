package com.sparta.w1homework.domain;

import java.time.LocalDateTime;

import com.sparta.w1homework.domain.Cheer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@ToString
@NoArgsConstructor
@Getter

//CheerController에서 GetMapping("/api/cheers/{id}")이 실행되면, 사용자가 웹에서 입력한 id, title 등 각 정보를 담아서 반환해줌.
public class CheerDetailResponse {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createdAt;

    @Builder
    public CheerDetailResponse (Long id, String title, String username, String contents, LocalDateTime createdAt ) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public static CheerDetailResponse of ( Cheer cheer ) {
        return CheerDetailResponse.builder()
                .id(cheer.getId())
                .title(cheer.getTitle())
                .username(cheer.getUsername())
                .contents(cheer.getContents())
                .createdAt(cheer.getCreatedAt())
                .build();
    }
}
