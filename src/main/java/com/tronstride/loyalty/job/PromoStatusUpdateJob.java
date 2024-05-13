package com.tronstride.loyalty.job;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.model.Timeframe;
import com.tronstride.loyalty.repository.ProgramDetailRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

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
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);

        entities.stream().filter(programDetail -> Objects.nonNull(programDetail.getProductTimeframe()) && Objects.nonNull(programDetail.getProductTimeframe().getExpiresOn()) &&
                programDetail.getProductTimeframe().getExpiresOn().isBefore(currentTime)
        ).map(programDetail -> {
            programDetail.setExpired(true);
            return programDetail;
        }).forEach(programDetail -> {
            programDetailRepo.save(programDetail);
        });
        // Logic to set Expired On in case npt already set or valid for flag is set to true
        entities.stream().filter(programDetail -> programDetail.isExpired() != true && Objects.nonNull(programDetail.getProductTimeframe()) && Objects.nonNull(programDetail.getProductTimeframe().getPublishedOn()) && programDetail.getProductTimeframe().getValidFor())
                .map(programDetail -> {
                    Timeframe timeframe = programDetail.getProductTimeframe();
                    switch (timeframe.getTimeSpanEntity().getType()) {
                        case "hour":
                            timeframe.setExpiresOn(timeframe.getPublishedOn().plus(Duration.ofHours(timeframe.getSpanValue())));
                            programDetail.setProductTimeframe(timeframe);
                            return programDetail;
                        case "day":
                            timeframe.setExpiresOn(timeframe.getPublishedOn().plus(Period.ofDays(timeframe.getSpanValue())));
                            programDetail.setProductTimeframe(timeframe);
                            return programDetail;
                        case "month":
                            timeframe.setExpiresOn(timeframe.getPublishedOn().plus(Period.ofMonths(timeframe.getSpanValue())));
                            programDetail.setProductTimeframe(timeframe);
                            return programDetail;
                        case "year":
                            timeframe.setExpiresOn(timeframe.getPublishedOn().plus(Period.ofYears(timeframe.getSpanValue())));
                            programDetail.setProductTimeframe(timeframe);
                            return programDetail;
                        default:
                            return programDetail;
                    }
                }).forEach(programDetail -> programDetailRepo.save(programDetail));

        log.info("PromoStatusUpdateJob ended..");

    }
}
