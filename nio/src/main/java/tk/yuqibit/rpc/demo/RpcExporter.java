package tk.yuqibit.rpc.demo;


import org.apache.commons.io.IOUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by YuQi on 2017/9/5.
 */
public class RpcExporter {
    static Executor  executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void exporter(String hostName,int port) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName,port));
        try {
            while (true){
                executor.execute(new ExporterTask(server.accept()));
            }
        }
        finally {
            server.close();
        }
    }

    private static class ExporterTask implements Runnable
    {
        Socket client = null;

        public ExporterTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                Object[] arguments = (Object[])input.readObject();
                Method method = service.getMethod(methodName,parameterTypes);
                Object result = method.invoke(service.newInstance(),arguments);
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(output);
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(client);
            }

        }
    }
}
