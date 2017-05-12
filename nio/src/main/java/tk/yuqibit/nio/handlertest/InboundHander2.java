package tk.yuqibit.nio.handlertest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by YuQi on 2017/5/12.
 */
public class InboundHander2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHander2");
        String currentTime = new java.util.Date(System.currentTimeMillis()).toString() + " InboundHander2";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
        super.channelRead(ctx, msg);
    }
}
