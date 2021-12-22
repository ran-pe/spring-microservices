package com.example.springoauth2server.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "member")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
}
