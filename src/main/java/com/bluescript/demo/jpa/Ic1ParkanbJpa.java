package com.bluescript.demo.jpa;

import java.util.stream.Stream;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bluescript.demo.dto.Ic1ParkanbJpaDto;
import com.bluescript.demo.entity.ParkanbEntity;

public interface Ic1ParkanbJpa extends Repository<ParkanbEntity, String> {
    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FETCH_SIZE, value = "100"), // modify
                                                                                                             // based
                                                                                                             // on
                                                                                                             // performance
            @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.annotations.QueryHints.READ_ONLY, value = "true") })
    //LOT_QUANTIY -- REVATHI
    @Query(value = "SELECT CUSTOMER_SUPP as hvPmCustomerSupp ,LOCATION as location ,EMPLOYEE as employee ,KANBAN as kanban ,ITEMID as itemid ,LOT_QUANTITY as lotQuantity ,EFF_START as effStart ,ORDER_METHOD as orderMethod FROM PARKANB WHERE TYPE = 'CD' AND CSI_TYPE = 'SU' AND ( EFF_START <= :wsStartDate AND ( EFF_STOP >= :wsCurrentDate OR EFF_STOP IS NULL ) ) ORDER BY CUSTOMER_SUPP , LOCATION , ITEMID , EFF_START", nativeQuery = true)
    Stream<Ic1ParkanbJpaDto> selectc1Parkanb(@Param("wsStartDate") String wsStartDate,
            @Param("wsCurrentDate") String wsCurrentDate);
}
