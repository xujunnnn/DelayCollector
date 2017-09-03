/*
 * Copyright (c) 2013 - 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.opendaylight.controller.liblldp.BitBufferHelper;
import org.opendaylight.controller.liblldp.Packet;

public class Ipv4Option extends Packet{
	private static final String TYPE="type";
	private static final String LENGTH="length";
	private static final String POINTER="pointer";
	private static final String OVERFLOW="overflow";
	private static final String FLAG="flag";
	private static final String FSECOND="fsecond";
	private static final String FNANOSECOND="fnanosecond";
	private static final String SSECOND="ssecond";
	private static final String SNANOSECOND="snanosecond";
    private static Map<String, Pair<Integer, Integer>> fieldCoordinates
    = new LinkedHashMap<String, Pair<Integer, Integer>>() { {
            put(TYPE, new ImmutablePair<>(0, 8));
            put(LENGTH, new ImmutablePair<>(8, 8));
            put(POINTER, new ImmutablePair<>(16, 8));
            put(OVERFLOW, new ImmutablePair<>(24, 4));
            put(FLAG, new ImmutablePair(28, 4));
            put(FSECOND, new ImmutablePair<>(32, 32));
            put(FNANOSECOND, new ImmutablePair<>(64, 32));
            put(SSECOND, new ImmutablePair<>(96, 32));
            put(SNANOSECOND, new ImmutablePair<>(128, 32));
        }
    };
    
    
    
    private final Map<String, byte[]> fieldValues;
    
    public Ipv4Option(){
    	fieldValues=new HashMap<>();
    	hdrFieldsMap=fieldValues; 
    	hdrFieldCoordMap = fieldCoordinates;
    }
    
    public void setHeaderField(String headerField, byte[] readValue) {		
        hdrFieldsMap.put(headerField, readValue);
    }
    
    
    public short getType(){
    	return BitBufferHelper.getShort(fieldValues.get(TYPE));
    }
    public short getLength(){
    	return BitBufferHelper.getShort(fieldValues.get(LENGTH));
    }
    public short getPointer(){
    	return BitBufferHelper.getShort(fieldValues.get(POINTER));
    }
    public byte getOverFlow(){
    	return BitBufferHelper.getByte(fieldValues.get(OVERFLOW));
    }
    public byte getFlag(){
    	return BitBufferHelper.getByte(fieldValues.get(FLAG));
    }
    public long getFsecond(){
    	return BitBufferHelper.getLong(fieldValues.get(FSECOND));
    }
    public long getFNanosecond(){
    	return BitBufferHelper.getLong(fieldValues.get(FNANOSECOND));
    }
    public long getSsecond(){
    	return BitBufferHelper.getLong(fieldValues.get(SSECOND));
    }
    public long getSNanosecond(){
    	return BitBufferHelper.getLong(fieldValues.get(SNANOSECOND));
    }
    
    
}
