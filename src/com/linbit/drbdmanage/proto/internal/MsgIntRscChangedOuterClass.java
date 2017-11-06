// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MsgIntRscChanged.proto

package com.linbit.drbdmanage.proto.internal;

public final class MsgIntRscChangedOuterClass {
  private MsgIntRscChangedOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MsgIntRscChangedOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.linbit.drbdmanage.proto.internal.MsgIntRscChanged)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Resource name
     * </pre>
     *
     * <code>string resource_name = 1;</code>
     */
    java.lang.String getResourceName();
    /**
     * <pre>
     * Resource name
     * </pre>
     *
     * <code>string resource_name = 1;</code>
     */
    com.google.protobuf.ByteString
        getResourceNameBytes();
  }
  /**
   * <pre>
   * LinStor - Internal message for changed resource
   * </pre>
   *
   * Protobuf type {@code com.linbit.drbdmanage.proto.internal.MsgIntRscChanged}
   */
  public  static final class MsgIntRscChanged extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.linbit.drbdmanage.proto.internal.MsgIntRscChanged)
      MsgIntRscChangedOrBuilder {
    // Use MsgIntRscChanged.newBuilder() to construct.
    private MsgIntRscChanged(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MsgIntRscChanged() {
      resourceName_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private MsgIntRscChanged(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              resourceName_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.class, com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.Builder.class);
    }

    public static final int RESOURCE_NAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object resourceName_;
    /**
     * <pre>
     * Resource name
     * </pre>
     *
     * <code>string resource_name = 1;</code>
     */
    public java.lang.String getResourceName() {
      java.lang.Object ref = resourceName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        resourceName_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Resource name
     * </pre>
     *
     * <code>string resource_name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getResourceNameBytes() {
      java.lang.Object ref = resourceName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        resourceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getResourceNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, resourceName_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getResourceNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, resourceName_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged)) {
        return super.equals(obj);
      }
      com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged other = (com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged) obj;

      boolean result = true;
      result = result && getResourceName()
          .equals(other.getResourceName());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RESOURCE_NAME_FIELD_NUMBER;
      hash = (53 * hash) + getResourceName().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * LinStor - Internal message for changed resource
     * </pre>
     *
     * Protobuf type {@code com.linbit.drbdmanage.proto.internal.MsgIntRscChanged}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.linbit.drbdmanage.proto.internal.MsgIntRscChanged)
        com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChangedOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.class, com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.Builder.class);
      }

      // Construct using com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        resourceName_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor;
      }

      public com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged getDefaultInstanceForType() {
        return com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.getDefaultInstance();
      }

      public com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged build() {
        com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged buildPartial() {
        com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged result = new com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged(this);
        result.resourceName_ = resourceName_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged) {
          return mergeFrom((com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged other) {
        if (other == com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged.getDefaultInstance()) return this;
        if (!other.getResourceName().isEmpty()) {
          resourceName_ = other.resourceName_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object resourceName_ = "";
      /**
       * <pre>
       * Resource name
       * </pre>
       *
       * <code>string resource_name = 1;</code>
       */
      public java.lang.String getResourceName() {
        java.lang.Object ref = resourceName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          resourceName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Resource name
       * </pre>
       *
       * <code>string resource_name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getResourceNameBytes() {
        java.lang.Object ref = resourceName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          resourceName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Resource name
       * </pre>
       *
       * <code>string resource_name = 1;</code>
       */
      public Builder setResourceName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        resourceName_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Resource name
       * </pre>
       *
       * <code>string resource_name = 1;</code>
       */
      public Builder clearResourceName() {
        
        resourceName_ = getDefaultInstance().getResourceName();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Resource name
       * </pre>
       *
       * <code>string resource_name = 1;</code>
       */
      public Builder setResourceNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        resourceName_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:com.linbit.drbdmanage.proto.internal.MsgIntRscChanged)
    }

    // @@protoc_insertion_point(class_scope:com.linbit.drbdmanage.proto.internal.MsgIntRscChanged)
    private static final com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged();
    }

    public static com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MsgIntRscChanged>
        PARSER = new com.google.protobuf.AbstractParser<MsgIntRscChanged>() {
      public MsgIntRscChanged parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new MsgIntRscChanged(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MsgIntRscChanged> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MsgIntRscChanged> getParserForType() {
      return PARSER;
    }

    public com.linbit.drbdmanage.proto.internal.MsgIntRscChangedOuterClass.MsgIntRscChanged getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026MsgIntRscChanged.proto\022$com.linbit.drb" +
      "dmanage.proto.internal\")\n\020MsgIntRscChang" +
      "ed\022\025\n\rresource_name\030\001 \001(\tb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_linbit_drbdmanage_proto_internal_MsgIntRscChanged_descriptor,
        new java.lang.String[] { "ResourceName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
