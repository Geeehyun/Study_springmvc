package org.fullstack4.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    @NotBlank
    private String user_id;
    @NotBlank
    private String name;
    @NotBlank
    private String pwd;
    private String jumin;
    private String addr1;
    private String addr2;
    @NotNull(message = "공백일 수 없습니다.")
    @PastOrPresent(message = "오늘 보다 미래일 수 없습니다.")
    private LocalDate birthday;
    private String job_code;
    private int mileage;
    private String user_state;
    private LocalDate reg_date;
    private LocalDate leave_date;
    private LocalDate pwd_change_date;
    private String interest;
    @Email
    private String email;
}
