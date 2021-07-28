package com.bluescript.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import com.bluescript.demo.jpa.Ic1ParkanbJpa;
import com.bluescript.demo.jpa.Ic2ParkanbJpa;
import com.bluescript.demo.mapper.ConvStrToObj;

import java.time.LocalDate;
//import java.time.DateTimeFormatter;  -- commented as it comes under format package
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import com.bluescript.demo.dto.c1ParkanbJpaDto;
import com.bluescript.demo.dto.M2212csiplntJpaDto;
import com.bluescript.demo.dto.M2215pardescJpaDto;
import com.bluescript.demo.dto.IM2212csiplntJpaDto;
import com.bluescript.demo.jpa.IM2212csiplntJpa;
import com.bluescript.demo.dto.IM2215pardescJpaDto;
import com.bluescript.demo.dto.Ic1ParkanbJpaDto;
import com.bluescript.demo.jpa.IM2215pardescJpa;
import com.bluescript.demo.dto.c2ParkanbJpaDto;
import com.bluescript.demo.dto.Ic2ParkanbJpaDto;
//import org.springframework.cloud.stream.function.StreamBridge;
import com.bluescript.demo.model.FileStatusCodes;
import com.bluescript.demo.model.WsSwitches;
import com.bluescript.demo.model.WsDateReformatAreas;
import com.bluescript.demo.model.WsWorkDate;
import com.bluescript.demo.model.WsCcyymmdd;
import com.bluescript.demo.model.HostVariablesPm;
import com.bluescript.demo.model.V01Rec;
import com.bluescript.demo.model.V01OwkData;
import com.bluescript.demo.model.V02Datetime;

@Getter
@Setter
@RequiredArgsConstructor
@Log4j2
@Component

public class Testjf10 {

    @Autowired
    private FileStatusCodes fileStatusCodes;
    @Autowired
    private WsSwitches wsSwitches;
    @Autowired
    private WsDateReformatAreas wsDateReformatAreas;
    @Autowired
    private WsWorkDate wsWorkDate;
    @Autowired
    private WsCcyymmdd wsCcyymmdd;
    @Autowired
    private HostVariablesPm hostVariablesPm;
    @Autowired
    private V01Rec v01Rec;
    @Autowired
    private V01OwkData v01OwkData;
    @Autowired
    private V02Datetime v02Datetime;
    @Autowired
    private Ic1ParkanbJpa c1ParkanbJpa;
    @Autowired
    private Ic2ParkanbJpa c2ParkanbJpa;
    @Autowired
    private c1ParkanbJpaDto c1ParkanbJpaDto;
    @Autowired
    private M2212csiplntJpaDto m2212CsiplntJpaDto;
    @Autowired
    private IM2212csiplntJpa csiplntJpa;
    @Autowired
    private M2215pardescJpaDto m2215PardescJpaDto;
    @Autowired
    private IM2215pardescJpa pardescJpa;
    @Autowired
    private c2ParkanbJpaDto c2ParkanbJpaDto;
    // @Autowired
    // private StreamBuilder streamBuilder;
    private int wsOut01Counter;
    private long wsOut01DisplayCount;
    private String t1SuppCode;
    private static final Set<String> validSuppCode = Set.of("00001", "00002", "00003", "00004");
    private String t2OrderMethod;
    private static final Set<String> validOrderMethod = Set.of("A", "B", "C", "D", "E");
    private String hvSmSuppPlantName;
    private String hvSmPartDescription;
    private String hvPkLocation;
    private String hvPkStoreAddrPrim;
    private String hvPkPackingStyle;
    private String wsPartNumber;
    private String wsCustomerSupp;
    private String wsDock;
    private String wsKanban;
    private int wsQtyPerBox;
    private String v03ErrorMessage;
    private String v04OwkBusEnt;
    private String v04OwkRelType;
    private String v04OwkDateTime;
    @Autowired
    private WebClient.Builder webClientBuilder;
    // @Autowired
    // private WebClient.Builder webClientBuilder;

    @Autowired
    private ConvStrToObj copyStrToObj;

    @Transactional(readOnly = true)
    public void mainModule() {
        log.debug("Methodm0000MainModulestarted..");
        log.info("OWKB010 START");

        initialization();

        mainline();
        mainlineExit();

        closeFiles();

        log.debug("Method m0000MainModule completed..");
    }

