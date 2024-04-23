package org.fullstack4.springmvc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.common.CommonUtil;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private int total_count;
    private int page;
    private int page_size;
    private int page_skip_count;
    private int total_page;
    private int page_block_size;
    private int page_block_start;
    private int page_block_end;
    private boolean prev_page_flag;
    private boolean next_page_flag;
    private String[] search_type;
    private String search_type_st;
    private String search_word;
    private String search_data1;
    private String search_data2;
    private String linked_params;
    List<E> dtoList;
    PageResponseDTO() {}
    
    @Builder(builderMethodName = "withAll")  //생성자에 들어오는 값이 몇개가 되건 다 줘
    public PageResponseDTO(PageRequestDTO requestDTO, List<E> dtoList, int total_count) {
        log.info("-------------------");
        log.info("PageResponseDTO Start");
        this.total_count = total_count; // 전체 게시글 수
        this.page = requestDTO.getPage(); // 현재 페이지 번호
        this.page_size = requestDTO.getPage_size(); // 한 페이지에 표시될 게시글 수
        this.page_skip_count = (this.page-1) * this.page_size; //DB 조회해올 로우 시작 인덱스
        this.total_page = (this.total_count > 0) ? (int) Math.ceil(this.total_count / (double) this.page_size) : 1; // 총 페이지수
        this.page_block_size = requestDTO.getPage_block_size(); // 페이지네이션에서 페이징 최대 한번에 몇 개씩 할지
        this.page_block_start = ((int) Math.floor((((double)page - 1)*((double) 1/page_block_size)))*page_block_size)+1; // 현재 페이징의 시작 번호
        this.page_block_end = (page_block_start + (page_block_size-1)) <  total_page ? (page_block_start + (page_block_size-1)) : total_page;
        this.prev_page_flag = (this.page_block_start > 1); // 이전페이지 있는지 여부(페이지네이션에서 10개씩 이전 가는거)
        this.next_page_flag = (this.total_page > this.page_block_end); // 다음페이지 있는지 여부(페이지네이션에서 10개씩 다음 가는거)
        this.search_word = CommonUtil.parseString(requestDTO.getSearch_word());
        this.search_type =  requestDTO.getSearch_type();
        this.search_type_st = (search_type != null) ? Arrays.toString(search_type).replace("[","").replace("]","").replace(" ","") : "t,u";
        this.search_data1 = CommonUtil.parseString(requestDTO.getSearch_data1());
        this.search_data2 = CommonUtil.parseString(requestDTO.getSearch_data2());
        this.linked_params = "?page_size=" + this.page_size + "&search_type=" + search_type_st + "&search_word=" + this.search_word + "&search_data1=" + search_data1 + "&search_data2=" + search_data2;  // 쿼리스트링
        this.dtoList = dtoList;
        log.info("PageResponseDTO End");
        log.info("-------------------");
    }

    public int getTotal_page() {
        return (this.total_count > 0) ? (int) Math.ceil(this.total_count / (double) this.page_size) : 1;
    }
    public int getPage_skip_count() {
        return (this.page-1)*this.page_size;
    }
    public void setPage_block_start() {
        this.page_block_start = ((int)Math.floor(this.page/(double)this.page_block_size)*this.page_block_size)+1;
    }
    public void setPage_block_end() {
        this.page_block_end = ((int)Math.floor(this.page/(double)this.page_block_size)*this.page_block_size)+this.page_block_size;
    }
}
