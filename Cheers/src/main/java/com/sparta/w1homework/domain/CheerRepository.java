package com.sparta.w1homework.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheerRepository extends JpaRepository<Cheer, Long> {
    List<Cheer> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
}