package com.inditex.prueba.service;

import com.inditex.prueba.model.entity.Prices;
import com.inditex.prueba.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PricesServiceImpl implements PricesService {

  private final PriceRepository priceRepository;

  @Override
  public Prices getPrices(LocalDateTime fechaAplicacion, Long idProducto, Long idCadena) {
    return priceRepository
        .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            idProducto, idCadena, fechaAplicacion, fechaAplicacion)
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "No se encontraron precios para los criterios especificados"));
  }
}
