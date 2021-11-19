package com.example.querydlssample.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainContorller {

    @Autowired
    StudyRepository studyRepository;

    @GetMapping("/search/study")
    @ResponseBody
    public List<StudyDto> searchStudy(String keyword, String startDt, String endDt) {
        return studyRepository.findByKeywordAndDate(keyword, startDt, endDt);
    }
}
