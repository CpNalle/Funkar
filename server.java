package funkar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server
{
	public static void main(String[] args)
	{
		

		try { 
				ServerSocket serverSocket = new ServerSocket(8000);
				System.out.println("Starting server");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Player 1 connected");
				Socket clientSocket2 = serverSocket.accept();
				System.out.println("Player 2 connected");
				PrintWriter out =
				new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out2 =
				new PrintWriter(clientSocket2.getOutputStream(), true);
				BufferedReader in2 = new BufferedReader(
					new InputStreamReader(clientSocket2.getInputStream()));
				while(true)
				{
					String userInput = in.readLine();
					out2.println(userInput);
					String userInput2 = in2.readLine();
					out.println(userInput2);
				}
		} catch (Exception e) {
			System.out.println("Serverside error");
		}
	}
}
