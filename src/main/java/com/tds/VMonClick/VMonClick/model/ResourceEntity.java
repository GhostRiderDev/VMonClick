package com.tds.VMonClick.VMonClick.model;

import java.time.LocalDateTime;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "resource")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity {
  @PrimaryKey
  private Integer id;

  @Transient
  public static Integer incrementalId = 0;

  private Integer cpu;

  private Integer ram;

  private Double disk;

  private Double price_hourly;

  private LocalDateTime created_at;
}
