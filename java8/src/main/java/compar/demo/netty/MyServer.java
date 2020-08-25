package compar.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
		public static void main(String[] args) {
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup) 
				  .channel(NioServerSocketChannel.class)
				  .childHandler(new MyWebSocketChannelInitializer());
				
				System.out.println("服务start...");
				Channel ch = b.bind(8888).sync().channel();
				ch.closeFuture().sync();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
		}
}
