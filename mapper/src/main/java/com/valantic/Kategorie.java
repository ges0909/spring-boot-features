package com.valantic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "MELDUNG_KATEGORIE")
public class Kategorie {

    @Id
    @Column(name = "KATEGORIE", length = 3)
    @Size(max = 3)
    private String id;

    @Column(name = "NAME", length = 25)
    @Size(max = 25)
    private String name;

    @Column(name = "PRIORITAET", length = 2)
    private Short prioritaet;

    @Column(name = "AENDERUNGS_TIMESTAMP", columnDefinition = "TIMESTAMP", insertable = false, updatable = false)
    private Instant aenderungsTimestamp;

    @Column(name = "CORRELATION_ID", length = 1024)
    @Size(max = 1024)
    private String correlationId;

    public String getId() {
        return id;
    }

    public void setId(String kategorieName) {
        this.id = kategorieName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(Short prioritaet) {
        this.prioritaet = prioritaet;
    }

    public Instant getAenderungsTimestamp() {
        return aenderungsTimestamp;
    }

    public void setAenderungsTimestamp(Instant aenderungsTimestamp) {
        this.aenderungsTimestamp = aenderungsTimestamp;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
