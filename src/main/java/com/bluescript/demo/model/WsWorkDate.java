package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WsWorkDate {
    private String wsWorkDateCcyy;
    private String dash1;
    private String wsWorkDateMm;
    private String dash2;
    private String wsWorkDateDd;

    public String toFixedWidthString() {
        return wsWorkDateCcyy + dash1 + wsWorkDateMm + dash2 + wsWorkDateDd;
    }

}