package com.sparta.w1homework.controller;

import com.sparta.w1homework.domain.Cheer;
import com.sparta.w1homework.domain.CheerRepository;
import com.sparta.w1homework.domain.CheerRequestDto;
import com.sparta.w1homework.service.CheerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CheerController {
    private final CheerRepository cheerRepository;
    private final CheerService cheerService;

    @PostMapping("/api/cheers")
    public Cheer createCheer(@RequestBody CheerRequestDto requestDto) {
        Cheer cheer = new Cheer(requestDto);
        return cheerRepository.save(cheer);
    }

    @GetMapping("/api/cheers")
    public List<Cheer> getCheers() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return cheerRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    @DeleteMapping("/api/cheers/{id}")
    public Long deleteCheer (@PathVariable Long id) {
        cheerRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/cheers/{id}")
    public Long updateCheer(@PathVariable Long id, @RequestBody CheerRequestDto requestDto) {
        cheerService.update(id, requestDto);
        return id;
    }
}