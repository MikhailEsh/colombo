package main.logger.repository;

import main.logger.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public interface LogEntityRepository extends JpaRepository<Log, Long> {
}
