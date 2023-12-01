package com.example.isaacwebproject.gameServer.miniprojectServer;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import data.DataClass;
import lombok.Data;

@Data

public class Server {
	private List<ObjectOutputStream> dataSendList;
	private int socket;
	private ServerSocket serverSocket;
	private int startTime;
	public Server() {
		this.socket = 8056;
		this.dataSendList = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	}
	public void start() {}
	public void ReceiveThread(Socket socket) {}
	public void sendData(DataClass sendDataClass, ObjectOutputStream objectOutputStream) {}
	
}
