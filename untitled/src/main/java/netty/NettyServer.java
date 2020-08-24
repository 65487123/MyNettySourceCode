package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @Author：luzeping
 * @Date: 2020/2/21 12:02
 */
public class NettyServer {
    private static NioEventLoopGroup bossGroup;
    private static NioEventLoopGroup workGroup;
    private static ServerBootstrap serverBootstrap = new ServerBootstrap();

    static {
        startServer(8297);
    }

    static void startServer(int port){
        bossGroup = new NioEventLoopGroup(1);
        workGroup =new NioEventLoopGroup(1);
        try {
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder()).addLast(new StringEncoder()).addLast(new RpcService());
                }
            });
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (Exception e){
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
