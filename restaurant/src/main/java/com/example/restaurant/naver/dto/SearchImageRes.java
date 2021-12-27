package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    // Naver Developer의 출력 결과에서 원하는 것들을 가져올 것이다.
    private String lastBuildDate;       // 검색 결과 생성 시간
    private int total;                  // 검색 결과 문서의 총 개수
    private int start;                  // 검색 결과 문서의 시작점
    private int display;                // 검색된 검색 결과의 개수
    private List<SearchImageItem> items;// item들은 List형태로 되어있기 때문에 이를 다같이 SerachImageItem으로 받아온다. -> 그리고 이름은 items로 맞춰준다.



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title;           // 업체, 기관명
        private String link;            // 링크
        private String thumbnail;       // 썸넬
        private String sizeheight;      // 이미지 크기 높이, 너비 가져오기
        private String sizewidth;
    }
}
