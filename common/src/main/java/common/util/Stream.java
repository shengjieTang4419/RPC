package common.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 14:20
 * @description：
 * @modified By：
 * @version: $
 */
public class Stream {
    public static void closeStream(ObjectInputStream stream){
        if(stream!=null){
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStream(ObjectOutputStream stream){
        if(stream!=null){
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStock(ServerSocket stock){
        if (stock!=null){
            try {
                stock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStock(Socket stock){
        if (stock!=null){
            try {
                stock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
