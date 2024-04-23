package org.fullstack4.springmvc.mapper;

import org.fullstack4.springmvc.domain.BbsReplyVO;

import java.util.List;

public interface BbsReplyMapper {
    int regist(BbsReplyVO bbsReplyVO);
    int update_replyCnt(int bbs_idx);
    List<BbsReplyVO> list(int bbs_idx);
}
