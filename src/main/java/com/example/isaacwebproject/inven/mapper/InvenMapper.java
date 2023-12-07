package com.example.isaacwebproject.inven.mapper;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvenMapper {
//    InvenVO.Inventory getInvenData(Map<String, Object> param) throws Exception;
    List<InvenVO> getInvenData(@Param("memId")String memId) throws Exception;
}
