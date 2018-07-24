package net.eutkin.redirect.repository;

import net.eutkin.redirect.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface SourcesRepository extends JpaRepository<Source, String > {

    Optional<Source> findByPath(String path);
}