    public void initialization() {
        log.debug("Methodm1000Initializationstarted....");
        wsDateReformatAreas.setWsTodayDate(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"))); // --
                                                                                                             // Formatter
                                                                                                             // change
                                                                                                             // to small
                                                                                                             // dd
        wsCcyymmdd = copyStrToObj.wsTodayDateTowsCcyymmdd_1(wsDateReformatAreas.getWsTodayDate(), wsCcyymmdd);
        v02Datetime.setV02ProcessDate(wsDateReformatAreas.getWsTodayDate());

        wsDateReformatAreas.setWsTodayTime(LocalTime.now().toString()); // HH-mm-ss

        v01Rec.setV01OwkOrdRelDt(v02Datetime.toFixedWidthString());
        v04OwkDateTime = v02Datetime.toFixedWidthString();
        v02Datetime.setV02ProcessTime(wsDateReformatAreas.getWsTodayTime());

        wsWorkDate.setWsWorkDateCcyy(wsCcyymmdd.getWsCcyyDate());
        wsWorkDate.setWsWorkDateMm(wsCcyymmdd.getWsMmDate());
        wsWorkDate.setWsWorkDateDd(wsCcyymmdd.getWsDdDate());
        wsWorkDate.setDash1("-");
        wsWorkDate.setDash2("-"); // Added dash2 to make sure date format correct - Revathi

        wsDateReformatAreas.setWsCurrentDate(wsWorkDate.toFixedWidthString());

        fileStatusCodes.setWsRoutine("BUMPWORK");
        fileStatusCodes.setWsInputDate(wsDateReformatAreas.getWsCurrentDate());
        fileStatusCodes.setWsParm2Num(20);
        fileStatusCodes.setWsParm3("+");
        fileStatusCodes.setWsParm4("");
        fileStatusCodes.setWsParm5("");

        List subprogram_parms = Arrays.asList(fileStatusCodes.getWsRoutine(), fileStatusCodes.getWsInputDate(),
                fileStatusCodes.getWsParm2Num(), fileStatusCodes.getWsParm3(), fileStatusCodes.getWsParm4(),
                fileStatusCodes.getWsParm5());

        // ubprogram_parms =
        // webclientBuiler.build().get.uri(fileStatusCodesIsmr121Api).syncBody(subprogram_parms).retrive().bodyToMono(List.class).block();

        fileStatusCodes.setWsParm5("1/1/2020"); // Assuming response from ISMR121API
        if (fileStatusCodes.getWsParm5() == "") {
            log.info("PROGRAM NAME : OWKB010");
            log.info("FORCED ABEND - CALENDAR ROUTINE ABEND");
            // webClientBuilder.build().get().uri(abendmeApi).syncBody().retrive().bodyToMono().block();

            // Method to DLQ write
        }

        wsDateReformatAreas.setWsStartDate(fileStatusCodes.getWsParm5());
        log.info("WS START DATE: " + wsDateReformatAreas.getWsStartDate());

        log.debug("Method m1000Initialization completed..");
    }

    @Transactional(readOnly = true)
    public void mainline() {
        log.debug("Methodm2000Mainlinestarted..");
        v01Rec.setV01OwkBusinessEntity("BK005");
        v04OwkBusEnt = "BK005";
        v01Rec.setV01OwkOrdRelTypeCode("DO");
        v04OwkRelType = "DO";
        v01Rec.setV01OwkOrdRelStatus("RP");
        v01Rec.setV01OwkNamcData("");

        // m2100OpenC1Parkanb();

        while (wsSwitches.getWsParkanbSwitch().equals("Y")) { // changed the equals method to intial noPerk -- revathi
            mainProcessLoop();
        }
        ;

        log.debug("Method m2000Mainline completed..");
    }

    public void mainlineExit() {
        log.debug("Methodm2000MainlineExitstarted..");

        log.debug("Method m2000MainlineExit completed..");
    }

    @Transactional(readOnly = true)
    public void mainProcessLoop() {
        log.debug("Methodm2200MainProcessLoopstarted..");
        try {
            Stream<Ic1ParkanbJpaDto> c1parkanbStream = c1ParkanbJpa
                    .selectc1Parkanb(wsDateReformatAreas.getWsStartDate(), wsDateReformatAreas.getWsCurrentDate());
            c1parkanbStream.forEach(item -> {

                // wsSwitches.suppNotFound;
                // wsSwitches.ordMtdNotFound;
            	

                lookForSupplier(item.getHvPmCustomerSupp());
                // hvPmCustomerSuppexit();
                lookForOrdMetd(item.getHvPmOrderMethod());
                if (wsSwitches.getSuppFound().equals(wsSwitches.getSuppNotFound())
                        && wsSwitches.getOrdMtdFound().equals(wsSwitches.getOrdMtdNotFound())) {
                    moveReformat(item.getHvPmItemid(), item.getHvPmKanban(), item.getHvPmEmployee(),
                            item.getHvPmLocation(), item.getHvPmCustomerSupp(), item.getHvPmLotQuantity());

                }

            });
        } catch (Exception e) {
            log.error(e);
        }

        wsSwitches.setWsParkanbSwitch("N");

        log.debug("Method m2200MainProcessLoop completed..");
    }

    public void lookForSupplier(String hvPmCustomerSupp) {
        log.debug("Methodm2205LookForSupplierstarted..");
       // hvPmCustomerSupp.substring(0, 5); // Commented as it is not used
        if (validSuppCode.contains(t1SuppCode)) {
            // wsSwitches.suppFound;
            // inlinElOopMethod();

        }

        log.debug("Method m2205LookForSupplier completed..");
    }

    public void lookForOrdMetd(String hvPmOrderMethod) {
        log.debug("Methodm2207LookForOrdMetdstarted..");
        t2OrderMethod = hostVariablesPm.getHvPmOrderMethod();

        if (validOrderMethod.contains(t2OrderMethod)) {
            // wsSwitches.ordMtdFound;
        }

        log.debug("Method m2207LookForOrdMetd completed..");
    }

    public void moveReformat(String hvPmItemid, String hvPmKanban, String hvPmEmployee, String hvPmLocation,
            String hvPmCustomerSupp, int hvPmLotQuantity) {
        log.debug("Methodm2210MoveReformatstarted..");
        // v01Rec.setV01Rec("");

        v01Rec.setV01OwkPartNum(hostVariablesPm.getHvPmItemid());
        v01Rec.setV01OwkKanbanNum(hostVariablesPm.getHvPmKanban());
        v01Rec.setV01OwkOrdSpecialist(hostVariablesPm.getHvPmEmployee().substring(0, 2));
        // v01OwkBeDock = StringUtils.replace(v01OwkBeDock, v01OwkBeDock.substring(-1,-1), hvPmLocation.substring(0,2));
        v01Rec.setV01OwkBeDock(StringUtils.replace(v01Rec.getV01OwkBeDock(), v01Rec.getV01OwkBeDock().substring(-1, -1),hvPmLocation.substring(0, 2)));
        // v01OwkSupPlantCode = StringUtils.replace(v01OwkSupPlantCode,
        // v01OwkSupPlantCode.substring(-1,-1), hvPmCustomerSupp.substring(2,7));
        v01Rec.setV01OwkSupPlantCode(StringUtils.replace(v01Rec.getV01OwkSupPlantCode(),
                v01Rec.getV01OwkSupPlantCode().substring(-1, -1), hvPmCustomerSupp.substring(2, 7)));
        v01Rec.setV01OwkOrdRelDt(v02Datetime.toFixedWidthString());

        String.valueOf(hostVariablesPm.getHvPmLotQuantity()).substring(0, 7);

        v01Rec.setV01OwkQtyPerBox(String.valueOf(wsQtyPerBox));

        csiplntRead(hvPmCustomerSupp);

        processPardesc(hvPmItemid);

        processParkanbCl(hvPmItemid, hvPmLocation, hvPmCustomerSupp, hvPmKanban);

        processParkanbCl(hostVariablesPm.getHvPmItemid(), hostVariablesPm.getHvPmLocation(),
                hostVariablesPm.getHvPmCustomerSupp(), hostVariablesPm.getHvPmKanban());
        writePartmstr();

        log.debug("Method m2210MoveReformat completed..");
    }

    @Transactional(readOnly = true)
    public void csiplntRead(String hvPmCustomerSupp) {
        log.debug("Methodm2212CsiplntReadstarted..");

        // Singleton
        try {
            List<IM2212csiplntJpaDto> selectCSIPLNTlist = csiplntJpa.selectcsiplnt(hvPmCustomerSupp);
            if (selectCSIPLNTlist != null) {

                v01Rec.setV01OwkSupPlantName(StringUtils.replace(v01Rec.getV01OwkSupPlantName(),
                        v01Rec.getV01OwkSupPlantName().substring(-1, -1), hvSmSuppPlantName.substring(0, 30)));
                if (hostVariablesPm.getHvSuppPlantCnt() > 1) {
                    v03ErrorMessage = "MULTIPLE SUPPLIER PLANT NAME";
                }

            }
            ;
        } catch (Exception e) {
            log.error(e);
        }

        v01Rec.setV01OwkSupPlantName("NO DATA");
        v03ErrorMessage = "NO SUPPLIER PLANT NAME";

        log.debug("Method m2212CsiplntRead completed..");
    }

    @Transactional(readOnly = true)
    public void processPardesc(String hvPmItemid) {
        log.debug("Methodm2215ProcessPardescstarted..");

        // Singleton
        try {
            List<IM2215pardescJpaDto> selectPARDESClist = pardescJpa.selectpardesc(hvPmItemid);
            if (selectPARDESClist != null) {

                v01Rec.setV01OwkPartDesc(StringUtils.replace(v01Rec.getV01OwkPartDesc(),
                        v01Rec.getV01OwkPartDesc().substring(-1, -1), hvSmPartDescription.substring(0, 30)));
                if (hostVariablesPm.getHvPartDescriptionCnt() > 1) {
                    v03ErrorMessage = "MULTIPLE PART DESCRIPTION";
                }

            }
            ;
        } catch (Exception e) {
            log.error(e);
        }

        v01Rec.setV01OwkPartDesc("NO DATA");
        v03ErrorMessage = "NO PART DESCRIPTION";

        log.debug("Method m2215ProcessPardesc completed..");
    }

    @Transactional(readOnly = true)
    public void processParkanbCl(String hvPmItemid, String hvPmLocation, String hvPmCustomerSupp,
            String hvPmKanban) {
        log.debug("Methodm2218ProcessParkanbClstarted..");
        wsPartNumber = hostVariablesPm.getHvPmItemid();
        wsDock = StringUtils.replace(wsDock, wsDock.substring(-1, -1), hvPmLocation.substring(0, 2));
        wsCustomerSupp = hostVariablesPm.getHvPmCustomerSupp();
        wsKanban = hostVariablesPm.getHvPmKanban();
        hvPkLocation = "";
        hvPkStoreAddrPrim = "";

        try {
            Stream<Ic2ParkanbJpaDto> c2parkanbStream = c2ParkanbJpa.selectc2Parkanb(wsPartNumber, wsCustomerSupp,
                    wsDock, wsDateReformatAreas.getWsStartDate(), wsDateReformatAreas.getWsCurrentDate());

            c2parkanbStream.forEach(item -> {

                // v01OwkLinesideAddress = StringUtils.replace(v01OwkLinesideAddress,
                // v01OwkLinesideAddress.substring(-1,-1),
                // hvPkLocation.substring(2,12));V01Rec.setV01OwkStoreAddress(hvPkStoreAddrPrim);
            });
        } catch (Exception e) {
            log.error(e);
        }

        v01Rec.setV01OwkLinesideAddress("NO DATA");
        v01Rec.setV01OwkStoreAddress("NO DATA");
        v01Rec.setV01OwkNamcData("");

        log.debug("Method m2218ProcessParkanbCl completed..");
    }

    public void closeFiles() {
        log.debug("Methodm3000CloseFilesstarted..");
        wsOut01DisplayCount = wsOut01Counter;

        log.info("OWKB010 EXTRACT RECORDS WRITTEN = " + wsOut01DisplayCount);

        log.debug("Method m3000CloseFiles completed..");
    }

    public void writePartmstr() {
        log.debug("Methodm8000WritePartmstrstarted..");
        // streamBridge.send(0, recOut01);

        log.debug("Method m8000WritePartmstr completed..");
    }

    /* End of program */
}