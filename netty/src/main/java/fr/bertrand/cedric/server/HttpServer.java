package fr.bertrand.cedric.server;

import static com.google.inject.name.Names.named;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import fr.bertrand.cedric.controller.FavIconController;
import fr.bertrand.cedric.controller.IController;
import fr.bertrand.cedric.controller.IndexController;
import fr.bertrand.cedric.controller.ListController;

public class HttpServer extends AbstractExecutionThreadService {

	private final Injector injector;
	private final int port;
	private ServerBootstrap bootstrap;
	private Channel channel;

	private final static Logger LOGGER = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("ROOT");

	public HttpServer(int port) {
		this.port = port;
		this.injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(Key.get(IController.class, named("/favicon.ico"))).to(FavIconController.class);
				bind(Key.get(IController.class, named("/list"))).to(ListController.class);
				bind(Key.get(IController.class, named("/"))).to(IndexController.class);
			}
		});
	}

	@Override
	protected void run() {
		LOGGER.info("Server starting ...");

		// Configure the server.
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(new HttpServerPipelineFactory(injector));

		// Bind and start to accept incoming connections.
		channel = bootstrap.bind(new InetSocketAddress(port));

		LOGGER.info("Server started !!!");
	}

	public static void main(String[] args) {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new HttpServer(port).startAndWait();
	}

	public Channel getChannel() {
		return channel;
	}
}
