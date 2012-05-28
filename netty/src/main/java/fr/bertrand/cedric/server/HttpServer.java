package fr.bertrand.cedric.server;

import static com.google.inject.name.Names.named;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import fr.bertrand.cedric.controller.HomeController;
import fr.bertrand.cedric.controller.IController;
import fr.bertrand.cedric.controller.ListController;

public class HttpServer extends AbstractExecutionThreadService {

	private final Injector injector;
	private final int port;
	private ServerBootstrap bootstrap;

	public HttpServer(int port) {
		this.port = port;
		this.injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(Key.get(IController.class, named("/list"))).to(ListController.class);
				bind(Key.get(IController.class, named("/home"))).to(HomeController.class);
			}
		});
	}

	@Override
	protected void run() {
		// Configure the server.
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(new HttpServerPipelineFactory(injector));

		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(port));
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
}
