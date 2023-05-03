package com.example.Demo.NetServer;

import io.vertx.core.buffer.Buffer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class demo
{


  public static void main(String[] args) throws IOException, InterruptedException {

    Socket socket = new Socket("localhost" , 9999);
    DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
    DataInputStream din  = new DataInputStream(socket.getInputStream());

    //System.out.println(((int)'\u000E'));

    dout.write(("kesen ho babua" + '\0').getBytes(Charset.forName("UTF-16")));
    dout.flush();


    //Buffer buffer = Buffer.buffer(din.readByte());
    byte buffer[] = new byte[1024];
    //din.read(buffer);

    System.out.println(new String(din.readAllBytes() , Charset.forName("UTF-16")));
   // System.out.println(buffer.toString());

    dout.write("bye bye".getBytes(Charset.forName("UTF-16")));

    dout.flush();
    socket.close();

    char c  =  '\u000E';



  }
}
