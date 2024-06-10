package com.tds.VMonClick.VMonClick.repository;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;

@Repository
public interface InstanceRepository extends CassandraRepository<InstanceEntity, String> {

  @Query("SELECT id, is_stop FROM instance WHERE is_stop=false AND is_finish=false ALLOW FILTERING")
  public List<InstanceEntity> findIntancesActive();

  @Query("SELECT * FROM instance WHERE id_user=?0 ALLOW FILTERING")
  public List<InstanceEntity> findByIdUser(String idUser);
}
