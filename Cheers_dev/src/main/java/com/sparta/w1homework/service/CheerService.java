package com.sparta.w1homework.service;

import com.sparta.w1homework.domain.Cheer;
import com.sparta.w1homework.domain.CheerRepository;
import com.sparta.w1homework.domain.CheerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheerService {
    private final CheerRepository cheerRepository;
    @Transactional
    public Long update(Long id, CheerRequestDto requestDto) {
        Cheer cheer = cheerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        cheer.update(requestDto);
        return cheer.getId();
    }
}