package com.example.querydlssample.account;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface StudyRepositoryExtension {
    List<StudyDto> findByKeywordAndDate(String keyword, String startDt, String endDt);
}
