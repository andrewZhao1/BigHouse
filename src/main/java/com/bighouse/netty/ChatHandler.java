package com.bighouse.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Description: 处理消息的handler TextWebSocketFrame：在netty中是用于为websocket专门处理文本的对象，frames是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

  //用户记录和管理所有客户端的channel
  private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    //当客户端连接服务器之后（打开连接），获取客户端的channel，放到ChannelGroup中进行管理
    clients.add(ctx.channel());
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    //当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
//        clients.remove(ctx.channel());
    System.out.println("客户端断开，channel对应的长id：" + ctx.channel().id().asLongText());
    System.out.println("客户端断开，channel对应的短id：" + ctx.channel().id().asShortText());
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      TextWebSocketFrame textWebSocketFrame) throws Exception {

    //获取客户端传输过来的消息
    String content = textWebSocketFrame.text();
    System.out.println("接受到的消息：" + content);

    for (Channel channel : clients) {
      channel.writeAndFlush(
          new TextWebSocketFrame("[服务器在" + LocalDateTime.now() + " 接受到消息，消息为：" + content + "]"));
    }

//        clients.writeAndFlush(new TextWebSocketFrame("[服务器在" + LocalDateTime.now() + " 接受到消息，消息为：" + content + "]"));

  }
}
