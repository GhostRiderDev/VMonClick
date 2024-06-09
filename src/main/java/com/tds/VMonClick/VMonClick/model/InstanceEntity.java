package com.tds.VMonClick.VMonClick.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("instance")
// @UserDefinedType("instance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstanceEntity {

  @PrimaryKey
  @CassandraType(type = CassandraType.Name.TEXT)
  private String id;

  @CassandraType(type = CassandraType.Name.INT)
  @Column(value = "id_vm")
  private Integer idVM;

  @Column(value = "id_rsc")
  @CassandraType(type = CassandraType.Name.INT)
  private Integer idRsc;

  @Column(value = "id_user")
  @CassandraType(type = CassandraType.Name.TEXT)
  private String idUser;

  @Column(value = "id_created")
  private LocalDateTime dateCreated;

  @Column(value = "date_finished")
  private LocalDateTime date_finished;

  @CassandraType(type = CassandraType.Name.BOOLEAN)
  @Column(value = "is_finish")
  private boolean isFinish;

  @CassandraType(type = CassandraType.Name.BOOLEAN)
  @Column(value = "is_stop")
  private boolean isStop;

  @Transient
  List<MetricEntity> metrics;
}
