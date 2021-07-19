package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;

@Data
@Component

public class WsCcyymmdd {
    private String wsCcyyDate;
    private String wsMmDate;
    private String wsDdDate;

    public String toFixedWidthString() {
        return wsCcyyDate + wsMmDate + wsDdDate;
    }

}