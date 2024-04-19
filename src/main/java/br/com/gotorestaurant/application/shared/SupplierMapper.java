package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.SupplierEntityJPA;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import br.com.gotorestaurant.core.records.Supplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SupplierMapper {

    private SupplierMapper() {}

    public static SupplierEntityJPA toAddressEntity(br.com.gotorestaurant.core.records.Supplier supplier) {
        SupplierEntityJPA entity = new SupplierEntityJPA();
        entity.setDocument(supplier.document());
        entity.setName(supplier.name());
        entity.setEmail(supplier.email());
        entity.setSocialMediaEntityJPA(SocialMediaMapper.toListSocialMediaEntity(supplier.socialMedia()));
        entity.setPhoneEntityJPAS(PhoneMapper.toListPhoneEntity(supplier.phones()));
        return entity;
    }

    public static List<SupplierEntityJPA> toListSupplierEntity(List<br.com.gotorestaurant.core.records.Supplier> suppliers) {
        List<SupplierEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Supplier supplier : suppliers) {
            entities.add(toAddressEntity(supplier));
        }
        return entities;
    }

    public static List<Supplier> toListSupplier(List<SupplierEntityJPA> supplierEntityJPA) {
        List<Supplier> entities = new ArrayList<>();
        for (SupplierEntityJPA supplier : supplierEntityJPA) {
            entities.add(toSupplier(supplier));
        }
        return entities;
    }

    private static Supplier toSupplier(SupplierEntityJPA supplierEntityJPA) {
        return new Supplier(
            supplierEntityJPA.getName(),
            supplierEntityJPA.getEmail(),
            supplierEntityJPA.getDocument(),
            SocialMediaMapper.toListSocialMedia(supplierEntityJPA.getSocialMediaEntityJPA()),
            PhoneMapper.toListPhone(supplierEntityJPA.getPhoneEntityJPAS())
        );
    }
}
