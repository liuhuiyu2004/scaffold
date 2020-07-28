package com.liuhuiyu.scaffold.service;

import com.liuhuiyu.scaffold.constant.enums.CommonEnum;
import com.liuhuiyu.scaffold.domain.entity.CO_Member;
import com.liuhuiyu.scaffold.repository.CO_MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;

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

    /**
     * 有效用户查询
     * @param account   用户信息（账号名/姓名）
     * @param pageable  分页信息
     * @return  分页信息
     */
    public Page<CO_Member> findInfoPage(String account, Pageable pageable) {
        Specification<CO_Member> specification = (Specification<CO_Member>) (root, criteriaQuery, criteriaBuilder) -> {
            String info = "%" + account + "%";
            Path<String> exp1 = root.get("account");
            Path<String> exp2 = root.get("cname");
            Path<String> exp3 = root.get("stateId");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(exp3, CommonEnum.CO_MEMBER_STATE_1.getCodeId()),
                    criteriaBuilder.or(criteriaBuilder.like(exp1, info), criteriaBuilder.like(exp2, info)));
        };
        return this.memberRepository.findAll(specification, pageable);
    }
}
