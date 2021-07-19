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

public interface Ic1ParkanbJpa extends Repository<ParkanbEntity, ID> {
    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FETCH_SIZE, value = "100"), // modify
                                                                                                             // based on
                                                                                                             // performance
            @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.annotations.QueryHints.READ_ONLY, value = "true") })
    @Query(value = "SELECT CUSTOMER_SUPP as customerSupp ,LOCATION as location ,EMPLOYEE as employee ,KANBAN as kanban ,ITEMID as itemid ,LOT-QUANTITY as lotQuantity ,EFF_START as effStart ,ORDER_METHOD as orderMethod FROM PARKANB WHERE TYPE = 'CD 'AND CSI_TYPE = 'SU ' ( EFF_START < = :wsStartDate AND ( EFF_STOP > = :wsCurrentDate OR EFF_STOP IS NULL ) ) ORDER BY CUSTOMER-SUPP , LOCATION , ITEMID , EFF-START", nativeQuery = true)
    Stream<Ic1ParkanbJpaDto> selectc1Parkanb(@Param("wsStartDate") String wsStartDate,
            @Param("wsCurrentDate") String wsCurrentDate);
}
