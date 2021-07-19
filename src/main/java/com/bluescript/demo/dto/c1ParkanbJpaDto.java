package com.bluescript.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Component

public class c1ParkanbJpaDto {
    private String hvPmCustomerSupp;
    private String hvPmLocation;
    private String hvPmEmployee;
    private String hvPmKanban;
    private int niKanban;
    private String hvPmItemid;
    private int hvPmLotQuantity;
    private int niLotQuantity;
    private String hvPmEffStart;
    private String hvPmOrderMethod;
}
