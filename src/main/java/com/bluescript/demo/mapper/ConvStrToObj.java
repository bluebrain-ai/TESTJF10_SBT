package com.bluescript.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.InheritInverseConfiguration;
import com.bluescript.demo.model.WsCcyymmdd;

@Mapper
public interface ConvStrToObj {
    @Mapping(target = "wsCcyyDate", expression = "java(wsDateReformatAreas.getWsTodayDate().substring(0,4))")
    @Mapping(target = "wsMmDate", expression = "java(wsDateReformatAreas.getWsTodayDate().substring(4,6))")
    @Mapping(target = "wsDdDate", expression = "java(wsDateReformatAreas.getWsTodayDate().substring(6,8))")
    WsCcyymmdd wsTodayDateTowsCcyymmdd_1(String wsTodayDate, @MappingTarget WsCcyymmdd wsCcyymmdd);

}