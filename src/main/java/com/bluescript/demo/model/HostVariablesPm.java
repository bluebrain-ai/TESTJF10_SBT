package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class HostVariablesPm {
    private String hvPmCustomerSupp;
    private String hvPmLocation;
    private String hvPmItemid;
    private int hvPmLotQuantity;
    private String hvPmKanban;
    private String hvPmEmployee;
    private String hvPmOrderMethod;
    private int hvCountLocation;
    private int hvCountStoradd;
    private int hvPartDescriptionCnt;
    private int hvSuppPlantCnt;

    public String toFixedWidthString() {
        return hvPmCustomerSupp + hvPmLocation + hvPmItemid + hvPmLotQuantity + hvPmKanban + hvPmEmployee
                + hvPmOrderMethod + hvCountLocation + hvCountStoradd + hvPartDescriptionCnt + hvSuppPlantCnt;
    }

}