package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class FileStatusCodes {
    private String ismr121 = "ISMR121";
    private String wsRoutine;
    private String wsInputDate;
    private int wsParm2Num;
    private String wsParm3;
    private String wsParm4;
    private String wsParm5;

    public String toFixedWidthString() {
        return ismr121 + wsRoutine + wsInputDate + wsParm2Num + wsParm3 + wsParm4 + wsParm5;
    }

}