package com.example.springtest.memo.entity;

import com.example.springtest.memo.dto.MemoRequestDto;
import com.example.springtest.memo.dto.MemoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Memo {
    private Long id;
    private String title;
    private String contents;
    public void updateMemo(MemoRequestDto dto){

        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
    public void updateTitle(MemoRequestDto dto){
        this.title = dto.getTitle();
    }
}
