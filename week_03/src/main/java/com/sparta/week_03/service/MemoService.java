package com.sparta.week_03.service;

import com.sparta.week_03.domain.Memo;
import com.sparta.week_03.domain.MemoRepository;
import com.sparta.week_03.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository; //final은 필수적으로 입력! final = 필수적으로 가져와라.
    // 스프링이 memorepository를 이용해서  데이터를 가져오게 하려면 final 필수임!!

    //update#1. 메소드 만들기. public 반환타입 메소드이름(재료)
    @Transactional // 아래 update메소드를 쓸 때 이거 DB에 반영해야된다는 의미에서 쓰는것!!
    public Long update(Long id, MemoRequestDto requestDto) { //update#2. 메소드 선언하기. id와 requestDto를 가져온다.
        Memo memo = memoRepository.findById(id).orElseThrow( //id를 갖고 해당 콘텐츠(memo)를 찾고, 그 콘텐츠를 수정해야 하므로 findById 메소드 사용.
                                                            //이를 위해서는 jpa 기능 갖고있는 MemoRepository를 이용해야 함.
                                                            //id를 찾을 수 없을 땐 어떻게 할지 정해주는 orElseThrow도 필수!!
                                                            //id를 통해 찾은 콘텐츠를 memo에 저장하고, 이를 requestDto로 update해주고, 변경된 memo의 id값을 반환해준다!!
                                                            //ex) id=1로 찾았으면 콘텐츠를 수정하고, 수정이 성공적으로 이루어지면 id=1 반환하기!!
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }
}