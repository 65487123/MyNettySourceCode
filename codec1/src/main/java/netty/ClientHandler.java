package com.utstar.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @Author：luzeping
 * @Date: 2020/1/9 14:33
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush(Unpooled.copiedBuffer("11", Charset.forName("GBK")));
        for (int i = 0; i < 10; i++) {
            ByteBuf buf = Unpooled.copiedBuffer(" hello,server" + i, Charset.forName("utf-8"));
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println(byteBuf.toString(Charset.forName("GBK")));
    }
}
