package com.example.isaacwebproject.gameServer.miniprojectServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import com.example.isaacwebproject.config.SessionConfig;
import com.example.isaacwebproject.gameServer.battleroom.service.BattleRoomService;
import com.example.isaacwebproject.gameServer.mem.Service.servermemservice;
import com.example.isaacwebproject.gameServer.mem.Vo.memVo;
import data.DataClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
@RequiredArgsConstructor
public class ServerControl extends Server implements ApplicationRunner {
	private final servermemservice servermemservice;
	private final BattleRoomService battleRoomService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		start();
	}
	@Override
	public void start() {
		Socket socket = null;
		try {
			setServerSocket(new ServerSocket(getSocket()));
			while (true) {
				System.out.println("유저 대기중");
				socket = getServerSocket().accept();
				ReceiveThread(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (getServerSocket() != null) {
				try {
					getServerSocket().close();
					System.out.println("서버종료");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("서버에러");
				}
			}
		}
	}

	@Override
	public void ReceiveThread(Socket socket) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				OutputStream outputStream = null;
				InputStream inputStream = null;
				ObjectInputStream player_In_Data = null;
				ObjectOutputStream player_Out_Data = null;
				DataClass reciveDataClass = null;
				String name = null;
				try {
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
					player_Out_Data = new ObjectOutputStream(outputStream);
					player_In_Data = new ObjectInputStream(inputStream);
					System.out.println("실행중");
					getDataSendList().add(player_Out_Data);
					reciveDataClass = (DataClass) player_In_Data.readObject();
					System.out.println(reciveDataClass.toString());
					memVo mem = servermemservice.findById(reciveDataClass.getMem_Id());
					if(!servermemservice.pwmatch(reciveDataClass.getMem_pw(),mem.getPw())){
						reciveDataClass.setLogin_success(false);
					}else{
						reciveDataClass.setLogin_success(true);
					}
					if(battleRoomService.findNumByMemId(reciveDataClass.getMem_Id())!=null){
						reciveDataClass.setBattleRoomNum(battleRoomService.findNumByMemId(reciveDataClass.getMem_Id()));
						reciveDataClass.setClientName(mem.getNickname());
					}
					System.out.println(reciveDataClass.getClientName()+ "연결 성공");
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("데이터 가져오기 실패");
					e.printStackTrace();
				}
				try {
					while (true) {
						reciveDataClass = (DataClass) player_In_Data.readObject();
						System.out.println(reciveDataClass.toString());
						sendData(reciveDataClass, player_Out_Data);
					}
				} catch (IOException | ClassNotFoundException e) {
				} finally {
					try {
						System.out.println(name + "연결 종료");
						getDataSendList().remove(player_Out_Data);
						socket.close();
					} catch (IOException e) {
						System.out.println("클라이언트 강제 종료");
					}
				}
			}
		}).start();
	}

	@Override
	public void sendData(DataClass sendDataClass, ObjectOutputStream objectOutputStream) {

		for (ObjectOutputStream send : getDataSendList()) {
			if (!send.equals(objectOutputStream)) {
				try {
					send.writeObject(sendDataClass);
					send.reset();

				} catch (Exception e) {
					System.out.println("전송 종료");
				}

			}
		}
	}


}
