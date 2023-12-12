package com.example.isaacwebproject.inven.service;

import com.example.isaacwebproject.inven.mapper.InvenMapper;
import com.example.isaacwebproject.inven.repo.InvenRepo;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvenService {

    private final MemberMapper memberMapper;
    private final InvenRepo invenRepo;
    private final InvenMapper mapper;

    public List<InvenVO> getInvenData(String ID) throws Exception {
        List<InvenVO> inventory = mapper.getInvenData(ID);
        return inventory;
    }
    public List<InvenVO> findElementsByMemid(String ID){
        return invenRepo.findElementsByMemid(ID);
    }






}
