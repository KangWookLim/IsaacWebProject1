package com.example.isaacwebproject.websocket.chat.Controller;

import com.example.isaacwebproject.battle.service.BattleService;
import com.example.isaacwebproject.battle.vo.BattleVO;
import com.example.isaacwebproject.error.exception.AlreadyUseSockectException;
import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.example.isaacwebproject.websocket.Handler.CustomWebSocketHandler;
import com.example.isaacwebproject.websocket.chat.service.ChatService;
import com.example.isaacwebproject.websocket.chat.vo.ChatVo;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final BattleService battleService;
    private final CustomWebSocketHandler customWebSocketHandler;
    @GetMapping("/chat")
    public synchronized ModelAndView chatHome(HttpServletRequest request){
        List<ChatVo> chatLogs = chatService.findAllChat();
        List<BattleVO> roomList = battleService.findAllRoom();
        ModelAndView view = new ModelAndView();
        view.addObject("chatLogs", chatLogs);
        view.addObject("roomList", roomList);
        view.addObject("CurrentURI",request.getRequestURI());
        HttpSession session = request.getSession();
        String memId = (String)session.getAttribute("userInfo");
        Integer memCoin = (Integer)session.getAttribute("userCoin");
        Integer memExp = (Integer)session.getAttribute("userExp");
        if(memId == null) {
            throw new DoNotLoginException();
        }else{
            customWebSocketHandler.setHttpSession(request.getSession());
            if(customWebSocketHandler.getSocket_HttpSessionBindMap().containsKey(session)){
                throw new AlreadyUseSockectException();
            }else{
                view.setViewName("views/chat");
                return view;
            }
        }
    }
    @RequestMapping("/ws/sessionrefresh")
    @ResponseBody
    public Set<Object> connectSession(HttpServletRequest request){
        int oldSessionLen = customWebSocketHandler.getConnectedUsers().size();
        while(true){
            int curruntSessionLen = customWebSocketHandler.getConnectedUsers().size();
            if(oldSessionLen!=curruntSessionLen){
                break;
            }
        }
        return customWebSocketHandler.getConnectedUsers();
    }
    @RequestMapping("/ws/checkroom")
    @ResponseBody
    public List<BattleVO> connectRoom(HttpServletRequest request) {
        int oldSessionLen = battleService.findAllRoom().size();
        while(true){
            int curruntSessionLen = battleService.findAllRoom().size();
            if(oldSessionLen!=curruntSessionLen){
                break;
            }
        }
        return battleService.findAllRoom();
    }

    @RequestMapping("/ws/getrooms")
    @ResponseBody
    public List<BattleVO> getRoom(HttpServletRequest request){
        return battleService.findAllRoom();
    }

    @RequestMapping("/ws/getsessions")
    @ResponseBody
    public Set<Object> getSession(HttpServletRequest request){
        return customWebSocketHandler.getConnectedUsers();
    }
}
