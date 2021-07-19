package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class V02Datetime {
    private String v02ProcessDate;
    private String v02ProcessTime;
    private String v02Filler1;

    public String toFixedWidthString() {
        return v02ProcessDate + v02ProcessTime + v02Filler1;
    }

}