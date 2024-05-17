package com.tds.VMonClick.VMonClick.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.tds.VMonClick.VMonClick.model.MetricEntity;

@Repository
public interface MetricRepository extends CassandraRepository<MetricEntity, UUID> {

  @Query(value = "SELECT * FROM metric WHERE id_instance = ?0 ALLOW FILTERING")
  public List<MetricEntity> findByInstance(String idInstance);
}
