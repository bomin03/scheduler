package com.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 응답 DTO 클라이언트에게 내보낼 데이터
// entity를 그대로 노출하지 않고, 필요한 값만 골라 DTO로 변환하여 전달
@Getter
public class ScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String author;
    private final String password;

    public ScheduleResponse(Long id, String title, String contents,
                            String author, String password) {

        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }



}
