package tk.yuqibit.nio.handlertest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by YuQi on 2017/5/12.
 */
public class InboundHander1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHander1");
        super.channelRead(ctx, msg);
    }
}
