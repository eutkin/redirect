package net.eutkin.redirect.repository;

import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.ParameterKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface ParameterRepository extends JpaRepository<Parameter, ParameterKey> {

    List<Parameter> findAllByDomain(String domain);
}
