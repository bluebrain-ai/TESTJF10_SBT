package com.bluescript.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Component

public class c2ParkanbJpaDto {
    private double hvPkShare;
    private String hvPkLocation;
    private int niPkLocation;
    private String hvPkStoreAddrPrim;
    private int niPkStoreAddrPrim;
    private String hvPkPackingStyle;
    private int niPkPackingStyle;
}
