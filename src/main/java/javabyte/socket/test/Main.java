package javabyte.socket.test;

import kirchnersolutions.javabyte.driver.common.driver.DatabaseResults;
import kirchnersolutions.javabyte.driver.common.driver.Transaction;
import kirchnersolutions.javabyte.driver.common.utilities.ByteTools;
import kirchnersolutions.javabyte.driver.singleclient.SingleClient;

import java.io.File;
import java.util.Base64;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws  Exception{
        String[] connectInfo = new String[5];
        System.out.println("JavaByte Socket Terminal");
        System.out.println("");
        System.out.println("Version 1.0.01a");
        System.out.println("");
        Scanner in = new Scanner(System.in);
        System.out.println("Looking for connection file...");
        File connectDir = new File("Files/Connections");
        File connectFile = new File(connectDir,"/connection.txt");
        if(!connectDir.exists()){
            connectDir.mkdirs();
            connectFile.createNewFile();
            System.out.println("Connection file does exist, creating file...");
            System.out.println("Hostname?");
            String hostname = in.next();
            System.out.println("IP?");
            String ip = in.next();
            System.out.println("Port?");
            String port = in.next();
            System.out.println("Username?");
            String username = in.next();
            System.out.println("Password?");
            String password = in.next();
            hostname+= "," + ip + "," + port+ "," + username+ "," + password;
            connectInfo = hostname.split(",");
            ByteTools.writeBytesToFile(connectFile, Base64.getEncoder().encode(hostname.getBytes("UTF-8")));
            System.out.println("Connection file created");
        }else if(!connectFile.exists()){
            connectFile.createNewFile();
            System.out.println("Connection file does exist, creating file...");
            System.out.println("Hostname?");
            String hostname = in.next();
            System.out.println("IP?");
            String ip = in.next();
            System.out.println("Port?");
            String port = in.next();
            System.out.println("Username?");
            String username = in.next();
            System.out.println("Password?");
            String password = in.next();
            hostname+= "," + ip + "," + port+ "," + username+ "," + password;
            connectInfo = hostname.split(",");
            ByteTools.writeBytesToFile(connectFile, Base64.getEncoder().encode(hostname.getBytes("UTF-8")));
            System.out.println("Connection file created");
        }else{
            System.out.println("Loading connection file...");
            byte[] bytes = ByteTools.readBytesFromFile(connectFile);
            bytes = Base64.getDecoder().decode(bytes);
            String temp = new String(bytes, "UTF-8");
            connectInfo = temp.split(",");
            System.out.println("Done loading connection file");
        }
        SingleClient client = new SingleClient(connectInfo[0], connectInfo[1], Integer.parseInt(connectInfo[2]), connectInfo[3], connectInfo[4]);
        boolean exit = false;
        while(!exit){
            System.out.println("Main Menu");
            System.out.println("1. Create New Connection File");
            System.out.println("2. Logon");
            System.out.println("3. Send Command");
            System.out.println("4. Logout");
            System.out.println("0. Exit");
            String input = in.next();
            if(input.contains("1")){
                System.out.println("Hostname?");
                String hostname = in.next();
                System.out.println("IP?");
                String ip = in.next();
                System.out.println("Port?");
                String port = in.next();
                System.out.println("Username?");
                String username = in.next();
                System.out.println("Password?");
                String password = in.next();
                hostname+= "," + ip + "," + port+ "," + username+ "," + password;
                connectInfo = hostname.split(",");
                ByteTools.writeBytesToFile(connectFile, Base64.getEncoder().encode(hostname.getBytes("UTF-8")));
                System.out.println("Connection file created");
            }else if(input.contains("3")){
                System.out.println("Command?");
                input = in.next();
                input+= in.nextLine();
                Transaction transaction = new Transaction();
                transaction.setOperation(input);
                PrintResults.printResult(client.sendCommand(transaction));
                System.out.println("Results End");
            }else if(input.contains("4")){
                System.out.println("Logoff?(y|n)?");
                input = in.next();
                if(input.contains("y")){
                    client.logout();
                    System.out.println("Logged out successfully.");
                }
            }else if(input.contains("0")){
                System.out.println("Exit?(y|n)?");
                input = in.next();
                if(input.contains("y")){
                    client.logout();
                    exit = true;
                }
            }else if(input.contains("2")){
                if(client.logon()){
                    System.out.println("Logon successfully.");
                }else{
                    System.out.println("Logon unsuccessful.");
                    System.out.println("Update the connection file...");
                }
            }
        }
        System.out.println("Terminal is shutting down...");
    }
}
