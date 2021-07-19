package com.bluescript.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.util.*;

import org.springframework.stereotype.Component;
import com.bluescript.demo.model.V01OwkData;

@Data
@Component

public class V01Rec {
    private String v01OwkBusinessEntity;
    private String v01OwkOrdRelTypeCode;
    private String v01OwkOrdRelStatus;
    private String v01OwkOrdRelDt;
    private String v01OwkBeDock;
    private String v01OwkDockDesc;
    private String v01OwkOrdNum;
    private String v01OwkOrdSeriesNum;
    private String v01OwkOrdDelNum;
    private String v01OwkSupPlantCode;
    private String v01OwkSupPlantName;
    private String v01OwkOrdSpecialist;
    private String v01OwkOrdHdlTypeCode;
    private String v01OwkBeUnloadDt;
    private String v01OwkRpChecksheetPrint;
    private String v01OwkRpPicklistPrint;
    private String v01OwkRpSkidmfstPrint;
    private String v01OwkPuRouteStartDt;
    private String v01OwkSupArvDt;
    private String v01OwkSupShipDock;
    private V01OwkData v01OwkData;
    private String v01OwkKanbanNum;
    private String v01OwkPartNum;
    private String v01OwkPartDesc;
    private String v01OwkQtyPerBox;
    private String v01OwkLastBoxQty;
    private String v01OwkTtlBoxOrd;
    private String v01OwkBoRemainToOrd;
    private String v01OwkStoreAddress;
    private String v01OwkLinesideAddress;
    private String v01OwkNamcData;
    private String v01OwkFiller;

    public String toFixedWidthString() {
        return v01OwkBusinessEntity + v01OwkOrdRelTypeCode + v01OwkOrdRelStatus + v01OwkOrdRelDt + v01OwkBeDock
                + v01OwkDockDesc + v01OwkOrdNum + v01OwkOrdSeriesNum + v01OwkOrdDelNum + v01OwkSupPlantCode
                + v01OwkSupPlantName + v01OwkOrdSpecialist + v01OwkOrdHdlTypeCode + v01OwkBeUnloadDt
                + v01OwkRpChecksheetPrint + v01OwkRpPicklistPrint + v01OwkRpSkidmfstPrint + v01OwkPuRouteStartDt
                + v01OwkSupArvDt + v01OwkSupShipDock + v01OwkData.toFixedWidthString() + v01OwkKanbanNum + v01OwkPartNum
                + v01OwkPartDesc + v01OwkQtyPerBox + v01OwkLastBoxQty + v01OwkTtlBoxOrd + v01OwkBoRemainToOrd
                + v01OwkStoreAddress + v01OwkLinesideAddress + v01OwkNamcData + v01OwkFiller;
    }

}