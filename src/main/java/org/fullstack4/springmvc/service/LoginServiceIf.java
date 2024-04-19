package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.MemberDTO;

import java.util.List;


public interface LoginServiceIf {
    MemberDTO login(String user_id, String pwd);
    MemberDTO login(String user_id);
}

