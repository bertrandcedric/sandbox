package fr.bertrand.cedric.server;

import static com.google.inject.name.Names.named;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;

import com.google.inject.Injector;
import com.google.inject.Key;

import fr.bertrand.cedric.controller.IController;

public class HttpServerHandler extends SimpleChannelUpstreamHandler {

	private final Injector injector;

	public HttpServerHandler(Injector injector) {
		this.injector = injector;
	}

	private IController controller(String action) {
		return injector.getInstance(Key.get(IController.class, named(action)));
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		HttpRequest request = (HttpRequest) e.getMessage();
		writeResponse(e, controller(request.getUri()).render());
	}

	private void writeResponse(MessageEvent e, String content) {
		HttpRequest request = (HttpRequest) e.getMessage();

		// Build the response object.
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
		response.setContent(ChannelBuffers.copiedBuffer(content, CharsetUtil.UTF_8));
		response.setHeader(CONTENT_TYPE, controller(request.getUri()).getContentType());

		// Write the response.
		ChannelFuture future = e.getChannel().write(response);

		// Close the non-keep-alive connection after the write operation is done.
		future.addListener(ChannelFutureListener.CLOSE);
	}
}
