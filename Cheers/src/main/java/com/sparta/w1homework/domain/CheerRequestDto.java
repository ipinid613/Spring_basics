package com.sparta.w1homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CheerRequestDto {
    private final String username;
    private final String title;
    private final String contents;
}