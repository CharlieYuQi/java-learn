package tk.yuqibit.rpc.demo;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by YuQi on 2017/9/5.
 */
public class RpcBoot {
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost",8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        RpcImporter<EchoService> importer = new RpcImporter<>();
        EchoService echoService = importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8088));
        System.out.println(echoService.echo("Yuqi"));
    }
}
