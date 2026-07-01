package com.scheduler.dto;

import lombok.Getter;
// 요청 DTO - 등록(POST) 요청의 JSON 본문을 담는 그릇
@Getter
public class UpdateSchedule {
    private String title;
    private String contents;
    private String author;
    private String password;
}
