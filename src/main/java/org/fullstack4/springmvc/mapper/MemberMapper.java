package org.fullstack4.springmvc.mapper;

import org.fullstack4.springmvc.domain.MemberVO;

public interface MemberMapper {
    int join(MemberVO memberVO);
    MemberVO select(String user_id);
    int update(MemberVO memberVO);
    int leave(String user_id);
}
