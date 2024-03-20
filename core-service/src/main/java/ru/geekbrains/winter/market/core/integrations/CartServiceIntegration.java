package ru.geekbrains.winter.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.winter.market.api.CartDto;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private   final WebClient cartServiceWebclient;
    public CartDto getCurrentCart(){
        return cartServiceWebclient.get()
                .uri("api/v1/cart")
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
//                )
                .bodyToMono(CartDto.class)
                .block();
    }
    public void clear(){
         cartServiceWebclient.get()
                .uri("api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
