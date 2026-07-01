package com.scheduler.service;

import com.scheduler.controller.ScheduleController;
import com.scheduler.dto.CreateSchedule;
import com.scheduler.dto.ScheduleResponse;
import com.scheduler.dto.UpdateSchedule;
import com.scheduler.entity.Schedule;
import com.scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service // 비즈니스 로직을 담당하는 계층 (컨트롤러에서 받은 요청을 여기서 처리)
@RequiredArgsConstructor // final 필드를 받는 생성자 자동 생성 -> 의존성 주입
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse create (CreateSchedule schedule) {
        // 요청 DTO의 값으로 entity 생성
        Schedule schedule = new Schedule(
                createSchedule.getTitle(),
                createSchedule.getContents(),
                createSchedule.getAuthor(),
                createSchedule.getPassword()
        );

        Schedule savedSchesule = scheduleRepository.save(schedule);

        ScheduleResponse scheduleResponse = new ScheduleResponse(
                savedSchesule.getId(),
                savedSchesule.getTitle(),
                savedSchesule.getContents(),
                savedSchesule.getAuthor(),
                savedSchesule.getPassword()
        );

        return scheduleResponse;
    }

    public List<ScheduleResponse> findAll() {
        List<ScheduleResponse> scheduleResponseList = new ArrayList<>();

        List<Schedule> scheduleList = scheduleRepository.findAll();
        for (Schedule schedule : scheduleList) {
            ScheduleResponse scheduleResponse = new ScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getAuthor(),
                    schedule.getPassword()
            );
            scheduleResponseList.add(scheduleResponse);
        }

        return scheduleResponseList;
    }

    @Transactional
    public ScheduleController findOne(Long id) {
        Schedule schedule = getOrThrow(id);

        ScheduleResponse scheduleResponse = new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getPassword()
        );
        return scheduleResponse;
    }

    @Transactional
    public ScheduleResponse update (Long id, UpdateSchedule updateSchedule) {
        Schedule schedule = getOrThrow(id);

        // entity의 update 메서드로 값 변경
        schedule.update(
                updateSchedule.getTitle(),
                updateSchedule.getContents(),
                updateSchedule.getAuthor(),
                updateSchedule.getPassword()
        );

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        ScheduleResponse scheduleResponse = new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getPassword()
        );
        return scheduleResponse;
    }

    // 삭제 - 먼저 존재 여부 확인 후 없으면 404 하고 삭제
    @Transactional
    public ScheduleResponse delete (Long id) {
        Schedule schedule = getOrThrow(id);
        scheduleRepository.delete(schedule);
    }

    // 내부 공통 메서드 - id로 entity 찾고 없으면 404(ResponseStatusException) 던짐
    // private 라 외부에서는 사용하지 못 하고 이 서비스 안에서 중복으 줄이는 용도
    private Schedule getOrThrow(Long id) {
        return ScheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"일정을 찾을 수 없어요: " + id));
    }
}
