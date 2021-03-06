/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
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
package com.helger.as4server.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.as4lib.ebms3header.ObjectFactory;
import com.helger.as4server.receive.soap.SOAPHeaderElementProcessorEbms3Messaging;
import com.helger.as4server.receive.soap.SOAPHeaderElementProcessorRegistry;
import com.helger.web.servlet.ServletContextPathHolder;

public class AS4WebAppListener implements ServletContextListener
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AS4WebAppListener.class);

  public void contextInitialized (final ServletContextEvent sce)
  {
    // Logging: JUL to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger ();
    SLF4JBridgeHandler.install ();
    ServletContextPathHolder.setServletContextPath (sce.getServletContext ().getContextPath ());

    // Register all SOAP header element processors
    // Registration order matches execution order!
    SOAPHeaderElementProcessorRegistry.registerHeaderElementProcessor (ObjectFactory._Messaging_QNAME,
                                                                       new SOAPHeaderElementProcessorEbms3Messaging ());
    s_aLogger.info ("AS4 server started");
  }

  public void contextDestroyed (final ServletContextEvent sce)
  {
    s_aLogger.info ("AS4 server destroyed");
  }
}
