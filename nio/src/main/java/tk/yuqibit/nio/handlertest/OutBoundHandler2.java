package tk.yuqibit.nio.handlertest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by YuQi on 2017/5/12.
 */
public class OutBoundHandler2 extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
            throws Exception {
        System.out.println("OutBoundHandler2");
        super.write(ctx, msg, promise);
    }
}
