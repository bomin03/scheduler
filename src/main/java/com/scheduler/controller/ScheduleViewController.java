package com.scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 화면용 컨트롤러 - 데이터 API(ScheduleController)와는 별개
@Controller// 뷰(HTML 화면) 이름을 반환 (데이터용 @RestController랑 다름)
public class ScheduleViewController {

    @GetMapping
    public String name() {
        return "index"; // 문자열 "index" -> ttemplates/index.html 을 찾아 브라우저에 보여줌
    }
}
