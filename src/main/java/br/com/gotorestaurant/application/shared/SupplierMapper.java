package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.SupplierEntity;
import br.com.gotorestaurant.core.records.Supplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SupplierMapper {

    private SupplierMapper() {}

    public static SupplierEntity toAddressEntity(Supplier supplier) {
        if (supplier == null) return null;
        SupplierEntity entity = new SupplierEntity();
        entity.setDocument(supplier.document());
        entity.setName(supplier.name());
        entity.setEmail(supplier.email());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(supplier.socialMedia()));
        entity.setPhoneEntity(PhoneMapper.fromListCoreToListEntity(supplier.phones()));
        return entity;
    }

    public static List<SupplierEntity> toListSupplierEntity(List<Supplier> suppliers) {
        if (suppliers == null) return null;
        List<SupplierEntity> entities = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            entities.add(toAddressEntity(supplier));
        }
        return entities;
    }

    public static List<Supplier> toListSupplier(List<SupplierEntity> supplierEntity) {
        if (supplierEntity == null) return null;
        List<Supplier> entities = new ArrayList<>();
        for (SupplierEntity supplier : supplierEntity) {
            entities.add(toSupplier(supplier));
        }
        return entities;
    }

    private static Supplier toSupplier(SupplierEntity supplierEntity) {
        if (supplierEntity == null) return null;
        return new Supplier(
            supplierEntity.getName(),
            supplierEntity.getEmail(),
            supplierEntity.getDocument(),
            SocialMediaMapper.toListSocialMedia(supplierEntity.getSocialMediaEntity()),
            PhoneMapper.fromListEntityToListCore(supplierEntity.getPhoneEntity())
        );
    }
}
