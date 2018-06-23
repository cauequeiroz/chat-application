package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager implements Runnable {
	
	private Socket client;
	
	public ClientManager(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			
			Scanner clientMessages = new Scanner(client.getInputStream());
			
			while (clientMessages.hasNextLine()) {
				System.out.println(clientMessages.nextLine());
			}
			
			clientMessages.close();
			client.close();
			
		} catch(IOException e) {
			System.out.println("[Error] Server error: " + e.getMessage());
		}
	}
}
