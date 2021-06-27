package com.sparta.week01_homework.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다. 이걸 써줘야 DB로 인식해서 14열 좌측에 아이콘 생김
@EntityListeners(AuditingEntityListener.class) // course와 같은 테이블의 생성/수정(auditing) 시간을 자동으로 반영(listen)하도록 설정
public abstract class Timestamped { // <-- abstract = 추상클래스. 이거 절대 단독 클래스로 못 쓰고 누가 상속하면 거기서만 써진다!!

    @CreatedDate // 생성일자임을 나타냅니다.
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    private LocalDateTime modifiedAt;
}
