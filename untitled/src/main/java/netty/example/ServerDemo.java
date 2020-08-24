package netty.example;



/**
 * @Author：luzeping
 * @Date: 2019/12/26 15:29
 */

public class ServerDemo {
    public static void main(String[] args) throws InterruptedException {
        /*EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childOption(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new ServerInitializer());
            ChannelFuture cf = bootstrap.bind(8000).sync();
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }*/

    }
}
