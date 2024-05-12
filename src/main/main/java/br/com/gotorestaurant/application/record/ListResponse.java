package br.com.gotorestaurant.application.record;

public record ListResponse<T>(T list, String description) {
}
