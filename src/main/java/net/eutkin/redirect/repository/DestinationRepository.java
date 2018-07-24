package net.eutkin.redirect.repository;

import net.eutkin.redirect.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface DestinationRepository extends JpaRepository<Destination, UUID> {
}
