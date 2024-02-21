package com.inditex.prueba.service;

import com.inditex.prueba.model.entity.Prices;

import java.time.LocalDateTime;

public interface PricesService {

  Prices getPrices(LocalDateTime fechaAplicacion, Long idProducto, Long idCadena);
}
