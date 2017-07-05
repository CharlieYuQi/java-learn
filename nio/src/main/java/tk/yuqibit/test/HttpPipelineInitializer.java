package tk.yuqibit.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.util.ArrayList;
import java.util.List;

public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private SSLContext sslContext = null;
    private static final int DEFAULT_MAX_CONTENT_LENGTH = 20480;
    private int maxContentLength = DEFAULT_MAX_CONTENT_LENGTH;
    private List<ChannelHandler> requestHandlers = new ArrayList<>();
    private EventExecutorGroup eventExecutorGroup = null;

    public HttpPipelineInitializer() {
        System.out.println();
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new LoggingHandler(LogLevel.ERROR));
        if (null != sslContext) {
            SSLEngine sslEngine = sslContext.createSSLEngine();
            sslEngine.setUseClientMode(false);
            SslHandler sslHandler = new SslHandler(sslEngine);
            pipeline.addLast("ssl", sslHandler);
        }

        // Inbound handlers
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("inflater", new HttpContentDecompressor());

        // Outbound handlers
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("chunkWriter", new ChunkedWriteHandler());
        pipeline.addLast("deflater", new HttpContentCompressor());

        // Aggregator MUST be added last, otherwise results are not correct
        pipeline.addLast("aggregator", new HttpObjectAggregator(maxContentLength));

        addAllHandlers(pipeline);
    }

    private void addAllHandlers(ChannelPipeline pipeline)
    {
        if (eventExecutorGroup != null)
        {
            for (ChannelHandler handler : requestHandlers)
            {
                pipeline.addLast(eventExecutorGroup, handler.getClass().getSimpleName(), handler);
            }
        }
        else
        {
            for (ChannelHandler handler : requestHandlers)
            {
                pipeline.addLast(handler.getClass().getSimpleName(), handler);
            }
        }
    }

    public HttpPipelineInitializer setMaxContentLength(int value)
    {
        this.maxContentLength = value;
        return this;
    }

    public HttpPipelineInitializer setSSLContext(SSLContext sslContext)
    {
        this.sslContext = sslContext;
        return this;
    }

    public HttpPipelineInitializer setExecutionHandler(EventExecutorGroup executorGroup)
    {
        this.eventExecutorGroup = executorGroup;
        return this;
    }

    public HttpPipelineInitializer addRequestHandler(ChannelHandler handler)
    {
        if (!requestHandlers.contains(handler))
        {
            requestHandlers.add(handler);
        }

        return this;
    }
    public SSLContext getSslContext() {
        return sslContext;
    }

    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }
}
