package com.tds.VMonClick.VMonClick.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;

@Repository
public interface InstanceRepository extends CassandraRepository<InstanceEntity, String> {

}
