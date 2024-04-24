package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsReplyVO;
import org.fullstack4.springmvc.dto.BbsReplyDTO;
import org.fullstack4.springmvc.mapper.BbsReplyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BbsReplyServiceImpl implements BbsReplyServiceIf {
    private final BbsReplyMapper bbsReplyMapper;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public int regist(BbsReplyDTO bbsReplyDTO) {
        log.info("-------------------");
        BbsReplyVO bbsReplyVO = modelMapper.map(bbsReplyDTO, BbsReplyVO.class);
        log.info("BbsReplyServiceImpl >> regist()" );
        log.info("BbsReplyServiceImpl >> bbsReplyDTO : " + bbsReplyDTO);
        log.info("BbsReplyServiceImpl >> bbsReplyVO : " + bbsReplyVO);
        int result = bbsReplyMapper.regist(bbsReplyVO);
        int reply_result = 0;
        if(result > 0 ) reply_result = bbsReplyMapper.update_replyCnt(bbsReplyDTO.getBbs_idx());
        log.info("BbsReplyServiceImpl >> result : " + result);
        log.info("-------------------");
        return result;
    }

    @Override
    public int update_replyCnt(int bbs_idx) {
        log.info("-------------------");
        log.info("BbsReplyServiceImpl >> update_replyCnt()" );
        log.info("BbsReplyServiceImpl >> bbs_idx : " + bbs_idx);
        int result = bbsReplyMapper.update_replyCnt(bbs_idx);
        log.info("BbsReplyServiceImpl >> result : " + result);
        log.info("-------------------");
        return result;
    }

    @Override
    public List<BbsReplyDTO> list(int bbs_idx) {
        log.info("-------------------");
        log.info("BbsReplyServiceImpl >> list()" );
        log.info("BbsReplyServiceImpl >> bbs_idx : " + bbs_idx);
        List<BbsReplyVO> voList = bbsReplyMapper.list(bbs_idx);
        List<BbsReplyDTO> dtoList = null;
        if (voList != null) {
            dtoList = voList.stream().map(vo -> modelMapper.map(vo, BbsReplyDTO.class)).collect(Collectors.toList());
        }
        log.info("-------------------");
        return dtoList;
    }


}
