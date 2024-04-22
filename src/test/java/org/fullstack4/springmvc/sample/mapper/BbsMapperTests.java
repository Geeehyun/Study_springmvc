package org.fullstack4.springmvc.sample.mapper;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsVO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.mapper.BbsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BbsMapperTests {
    @Autowired(required = false)  //(required = false) => 추후 쓸 데 객체 생성하라는 의미
    private BbsMapper bbsMapper;

    @Test
    public void testBbsTotalCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .build();

        int totalCount = bbsMapper.bbsTotalCount(pageRequestDTO);
        log.info("--------------------------");
        log.info("testBbsTotalCount totalCount : " + totalCount);
        log.info("--------------------------");
    }
    @Test
    public void testBbsListByPage() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .build();
        List<BbsVO> bbsVOList = bbsMapper.bbsListByPage(pageRequestDTO);
        log.info("--------------------------");
        bbsVOList.forEach(list -> log.info(list));
        log.info("--------------------------");
    }
    
    @Test
    public void testBbsListBySearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .search_type(new String[]{"u", "t"})
                .search_word("테스트")
                .build();
        int totalCount = bbsMapper.bbsTotalCount(pageRequestDTO);
        List<BbsVO> bbsVOList = bbsMapper.bbsListByPage(pageRequestDTO);
        log.info("--------------------------");
        log.info("testBbsListBySearch totalCount : " + totalCount);
        bbsVOList.forEach(list -> log.info(list));
        log.info("--------------------------");
    } 

}
