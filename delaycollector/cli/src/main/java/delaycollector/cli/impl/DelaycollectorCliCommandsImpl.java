/*
 * Copyright © 2017 org.bupt.xu and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import delaycollector.cli.api.DelaycollectorCliCommands;

public class DelaycollectorCliCommandsImpl implements DelaycollectorCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(DelaycollectorCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public DelaycollectorCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("DelaycollectorCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}