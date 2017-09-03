/*
 * Copyright © 2017 org.bupt.xu and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.delaycollector.config.rev140528.DelaycollectorConfig;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.DelaycollectorService;
import org.opendaylight.yangtools.concepts.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelaycollectorProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DelaycollectorProvider.class);
    private final DataBroker dataBroker;
    private final DelaycollectorConfig config;
    private final PacketProcessingService packetProcessingService;
    private final NotificationProviderService notificationProviderService;
    private RpcRegistration<DelaycollectorService> rpcRegistration;
    private final RpcProviderRegistry rpcProviderRegistry;
    private static Thread thread;
    private static Registration registration;
    private static Map<String, Long> delayMap=new ConcurrentHashMap<String, Long>();
    public DelaycollectorProvider(final DataBroker dataBroker,final DelaycollectorConfig config,final NotificationProviderService notificationProviderService,PacketProcessingService packetProcessingService,RpcProviderRegistry rpcProviderRegistry) {
        this.dataBroker = dataBroker;
        this.config=config;
        this.notificationProviderService=notificationProviderService;
        this.packetProcessingService=packetProcessingService;
        this.rpcProviderRegistry=rpcProviderRegistry;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("DelaycollectorProvider Session Initiated");
        Delay_Sender_Task delay_Sender_Task=new Delay_Sender_Task(config, packetProcessingService, dataBroker);
        thread=new Thread(delay_Sender_Task, "ipv4packetSpeaker");
        thread.start();
        DelayFromIpv4 delayFromIpv4=new DelayFromIpv4(config,delayMap);
        registration=notificationProviderService.registerNotificationListener(delayFromIpv4);
        DelayServiceImpl delayServiceImpl=new DelayServiceImpl(delayMap);
        rpcRegistration=rpcProviderRegistry.addRpcImplementation(DelaycollectorService.class,delayServiceImpl);
     }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("DelaycollectorProvider Closed");
        thread.stop();
        thread.destroy();
        try {
			registration.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}