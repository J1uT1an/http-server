package com.luxintong;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @projectName: <h3>httpserver</h3>
 * @package: PACKAGE_NAME
 * @className: com.luxintong.demo.HttpServer
 * @author: Lu Xintong
 * @description <p>com.luxintong.HttpServer</p>
 * @date: 2023-12-12 14:58
 * @version: 1.0
 */
public class HttpServer {
	public static void main(String[] args) {
		try {
			// 侦听8080端口
			ServerSocket server = new ServerSocket(8080);

			System.out.println("Info:Server start," + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			while (true) {
				// 接收客户端请求数据
				Socket socket = server.accept();

				// 读取
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 写入
				BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				String line = input.readLine();

				while (line != null && !line.isEmpty()) {
					System.out.println(line);
					line = input.readLine();
				}
				output.write("HTTP/1.1 200 OK\r\n");
				output.write("Content-type:text/plain\r\n");
				output.write("\r\n");
				output.write("Success!!" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "\r\n");
				output.flush();

				//必须关闭
				input.close();
				output.close();

				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error binding the specified port.");
		}
	}
}
