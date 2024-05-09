package com.tds.VMonClick.VMonClick.repository;

import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;

@Repository
public interface HostRscRepository extends CassandraRepository<HostRscEntity, UUID> {
  
}
