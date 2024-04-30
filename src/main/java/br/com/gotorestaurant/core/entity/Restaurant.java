package br.com.gotorestaurant.core.entity;

import br.com.gotorestaurant.core.exceptions.*;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.records.Brand;
import br.com.gotorestaurant.core.records.Address;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import br.com.gotorestaurant.core.records.Employee;
import br.com.gotorestaurant.core.records.Supplier;
import br.com.gotorestaurant.core.records.Partner;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.shared.ValidateShared;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String document;
    private String name;
    private Integer capacity;
    private Brand brand;
    private List<Address> addresses = new ArrayList<>();
    private List<Phone> phones = new ArrayList<>();
    private List<SocialMedia> socialMedia = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();
    private List<Partner> partners = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private String subject;

    public Restaurant(String document, String name, int capacity) {
        subject = "RestaurantEntity";

        this.verifyDocument(document);
        this.verifyName(name);
        this.verifyCapacity(capacity);

        this.document = document;
        this.name = name;
        this.capacity = capacity;
    }

    public String document() {
        return document;
    }

    public String name() {
        return name;
    }

    public int capacity() {
        return capacity;
    }

    public Brand brand() {
        return brand;
    }

    public List<Address> addresses() {
        return addresses;
    }

    public List<Phone> phones() {
        return phones;
    }

    public List<SocialMedia> socialMedia() {
        return socialMedia;
    }

    public List<Employee> employees() {
        return employees;
    }

    public List<Customer> customers() {
        return customers;
    }

    public List<Supplier> suppliers() {
        return suppliers;
    }

    public List<Partner> partners() {
        return partners;
    }

    public List<Reservation> reservations() {
        return reservations;
    }

    public Restaurant setBrand(Brand brand) {
        this.verifyBrand(brand);
        this.brand = brand;
        return this;
    }

    public Restaurant setAddress(Address address) {
        if (addresses == null) return this;
        this.verifyAddress(address);
        this.addresses.add(address);
        return this;
    }

    public Restaurant addAddress(List<Address> addresses) {
        if (addresses == null) return this;
        addresses.forEach(this::verifyAddress);
        this.addresses = addresses;
        return this;
    }

    public Restaurant setPhones(List<Phone> phones) {
        phones.forEach(this::addPhone);
        return this;
    }

    public Restaurant setSocialMedia(List<SocialMedia> socialMedia) {
        if (socialMedia == null) return this;
        socialMedia.forEach(this::addSocialMedia);
        return this;
    }

    public Restaurant setEmployees(List<Employee> employees) {
        if (employees == null) return this;
        employees.forEach(this::addEmployee);
        return this;
    }

    public Restaurant setCustomers(List<Customer> customers) {
        if (customers == null) return this;
        customers.forEach(this::addCustomer);
        return this;
    }

    public Restaurant setSuppliers(List<Supplier> suppliers) {
        if (suppliers == null) return this;
        suppliers.forEach(this::addSupplier);
        return this;
    }

    public Restaurant setPartners(List<Partner> partners) {
        if (partners == null) return this;
        partners.forEach(this::addPartiner);
        return this;
    }

    public Restaurant setReservations(List<Reservation> reservations) {
        if (reservations == null) return this;
        reservations.forEach(this::addReservation);
        return this;
    }

    public Restaurant addSocialMedia(SocialMedia socialMedia) {
        if (socialMedia == null) return this;
        this.socialMedia.add(socialMedia);
        return this;
    }

    public Restaurant addEmployee(Employee employee) {
        if (employee == null) return this;
        this.verifyEmployee(employee);
        this.employees.add(employee);
        return this;
    }

    public Restaurant addCustomer(Customer customer) {
        if (customer == null) return this;
        this.verifyCustomer(customer);
        this.customers.add(customer);
        return this;
    }

    public Restaurant addSupplier(Supplier supplier) {
        if (supplier == null) return this;
        this.verifySupplier(supplier);
        this.suppliers.add(supplier);
        return this;
    }

    public Restaurant addPartiner(Partner partner) {
        if (partner == null) return this;
        this.verifyPartner(partner);
        this.partners.add(partner);
        return this;
    }

    public Restaurant addReservation(Reservation reservation) {
        if (reservation == null) return this;
        this.verifyReservation(reservation);
        this.reservations.add(reservation);
        return this;
    }

    public Restaurant addPhone(Phone phone) {
        if (phone == null) return this;
        ValidateShared.verifyPhone(phone);
        this.phones.add(phone);
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
        String subject = "RestaurantEntity";
        if (address == null) throw new AddressNullException(subject);
        int minLengthPublicPlace = 1;
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
        String subject = "EmployeeEntity";
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
        String subject = "Customer";
        if (customer.getDocument().isBlank()) throw new DocumentNullException(subject);
        if (ValidateShared.validateEmail(customer.getEmail())) throw new EmailInvalidException(subject);
        if (customer.getName().isBlank()) throw new NameNullException(subject);
        if (customer.getDocument().length() < minLengthNumberDocument) throw new DocumentIncompleteException(subject);
        if (customer.getPhones().size() < minItemListPhoneNumber) {
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
        String subject = "SupplierEntity";
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
        String subject = "PartnerEntity";
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
