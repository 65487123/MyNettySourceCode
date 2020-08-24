package com.utstar.netty.example;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Author：luzeping
 * @Date: 2019/12/30 20:16
 */
public class ChannelHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            byte[] bytes ;
            if (System.getProperty("os.name").toLowerCase().startsWith("win")){
                bytes=readFromByteFile("E:\\project\\project\\index.html");
            }else {
                bytes=readFromByteFile("/root/project/html/index.html");
            }
            ByteBuf content = Unpooled.copiedBuffer(bytes);
            //构造一个http响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }
    }
    public byte[] readFromByteFile(String pathname) throws IOException{
        File filename = new File(pathname);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while((size = in.read(temp)) != -1){
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        return content;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel());
    }
}
