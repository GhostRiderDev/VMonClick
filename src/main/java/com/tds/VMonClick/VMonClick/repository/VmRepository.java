package com.tds.VMonClick.VMonClick.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import com.tds.VMonClick.VMonClick.model.VmEntity;

@Repository
public interface VmRepository extends CassandraRepository<VmEntity, Integer> {

}
