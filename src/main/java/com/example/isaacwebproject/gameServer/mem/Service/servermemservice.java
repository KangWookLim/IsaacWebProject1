package com.example.isaacwebproject.gameServer.mem.Service;

import com.example.isaacwebproject.gameServer.mem.Repo.Servermemrepo;
import com.example.isaacwebproject.gameServer.mem.Vo.memVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class servermemservice {
    private final Servermemrepo servermemrepo;
    private final PasswordEncoder passwordEncoder;

    public memVo findById(String id) {
        Optional<memVo> opmem = this.servermemrepo.findById(id);
        return opmem.orElse(null);
    }

    public boolean pwmatch(String lowpw, String encryptionpw) {
        return passwordEncoder.matches(lowpw,encryptionpw);
    }

    public void SingleincreaseCoinById(String id){
        servermemrepo.SingleincreaseCoinById(id);
    }

    public void multiIncreaseCoinById(String id){
        servermemrepo.multiIncreaseCoinById(id);
    }
}
