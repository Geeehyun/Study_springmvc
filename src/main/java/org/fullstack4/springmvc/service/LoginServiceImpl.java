package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.mapper.LoginMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginServiceIf {
    private final LoginMapper loginMapper;
    private final ModelMapper modelMapper;
    @Override
    public MemberDTO login(String user_id, String pwd) {
        MemberVO memberVO = loginMapper.login(user_id);
        MemberDTO memberDTO = null;
        if (memberVO != null) {
            memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        }
        return memberDTO;
    }
}