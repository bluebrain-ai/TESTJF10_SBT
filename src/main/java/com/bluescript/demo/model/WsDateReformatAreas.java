package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;
import com.bluescript.demo.model.WsWorkDate;
import com.bluescript.demo.model.WsCcyymmdd;

@Data
@Component

public class WsDateReformatAreas {
    private WsWorkDate wsWorkDate;
    private WsCcyymmdd wsCcyymmdd;
    private String wsCurrentDate;// = VALUE;
    private String wsTodayDate;
    private String wsTodayTime;
    private String wsStartDate = "0000-00-00";

    public String toFixedWidthString() {
        return wsWorkDate.toFixedWidthString() + wsCcyymmdd.toFixedWidthString() + wsCurrentDate + wsTodayDate
                + wsTodayTime + wsStartDate;
    }

}