package com.example.querydlssample.account;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StudyRepositoryExtensionImpl extends QuerydslRepositorySupport implements StudyRepositoryExtension {
    public StudyRepositoryExtensionImpl() {
        super(Study.class);
    }

    @Override
    public List<StudyDto> findByKeywordAndDate(String keyword, String startDt, String endDt) {

        QStudy study = QStudy.study;
        QAccount account = QAccount.account;
        JPQLQuery<StudyDto> query = from(study)
                .select(Projections.fields(StudyDto.class,
                        study.id, study.title, study.keyword, study.measureDt, study.user_id, account.username))
                .where(study.title.containsIgnoreCase(keyword)
                        .and(study.measureDt.between(startDt, endDt)))
                .join(account).on(study.user_id.eq(account.id));
        return query.fetch();
    }
}
