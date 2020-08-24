package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author：luzeping
 * @Date: 2020/1/6 20:23
 */
public class Server {

    public static void main(String[] args) {
        //NioEventLoop包含一个jdknio包下的Slector对象，并且持有一个线程，这个线程除了做select()操作外，还能处理其他任务。
        //根据是否有任务，他会选择是selectNow()还是阻塞select();NioEventLoopGroup从名字就能看出包含多个NioEventLoop
        //不带参数构造，默认是逻辑处理器的两倍数量。
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(32);
        try {
            //服务端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    //要启动的Channel类型
                    .channel(NioServerSocketChannel.class)
                    //option是给serversocketchannel配置的
                    .option(ChannelOption.SO_BACKLOG, 1000)
                    //childOption是给socketchannel配置的
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //给socketchannel添加
                    .childHandler(new SocketChannelInitializer());
            serverBootstrap.bind(8000).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
