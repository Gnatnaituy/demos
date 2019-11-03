package com.hasaker.kafka.serialization;

/**
 * @author 余天堂
 * @since 2019/11/3 13:49
 * @description 序列化枚举类
 */
public enum  SerializeTypeEnum {

    /**
     * 需要有Serializable接口
     */
    DefaultJavaSerializer(0),

    /**
     * 需要有Serializable接口
     */
    HessianSerializer(1),

    XMLSerializer(2),

    JOSNSerializer(3),

    ProtoStuffSerializer(4),

    /**
     * 只能序列化IDL产生的类
     */
    ProtoBufferSerializer(5),

    /**
     * 需要借助IDL
     */
    AvroSerializer(6),

    /**
     * 需要借助IDL
     */
    ThriftSerializer(7);

    private int code;

    SerializeTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static SerializeTypeEnum queryByCode(int code) {
        for (SerializeTypeEnum typeEnum : values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }

        return null;
    }
}
