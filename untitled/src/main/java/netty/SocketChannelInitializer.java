package netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @Author：luzeping
 * @Date: 2020/1/6 20:39
 */
public class SocketChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast("handler1",new Handler());
    }
}
