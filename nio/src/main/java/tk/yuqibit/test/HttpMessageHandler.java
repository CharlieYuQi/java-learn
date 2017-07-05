package tk.yuqibit.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by YuQi on 2017/5/18.
 */
@ChannelHandler.Sharable
public class HttpMessageHandler extends SimpleChannelInboundHandler<HttpMessage> {
    private static Logger logger = LoggerFactory.getLogger(HttpMessageHandler.class);
    private static final AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpMessage msg) throws Exception {
        HttpRequest request = (HttpRequest) msg;
        if (!request.method().equals(HttpMethod.GET)) {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        increment();
        sendOK(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        decrement();
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        decrement();
        sendError(ctx, HttpResponseStatus.BAD_REQUEST);
    }

    private void sendOK(ChannelHandlerContext ctx) {
        ByteBufAllocator allocator = ctx.channel().alloc();
        ByteBuf byteBuf = allocator.buffer();
        byteBuf.writeCharSequence("OK", Charset.forName("UTF-8"));
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                HttpResponseStatus.OK, byteBuf);
        response.headers().add(CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().add(CONNECTION, KEEP_ALIVE);
        ctx.writeAndFlush(response);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        ByteBufAllocator allocator = ctx.channel().alloc();
        ByteBuf byteBuf = allocator.buffer();
        byteBuf.writeCharSequence("Failed", Charset.forName("UTF-8"));
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, byteBuf);
        response.headers().add(CONTENT_TYPE, "text/plain; charset=UTF-8");

        // Close the connection as soon as the error message is sent.
        ctx.channel().write(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void increment() {
        logger.info("online user "+ count.incrementAndGet());
    }

    private void decrement() {
        if (count.get() <= 0) {
            logger.info("~online user 0");
        } else {
            logger.info("~online user " + count.decrementAndGet());
        }
    }
}
