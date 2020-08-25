package compar.demo.netty;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {
	private static final Logger logger = 
			       Logger.getLogger(MyWebSocketHandler.class.getName());
	 private WebSocketServerHandshaker  handshaker;
	 private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";
	
	/**
	 * 处理客户端websocket请求的方法
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof FullHttpRequest) {  //处理发起http握手

			handHttpRequest(ctx, (FullHttpRequest) msg);
		}else if(msg instanceof WebSocketFrame){ //处理websocket链接
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}
	/**处理发起http握手*/
	private void handHttpRequest(ChannelHandlerContext ctx,FullHttpRequest 	req) {
		//如果http解码失败 则返回http异常 并且判断消息头有没有包含Upgrade字段(协议升级)
		 if(!req.decoderResult().isSuccess() || 
				 !"websocket".equals(req.headers().get("Upgrade"))) {
			 	sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
			 	return;
		 }
		//构造握手响应返回
		  WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
		   handshaker = wsFactory.newHandshaker(req);
		   if(handshaker==null) {
			   			//版本不支持
			   	WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		   }else{
			   handshaker.handshake(ctx.channel(), req);
		   			}
	}
	/**服务端向客户端响应消息*/
	private void sendHttpResponse(ChannelHandlerContext ctx,FullHttpRequest 	req,DefaultFullHttpResponse  response) {
		//返回给客户端
		  if(!HttpResponseStatus.OK.equals(response.status())) {
			  ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
			  response.content().writeBytes(buf);
			  buf.release();
			  //HttpHeaderUtil.setContentLength(response, response.content().readableBytes());
		  }
		//如果不是keepalive那么就关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(response);
		if (!HttpHeaderUtil.isKeepAlive(response) || !HttpResponseStatus.OK.equals(response.status())) {
			  f.addListener(ChannelFutureListener.CLOSE);
		}
	}
/**websocket帧*/
	 private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
		//判断是否关闭链路指令
		 if(frame instanceof CloseWebSocketFrame){
			 handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			 return;
		 }
		 
		//判断是否Ping消息 -- ping/pong心跳包
		 if(frame instanceof PingWebSocketFrame){
			 ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			 return;
		 }
		 
		//本程序仅支持文本消息， 不支持二进制消息
		 if(!(frame instanceof TextWebSocketFrame)){
			 throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
		 }
		 
		//返回应答消息 text文本帧
		 String request = ((TextWebSocketFrame) frame).text();
		
		logger.fine(String.format("%s received %s", ctx.channel(), request));

		 
		//发送到客户端websocket
		 TextWebSocketFrame tws =  new TextWebSocketFrame(request 
				 + ", 欢迎使用Netty WebSocket服务， 现在时刻:" 
				 + new java.util.Date().toString());
		 ctx.channel().write(tws);     //单发
		 //NettyConfig.group.write(tws);  群发
	 }
	
	
	@Override
	/**客户端与服务端链接时候调用*/
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.add(ctx.channel());
		System.out.println("链接开启channelActive");
	}

	/**
	 * 客户端与服务端断开链接时候调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.remove(ctx.channel());
		System.out.println("链接断开channelInactive");
	}
/**
 * 客户端发送数据过完成时候调用
 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/**
	 * 异常时候调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
