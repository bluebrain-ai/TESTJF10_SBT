package com.bluescript.demo.jpa;

import java.util.stream.Stream;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bluescript.demo.dto.IM2212csiplntJpaDto;
import com.bluescript.demo.entity.CsiplntEntity;

public interface IM2212csiplntJpa extends Repository<CsiplntEntity, ID> {
    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FETCH_SIZE, value = "100"), // modify
                                                                                                             // based on
                                                                                                             // performance
            @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.annotations.QueryHints.READ_ONLY, value = "true") })
    @Query(value = "SELECT  A.NAME as hvSmSuppPlantName,B.CNT as hvSuppPlantCnt FROM CSIPLNT A , ( SELECT COUNT ( DISTICT ( NAME ) ) AS CNT FROM CSIPLNT WHERE PLANT_TYPE = 'PC 'AND CUSTOMER_SUPP = :hvPmCustomerSupp ) AS B WHERE PLANT_TYPE = 'PC 'AND CUSTOMER-SUPP = :hvPmCustomerSupp", nativeQuery = true)
    List<IM2212csiplntJpaDto> selectcsiplnt(@Param("hvPmCustomerSupp") String hvPmCustomerSupp);
}
