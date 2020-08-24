package netty.example;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @Author：luzeping
 * @Date: 2020/1/2 9:58
 */
public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel)  {
        ChannelPipeline pipeline = channel.pipeline();
        //pipeline.addLast("MyhttpServerCodec",new HttpServerCodec());
        //pipeline.addLast("MyHttpServerHandler",new ChannelHandler());
        pipeline.addLast(new SendPackageHandler());
    }
}
