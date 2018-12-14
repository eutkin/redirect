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

    @Query("select lr " +
            "from #{#entityName} lr " +
            "where ip = :ip and lr.from = :path" +
            "  and lr.time = (select max(l.time) from #{#entityName} l where l.ip = lr.ip) "
    )
    Optional<LogRecord> findLastRedirectByIp(@Param("ip") String ip, @Param("path") String path);
}
