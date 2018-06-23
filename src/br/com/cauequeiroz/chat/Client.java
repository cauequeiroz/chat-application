package br.com.cauequeiroz.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		System.out.println("Chat Application (Client)\n\n");
		
		try {
		
			Socket client = new Socket("127.0.0.1", 8080);
			System.out.println("[Client] Client connected!");
			
			PrintStream clientMessages = new PrintStream(client.getOutputStream());
			
			Scanner keyboard = new Scanner(System.in);
			
			while (keyboard.hasNextLine()) {
				String message = keyboard.nextLine();
				clientMessages.println(message);				
			}
			
			keyboard.close();
			clientMessages.close();
			client.close();			
			
		} catch (IOException e) {
			System.out.println("[Error] Client error: " + e.getMessage());
		}
		
	}
}
