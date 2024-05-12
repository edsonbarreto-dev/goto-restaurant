package br.com.gotorestaurant.application.record;

public record CreateResponse<T>(T result, String description) {
}
