package org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig} instances.
 * 
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig
 *
 */
public class ArpHandlerConfigBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig> {

    private java.lang.Integer _arpFlowHardTimeout;
    private java.lang.Integer _arpFlowIdleTimeout;
    private java.lang.Integer _arpFlowPriority;
    private java.lang.Short _arpFlowTableId;
    private java.lang.Integer _floodFlowHardTimeout;
    private java.lang.Integer _floodFlowIdleTimeout;
    private java.lang.Long _floodFlowInstallationDelay;
    private java.lang.Integer _floodFlowPriority;
    private java.lang.Short _floodFlowTableId;
    private java.lang.Boolean _isHybridMode;
    private java.lang.Boolean _isProactiveFloodMode;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> augmentation = Collections.emptyMap();

    public ArpHandlerConfigBuilder() {
    }

    public ArpHandlerConfigBuilder(ArpHandlerConfig base) {
        this._arpFlowHardTimeout = base.getArpFlowHardTimeout();
        this._arpFlowIdleTimeout = base.getArpFlowIdleTimeout();
        this._arpFlowPriority = base.getArpFlowPriority();
        this._arpFlowTableId = base.getArpFlowTableId();
        this._floodFlowHardTimeout = base.getFloodFlowHardTimeout();
        this._floodFlowIdleTimeout = base.getFloodFlowIdleTimeout();
        this._floodFlowInstallationDelay = base.getFloodFlowInstallationDelay();
        this._floodFlowPriority = base.getFloodFlowPriority();
        this._floodFlowTableId = base.getFloodFlowTableId();
        this._isHybridMode = base.isIsHybridMode();
        this._isProactiveFloodMode = base.isIsProactiveFloodMode();
        if (base instanceof ArpHandlerConfigImpl) {
            ArpHandlerConfigImpl impl = (ArpHandlerConfigImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.Integer getArpFlowHardTimeout() {
        return _arpFlowHardTimeout;
    }
    
    public java.lang.Integer getArpFlowIdleTimeout() {
        return _arpFlowIdleTimeout;
    }
    
    public java.lang.Integer getArpFlowPriority() {
        return _arpFlowPriority;
    }
    
    public java.lang.Short getArpFlowTableId() {
        return _arpFlowTableId;
    }
    
    public java.lang.Integer getFloodFlowHardTimeout() {
        return _floodFlowHardTimeout;
    }
    
    public java.lang.Integer getFloodFlowIdleTimeout() {
        return _floodFlowIdleTimeout;
    }
    
    public java.lang.Long getFloodFlowInstallationDelay() {
        return _floodFlowInstallationDelay;
    }
    
    public java.lang.Integer getFloodFlowPriority() {
        return _floodFlowPriority;
    }
    
    public java.lang.Short getFloodFlowTableId() {
        return _floodFlowTableId;
    }
    
    public java.lang.Boolean isIsHybridMode() {
        return _isHybridMode;
    }
    
    public java.lang.Boolean isIsProactiveFloodMode() {
        return _isProactiveFloodMode;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
     private static void checkArpFlowHardTimeoutRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setArpFlowHardTimeout(final java.lang.Integer value) {
    if (value != null) {
        checkArpFlowHardTimeoutRange(value);
    }
        this._arpFlowHardTimeout = value;
        return this;
    }
    
     
     private static void checkArpFlowIdleTimeoutRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setArpFlowIdleTimeout(final java.lang.Integer value) {
    if (value != null) {
        checkArpFlowIdleTimeoutRange(value);
    }
        this._arpFlowIdleTimeout = value;
        return this;
    }
    
     
     private static void checkArpFlowPriorityRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setArpFlowPriority(final java.lang.Integer value) {
    if (value != null) {
        checkArpFlowPriorityRange(value);
    }
        this._arpFlowPriority = value;
        return this;
    }
    
     
     private static void checkArpFlowTableIdRange(final short value) {
         if (value >= (short)0 && value <= (short)255) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥255]].", value));
     }
    
    public ArpHandlerConfigBuilder setArpFlowTableId(final java.lang.Short value) {
    if (value != null) {
        checkArpFlowTableIdRange(value);
    }
        this._arpFlowTableId = value;
        return this;
    }
    
     
     private static void checkFloodFlowHardTimeoutRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setFloodFlowHardTimeout(final java.lang.Integer value) {
    if (value != null) {
        checkFloodFlowHardTimeoutRange(value);
    }
        this._floodFlowHardTimeout = value;
        return this;
    }
    
     
     private static void checkFloodFlowIdleTimeoutRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setFloodFlowIdleTimeout(final java.lang.Integer value) {
    if (value != null) {
        checkFloodFlowIdleTimeoutRange(value);
    }
        this._floodFlowIdleTimeout = value;
        return this;
    }
    
     
     private static void checkFloodFlowInstallationDelayRange(final long value) {
         if (value >= 0L && value <= 4294967295L) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥4294967295]].", value));
     }
    
