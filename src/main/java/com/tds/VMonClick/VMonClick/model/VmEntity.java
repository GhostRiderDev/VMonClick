package com.tds.VMonClick.VMonClick.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "VM")
@NoArgsConstructor
@Data
public class VmEntity {
  @PrimaryKey
  private Integer id;

  @Transient
  private static Integer idStatic = 0;

  @Column(value = "iso")
  private String iso;

  @Column(value = "date_created")
  private Date dateCreated;

  // @Column
  // @CassandraType(type = CassandraType.Name.LIST, typeArguments = { CassandraType.Name.UDT }, userTypeName = "instance")
  // private List<InstanceEntity> instances = new ArrayList<>();
}
