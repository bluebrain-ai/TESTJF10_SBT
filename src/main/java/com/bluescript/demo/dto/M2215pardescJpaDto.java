package com.bluescript.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Component

public class M2215pardescJpaDto {
    private String hvSmPartDescription;
    private int hvPartDescriptionCnt;
}
