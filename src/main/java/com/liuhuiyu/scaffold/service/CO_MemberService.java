package com.liuhuiyu.scaffold.service;

import com.liuhuiyu.scaffold.repository.CO_MemberRepository;
import org.springframework.stereotype.Service;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 14:59
 */
@Service
public class CO_MemberService {
    private com.liuhuiyu.scaffold.repository.CO_MemberRepository memberRepository;

    public CO_MemberService(CO_MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
