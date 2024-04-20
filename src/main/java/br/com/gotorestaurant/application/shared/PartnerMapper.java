package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.PartnerEntity;
import br.com.gotorestaurant.core.records.Partner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class PartnerMapper {

    private PartnerMapper() {}

    public static PartnerEntity toPartnerEntity(br.com.gotorestaurant.core.records.Partner partner) {
        PartnerEntity entity = new PartnerEntity();
        entity.setDocument(partner.document());
        entity.setName(partner.name());
        entity.setEmail(partner.email());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(partner.socialMedia()));
        entity.setPhoneEntity(PhoneMapper.toListPhoneEntity(partner.phones()));
        return entity;
    }

    public static List<PartnerEntity> toListPartnerEntity(List<br.com.gotorestaurant.core.records.Partner> partners) {
        List<PartnerEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Partner partner : partners) {
            entities.add(toPartnerEntity(partner));
        }
        return entities;
    }

    public static List<Partner> toListPartner(List<PartnerEntity> listPartnerEntity) {
        List<Partner> entities = new ArrayList<>();
        for (PartnerEntity partner : listPartnerEntity) {
            entities.add(toPartiner(partner));
        }
        return entities;
    }

    private static Partner toPartiner(PartnerEntity partner) {
        return new Partner(
            partner.getName(),
            partner.getEmail(),
            partner.getDocument(),
            SocialMediaMapper.toListSocialMedia(partner.getSocialMediaEntity()),
            PhoneMapper.toListPhone(partner.getPhoneEntity())
        );
    }
}
