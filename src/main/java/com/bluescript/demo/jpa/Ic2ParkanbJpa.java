package com.bluescript.demo.jpa;

import java.util.stream.Stream;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bluescript.demo.dto.Ic2ParkanbJpaDto;
import com.bluescript.demo.entity.ParkanbEntity;

public interface Ic2ParkanbJpa extends Repository<ParkanbEntity, ID> {
    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FETCH_SIZE, value = "100"), // modify
                                                                                                             // based on
                                                                                                             // performance
            @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.annotations.QueryHints.READ_ONLY, value = "true") })
    @Query(value = "SELECT SHARE as share ,LOCATION as location ,STORE_ADDRESS_PRIM as storeAddressPrim ,PACKING_STYLE as packingStyle FROM PARKANB WHERE TYPE = 'CL 'AND CSI_TYPE = 'SU 'AND ITEMID = :wsPartNumber AND CUSTOMER_SUPP = :wsCustomerSupp AND SUBSTR ( LOCATION , 1 , 2 ) = :wsDock AND ( EFF_START < = :wsStartDate AND ( EFF_STOP > = :wsCurrentDate OR EFF_STOP IS NULL ) ) ORDER BY SHARE DESC , LOCATION DESC", nativeQuery = true)
    Stream<Ic2ParkanbJpaDto> selectc2Parkanb(@Param("wsPartNumber") String wsPartNumber,
            @Param("wsCustomerSupp") String wsCustomerSupp, @Param("wsDock") String wsDock,
            @Param("wsStartDate") String wsStartDate, @Param("wsCurrentDate") String wsCurrentDate);
}
