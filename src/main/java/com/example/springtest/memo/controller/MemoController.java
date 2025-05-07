package com.example.springtest.memo.controller;

import com.example.springtest.memo.customexception.ExceptionTestEnum;
import com.example.springtest.memo.customexception.TestException;
import com.example.springtest.memo.dto.MemoRequestDto;
import com.example.springtest.memo.dto.MemoResponseDto;
import com.example.springtest.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
        Long memoId = memoList.isEmpty() ? 1L : Collections.max(memoList.keySet()) + 1;

        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());
        memoList.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping
    public Collection<Memo> entireMemoList() {
        return memoList.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
        Memo memo = memoList.get(id);

        if (memo == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoById(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        Memo memo = memoList.get(id);
        if (memo.getContents() == null || memo.getTitle() == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        memo.updateMemo(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoTitleById(@PathVariable Long id, @RequestBody MemoRequestDto dto) throws TestException {
        Memo memo = memoList.get(id);

        if (memo == null) {
            throw new TestException(ExceptionTestEnum.Exceptions.TESTA, HttpStatus.NOT_FOUND, "테스트예외 발생");
        }
        if (dto.getTitle() == null || dto.getContents() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        memo.updateTitle(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        if (memoList.containsKey(id)) {
            memoList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

