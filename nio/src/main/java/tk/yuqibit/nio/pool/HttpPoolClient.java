package tk.yuqibit.nio.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.net.InetSocketAddress;

/**
 * Created by YuQi on 2017/7/31.
 */
public class HttpPoolClient {
    final EventLoopGroup group = new NioEventLoopGroup();
    private InetSocketAddress remoteaddress = null;
    final Bootstrap strap = new Bootstrap();
    InetSocketAddress addr1 = new InetSocketAddress("127.0.0.1", 8080);
    InetSocketAddress addr2 = new InetSocketAddress("10.0.0.11", 8888);

    ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;

    public void connect(int port, String host) throws Exception {
        strap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);
        remoteaddress = InetSocketAddress.createUnresolved(host, port);
        strap.remoteAddress(remoteaddress);
        poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
            @Override
            protected SimpleChannelPool newPool(InetSocketAddress key) {
                return new FixedChannelPool(strap, new HttpChannelPoolHandler(), 2);
            }
        };

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        HttpPoolClient client = new HttpPoolClient();
        client.connect(port, "127.0.0.1");
        final String ECHO_REQ = "Hello Netty.$_";
        for (int i = 0; i < 10; i++) {
            // depending on when you use addr1 or addr2 you will get different pools.
            final SimpleChannelPool pool = client.poolMap.get(client.addr1);
            Future<Channel> f = pool.acquire();
            f.addListener(new FutureListener<Channel>() {
                @Override
                public void operationComplete(Future<Channel> f) {
                    if (f.isSuccess()) {
                        Channel ch = f.getNow();
                        ch.writeAndFlush(ECHO_REQ);

                        // Release back to pool
                        pool.release(ch);
                    }
                }
            });
        }
    }
}
