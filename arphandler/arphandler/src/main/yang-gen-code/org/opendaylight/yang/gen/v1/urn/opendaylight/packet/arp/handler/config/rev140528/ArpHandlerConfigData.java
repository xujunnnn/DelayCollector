package org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528;
import org.opendaylight.yangtools.yang.binding.DataRoot;


/**
 * This module contains the base configuration for arphandler-impl implementation.
 * 
 * <p>This class represents the following YANG schema fragment defined in module <b>arp-handler-config</b>
 * <pre>
 * module arp-handler-config {
 *     yang-version 1;
 *     namespace "urn:opendaylight:packet:arp-handler-config";
 *     prefix "arp-handler-config";
 * 
 *     revision 2014-05-28 {
 *         description "This module contains the base configuration for arphandler-impl implementation.
 *         ";
 *     }
 * 
 *     container arp-handler-config {
 *         leaf arp-flow-table-id {
 *             type arp-flow-table-id;
 *         }
 *         leaf arp-flow-priority {
 *             type arp-flow-priority;
 *         }
 *         leaf arp-flow-hard-timeout {
 *             type arp-flow-hard-timeout;
 *         }
 *         leaf arp-flow-idle-timeout {
 *             type arp-flow-idle-timeout;
 *         }
 *         leaf flood-flow-table-id {
 *             type flood-flow-table-id;
 *         }
 *         leaf flood-flow-priority {
 *             type flood-flow-priority;
 *         }
 *         leaf flood-flow-hard-timeout {
 *             type flood-flow-hard-timeout;
 *         }
 *         leaf flood-flow-idle-timeout {
 *             type flood-flow-idle-timeout;
 *         }
 *         leaf flood-flow-installation-delay {
 *             type flood-flow-installation-delay;
 *         }
 *         leaf is-proactive-flood-mode {
 *             type is-proactive-flood-mode;
 *         }
 *         leaf is-hybrid-mode {
 *             type is-hybrid-mode;
 *         }
 *     }
 * }
 * </pre>
 *
 */
public interface ArpHandlerConfigData
    extends
    DataRoot
{




    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig</code> <code>arpHandlerConfig</code>, or <code>null</code> if not present
     */
    ArpHandlerConfig getArpHandlerConfig();

}

