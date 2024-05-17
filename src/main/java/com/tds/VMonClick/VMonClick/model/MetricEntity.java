package com.tds.VMonClick.VMonClick.model;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "metric")
// @UserDefinedType("metric")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricEntity {
  @PrimaryKey
  private UUID id;

  @CassandraType(type = CassandraType.Name.INT)
  private Integer cpu;

  @CassandraType(type = CassandraType.Name.INT)
  private Integer ram;

  @CassandraType(type = CassandraType.Name.INT)
  private Integer disk;

  @CassandraType(type = CassandraType.Name.INT)
  @Column("band_width")
  private Integer bandWidth;

  @Column("date_registered")
  private LocalDateTime dateRegistered;

  @Column("id_instance")
  private String idInstance;
}
