package com.unitedinternet.azubiakademie.services.data;

import com.unitedinternet.azubiakademie.model.Presentation;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface to load entrys from database
 */
public interface PresentationRepository extends CrudRepository<Presentation,Long> {
}
