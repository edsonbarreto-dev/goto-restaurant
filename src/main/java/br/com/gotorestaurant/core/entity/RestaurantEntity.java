package br.com.gotorestaurant.core.entity;

import br.com.gotorestaurant.core.exceptions.*;
import br.com.gotorestaurant.core.records.*;
import br.com.gotorestaurant.core.shared.ValidateShared;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RestaurantEntity {
    private final AtomicReference<String> document = new AtomicReference<>();
    private final AtomicReference<String> name = new AtomicReference<>();
    private final AtomicInteger capacity = new AtomicInteger();
    private final AtomicReference<Brand> brand = new AtomicReference<>();
    private final AtomicReference<List<Address>> address = new AtomicReference<>();
    private final AtomicReference<List<Phone>> phones = new AtomicReference<>(List.of());
    private final AtomicReference<List<SocialMedia>> socialMedia = new AtomicReference<>(List.of());
    private final AtomicReference<List<Employee>> employees = new AtomicReference<>(List.of());
    private final AtomicReference<List<Customer>> customers = new AtomicReference<>(List.of());
    private final AtomicReference<List<Supplier>> suppliers = new AtomicReference<>(List.of());
    private final AtomicReference<List<Partner>> partners = new AtomicReference<>(List.of());
    private final AtomicReference<List<Reservation>> reservations = new AtomicReference<>(List.of());
    private final String subject;

    public RestaurantEntity(String document, String name, int capacity) {
        subject = "RestaurantEntityJPA";

        this.verifyDocument(document);
        this.verifyName(name);
        this.verifyCapacity(capacity);

        this.document.set(document);
        this.name.set(name);
        this.capacity.set(capacity);
    }

    public String document() {
        return document.get();
    }

    public String name() {
        return name.get();
    }

    public int capacity() {
        return capacity.get();
    }

    public Brand brand() {
        return brand.get();
    }

    public List<Address> address() {
        return address.get();
    }

    public List<Phone> phones() {
        return phones.get();
    }

    public List<SocialMedia> socialMedia() {
        return socialMedia.get();
    }

    public List<Employee> employees() {
        return employees.get();
    }

    public List<Customer> customers() {
        return customers.get();
    }

    public List<Supplier> suppliers() {
        return suppliers.get();
    }

    public List<Partner> partners() {
        return partners.get();
    }

    public List<Reservation> reservations() {
        return reservations.get();
    }

    public RestaurantEntity setBrand(Brand brand) {
        this.verifyBrand(brand);
        this.brand.set(brand);
        return this;
    }

    public RestaurantEntity setAddress(Address address) {
        this.verifyAddress(address);
        this.address.get().add(address);
        return this;
    }

    public RestaurantEntity addAddress(List<Address> addresses) {
        addresses.forEach(this::verifyAddress);
        this.address.set(addresses);
        return this;
    }

    public RestaurantEntity setPhones(List<Phone> phones) {
        phones.forEach(this::addPhone);
        return this;
    }

    public RestaurantEntity setSocialMedia(List<SocialMedia> socialMedia) {
        socialMedia.forEach(this::addSocialMedia);
        return this;
    }

    public RestaurantEntity setEmployees(List<Employee> employees) {
        employees.forEach(this::addEmployee);
        return this;
    }

    public RestaurantEntity setCustomers(List<Customer> customers) {
        customers.forEach(this::addCustomer);
        return this;
    }

    public RestaurantEntity setSuppliers(List<Supplier> suppliers) {
        suppliers.forEach(this::addSupplier);
        return this;
    }

    public RestaurantEntity setPartners(List<Partner> partners) {
        partners.forEach(this::addPartiner);
        return this;
    }

    public RestaurantEntity setReservations(List<Reservation> reservations) {
        reservations.forEach(this::addReservation);
        return this;
    }

    public RestaurantEntity addSocialMedia(SocialMedia socialMedia) {
        this.socialMedia.get().add(socialMedia);
        return this;
    }

    public RestaurantEntity addEmployee(Employee employee) {
        this.verifyEmployee(employee);
        this.employees.get().add(employee);
        return this;
    }

    public RestaurantEntity addCustomer(Customer customer) {
        this.verifyCustomer(customer);
        this.customers.get().add(customer);
        return this;
    }

    public RestaurantEntity addSupplier(Supplier supplier) {
        this.verifySupplier(supplier);
        this.suppliers.get().add(supplier);
        return this;
    }

    public RestaurantEntity addPartiner(Partner partner) {
        this.verifyPartner(partner);
        this.partners.get().add(partner);
        return this;
    }

    public RestaurantEntity addReservation(Reservation reservation) {
        this.verifyReservation(reservation);
        this.reservations.get().add(reservation);
        return this;
    }

    public RestaurantEntity addPhone(Phone phone) {
        ValidateShared.verifyPhone(phone);
        this.phones.get().add(phone);
        return this;
    }

    private void verifyName(String name) {
        if (name == null || name.isBlank()) throw new NameNullException(subject);
    }

    private void verifyCapacity(int capacity) {
        int maxPeopleOnPlace = 1;
        if (capacity < maxPeopleOnPlace) throw new BuildingCapacityException();
    }

    private void verifyDocument(String document) {
        int minLength = 4;
        if (document.isBlank()) throw new DocumentNullException(subject);
        if (document.length() < minLength) throw new DocumentIncompleteException(subject);
    }

    private void verifyAddress(Address address) {
        String subject = "RestaurantEntityJPA";
        if (address == null) throw new AddressNullException(subject);
        int minLengthPublicPlace = 5;
        if (address.publicPlace().isBlank() || address.publicPlace().length() < minLengthPublicPlace) {
            throw new PublicPlaceNullException();
        }
        if (address.neighborhood().isEmpty()) throw new NeighborhoodNullException();
        if (address.city().isEmpty()) throw new CityNullException();
        if (address.state().isEmpty()) throw new StateNullException();
        if (address.country().isEmpty()) throw new CountryNullException();
        if (address.zipCode().isEmpty()) throw new ZipCodeNullException();
    }

    private void verifyEmployee(Employee employee) {
        if (employee == null) throw new EmployeeNullException();
        int minLengthNumberDocument = 5;
        int minItemListPhoneNumber = 1;
        String subject = "EmployeeEntityJPA";
        if (ValidateShared.validateEmail(employee.email())) throw new EmailInvalidException(subject);
        if (employee.document().isBlank()) throw new DocumentNullException(subject);
        if (employee.name().isBlank()) throw new NameNullException(subject);
        if (employee.document().length() < minLengthNumberDocument) throw new DocumentIncompleteException(subject);
        if (employee.phones().size() < minItemListPhoneNumber) {
            throw new MinItemListPhoneNumberException(subject, minItemListPhoneNumber);
        }
    }

    private void verifyCustomer(Customer customer) {
        if (customer == null) throw new CustomerNullException();
        int minLengthNumberDocument = 5;
        int minItemListPhoneNumber = 1;
        String subject = "CustomerEntityJPA";
        if (customer.document().isBlank()) throw new DocumentNullException(subject);
        if (ValidateShared.validateEmail(customer.email())) throw new EmailInvalidException(subject);
        if (customer.name().isBlank()) throw new NameNullException(subject);
        if (customer.document().length() < minLengthNumberDocument) throw new DocumentIncompleteException(subject);
        if (customer.phones().size() < minItemListPhoneNumber) {
            throw new MinItemListPhoneNumberException(subject, minItemListPhoneNumber);
        }
    }

    private void verifyBrand(Brand brand) {
        if (brand == null) throw new BrandNullException();
        if (brand.pathImageBasic().isEmpty()) throw new PathImageBrandNullException("Basic");
        if (brand.pathImageDark().isEmpty()) throw new PathImageBrandNullException("Dark");
    }

    private void verifySupplier(Supplier supplier) {
        if (supplier == null) throw new SupplierNullException();
        int minLengthNumberDocument = 5;
        int minItemListPhoneNumber = 1;
        String subject = "SupplierEntityJPA";
        if (supplier.name().isBlank()) throw new NameNullException(subject);
        if (ValidateShared.validateEmail(supplier.email())) throw new EmailInvalidException(subject);
        if (supplier.document().isBlank()) throw new DocumentNullException(subject);
        if (supplier.document().length() < minLengthNumberDocument) throw new DocumentIncompleteException(subject);
        if (supplier.phones().size() < minItemListPhoneNumber) {
            throw new MinItemListPhoneNumberException(subject, minItemListPhoneNumber);
        }
    }

    private void verifyPartner(Partner partner) {
        if (partner == null) throw new PartnerNullException();
        int minLengthNumberDocument = 5;
        int minItemListPhoneNumber = 1;
        String subject = "PartnerEntityJPA";
        if (ValidateShared.validateEmail(partner.email())) throw new EmailInvalidException(subject);
        if (partner.name().isBlank()) throw new NameNullException(subject);
        if (partner.document().isBlank()) throw new DocumentNullException(subject);
        if (partner.document().length() < minLengthNumberDocument) throw new DocumentIncompleteException(subject);
        if (partner.phones().size() < minItemListPhoneNumber) {
            throw new MinItemListPhoneNumberException(subject, minItemListPhoneNumber);
        }
    }

    private void verifyReservation(Reservation reservation) {
        int minPeopleForReservation = 2;
        if (reservation == null) throw new ReservationNullException();
        if (reservation.customer() == null) throw new CustomerNullException();
        if (reservation.date() == null) throw new ReservationDateNullException();
        if (reservation.numberOfPeople() < minPeopleForReservation)
            throw new ReservationNumberOfPeopleException(minPeopleForReservation);
        if (!reservation.birthdays().isEmpty()) {
            reservation.birthdays().forEach(birthdayPerson -> {
                if (birthdayPerson.name().isEmpty()) throw new BirthdayPersonNullException();
            });
        }
    }
}
