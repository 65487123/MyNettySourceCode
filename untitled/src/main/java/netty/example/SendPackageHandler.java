package netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @Author：luzeping
 * @Date: 2020/3/7 14:39
 */
public class SendPackageHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        byte[] buf = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buf);
        String msg = new String(buf, Charset.forName("utf-8"));
        System.out.println("服务端接收到数据"+msg);
    }
}
