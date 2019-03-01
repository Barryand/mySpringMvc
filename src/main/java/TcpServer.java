import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String [] args) throws IOException
    {
        @SuppressWarnings("resource")
        ServerSocket server=new ServerSocket(8000);
        System.out.println(server.getLocalSocketAddress());

        Socket s=null;
        DataOutputStream dataOut=null;
        DataInputStream  dataIn=null;
        while(true){
            try{
                s=server.accept();
                dataOut=new DataOutputStream(s.getOutputStream());
                dataIn =new DataInputStream(s.getInputStream());
                while(true){
                    Byte c=dataIn.readByte();
                    if(c=='0'){
                        System.out.println("执行继电器快关变换！");
                        dataOut.writeByte('Y');
                    }else if(c=='w'){
                        System.out.println("将温度值传给服务器！");
                        dataOut.writeByte(2);
                    }else if(c=='s'){
                        System.out.println("将深度值传给服务器！");
                        dataOut.writeByte(4);
                    }else{
                        System.out.println("错误命令返回！");
                        dataOut.writeByte('N');
                    }
                }
            }catch(IOException e){}
            dataOut.close();
            dataIn.close();
            s.close();
        }
    }
}
