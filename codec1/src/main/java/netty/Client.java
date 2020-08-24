package com.utstar.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author：luzeping
 * @Date: 2020/1/9 14:23
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new ClientInitializer());
            bootstrap.connect("127.0.0.1", 8000);
            /*for (int i = 0 ;i<20000;i++) {
                bootstrap.connect("127.0.0.1", 6641).sync();
            }*/
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
