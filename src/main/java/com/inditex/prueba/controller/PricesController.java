package com.inditex.prueba.controller;

import com.inditex.prueba.model.entity.Prices;
import com.inditex.prueba.service.PricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PricesController {

  private final PricesService pricesService;

  @GetMapping("/precio")
  public ResponseEntity<Prices> obtenerPrecio(
      @RequestParam("fechaAplicacion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime fechaAplicacion,
      @RequestParam("idProducto") Long idProducto,
      @RequestParam("idCadena") Long idCadena) {
    Prices prices = pricesService.getPrices(fechaAplicacion, idProducto, idCadena);
    return ResponseEntity.ok(prices);
  }
}
