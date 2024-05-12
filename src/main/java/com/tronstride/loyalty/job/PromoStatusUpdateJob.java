package com.tronstride.loyalty.job;

import com.tronstride.loyalty.model.ProgramDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class PromoStatusUpdateJob {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 6000) // Run every 10 minutes (600,000 milliseconds)
    public void updateExpiredFlag() {
        log.info("PromoStatusUpdateJob started..");
        // Retrieve all rows from the table
        List<ProgramDetail> entities = jdbcTemplate.query("SELECT ID FROM program_detail", (resultSet, i) -> {
            ProgramDetail entity = new ProgramDetail();
            entity.setId(resultSet.getInt("id"));
            //entity.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
            return entity;
        });

        // Current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Check each row's timestamp and update expired flag if needed
        for (ProgramDetail entity : entities) {
            LocalDateTime timestamp = null;
            if (timestamp != null && timestamp.isBefore(currentTime)) {
                // Update the expired flag to true for this row
                jdbcTemplate.update("UPDATE your_table SET expired = true WHERE id = ?", entity.getId());
            }
        }
        log.info("PromoStatusUpdateJob ended..");

    }
}
