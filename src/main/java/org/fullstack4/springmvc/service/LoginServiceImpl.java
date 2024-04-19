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
        MemberVO LoginVO = loginMapper.login(user_id);
//        log.info("#################################" + LoginVO);
//        log.info("################################# user_id" + user_id);
//        log.info("################################# pwd" + pwd);
        MemberDTO loginDTO = null;
        if (LoginVO != null && user_id.equals(LoginVO.getUser_id()) && pwd.equals(LoginVO.getPwd())) {
            loginDTO = modelMapper.map(LoginVO, MemberDTO.class);
        }
        return loginDTO;
    }
    @Override
    public MemberDTO login(String user_id) {
        MemberVO LoginVO = loginMapper.login(user_id);
//        log.info("#################################" + LoginVO);
//        log.info("################################# user_id" + user_id);
//        log.info("################################# pwd" + pwd);
        MemberDTO loginDTO = null;
        if (LoginVO != null && user_id.equals(LoginVO.getUser_id())) {
            loginDTO = modelMapper.map(LoginVO, MemberDTO.class);
        }
        return loginDTO;
    }
}
