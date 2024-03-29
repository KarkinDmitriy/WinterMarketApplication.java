package ru.geekbrains.winter.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;


@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private   final WebClient productServiceWebclient;
    public ProductDto getProductById(Long id){
        return productServiceWebclient.get()
                .uri("api/v1/products" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }

}
