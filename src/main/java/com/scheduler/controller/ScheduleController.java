package com.scheduler.controller;

import com.scheduler.dto.CreateSchedule;
import com.scheduler.dto.ScheduleResponse;
import com.scheduler.entity.Schedule;
import com.scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터를 반환하는 컨트롤러
@RequiredArgsConstructor // final 필드를 받는 생성자를 자동 생성
@RequestMapping("/schedule") // 이 컨트롤러의 모든 경로 앞에 schedule 이 붙는다
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 등록 - POST / schedule
    // @RequestBody - 요청 본문을 CreateSchedule 객체로 변환해서 막음
    @PatchMapping
    public ResponseEntity<Schedule> create(@RequestBody CreateSchedule createSchedule) {
        ScheduleResponse saved = scheduleService.create(createSchedule);
        // ResponseEntity : 응답 본문 + 상태 코드 함께 지정 -> 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 전체 조회 - GET / schedule -> 200 OK
    @GetMapping
    public ResponseEntity<List<Schedule>> findAll() {
        List<ScheduleResponse> responsesList = scheduleService.findAll();
        return ResponseEntity.ok(responsesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findOne(@PathVariable Long id) {
        ScheduleController response = scheduleService.findOne(id);
        return ResponseEntity.ok(response);
    }
}
