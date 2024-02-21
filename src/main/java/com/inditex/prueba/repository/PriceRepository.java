package com.inditex.prueba.repository;

import com.inditex.prueba.model.entity.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Prices, Long> {

  Optional<Prices> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
          Long idProducto, Long idCadena, LocalDateTime fechaAplicacion, LocalDateTime fechaAplicacion2);
}
