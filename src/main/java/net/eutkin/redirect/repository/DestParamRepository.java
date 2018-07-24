package net.eutkin.redirect.repository;

import net.eutkin.redirect.entity.DestParam;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DestParamRepository extends CrudRepository<DestParam, UUID> {
}
