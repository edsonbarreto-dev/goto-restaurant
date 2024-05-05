package br.com.gotorestaurant.core.usecase.restaurant.implementation.read;

import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import org.springframework.stereotype.Service;

@Service
public class MakeReservationRestaurantUseCase implements IMakeReservationUseCase {

    private final IRestaurantPresenter presenter;

    public MakeReservationRestaurantUseCase(IRestaurantPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeReservation(Reservation reservation, String document) {

        //buscar o restaurante

        //buscar o cliente. Caso não exista na base, registrar

        //Salvar uma reserva com o customer e o restaurante selecionados

        //atualizar a reserva informando o(s) aniversariantes

        presenter.makeReservation(reservation, document);
    }
}
