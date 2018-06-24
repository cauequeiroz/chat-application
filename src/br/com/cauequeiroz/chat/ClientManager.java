package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientManager implements Runnable {
	
	private Socket client;
	private static List<PrintStream> clients = new ArrayList<>();
		
	public ClientManager(Socket client) throws IOException {
		this.client = client;
		ClientManager.clients.add(new PrintStream(client.getOutputStream()));
	}
	
	@Override
	public void run() {
		try {
			
			Scanner clientMessages = new Scanner(client.getInputStream());
			
			while (clientMessages.hasNextLine()) {
				ClientManager.notifyAllClients(clientMessages.nextLine());
			}
			
			clientMessages.close();
			client.close();
			
		} catch(IOException e) {
			System.out.println("[Error] Server error: " + e.getMessage());
		}
	}
	
	private static void notifyAllClients(String message) {
		for (PrintStream client : ClientManager.clients) {
			client.println(message);
		}
	}
}
