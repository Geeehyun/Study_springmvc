package org.fullstack4.springmvc.sample.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BbsServiceTests {
    @Autowired
    private BbsServiceIf bbsServiceIf;

    @Test
    public void testRegist() {
        //test 메서드에는 매개변수 못들어감 직접 안에서 생성해줘야함
        BbsDTO bbsDTO = BbsDTO.builder()
                        .user_id("test1")
                        .title("제목 테스트2")
                        .content("내용 테스트2")
                        .display_date("2024-04-18")
                        .readCnt(0)
                        .build();
        int result = bbsServiceIf.regist(bbsDTO);
    }
}
