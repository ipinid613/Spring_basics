package com.sparta.week_03.controller;

import com.sparta.week_03.domain.Memo;
import com.sparta.week_03.domain.MemoRepository;
import com.sparta.week_03.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.*;
import com.sparta.week_03.service.MemoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// CRUD 만들기. Update를 위해서는 Service가 필요하고, Create, Read, Delete를 위해서는 Repository가 필요함.
@RestController //다른데서 MemoController 사용할 일 있을 때 new MemoController 이런거 입력 안하도록 만들어줌.
@RequiredArgsConstructor // com.sparta.week_03.controller 만들 땐 @RestController와 세트임!

public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    //Create
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) { //생성할 데이터를 받아야죠. 데이터를 몰고다니는게 Dto!
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }
    //Read
    @GetMapping("/api/memos")
    public List<Memo> readMemo() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(now, yesterday);
    }

    //Update
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id, requestDto);
        return id;
    }

    //Delete
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) { //주소의 {id}를 변수(Long id)로 받으려면 @PathVariable 필요!!!
        memoRepository.deleteById(id);
        return id;
    }
}