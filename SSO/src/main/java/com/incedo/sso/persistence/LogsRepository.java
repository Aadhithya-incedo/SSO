package com.incedo.sso.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.sso.models.Logs;

public interface LogsRepository extends JpaRepository<Logs, Long>{

}
