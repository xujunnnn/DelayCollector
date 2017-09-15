package org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>arp-handler-config</b>
 * <pre>
 * container arp-handler-config {
 *     leaf arp-flow-table-id {
 *         type arp-flow-table-id;
 *     }
 *     leaf arp-flow-priority {
 *         type arp-flow-priority;
 *     }
 *     leaf arp-flow-hard-timeout {
 *         type arp-flow-hard-timeout;
 *     }
 *     leaf arp-flow-idle-timeout {
 *         type arp-flow-idle-timeout;
 *     }
 *     leaf flood-flow-table-id {
 *         type flood-flow-table-id;
 *     }
 *     leaf flood-flow-priority {
 *         type flood-flow-priority;
 *     }
 *     leaf flood-flow-hard-timeout {
 *         type flood-flow-hard-timeout;
 *     }
 *     leaf flood-flow-idle-timeout {
 *         type flood-flow-idle-timeout;
 *     }
 *     leaf flood-flow-installation-delay {
 *         type flood-flow-installation-delay;
 *     }
 *     leaf is-proactive-flood-mode {
 *         type is-proactive-flood-mode;
 *     }
 *     leaf is-hybrid-mode {
 *         type is-hybrid-mode;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>arp-handler-config/arp-handler-config</i>
 * 
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfigBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfigBuilder
 *
 */
public interface ArpHandlerConfig
    extends
    ChildOf<ArpHandlerConfigData>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:packet:arp-handler-config",
        "2014-05-28", "arp-handler-config").intern();

    /**
     * @return <code>java.lang.Short</code> <code>arpFlowTableId</code>, or <code>null</code> if not present
     */
    java.lang.Short getArpFlowTableId();
    
    /**
     * @return <code>java.lang.Integer</code> <code>arpFlowPriority</code>, or <code>null</code> if not present
     */
    java.lang.Integer getArpFlowPriority();
    
    /**
     * @return <code>java.lang.Integer</code> <code>arpFlowHardTimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getArpFlowHardTimeout();
    
    /**
     * @return <code>java.lang.Integer</code> <code>arpFlowIdleTimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getArpFlowIdleTimeout();
    
    /**
     * @return <code>java.lang.Short</code> <code>floodFlowTableId</code>, or <code>null</code> if not present
     */
    java.lang.Short getFloodFlowTableId();
    
    /**
     * @return <code>java.lang.Integer</code> <code>floodFlowPriority</code>, or <code>null</code> if not present
     */
    java.lang.Integer getFloodFlowPriority();
    
    /**
     * @return <code>java.lang.Integer</code> <code>floodFlowHardTimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getFloodFlowHardTimeout();
    
    /**
     * @return <code>java.lang.Integer</code> <code>floodFlowIdleTimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getFloodFlowIdleTimeout();
    
    /**
     * @return <code>java.lang.Long</code> <code>floodFlowInstallationDelay</code>, or <code>null</code> if not present
     */
    java.lang.Long getFloodFlowInstallationDelay();
    
    /**
     * Setting the value to false is same as passive discovery mode, where arp packets 
     * are sent to controller. Setting to true refers to proactive flood mode where 
     * flood flows are automatically written on to each switch.
     *
     *
     *
     * @return <code>java.lang.Boolean</code> <code>isProactiveFloodMode</code>, or <code>null</code> if not present
     */
    java.lang.Boolean isIsProactiveFloodMode();
    
    /**
     * Setting the value to true refers to proactive flood mode where flood flows are 
     * automatically written to each switch.
     *
     *
     *
     * @return <code>java.lang.Boolean</code> <code>isHybridMode</code>, or <code>null</code> if not present
     */
    java.lang.Boolean isIsHybridMode();

}

