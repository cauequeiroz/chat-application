package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		System.out.println("Chat Application (Server)\n\n");
		
		try {
			
			ServerSocket server = new ServerSocket(8080);
			System.out.println("[Server] Server running at port 8080...");
			
			while(true) {
				Socket client = server.accept();
				System.out.println("[Server] Client connected!");
				
				ClientManager clientManager = new ClientManager(client);
				
				new Thread(clientManager).start();
			}
			
		} catch (IOException e) {
			System.out.println("[Error] Server error: " + e.getMessage());
		}
		
	}
}
