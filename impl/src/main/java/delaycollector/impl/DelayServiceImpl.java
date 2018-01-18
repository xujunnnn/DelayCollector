/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.DelaycollectorService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.GetDelayInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.GetDelayOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.GetDelayOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.GetGlobalDelayOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.GetGlobalDelayOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.getglobaldelay.output.DelayList;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.getglobaldelay.output.DelayListBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.delaycollector.rev150105.getglobaldelay.output.DelayListKey;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;

public class DelayServiceImpl implements DelaycollectorService {
	private Map<String, Long> delayMap;
	public DelayServiceImpl(Map<String, Long> delayMap) {
		// TODO Auto-generated constructor stub
		this.delayMap=delayMap;
	}

	@Override
	public Future<RpcResult<GetDelayOutput>> getDelay(GetDelayInput input) {
		// TODO Auto-generated method stub
		String nodeconnector=input.getNodeConnector();
		GetDelayOutputBuilder getDelayOutputBuilder=new GetDelayOutputBuilder();
		getDelayOutputBuilder.setDelay(delayMap.get(nodeconnector));
		return RpcResultBuilder.success(getDelayOutputBuilder.build()).buildFuture();
	}

	@Override
	public Future<RpcResult<GetGlobalDelayOutput>> getGlobalDelay() {
		// TODO Auto-generated method stub
		GetGlobalDelayOutputBuilder getGlobalDelayOutputBuilder=new GetGlobalDelayOutputBuilder();
		List<DelayList> delayLists=new ArrayList<DelayList>();
		for(String ncid:delayMap.keySet()){
		DelayListBuilder delayListBuilder=new DelayListBuilder();
		delayListBuilder.setKey(new DelayListKey(ncid));
		delayListBuilder.setDelay(delayMap.get(ncid));
		delayLists.add(delayListBuilder.build());
		}
		getGlobalDelayOutputBuilder.setDelayList(delayLists);
		return RpcResultBuilder.success(getGlobalDelayOutputBuilder.build()).buildFuture();
	}

}
