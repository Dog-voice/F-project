package com.final_project.TodayDessert.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "itemImg")
@Getter
@Setter
public class ItemImg {

    @Id
    @Column(name = "itemImgId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; // 이미지 파일명
    private String oriImgName;  // 원본 이미지 파일명
    private String imgUrl;  // 이미지 조회 경로
    private String repimgYn;    // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
