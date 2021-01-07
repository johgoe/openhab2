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
package org.openhab.binding.cul.max.internal.messages;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.cul.max.internal.messages.constants.MaxCulMsgType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message class to handle Wall Thermostat Control messages
 *
 * @author Paul Hampson (cyclingengineer) - Initial contribution
 * @author Johannes Goehr (johgoe) - Migration to OpenHab 3.0
 * @since 1.6.0
 */
@NonNullByDefault
public class WallThermostatControlMsg extends BaseMsg implements ActualTemperatureStateMsg {

    private static final int WALL_THERMOSTAT_CONTROL_SET_POINT_AND_MEASURED_PAYLOAD_LEN = 2; /*
                                                                                              * in
                                                                                              * bytes
                                                                                              */
    private static final int WALL_THERMOSTAT_CONTROL_SET_POINT_ONLY_PAYLOAD_LEN = 1; /*
                                                                                      * in
                                                                                      * bytes
                                                                                      */

    private final Logger logger = LoggerFactory.getLogger(WallThermostatControlMsg.class);

    private @Nullable Double desiredTemperature;
    private @Nullable Double measuredTemperature;

    public WallThermostatControlMsg(String rawMsg) throws MaxCulProtocolException {
        super(rawMsg);
        logger.debug("{} Payload Len -> {}", this.msgType, this.payload.length);

        if (this.payload.length == WALL_THERMOSTAT_CONTROL_SET_POINT_AND_MEASURED_PAYLOAD_LEN) {
            desiredTemperature = (this.payload[0] & 0x7F) / 2.0;
            int mTemp = (this.payload[0] & 0x80);
            mTemp <<= 1;
            mTemp |= ((this.payload[1]) & 0xff);
            measuredTemperature = mTemp / 10.0; // temperature over 25.5 uses
                                                // extra bit in
                                                // desiredTemperature byte
        } else if (this.payload.length == WALL_THERMOSTAT_CONTROL_SET_POINT_ONLY_PAYLOAD_LEN) {
            desiredTemperature = (this.payload[0] & 0x7F) / 2.0;
            measuredTemperature = null;
        } else {
            logger.error("Got {} message with incorrect length!", this.msgType);
        }
    }

    public WallThermostatControlMsg(byte msgCount, byte msgFlag, byte groupId, String srcAddr, String dstAddr) {
        super(msgCount, msgFlag, MaxCulMsgType.WALL_THERMOSTAT_STATE, groupId, srcAddr, dstAddr);
    }

    @Override
    protected void printFormattedPayload() {
        logger.debug("\tDesired Temperature  => {}", desiredTemperature);
        logger.debug("\tMeasured Temperature => {}", measuredTemperature);
    }

    public @Nullable Double getMeasuredTemperature() {
        return measuredTemperature;
    }

    public @Nullable Double getDesiredTemperature() {
        return desiredTemperature;
    }
}