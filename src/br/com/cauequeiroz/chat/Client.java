package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		System.out.println("Chat Application (Client)\n");
		
		System.out.println("Type your nickname:");
		String nickname = new Scanner(System.in).nextLine().toLowerCase();
		System.out.println("----------\n");
		
		try {
		
			Socket client = new Socket("127.0.0.1", 8080);
			
			ServerManager serverManager = new ServerManager(client);
			new Thread(serverManager).start();
			
			PrintStream clientMessages = new PrintStream(client.getOutputStream());
			
			String welcomeMessage = "[Server] " + nickname + " joined the chat.";
			clientMessages.println(welcomeMessage);
			
			Scanner keyboard = new Scanner(System.in);
			
			while (keyboard.hasNextLine()) {
				String message = "[" + nickname + "] " + keyboard.nextLine();
				clientMessages.println(message);				
			}
			
			keyboard.close();
			
		} catch (IOException e) {
			System.out.println("[Error] Client error: " + e.getMessage());
		}
		
	}
}
