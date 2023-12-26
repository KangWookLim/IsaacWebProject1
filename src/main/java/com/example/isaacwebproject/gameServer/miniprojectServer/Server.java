package com.example.isaacwebproject.gameServer.miniprojectServer;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.example.isaacwebproject.config.SessionConfig;
import data.DataClass;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class Server {
	private Map<ObjectOutputStream,DataClass> dataSendMap = Collections.synchronizedMap(new HashMap<>());
	private int socket = 8056;
	private ServerSocket serverSocket;
	private int startTime;

	public void start() {}
	public void ReceiveThread(Socket socket) {}
	public void sendData(DataClass sendDataClass, ObjectOutputStream objectOutputStream) {}
	
}
