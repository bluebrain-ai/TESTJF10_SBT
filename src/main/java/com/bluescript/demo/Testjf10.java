package com.bluescript.demo;

import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import com.bluescript.demo.jpa.Ic1ParkanbJpa;
import com.bluescript.demo.jpa.Ic2ParkanbJpa;
import java.time.LocalDate;
import java.time.DateTimeFormatter;
import java.time.LocalTime;
import com.bluescript.demo.dto.c1ParkanbJpaDto;
import com.bluescript.demo.dto.Ic1ParkanbJpaDto;
import com.bluescript.demo.dto.M2212csiplntJpaDto;
import com.bluescript.demo.dto.IM2212csiplntJpaDto;
import com.bluescript.demo.jpa.IM2212csiplntJpa;
import com.bluescript.demo.dto.M2215pardescJpaDto;
import com.bluescript.demo.dto.IM2215pardescJpaDto;
import com.bluescript.demo.jpa.IM2215pardescJpa;
import com.bluescript.demo.dto.c2ParkanbJpaDto;
import com.bluescript.demo.dto.Ic2ParkanbJpaDto;
import org.springframework.cloud.stream.function.StreamBridge;
import com.bluescript.demo.model.FileStatusCodes;
import com.bluescript.demo.model.WsSwitches;
import com.bluescript.demo.model.WsDateReformatAreas;
import com.bluescript.demo.model.WsWorkDate;
import com.bluescript.demo.model.WsCcyymmdd;
import com.bluescript.demo.model.HostVariablesPm;
import com.bluescript.demo.model.V01Rec;
import com.bluescript.demo.model.V01OwkData;
import com.bluescript.demo.model.V02Datetime;Import org.springframework.beans.factory.annotation.webClientBuilder;

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
    @Autowired
    private StreamBuilder streamBuilder;
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
    @Autowired
    private WebClient.Builder webClientBuilder;

    public void m0000MainModule() {
        log.debug("Methodm0000MainModulestarted..");
        log.info("OWKB010 START");

        m1000Initialization();

        m2000Mainline();
        m2000MainlineExit();

        m3000CloseFiles();

        log.debug("Method m0000MainModule completed..");
    }

 public void m1000Initialization(){
 log.debug("Methodm1000Initializationstarted..");
wsDateReformatAreas.wsTodayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD"));
       wsCcyymmdd = copyStrToObj.wsTodayDateTowsCcyymmdd_1(wsTodayDate,wsCcyymmdd);
V02Datetime.setV02ProcessDate(wsDateReformatAreas.getWsTodayDate());wsDateReformatAreas.wsTodayTime = LocalTime.now().toString(); // HH-mm-ss 

       V01Rec.v01OwkOrdRelDt=v02Datetime.toFixedWidthString();
v04OwkDateTime=v02Datetime.toFixedWidthString();
       V02Datetime.setV02ProcessTime(wsDateReformatAreas.getWsTodayTime());
      
       wsWorkDate.setWsWorkDateCcyy(wsCcyymmdd.getWsCcyyDate());
       wsWorkDate.setWsWorkDateMm(wsCcyymmdd.getWsMmDate());
       wsWorkDate.setWsWorkDateDd(wsCcyymmdd.getWsDdDate());
       wsWorkDate.setDash1("-");

       wsDateReformatAreas.wsCurrentDate=wsWorkDate.toFixedWidthString();
      
       fileStatusCodes.setWsRoutine("BUMPWORK");
       fileStatusCodes.setWsInputDate(wsDateReformatAreas.getWsCurrentDate());fileStatusCodes.setWsParm2Num(20);fileStatusCodes.setWsParm3("+");fileStatusCodes.setWsParm4("");fileStatusCodes.setWsParm5(""); List subprogram_parms = new Arraylist(fileStatusCodes.getwsRoutine(),fileStatusCodes.getwsInputDate(),fileStatusCodes.getwsParm2Num(),fileStatusCodes.getwsParm3(),fileStatusCodes.getwsParm4(),fileStatusCodes.getwsParm5()); subprogram_parms = webclientBuiler.build().get.uri(fileStatusCodesIsmr121Api).syncBody(subprogram_parms).retrive().bodyToMono(List.class).block();
       if(fileStatusCodes.wsParm5 == "" )
 { 
 log.info("PROGRAM NAME : OWKB010");
log.info("FORCED ABEND - CALENDAR ROUTINE ABEND");
webclientBuiler.build().get.uri(abendmeApi).syncBody().retrive().bodyToMono().block();
 } 
wsDateReformatAreas.setWsStartDate(fileStatusCodes.getWsParm5());
       log.info("WS START DATE: " + wsDateReformatAreas.wsStartDate);

      
       
              
 
  
log.debug("Method m1000Initialization completed..");
 }

    public void m2000Mainline() {
        log.debug("Methodm2000Mainlinestarted..");
        V01Rec.setV01OwkBusinessEntity("BK005");
        v04OwkBusEnt = "BK005";
        V01Rec.setV01OwkOrdRelTypeCode("DO");
        v04OwkRelType = "DO";
        V01Rec.setV01OwkOrdRelStatus("RP");
        V01Rec.setV01OwkNamcData("");

        m2100OpenC1Parkanb();

        while (wsSwitches.getWsParkanbSwitch().equals(wsSwitches.getWsNoMoreParkanb())) {
            m2200MainProcessLoop();
        }
        ;

        log.debug("Method m2000Mainline completed..");
    }

    public void m2000MainlineExit() {
        log.debug("Methodm2000MainlineExitstarted..");

        log.debug("Method m2000MainlineExit completed..");
    }

  @Transactional(readOnly = true)  
 public void m2200MainProcessLoop(){
 log.debug("Methodm2200MainProcessLoopstarted..");
 try { 
 Stream<Ic1ParkanbJpaDto>  c1parkanbStream = c1parkanbjpa.selectc1Parkanb(wsStartDate,wsCurrentDate);c1parkanbStream.forEach(item -> {
  


wsSwitches.suppNotFound;wsSwitches.ordMtdNotFound;m2205LookForSupplier(item.getHvPmCustomerSupp());
hvPmCustomerSuppexit();
m2207LookForOrdMetd(item.getHvPmOrderMethod());
if(wsSwitches.getWsSuppFound().equals(wsSwitches.getSuppNotFound())&&wsSwitches.getWsOrdMtdFound().equals(wsSwitches.getOrdMtdNotFound()) )
 { 
 m2210MoveReformat(item.getHvPmItemid(),item.getHvPmKanban(),item.getHvPmEmployee(),item.getHvPmLocation(),item.getHvPmCustomerSupp(),item.getHvPmLotQuantity());

 } 
	 
 }; 
}catch(Exception e){
 log.error(e); 
 } 
 
  wsSwitches.setWsParkanbSwitch("N");
  
log.debug("Method m2200MainProcessLoop completed..");
 }

 public void m2205LookForSupplier(String hvPmCustomerSupp){
 log.debug("Methodm2205LookForSupplierstarted..");
=hostVariablesPm.getHvPmCustomerSupp().substring(0,5);if(validSuppCode.contains(t1SuppCode) )
 { 
 wsSwitches.suppFound;inlinElOopMethod();

 } 

  

 
 
   
log.debug("Method m2205LookForSupplier completed..");
 }

 public void m2207LookForOrdMetd(String hvPmOrderMethod){
 log.debug("Methodm2207LookForOrdMetdstarted..");
t2OrderMethod= hostVariablesPm.getHvPmOrderMethod();
     
      if(validOrderMethod.contains(t2OrderMethod) )
 { 
 wsSwitches.ordMtdFound;
 } 

  
 
  
log.debug("Method m2207LookForOrdMetd completed..");
 }

 public void m2210MoveReformat(String hvPmItemid,String hvPmKanban,String hvPmEmployee,String hvPmLocation,String hvPmCustomerSupp,int  hvPmLotQuantity){
 log.debug("Methodm2210MoveReformatstarted..");
V01Rec.setV01Rec("");
     

      V01Rec.setV01OwkPartNum(hostVariablesPm.getHvPmItemid());V01Rec.setV01OwkKanbanNum(hostVariablesPm.getHvPmKanban());V01Rec.setV01OwkOrdSpecialist(hostVariablesPm.getHvPmEmployee().substring(0,2));v01OwkBeDock = StringUtils.replace(v01OwkBeDock, v01OwkBeDock.substring(-1,-1), hvPmLocation.substring(0,2));v01OwkSupPlantCode = StringUtils.replace(v01OwkSupPlantCode, v01OwkSupPlantCode.substring(-1,-1), hvPmCustomerSupp.substring(2,7));V01Rec.v01OwkOrdRelDt=v02Datetime.toFixedWidthString();=hostVariablesPm.getHvPmLotQuantity().substring(0,7);V01Rec.setV01OwkQtyPerBox(wsQtyPerBox);m2212CsiplntRead(item.getHvPmCustomerSupp());

         m2215ProcessPardesc(item.getHvPmItemid());

         m2218ProcessParkanbCl(item.getHvPmItemid(),item.getHvPmLocation(),item.getHvPmCustomerSupp(),item.getHvPmKanban());
m8000WritePartmstr();

           
 
  
log.debug("Method m2210MoveReformat completed..");
 }

    @Transactional(readOnly = true)
    public void m2212CsiplntRead(String hvPmCustomerSupp) {
        log.debug("Methodm2212CsiplntReadstarted..");

        // Singleton
        try {
            List<IM2212csiplntJpaDto> selectCSIPLNTlist = csiplntJpa.selectcsiplnt(hvPmCustomerSupp);
            if (selectCSIPLNTlist != null) {

                v01OwkSupPlantName = StringUtils.replace(v01OwkSupPlantName, v01OwkSupPlantName.substring(-1, -1),
                        hvSmSuppPlantName.substring(0, 30));
                if (hostVariablesPm.hvSuppPlantCnt > 1) {
                    v03ErrorMessage = "MULTIPLE SUPPLIER PLANT NAME";
                }

            }
            ;
        } catch (Exception e) {
            log.error(e);
        }

        V01Rec.setV01OwkSupPlantName("NO DATA");
        v03ErrorMessage = "NO SUPPLIER PLANT NAME";

        log.debug("Method m2212CsiplntRead completed..");
    }

    @Transactional(readOnly = true)
    public void m2215ProcessPardesc(String hvPmItemid) {
        log.debug("Methodm2215ProcessPardescstarted..");

        // Singleton
        try {
            List<IM2215pardescJpaDto> selectPARDESClist = pardescJpa.selectpardesc(hvPmItemid);
            if (selectPARDESClist != null) {

                v01OwkPartDesc = StringUtils.replace(v01OwkPartDesc, v01OwkPartDesc.substring(-1, -1),
                        hvSmPartDescription.substring(0, 30));
                if (hostVariablesPm.hvPartDescriptionCnt > 1) {
                    v03ErrorMessage = "MULTIPLE PART DESCRIPTION";
                }

            }
            ;
        } catch (Exception e) {
            log.error(e);
        }

        V01Rec.setV01OwkPartDesc("NO DATA");
        v03ErrorMessage = "NO PART DESCRIPTION";

        log.debug("Method m2215ProcessPardesc completed..");
    }

  @Transactional(readOnly = true)  
 public void m2218ProcessParkanbCl(String hvPmItemid,String hvPmLocation,String hvPmCustomerSupp,String hvPmKanban){
 log.debug("Methodm2218ProcessParkanbClstarted..");
wsPartNumber= hostVariablesPm.getHvPmItemid();wsDock = StringUtils.replace(wsDock, wsDock.substring(-1,-1), hvPmLocation.substring(0,2));wsCustomerSupp= hostVariablesPm.getHvPmCustomerSupp();wsKanban= hostVariablesPm.getHvPmKanban();hvPkLocation="";
hvPkStoreAddrPrim="";
      

     
       try { 
 Stream<Ic2ParkanbJpaDto>  c2parkanbStream = c2parkanbjpa.selectc2Parkanb(wsPartNumber,wsCustomerSupp,wsDock,wsStartDate,wsCurrentDate);c2parkanbStream.forEach(item -> {
  


v01OwkLinesideAddress = StringUtils.replace(v01OwkLinesideAddress, v01OwkLinesideAddress.substring(-1,-1), hvPkLocation.substring(2,12));V01Rec.setV01OwkStoreAddress(hvPkStoreAddrPrim);	 
 }; 
}catch(Exception e){
 log.error(e); 
 } 
 
  V01Rec.setV01OwkLinesideAddress("NO DATA");V01Rec.setV01OwkStoreAddress("NO DATA");V01Rec.setV01OwkNamcData("");
      
       

  
log.debug("Method m2218ProcessParkanbCl completed..");
 }

    public void m3000CloseFiles() {
        log.debug("Methodm3000CloseFilesstarted..");
        wsOut01DisplayCount = wsOut01Counter;

        log.info("OWKB010 EXTRACT RECORDS WRITTEN = " + wsOut01DisplayCount);

        log.debug("Method m3000CloseFiles completed..");
    }

    public void m8000WritePartmstr() {
        log.debug("Methodm8000WritePartmstrstarted..");
        streamBridge.send(0, recOut01);

        log.debug("Method m8000WritePartmstr completed..");
    }

    /* End of program */
}