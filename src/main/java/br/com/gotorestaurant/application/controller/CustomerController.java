package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.application.record.SocialMediaVO;
import br.com.gotorestaurant.application.record.UpdateResponse;
import br.com.gotorestaurant.application.shared.SocialMediaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gotorestaurant.application.record.CreateResponse;
import br.com.gotorestaurant.application.record.CustomerVO;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerService;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/find/document/{document}")
    public ResponseEntity<Customer> findByDocument(@PathVariable String document) {
        Customer customer = this.customerService.findByDocument(document);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping
    public ResponseEntity<CreateResponse<Long>> create(@RequestBody CustomerVO customer) {
        Long id = this.customerService.create(CustomerMapper.toCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreateResponse<>(id, "Success Create Customer")
        );
    }

    @PutMapping("/{document}/update/socialMedia")
    public ResponseEntity<UpdateResponse<Customer>> update(@RequestBody SocialMediaVO socialMediaVO, @PathVariable String document) {
        Customer customerUpdated = this.customerService.update(SocialMediaMapper.toSocialMedia(socialMediaVO), document);
        return ResponseEntity.status(HttpStatus.OK).body(
                new UpdateResponse<>(customerUpdated, "Success Update Customer")
        );
    }
}
