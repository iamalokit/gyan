package com.gyan.javaconcurrency.multithreadedserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost", 1234)) {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner sc = new Scanner(System.in);
			String line = null;
			
			while(!"exit".equalsIgnoreCase(line)) {
				line = sc.nextLine();
				out.println(line);
				out.flush();
				System.out.println("Server replied "+ in.readLine());
			}
			
			sc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
