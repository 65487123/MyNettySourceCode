package com.utstar.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author：luzeping
 * @Date: 2020/1/6 20:35
 */
public class Handler extends SimpleChannelInboundHandler {
    private static List<Channel> channelList = new CopyOnWriteArrayList<>();

    static {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    for (Channel channel : channelList) {
                        channel.writeAndFlush(Unpooled.copiedBuffer(Instant.now().atZone(ZoneId.systemDefault())+" 客户端"+channel.remoteAddress().toString() + ":当前有" + channelList.size() + "个连接", Charset.forName("GBK")));
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channelList.add(ctx.pipeline().channel());
        ctx.writeAndFlush(Unpooled.copiedBuffer(ctx.channel().remoteAddress()+"已建立连接", Charset.forName("GBK")));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("23");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("23");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("23");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("23");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        channelList.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
