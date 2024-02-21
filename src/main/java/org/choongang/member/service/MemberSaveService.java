package org.choongang.member.service;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;

    public void join(RequestJoin form) {

    }

    public void save(Member member) {

        memberRepository.saveAndFlush(member);
    }

}
