package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.EmployeeEntity;
import br.com.gotorestaurant.core.records.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class EmployeerMapper {

    private EmployeerMapper() {}

    public static EmployeeEntity toEmployeerEntity(br.com.gotorestaurant.core.records.Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setDocument(employee.document());
        entity.setName(employee.name());
        entity.setEmail(employee.email());
        entity.setWorkFunction(employee.workFunction());
        entity.setWorkSchedule(employee.workSchedule());
        entity.setSocialMediaEntityJPA(SocialMediaMapper.toListSocialMediaEntity(employee.socialMedia()));
        entity.setPhoneEntityJPAS(PhoneMapper.toListPhoneEntity(employee.phones()));
        return entity;
    }

    public static List<EmployeeEntity> toListEmployeeEntity(List<br.com.gotorestaurant.core.records.Employee> employees) {
        List<EmployeeEntity> entities = new ArrayList<>();
        employees.forEach(employee -> entities.add(toEmployeerEntity(employee)));
        return entities;
    }

    public static List<Employee> toListEmployee(List<EmployeeEntity> employeeEntityJPA) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employee : employeeEntityJPA) {
            employees.add(toEmployee(employee));
        }
        return employees;
    }

    private static Employee toEmployee(EmployeeEntity employee) {
        return new Employee(
            employee.getName(),
            employee.getEmail(),
            employee.getDocument(),
            employee.getWorkSchedule(),
            employee.getWorkFunction(),
            SocialMediaMapper.toListSocialMedia(employee.getSocialMediaEntityJPA()),
            PhoneMapper.toListPhone(employee.getPhoneEntityJPAS())
        );
    }
}
