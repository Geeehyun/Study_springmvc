package org.fullstack4.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsDTO {
    @PositiveOrZero
    private int idx;
    @NotBlank
    private String user_id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String display_date;
    @Builder.Default
    @PositiveOrZero
    @Min(value=0)
    private int readCnt=0;
    private LocalDate reg_date;
    private LocalDate modify_date;
    private String interest;
    @Builder.Default
    @PositiveOrZero
    @Min(value=0)
    private int replyCnt=0;
}
