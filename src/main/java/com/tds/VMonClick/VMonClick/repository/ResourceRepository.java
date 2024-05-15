package com.tds.VMonClick.VMonClick.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;

import java.util.List;

@Repository
public interface ResourceRepository extends CassandraRepository<ResourceEntity, Integer> {

  @Query("SELECT * FROM Resource WHERE cpu = ?0 AND ram = ?1 AND disk = ?2 ALLOW FILTERING")
  List<ResourceEntity> isDuplicateResource(int cpu, int ram, Double disk);
}
