package com.inditex.prueba.service;

import com.inditex.prueba.model.entity.Prices;
import com.inditex.prueba.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesServiceImplTest {

  @InjectMocks PricesServiceImpl pricesService;

  @Mock PriceRepository priceRepository;

  @Test
  void getPricesSuccess() {
    Prices expectedPrices = new Prices();
    expectedPrices.setPrice(50.0);

    when(priceRepository
            .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                any(), any(), any(), any()))
        .thenReturn(Optional.of(expectedPrices));

    Prices result = pricesService.getPrices(LocalDateTime.now(), 1L, 1L);

    assertEquals(expectedPrices, result);
  }
}
