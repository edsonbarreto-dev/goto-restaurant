package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "PHONE", schema = "TOGORESTAURANT")
public class PhoneEntityJPA {

    @Id
    @UuidGenerator
    private UUID uuid;
    private CountryCodeEnum countryCode;
    private String codeArea;
    private Long number;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CountryCodeEnum getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCodeEnum countryCode) {
        this.countryCode = countryCode;
    }

    public String getCodeArea() {
        return codeArea;
    }

    public void setCodeArea(String codeArea) {
        this.codeArea = codeArea;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
