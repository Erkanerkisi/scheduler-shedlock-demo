package com.erkan.scheduler.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = "XXER", name = "LOCK")
@Data
public class LockEntity {

    @Id
    private Long id;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "LOCK_UNTIL_AT")
    private LocalDateTime lockUntilAt;

    @Column(name = "LOCK_AT")
    private LocalDateTime lockAt;
}
