package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.dto.PageResponseDTO;

import java.util.List;


public interface BbsServiceIf {
    int regist(BbsDTO bbsDTO);

    List<BbsDTO> listAll();
    BbsDTO view(int idx);
    int modify(BbsDTO bbsDTO);
    int delete(int idx);

    int bbsTotalCount(PageRequestDTO requestDTO);
    PageResponseDTO<BbsDTO> bbsListByPage(PageRequestDTO pageRequestDTO);
}

