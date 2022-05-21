package com.dai.repository;

import com.dai.shared.ThresholdLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ThresholdLogsRepository extends JpaRepository<ThresholdLogs, Integer> {
    @Query(nativeQuery = true, value = "SELECT * from threshold_logs tl JOIN [user] u on tl.user_id = u.user_id JOIN threshold t on t.threshold_id = tl.threshold_id JOIN area a on a.area_id = t.area_id WHERE convert(date, tl.changed_on) = :date ORDER BY tl.log_id DESC")
    List<ThresholdLogs> findAllByChangedOnLike(@Param("date") LocalDate changed_on);

}
