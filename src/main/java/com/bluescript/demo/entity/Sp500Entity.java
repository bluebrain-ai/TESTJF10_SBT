package com.bluescript.demo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Table(name = "SP500")
@Getter
@Setter
@Data
@RequiredArgsConstructor
// Schema : TSTCNTL
public class Sp500Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "SP500_PORT_CODE")
    private String sp500PortCode;
    @NotNull
    @Column(name = "SP500_DATA_CODE")
    private String sp500DataCode;
    @Column(name = "SP500_VIN")
    private String sp500Vin;
    @Column(name = "SP500_MODEL")
    private String sp500Model;
    @Column(name = "SP500_PROCESS_IND")
    private String sp500ProcessInd;
    @NotNull
    @Column(name = "SP500_ADD_TS")
    private String sp500AddTs;

}
