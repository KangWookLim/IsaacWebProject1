package com.example.isaacwebproject.inven.service;

import com.example.isaacwebproject.inven.mapper.InvenMapper;
import com.example.isaacwebproject.inven.repo.InvenRepo;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.RowMapper;
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

    public List<InvenVO> getAllInvenDatas(){
        return invenRepo.getAllInvenDatas();
    }
    public List<InvenVO> findElementsByMemid(String ID){
        return invenRepo.findElementsByMemid(ID);
    }

    public InvenVO findElementByMemIDandItemId(String ID,int itemsId){
        return invenRepo.findElementByMemIDandItemId(ID,itemsId);
    }

    public void InsertItem(String memId, int itemsId, int amount){
        invenRepo.InsertItem(memId, itemsId, amount);
    }

    public void UpdateItem(String memId, int itemsId, int amount) {
        invenRepo.UpdateItem(memId, itemsId, amount);
    }

    public void DeleteItem(String memId, int itemsId){
        invenRepo.DeleteItem(memId, itemsId);
    }

    public boolean checkItem(String memId, int itemsId){
        return invenRepo.CheckItem(memId, itemsId);
    }
}
