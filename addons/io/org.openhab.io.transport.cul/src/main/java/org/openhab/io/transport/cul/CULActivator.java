/**
 * Copyright (c) 2010-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.io.transport.cul;

import org.openhab.io.transport.cul.internal.CULManager;
import org.openhab.io.transport.cul.internal.network.CULNetworkConfigFactory;
import org.openhab.io.transport.cul.internal.network.CULNetworkHandlerImpl;
import org.openhab.io.transport.cul.internal.serial.CULSerialConfigFactory;
import org.openhab.io.transport.cul.internal.serial.CULSerialHandlerImpl;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bundle activator for CUL transport bundle. CULHandler implementations are
 * registered here for their specific transport prefix.
 *
 * @author Till Klocke
 * @since 1.4.0
 *
 */
public class CULActivator {

    private static final Logger logger = LoggerFactory.getLogger(CULActivator.class);

    private static ComponentContext context;

    protected synchronized void activate(ComponentContext componentContext) {
        context = componentContext;
        logger.debug("CUL transport has been started.");
        CULManager manager = CULManager.getInstance();
        manager.registerHandlerClass("serial", CULSerialHandlerImpl.class, new CULSerialConfigFactory());
        manager.registerHandlerClass("network", CULNetworkHandlerImpl.class, new CULNetworkConfigFactory());
    }

    protected void deactivate() {
        context = null;
        logger.debug("CUL transport has been stopped.");
    }

}
