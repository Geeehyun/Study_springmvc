package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsVO;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.mapper.BbsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsServiceIf {
    private final BbsMapper bbsMapper;
    private final ModelMapper modelMapper;
    @Override
    public int regist(BbsDTO bbsDTO) {
        log.info("-------------------");
        log.info("BbsServiceImpl >> regist(BbsDTO) : " + bbsDTO);
        log.info("-------------------");

        BbsVO bbsVO = modelMapper.map(bbsDTO, BbsVO.class);
        int result = bbsMapper.regist(bbsVO);

        log.info("BbsServiceImpl >> bbsVO : " + bbsVO);
        log.info("BbsServiceImpl >> result : " + result);
        log.info("-------------------");
        return result;
    }
}
