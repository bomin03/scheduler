package com.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity // 이 클래스가 DB테이블과 연결되는 객체라는 표시
@Table(name = "schedules") // 매핑될 실제 테이블 이름 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자가 필요, 외부에서 함부로 빈 객체를 못 만들도록 PROTECTED로 막음
public class Schedule {

    // 기본 키(PK) / @GeneratedValue(IDENTITY) : DB가 1씩 자동 증가시켜 줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 컬럼 제약 조건, nullable의 기본 값이 true이기 때문에 false 필수, length(최대 길이)
    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String contents;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 50)
    private String password;

    // 값을 채워 새 일정(?) 을 만들 때 쓰는 생성자(?)
    public Schedule(String title, String contents, String author, String password) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    // 수정용 메서드: "무엇을 바꾸는지" 의도가 드러나게 setter대신 entity 안에 둠
    public void  update(String title, String contents, String author, String password) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }
}


