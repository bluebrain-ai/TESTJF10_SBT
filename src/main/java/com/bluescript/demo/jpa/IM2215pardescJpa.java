package com.bluescript.demo.jpa;

import java.util.stream.Stream;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.bluescript.demo.dto.IM2215pardescJpaDto;
import com.bluescript.demo.entity.PardescEntity;

public interface IM2215pardescJpa extends Repository<PardescEntity, String> {
    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FETCH_SIZE, value = "100"), // modify
                                                                                                             // based
                                                                                                             // on
                                                                                                             // performance
            @QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.annotations.QueryHints.READ_ONLY, value = "true") })
    @Query(value = "SELECT  A.DESCRIPTION as hvSmPartDescription,B.CNT as hvPartDescriptionCnt FROM PARDESC A , ( SELECT COUNT ( DISTINCT ( DESCRIPTION ) AS CNT FROM PARDESC WHERE ITEMID = :hvPmItemid ) AS B WHERE A . ITEMID = :hvPmItemid", nativeQuery = true)
    List<IM2215pardescJpaDto> selectpardesc(@Param("hvPmItemid") String hvPmItemid);
}
