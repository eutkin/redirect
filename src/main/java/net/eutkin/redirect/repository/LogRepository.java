package net.eutkin.redirect.repository;

import net.eutkin.redirect.entity.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface LogRepository extends JpaRepository<LogRecord, UUID> {

    @Query("SELECT lr.to from net.eutkin.redirect.entity.LogRecord lr where ip = :ip order by lr.time desc")
    Optional<LogRecord> findLastRedirectByIp(@Param("ip") String ip);
}
