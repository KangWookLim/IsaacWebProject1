package com.example.isaacwebproject.member.repo;

import com.example.isaacwebproject.member.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJPARepo extends JpaRepository<Member, Integer> {
}
