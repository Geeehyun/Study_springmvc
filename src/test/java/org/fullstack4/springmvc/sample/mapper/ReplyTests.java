package org.fullstack4.springmvc.sample.mapper;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsReplyVO;
import org.fullstack4.springmvc.mapper.BbsReplyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class ReplyTests {
    @Autowired(required = false)
    BbsReplyMapper bbsReplyMapper;

    @Test
    public void testBbsReplyRegist() {
        BbsReplyVO bbsReplyVO = BbsReplyVO.builder()
                .bbs_idx(51)
                .title("테스트")
                .content("내용 콘텐츠")
                .user_id("test1")
                .build();
        int result = bbsReplyMapper.regist(bbsReplyVO);
    }
}
