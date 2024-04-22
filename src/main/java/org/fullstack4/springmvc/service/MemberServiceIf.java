package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;


public interface MemberServiceIf {
    int join(MemberDTO memberDTO);
    MemberDTO select(String user_id);
    int update(MemberDTO memberDTO);
    int leave(String user_id);
    int idCheck(String user_id);

}

