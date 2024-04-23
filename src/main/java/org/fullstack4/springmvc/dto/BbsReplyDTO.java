package org.fullstack4.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BbsReplyDTO {
    private int idx;
    private int bbs_idx;
    private String title;
    private String content;
    private String user_id;
    private LocalDate reg_date;
    private LocalDate modify_date;
}
