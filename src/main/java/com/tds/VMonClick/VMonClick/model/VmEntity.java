package com.tds.VMonClick.VMonClick.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Table(value = "VM")
@NoArgsConstructor
@Data
public class VmEntity {
  @PrimaryKey
  private Integer id;

  @Transient
  private static Integer idStatic;

  @Column(value = "path_iso")
  private String pathISO;

  @Column(value = "date_created")
  private Date dateCreated;

  @Column
  @CassandraType(type = CassandraType.Name.LIST, typeArguments = { CassandraType.Name.UDT })
  private List<InstancEntity> instances = new ArrayList<>();
}
