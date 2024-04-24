package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.BbsReplyDTO;

import java.util.List;


public interface BbsReplyServiceIf {
    int regist(BbsReplyDTO bbsReplyDTO);
    int update_replyCnt(int bbs_idx);
    List<BbsReplyDTO> list(int bbs_idx);
}

