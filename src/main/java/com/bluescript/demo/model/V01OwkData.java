package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class V01OwkData {
    private String v01OwkPartnerCode;
    private String v01OwkPartnerName;
    private String v01OwkPntCode;
    private String v01OwkPntShortName;
    private String v01OwkPntName;
    private String v01OwkRespLpFlag;
    private String v01OwkRteCode;
    private String v01OwkRteDepartDt;
    private String v01OwkRteArriveDt;

    public String toFixedWidthString() {
        return v01OwkPartnerCode + v01OwkPartnerName + v01OwkPntCode + v01OwkPntShortName + v01OwkPntName
                + v01OwkRespLpFlag + v01OwkRteCode + v01OwkRteDepartDt + v01OwkRteArriveDt;
    }

}