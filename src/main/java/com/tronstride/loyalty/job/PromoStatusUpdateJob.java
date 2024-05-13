package com.tronstride.loyalty.job;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.model.Timeframe;
import com.tronstride.loyalty.repository.ProgramDetailRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PromoStatusUpdateJob {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProgramDetailRepo programDetailRepo;

    @Scheduled(fixedRate = 6000) // Run every 10 minutes (600,000 milliseconds)
    public void updateExpiredFlag() {
        // Logic to set promo as has expired
        log.info("PromoStatusUpdateJob started..");
        // Retrieve all rows from the table
        List<ProgramDetail> entities = programDetailRepo.findAll();
        LocalDate currentTime = LocalDate.now(ZoneOffset.UTC);

      entities.stream().filter(programDetail ->
               programDetail.getProductTimeframe().getExpiresOn().isBefore(currentTime)
       ).map(programDetail -> {
           programDetail.setExpired(true);
           return programDetail;
       }).forEach( programDetail -> {
           programDetailRepo.save(programDetail);
       });



        log.info("PromoStatusUpdateJob ended..");

    }
}
