package com.sparta.week_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> { //Memo라는 Class에서 id는 Long인 녀석을 갖다가 쓸거다
    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end); // <-- 무슨뜻일지 찾아보기 = https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
}