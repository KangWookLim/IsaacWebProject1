package com.example.isaacwebproject.battle.controller;

import com.example.isaacwebproject.battle.service.BattleService;
import com.example.isaacwebproject.battle.vo.BattleRoom;
import com.example.isaacwebproject.inven.service.InvenService;
import com.example.isaacwebproject.inven.vo.InvenVO;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ws")
public class BattleController {
    private final BattleService battleService;
    private final InvenService invenService;
    private List<BattleRoom> rooms = Collections.synchronizedList(new ArrayList<>());

    @RequestMapping("/makerooms")
    @ResponseBody
    public synchronized void makeRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        List<BattleRoom> reporoom = battleService.findAllRoom();
        for (BattleRoom room : reporoom){
            if(room.getMem1_Id().equals(memId)||room.getMem2_Id().equals(memId)){
                throw new RuntimeException();
            }
        }
        BattleRoom room = new BattleRoom();
        room.setRoom_num(rooms.size() + 1);
        room.setMem1_Id(memId);
        rooms.add(room);
    }

    @RequestMapping("/checkroom")
    @ResponseBody
    public List<BattleRoom> connectRoom() throws InterruptedException {
        int oldSessionLen = rooms.size();
        long starttime = System.currentTimeMillis();
        while (true) {
            int curruntSessionLen = rooms.size();
            int time = (int) (System.currentTimeMillis() - starttime) / 1000;
            if (time == 10) {
                break;
            }
            if (oldSessionLen != curruntSessionLen) {
                break;
            }
            Thread.sleep(500);
        }
        return rooms;
    }

    @RequestMapping("/getrooms")
    @ResponseBody
    public List<BattleRoom> getRoom() {
        return rooms;
    }

    @RequestMapping("/exitroom")
    @ResponseBody
    public synchronized void deleteRoom(HttpServletRequest request) throws NullPointerException {
        HttpSession session = request.getSession();
        int oldLen = rooms.size();
        int removedroomindex = 0;
        String memId = (String) session.getAttribute("userInfo");
        for (BattleRoom room : rooms) {
            if(room.getMem1_Id()!=null && room.getMem1_Id().equals(memId)){
                if(room.getMem2_Id()==null){
                    removedroomindex = room.getRoom_num()-1;
                    rooms.remove(room);
                    break;
                }else{
                    room.setMem1_Id(null);
                    room.setMEM1_USE_ITEM_ID(0);
                    break;
                }
            } else if (room.getMem2_Id()!=null && room.getMem2_Id().equals(memId)){
                if(room.getMem1_Id()==null){
                    removedroomindex = room.getRoom_num()-1;
                    rooms.remove(room);
                    break;
                }else {
                    room.setMem2_Id(null);
                    room.setMEM2_USE_ITEM_ID(0);
                    break;
                }
            }
        }
        if(oldLen!=rooms.size()){
            for(int i = removedroomindex; i<rooms.size(); i++){
                rooms.get(i).setRoom_num(i+1);
            }
        }
    }

    @RequestMapping("/getroominfo")
    @ResponseBody
    public List<BattleRoom> getRoomInfos() {
        return rooms;
    }

    @RequestMapping("/checkroominfo")
    @ResponseBody
    public BattleRoom checkRoomInfos(@RequestBody BattleRoom room, HttpServletRequest request) throws InterruptedException {
        String memId = (String) request.getSession().getAttribute("userInfo");
        int room_num = room.getRoom_num()-1;
        long starttime = System.currentTimeMillis();
        boolean isStarted = true;

        if(room.getMem2_Id()!=null && room.getMem1_Id()!=null && room.getMEM1_USE_ITEM_ID() != 0 && room.getMEM2_USE_ITEM_ID() != 0){
            isStarted=false;
            return rooms.get(room_num);
        }
        while (isStarted) {
            int time = (int) (System.currentTimeMillis() - starttime) / 1000;
            if(!rooms.isEmpty()){
                if(!room.equals(rooms.get(room_num))){
                    break;
                }
            }else{
                return new BattleRoom();
            }
            if (time == 10) {
                break;
            }
            Thread.sleep(500);
        }
        return rooms.get(room_num);
    }

    @GetMapping("/intoroom")
    @ResponseBody
    public void intoroom(@RequestParam int room_num, HttpServletRequest request) {
        String mem_id = (String) request.getSession().getAttribute("userInfo");
        if(rooms.get(room_num-1).getMem2_Id()==null){
            rooms.get(room_num-1).setMem2_Id(mem_id);
        } else if (rooms.get(room_num-1).getMem1_Id()==null){
            rooms.get(room_num-1).setMem1_Id(mem_id);
        }
    }

    @RequestMapping("/meminveninfo")
    @ResponseBody
    public List<InvenVO> meminveninfo(HttpServletRequest request){
        String principal_Mem_Id = (String) request.getSession().getAttribute("userInfo");
        return invenService.findElementsByMemid(principal_Mem_Id);
    }
    @RequestMapping("/ready_game")
    @ResponseBody
    public void intertitem(@RequestParam(name = "itemId")int itemId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String Principal = (String) session.getAttribute("userInfo");
        for(BattleRoom room : rooms){
            if(room.getMem1_Id()==Principal){
                room.setMEM1_USE_ITEM_ID(itemId);
            }
            if(room.getMem2_Id()==Principal){
                room.setMEM2_USE_ITEM_ID(itemId);
            }
        }
    }
    @RequestMapping("/start_game")
    @ResponseBody
    public void startGame(@RequestParam(name = "mem_id")String memId,HttpServletRequest request){
        for(BattleRoom room : rooms){
                if(room.getMem1_Id().equals(memId)){
                    battleService.addBattleRoom(room);
                }
        }
    }
}
