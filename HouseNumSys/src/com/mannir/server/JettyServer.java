package com.mannir.server;

import java.awt.Desktop;
import java.net.URI;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyServer {
	private Server server;
	public JettyServer() {
		this(1313);
	}
	public JettyServer(Integer runningPort) {
		server = new Server(runningPort);
	}
	
	public void setHandler(WebAppContext context) {
		server.setHandler(context);
	}
	
	public void start() throws Exception {
		server.start();
		String url ="http://localhost:1313/";
		try {
		Desktop dt = Desktop.getDesktop();
		URI uri = new URI(url);
		dt.browse(uri.resolve(uri));
		} catch(Exception e) { System.out.println("This is Linux!"); }
	}
	
	public void stop() throws Exception {
		server.stop();
		server.join();
	}
	
	public boolean isStarted() {
		return server.isStarted();
	}
	
	public boolean isStopped() {
		return server.isStopped();
	}
}
