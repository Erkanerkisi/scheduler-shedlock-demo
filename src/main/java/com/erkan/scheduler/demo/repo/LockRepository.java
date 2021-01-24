package com.erkan.scheduler.demo.repo;

import com.erkan.scheduler.demo.entity.LockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockRepository extends JpaRepository<LockEntity, Long> {

}
