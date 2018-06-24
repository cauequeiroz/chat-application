package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerManager implements Runnable {
	
	private Socket server;
	
	public ServerManager(Socket server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			
			Scanner serverMessages = new Scanner(server.getInputStream());
			while(serverMessages.hasNextLine()) {
				System.out.println(serverMessages.nextLine());
			}
			
		} catch (IOException e) {
			System.out.println("[Error] Client error: " + e.getMessage());
		}
	}
}
