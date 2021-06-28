package com.sparta.week_03.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemoRequestDto {
    private final String username;
    private final String contents;
}