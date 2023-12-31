package com.valantic;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "LOG_MESSAGES")
@ConditionalOnProperty(name = "mockserver.enabled", matchIfMissing = true)
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    private long createdAt;

    @Column(name = "updated", nullable = false, updatable = false)
    @LastModifiedDate
    private long updatedAt;

    @Column(nullable = false)
    private String text;

    public void setText(String text) {
        this.text = text;
    }
}
