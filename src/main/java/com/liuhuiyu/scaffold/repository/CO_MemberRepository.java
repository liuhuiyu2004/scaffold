package com.liuhuiyu.scaffold.repository;

import com.liuhuiyu.scaffold.domain.entity.CO_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 14:58
 */
public interface CO_MemberRepository extends JpaRepository<CO_Member, Integer>, JpaSpecificationExecutor<CO_Member> {
}
