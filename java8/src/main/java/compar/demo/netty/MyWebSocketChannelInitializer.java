package compar.demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//HttpServerCodec将请求和应答消息编码或解码为HTTP消息
		//通常接收到的http是一个片段，如果想要完整接受一次请求所有数据，我们需要绑定HttpObjectAggregator
		//然后就可以收到一个FullHttpRequest完整的请求信息了
		//ChunkedWriteHandler 向客户端发送HTML5文件，主要用于支持浏览器和服务器进行WebSocket通信
		//MyWebSocketHandler自定义Handler
		ch.pipeline().addLast("http-codec", new HttpServerCodec())
		        .addLast("aggregator", new HttpObjectAggregator(65536)) //定义缓冲大小
		        .addLast("http-chunked", new ChunkedWriteHandler())
		        .addLast("handler", new MyWebSocketHandler());
	}

}
