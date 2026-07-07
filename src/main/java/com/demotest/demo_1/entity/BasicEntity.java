package com.demotest.demo_1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter@Setter@ToString
public class BasicEntity {

    @Column(updatable = false)
    public LocalDateTime created_at;

    @Column(updatable = false)
    public String created_by;

    @Column(insertable = false)
    public LocalDateTime updated_at;

    @Column(insertable = false)
    public String updated_by;
}
