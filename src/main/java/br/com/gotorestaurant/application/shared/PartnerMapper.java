package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.PartnerEntityJPA;
import br.com.gotorestaurant.core.records.Partner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class PartnerMapper {

    private PartnerMapper() {}

    public static PartnerEntityJPA toPartnerEntity(br.com.gotorestaurant.core.records.Partner partner) {
        PartnerEntityJPA entity = new PartnerEntityJPA();
        entity.setDocument(partner.document());
        entity.setName(partner.name());
        entity.setEmail(partner.email());
        entity.setSocialMediaEntityJPA(SocialMediaMapper.toListSocialMediaEntity(partner.socialMedia()));
        entity.setPhoneEntityJPA(PhoneMapper.toListPhoneEntity(partner.phones()));
        return entity;
    }

    public static List<PartnerEntityJPA> toListPartnerEntity(List<br.com.gotorestaurant.core.records.Partner> partners) {
        List<PartnerEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Partner partner : partners) {
            entities.add(toPartnerEntity(partner));
        }
        return entities;
    }

    public static List<Partner> toListPartner(List<PartnerEntityJPA> listPartnerEntityJPA) {
        List<Partner> entities = new ArrayList<>();
        for (PartnerEntityJPA partner : listPartnerEntityJPA) {
            entities.add(toPartiner(partner));
        }
        return entities;
    }

    private static Partner toPartiner(PartnerEntityJPA partner) {
        return new Partner(
            partner.getName(),
            partner.getEmail(),
            partner.getDocument(),
            SocialMediaMapper.toListSocialMedia(partner.getSocialMediaEntityJPA()),
            PhoneMapper.toListPhone(partner.getPhoneEntityJPA())
        );
    }
}
