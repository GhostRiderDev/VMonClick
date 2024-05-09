package com.tds.VMonClick.VMonClick.model;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value = "Instance")
public class InstancEntity {

  @PrimaryKey
  private UUID id;

  @Column(value = "id_vm")
  private UUID idVM;

  @Column(value = "id_rsc")
  private Integer idRsc;

  @Column(value = "id_user")
  private UUID idUser;

  @Column(value = "id_created")
  private LocalDateTime dateCreated;

  @Column(value = "date_finished")
  private LocalDateTime date_finished;

  @Column(value = "is_finish")
  private boolean isFinish;

  @Column(value = "is_stop")
  private boolean isStop;
}
