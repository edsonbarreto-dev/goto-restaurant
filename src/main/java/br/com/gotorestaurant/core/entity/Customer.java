package br.com.gotorestaurant.core.entity;

import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.application.shared.PhoneMapper;
import br.com.gotorestaurant.core.exceptions.DocumentIncompleteException;
import br.com.gotorestaurant.core.exceptions.DocumentNullException;
import br.com.gotorestaurant.core.exceptions.EmailInvalidException;
import br.com.gotorestaurant.core.exceptions.NameNullException;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import br.com.gotorestaurant.core.shared.ValidateShared;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String document;
    private String name;
    private String email;
    private List<SocialMedia> socialMedia = new ArrayList<>();
    private List<Phone> phones = new ArrayList<>();
    private String subject;

    public Customer(String document, String name, String email) {
        subject = "Customer";

        this.verifyDocument(document);
        this.verifyName(name);
        this.verifyEmail(email);

        this.document = document;
        this.name = name;
        this.email = email;
    }

    private void verifyDocument(String document) {
        int minLength = 4;
        if (document.isBlank()) throw new DocumentNullException(subject);
        if (document.length() < minLength) throw new DocumentIncompleteException(subject);
    }

    private void verifyName(String name) {
        if (name == null || name.isBlank()) throw new NameNullException(subject);
    }

    private void verifyEmail(String email) {
        if (ValidateShared.validateEmail(email)) throw new EmailInvalidException(subject);
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void addSocialMedia(SocialMedia socialMedia) {
        if (socialMedia == null) return;
        this.socialMedia.add(socialMedia);
    }

    public void updateAllSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        ValidateShared.verifyPhone(phone);
        this.phones.add(phone);
    }

    public void addListPhone(List<PhoneEntity> phones) {
        List<Phone> listPhoneCore = PhoneMapper.fromListEntityToListCore(phones);
        listPhoneCore.forEach(this::addPhone);
    }
}
