package br.com.gotorestaurant.application.record;

import java.util.List;

public record CustomerVO(
        String document,
        String name,
        String email,
        List<PhoneVO> phones
) {
}
