package br.com.gotorestaurant.core.usecase.restaurant.implementation.update;

import br.com.gotorestaurant.application.shared.SocialMediaMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.SocialMedia;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateSocialMediaCustomerUseCase implements IUpdateCustomerUseCase {

    private final ICustomerPresenter presenter;
    private boolean updated = false;

    public UpdateSocialMediaCustomerUseCase(ICustomerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Customer update(SocialMedia socialMedia, String document) {

        Customer customer = this.presenter.findByDocument(document);
        if (customer == null) throw new CustomerNotFoundException();

        List<SocialMedia> socialMediaResult = new ArrayList<>();

        customer.getSocialMedia().forEach(socialMediaItem -> {
            if (socialMediaItem.name().equals(socialMedia.getName())) {
                SocialMedia n = new SocialMedia(socialMediaItem.name(), socialMediaItem.accountName(), socialMediaItem.fullUrlPlatform());
                socialMediaItem.comments().forEach(n::addComment);
                socialMediaItem.reviews().forEach(n::addReview);
                socialMediaResult.add(n);
                updated = true;
            } else {
                socialMediaResult.add(SocialMediaMapper.toSocialMedia(socialMediaItem));
            }
        });
        if ( ! updated) socialMediaResult.add(socialMedia);

        customer.updateAllSocialMedia(SocialMediaMapper.toSocialMedia(socialMediaResult));

        this.presenter.updateCustomer(customer);

        return customer;
    }
}
