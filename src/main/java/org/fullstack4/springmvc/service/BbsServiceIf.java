package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.BbsDTO;

import java.util.List;


public interface BbsServiceIf {
    int regist(BbsDTO bbsDTO);

    List<BbsDTO> listAll();
    BbsDTO view(int idx);
    int modify(BbsDTO bbsDTO);
}

