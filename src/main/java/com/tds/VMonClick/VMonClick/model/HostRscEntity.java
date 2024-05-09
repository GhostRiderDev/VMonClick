package com.tds.VMonClick.VMonClick.model;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "Host_rsc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostRscEntity {
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
}
