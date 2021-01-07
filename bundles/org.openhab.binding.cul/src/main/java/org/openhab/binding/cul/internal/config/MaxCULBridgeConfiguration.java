/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.cul.internal.config;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Configuration class for {@link org.openhab.binding.cul.internal.CULBindingConstants} bridge used to connect to the
 * cul device.
 *
 * @author Johannes Goehr (johgoe) - Initial contribution
 */
@NonNullByDefault
public class MaxCULBridgeConfiguration {

    /**
     * Name of the CUL device serial port
     */
    public @Nullable String serialPort;

    /**
     * Serial port baudrate.
     */
    public @Nullable Integer baudrate;

    /**
     * Serial port parity.
     */
    public @Nullable String parity;

    /**
     * Set timezone you want the units to be set to.
     */
    public @Nullable String timezone;
}