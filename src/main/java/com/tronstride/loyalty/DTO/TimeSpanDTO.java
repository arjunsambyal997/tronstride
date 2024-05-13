package com.tronstride.loyalty.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSpanDTO {

    private String type;
    private Integer value;
    private LocalDateTime startsOn;
    private LocalDateTime expiredOn;
}
