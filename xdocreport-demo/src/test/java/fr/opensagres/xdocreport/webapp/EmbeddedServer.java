/**
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com>, Pascal Leclercq <pascal.leclercq@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.opensagres.xdocreport.webapp;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;

public class EmbeddedServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext webappcontext = new WebAppContext("WebContent",
				"/xdocreport-webapp");

		ContextHandlerCollection servlet_contexts = new ContextHandlerCollection();
		webappcontext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] {servlet_contexts, webappcontext,
				new DefaultHandler() });

		server.setHandler(handlers);
	
		// JSP Servlet + Context
		Context jsp_ctx = new Context(servlet_contexts, "/jsp",
				Context.SESSIONS);
		jsp_ctx.addServlet(new ServletHolder(
				new org.apache.jasper.servlet.JspServlet()), "*.jsp");
		
		server.start();
		server.join();
	}
}
