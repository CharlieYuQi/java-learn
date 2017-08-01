package tk.yuqibit.nio.pool;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by YuQi on 2017/7/31.
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
    static AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActived");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println(count.getAndIncrement() + ":" + body);
        ctx.writeAndFlush("Welcome to Netty.$_");
    }
}
