package br.com.gotorestaurant.application.record;

public record UpdateResponse<T>(T result, String description) {
}
