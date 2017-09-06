package tk.yuqibit.rpc.demo;

import org.apache.commons.io.IOUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by YuQi on 2017/9/5.
 */
public class RpcImporter<S> {
    public S importer(final Class<?> serviceClass, final InetSocketAddress addr) {
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[] { serviceClass.getInterfaces()[0] }, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try {
                            socket = new Socket();
                            socket.connect(addr);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceClass.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();

                        } finally {
                            IOUtils.closeQuietly(socket);
                            IOUtils.closeQuietly(output);
                            IOUtils.closeQuietly(output);
                        }
                    }
                });
    }
}