    public ArpHandlerConfigBuilder setFloodFlowInstallationDelay(final java.lang.Long value) {
    if (value != null) {
        checkFloodFlowInstallationDelayRange(value);
    }
        this._floodFlowInstallationDelay = value;
        return this;
    }
    
     
     private static void checkFloodFlowPriorityRange(final int value) {
         if (value >= 0 && value <= 65535) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥65535]].", value));
     }
    
    public ArpHandlerConfigBuilder setFloodFlowPriority(final java.lang.Integer value) {
    if (value != null) {
        checkFloodFlowPriorityRange(value);
    }
        this._floodFlowPriority = value;
        return this;
    }
    
     
     private static void checkFloodFlowTableIdRange(final short value) {
         if (value >= (short)0 && value <= (short)255) {
             return;
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: [[0‥255]].", value));
     }
    
    public ArpHandlerConfigBuilder setFloodFlowTableId(final java.lang.Short value) {
    if (value != null) {
        checkFloodFlowTableIdRange(value);
    }
        this._floodFlowTableId = value;
        return this;
    }
    
     
    public ArpHandlerConfigBuilder setIsHybridMode(final java.lang.Boolean value) {
        this._isHybridMode = value;
        return this;
    }
    
     
    public ArpHandlerConfigBuilder setIsProactiveFloodMode(final java.lang.Boolean value) {
        this._isProactiveFloodMode = value;
        return this;
    }
    
    public ArpHandlerConfigBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public ArpHandlerConfigBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ArpHandlerConfig build() {
        return new ArpHandlerConfigImpl(this);
    }

    private static final class ArpHandlerConfigImpl implements ArpHandlerConfig {

        @Override
        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig.class;
        }

        private final java.lang.Integer _arpFlowHardTimeout;
        private final java.lang.Integer _arpFlowIdleTimeout;
        private final java.lang.Integer _arpFlowPriority;
        private final java.lang.Short _arpFlowTableId;
        private final java.lang.Integer _floodFlowHardTimeout;
        private final java.lang.Integer _floodFlowIdleTimeout;
        private final java.lang.Long _floodFlowInstallationDelay;
        private final java.lang.Integer _floodFlowPriority;
        private final java.lang.Short _floodFlowTableId;
        private final java.lang.Boolean _isHybridMode;
        private final java.lang.Boolean _isProactiveFloodMode;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> augmentation = Collections.emptyMap();

        private ArpHandlerConfigImpl(ArpHandlerConfigBuilder base) {
            this._arpFlowHardTimeout = base.getArpFlowHardTimeout();
            this._arpFlowIdleTimeout = base.getArpFlowIdleTimeout();
            this._arpFlowPriority = base.getArpFlowPriority();
            this._arpFlowTableId = base.getArpFlowTableId();
            this._floodFlowHardTimeout = base.getFloodFlowHardTimeout();
            this._floodFlowIdleTimeout = base.getFloodFlowIdleTimeout();
            this._floodFlowInstallationDelay = base.getFloodFlowInstallationDelay();
            this._floodFlowPriority = base.getFloodFlowPriority();
            this._floodFlowTableId = base.getFloodFlowTableId();
            this._isHybridMode = base.isIsHybridMode();
            this._isProactiveFloodMode = base.isIsProactiveFloodMode();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.Integer getArpFlowHardTimeout() {
            return _arpFlowHardTimeout;
        }
        
        @Override
        public java.lang.Integer getArpFlowIdleTimeout() {
            return _arpFlowIdleTimeout;
        }
        
        @Override
        public java.lang.Integer getArpFlowPriority() {
            return _arpFlowPriority;
        }
        
        @Override
        public java.lang.Short getArpFlowTableId() {
            return _arpFlowTableId;
        }
        
        @Override
        public java.lang.Integer getFloodFlowHardTimeout() {
            return _floodFlowHardTimeout;
        }
        
        @Override
        public java.lang.Integer getFloodFlowIdleTimeout() {
            return _floodFlowIdleTimeout;
        }
        
        @Override
        public java.lang.Long getFloodFlowInstallationDelay() {
            return _floodFlowInstallationDelay;
        }
        
        @Override
        public java.lang.Integer getFloodFlowPriority() {
            return _floodFlowPriority;
        }
        
        @Override
        public java.lang.Short getFloodFlowTableId() {
            return _floodFlowTableId;
        }
        
        @Override
        public java.lang.Boolean isIsHybridMode() {
            return _isHybridMode;
        }
        
        @Override
        public java.lang.Boolean isIsProactiveFloodMode() {
            return _isProactiveFloodMode;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hashCode(_arpFlowHardTimeout);
            result = prime * result + Objects.hashCode(_arpFlowIdleTimeout);
            result = prime * result + Objects.hashCode(_arpFlowPriority);
            result = prime * result + Objects.hashCode(_arpFlowTableId);
            result = prime * result + Objects.hashCode(_floodFlowHardTimeout);
            result = prime * result + Objects.hashCode(_floodFlowIdleTimeout);
            result = prime * result + Objects.hashCode(_floodFlowInstallationDelay);
            result = prime * result + Objects.hashCode(_floodFlowPriority);
            result = prime * result + Objects.hashCode(_floodFlowTableId);
            result = prime * result + Objects.hashCode(_isHybridMode);
            result = prime * result + Objects.hashCode(_isProactiveFloodMode);
            result = prime * result + Objects.hashCode(augmentation);
        
            hash = result;
            hashValid = true;
            return result;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig other = (org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig)obj;
            if (!Objects.equals(_arpFlowHardTimeout, other.getArpFlowHardTimeout())) {
                return false;
            }
            if (!Objects.equals(_arpFlowIdleTimeout, other.getArpFlowIdleTimeout())) {
                return false;
            }
            if (!Objects.equals(_arpFlowPriority, other.getArpFlowPriority())) {
                return false;
            }
            if (!Objects.equals(_arpFlowTableId, other.getArpFlowTableId())) {
                return false;
            }
            if (!Objects.equals(_floodFlowHardTimeout, other.getFloodFlowHardTimeout())) {
                return false;
            }
            if (!Objects.equals(_floodFlowIdleTimeout, other.getFloodFlowIdleTimeout())) {
                return false;
            }
            if (!Objects.equals(_floodFlowInstallationDelay, other.getFloodFlowInstallationDelay())) {
                return false;
            }
            if (!Objects.equals(_floodFlowPriority, other.getFloodFlowPriority())) {
                return false;
            }
            if (!Objects.equals(_floodFlowTableId, other.getFloodFlowTableId())) {
                return false;
            }
            if (!Objects.equals(_isHybridMode, other.isIsHybridMode())) {
                return false;
            }
            if (!Objects.equals(_isProactiveFloodMode, other.isIsProactiveFloodMode())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ArpHandlerConfigImpl otherImpl = (ArpHandlerConfigImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.packet.arp.handler.config.rev140528.ArpHandlerConfig>> e : augmentation.entrySet()) {
                    if (!e.getValue().equals(other.getAugmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public java.lang.String toString() {
            java.lang.String name = "ArpHandlerConfig [";
            java.lang.StringBuilder builder = new java.lang.StringBuilder (name);
            if (_arpFlowHardTimeout != null) {
                builder.append("_arpFlowHardTimeout=");
                builder.append(_arpFlowHardTimeout);
                builder.append(", ");
            }
            if (_arpFlowIdleTimeout != null) {
                builder.append("_arpFlowIdleTimeout=");
                builder.append(_arpFlowIdleTimeout);
                builder.append(", ");
            }
            if (_arpFlowPriority != null) {
                builder.append("_arpFlowPriority=");
                builder.append(_arpFlowPriority);
                builder.append(", ");
            }
            if (_arpFlowTableId != null) {
                builder.append("_arpFlowTableId=");
                builder.append(_arpFlowTableId);
                builder.append(", ");
            }
            if (_floodFlowHardTimeout != null) {
                builder.append("_floodFlowHardTimeout=");
                builder.append(_floodFlowHardTimeout);
                builder.append(", ");
            }
            if (_floodFlowIdleTimeout != null) {
                builder.append("_floodFlowIdleTimeout=");
                builder.append(_floodFlowIdleTimeout);
                builder.append(", ");
            }
            if (_floodFlowInstallationDelay != null) {
                builder.append("_floodFlowInstallationDelay=");
                builder.append(_floodFlowInstallationDelay);
                builder.append(", ");
            }
            if (_floodFlowPriority != null) {
                builder.append("_floodFlowPriority=");
                builder.append(_floodFlowPriority);
                builder.append(", ");
            }
            if (_floodFlowTableId != null) {
                builder.append("_floodFlowTableId=");
                builder.append(_floodFlowTableId);
                builder.append(", ");
            }
            if (_isHybridMode != null) {
                builder.append("_isHybridMode=");
                builder.append(_isHybridMode);
                builder.append(", ");
            }
            if (_isProactiveFloodMode != null) {
                builder.append("_isProactiveFloodMode=");
                builder.append(_isProactiveFloodMode);
            }
            final int builderLength = builder.length();
            final int builderAdditionalLength = builder.substring(name.length(), builderLength).length();
            if (builderAdditionalLength > 2 && !builder.substring(builderLength - 2, builderLength).equals(", ")) {
                builder.append(", ");
            }
            builder.append("augmentation=");
            builder.append(augmentation.values());
            return builder.append(']').toString();
        }
    }

}
