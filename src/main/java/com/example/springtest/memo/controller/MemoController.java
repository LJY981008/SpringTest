package com.example.springtest.memo.controller;

import com.example.springtest.memo.dto.MemoRequestDto;
import com.example.springtest.memo.dto.MemoResponseDto;
import com.example.springtest.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto){
        Long memoId = memoList.isEmpty() ? 1L : Collections.max(memoList.keySet()) + 1;

        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());
        memoList.put(memoId, memo);

        return new MemoResponseDto(memo);
    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id){
        Memo memo = memoList.get(id);

        return new MemoResponseDto(memo);
    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(@PathVariable Long id,
                                          @RequestBody MemoRequestDto dto){
        Memo memo = memoList.get(id);
        memo.updateMemo(dto);

        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id){
        memoList.remove(id);
    }
}

