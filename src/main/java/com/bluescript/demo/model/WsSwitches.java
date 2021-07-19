package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WsSwitches {
    private String wsParkanbSwitch = "Y";
    private String wsNoMoreParkanb = "N";
    private String suppFound = "Y";
    private String suppNotFound = "N";
    private String ordMtdFound = "Y";
    private String ordMtdNotFound = "N";

    public String toFixedWidthString() {
        return wsParkanbSwitch + wsNoMoreParkanb + suppFound + suppNotFound + ordMtdFound + ordMtdNotFound;
    }

}