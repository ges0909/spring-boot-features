package com.valantic.sti.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "mockserver.enabled", matchIfMissing = true)
public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {
}
