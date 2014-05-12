package com.mannir.server;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;



public class Runxml {
	//static String webxml = "src/main/webapp/WEB-INF/web.xml";
	static String webxml = "WebContent/WEB-INF/web.xml";
	//static String webdir = "src/main/webapp";
	static String webdir = "WebContent";
	//private static String dbURL = "jdbc:derby://localhost:1527/db;create=true;user=admin;password=13131";
	//public static Connection cn;
 
	public static void main(String[] args) {
		final JettyServer jettyServer = new JettyServer();
	       WebAppContext context = new WebAppContext();
	       context.setDescriptor(webxml);
	       context.setResourceBase(webdir);
	       context.setContextPath("/");
	       context.setParentLoaderPriority(true);
		jettyServer.setHandler(context);
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				new ServerRunner(jettyServer);
			}
		};
		EventQueue.invokeLater(runner);
	}
}
