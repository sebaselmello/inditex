package com.inditex.prueba.controller;

import com.inditex.prueba.model.entity.Prices;
import com.inditex.prueba.service.PricesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

  @InjectMocks PricesController pricesController;

  @Mock PricesService pricesService;

  @Test
  void obtenerPrecioCaso1() {
    LocalDateTime fechaAplicacion = LocalDateTime.parse("2020-06-14T10:00:00");
    Long idProducto = 35455L;
    Long idCadena = 1L;
    Prices expectedPrices =
        createExpectedPrices(
            35.5,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"));

    testObtenerPrecio(fechaAplicacion, idProducto, idCadena, expectedPrices);
  }

  @Test
  void obtenerPrecioCaso2() {
    LocalDateTime fechaAplicacion = LocalDateTime.parse("2020-06-14T16:00:00");
    Long idProducto = 35455L;
    Long idCadena = 1L;
    Prices expectedPrices =
        createExpectedPrices(
            25.45,
            LocalDateTime.parse("2020-06-14T15:00:00"),
            LocalDateTime.parse("2020-06-14T18:30:00"));

    testObtenerPrecio(fechaAplicacion, idProducto, idCadena, expectedPrices);
  }

  @Test
  void obtenerPrecioCaso3() {
    LocalDateTime fechaAplicacion = LocalDateTime.parse("2020-06-14T21:00:00");
    Long idProducto = 35455L;
    Long idCadena = 1L;
    Prices expectedPrices =
        createExpectedPrices(
            35.5,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"));

    testObtenerPrecio(fechaAplicacion, idProducto, idCadena, expectedPrices);
  }

  @Test
  void obtenerPrecioCaso4() {
    LocalDateTime fechaAplicacion = LocalDateTime.parse("2020-06-15T10:00:00");
    Long idProducto = 35455L;
    Long idCadena = 1L;
    Prices expectedPrices =
        createExpectedPrices(
            30.50,
            LocalDateTime.parse("2020-06-15T00:00:00"),
            LocalDateTime.parse("2020-06-15T11:00:00"));

    testObtenerPrecio(fechaAplicacion, idProducto, idCadena, expectedPrices);
  }

  @Test
  void obtenerPrecioCaso5() {
    LocalDateTime fechaAplicacion = LocalDateTime.parse("2020-06-16T21:00:00");
    Long idProducto = 35455L;
    Long idCadena = 1L;
    Prices expectedPrices =
        createExpectedPrices(
            38.95,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"));

    testObtenerPrecio(fechaAplicacion, idProducto, idCadena, expectedPrices);
  }

  private void testObtenerPrecio(
      LocalDateTime fechaAplicacion, Long idProducto, Long idCadena, Prices expectedPrices) {
    // Mock del servicio
    when(pricesService.getPrices(fechaAplicacion, idProducto, idCadena)).thenReturn(expectedPrices);

    // Llamada al controlador
    ResponseEntity<Prices> responsePrices =
        pricesController.obtenerPrecio(fechaAplicacion, idProducto, idCadena);

    // Verificaciones
    assertEquals(HttpStatus.OK, responsePrices.getStatusCode());
    assertEquals(expectedPrices, responsePrices.getBody());
  }

  private Prices createExpectedPrices(
      double price, LocalDateTime startDate, LocalDateTime endDate) {
    Prices prices = new Prices();
    prices.setPrice(price);
    prices.setBrandId(1L);
    prices.setProductId(35455L);
    prices.setCurrency("EUR");
    prices.setStartDate(startDate);
    prices.setEndDate(endDate);
    return prices;
  }
}
