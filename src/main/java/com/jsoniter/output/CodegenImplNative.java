package com.jsoniter.output;

import com.jsoniter.JsonException;
import com.jsoniter.any.Any;
import com.jsoniter.spi.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

class CodegenImplNative {
    public static final Map<Type, Encoder> NATIVE_ENCODERS = new IdentityHashMap<Type, Encoder>() {{
        put(boolean.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Boolean) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Boolean val = (Boolean) obj;
                return Any.wrap((boolean) val);
            }
        });
        put(Boolean.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Boolean) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Boolean val = (Boolean) obj;
                return Any.wrap((boolean) val);
            }
        });
        put(byte.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Short) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Byte val = (Byte) obj;
                return Any.wrap((int) val);
            }
        });
        put(Byte.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Short) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Byte val = (Byte) obj;
                return Any.wrap((int) val);
            }
        });
        put(short.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Short) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Short val = (Short) obj;
                return Any.wrap((int) val);
            }
        });
        put(Short.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Short) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Short val = (Short) obj;
                return Any.wrap((int) val);
            }
        });
        put(int.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Integer) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Integer val = (Integer) obj;
                return Any.wrap((int) val);
            }
        });
        put(Integer.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Integer) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Integer val = (Integer) obj;
                return Any.wrap((int) val);
            }
        });
        put(char.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Integer) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Character val = (Character) obj;
                return Any.wrap((int) val);
            }
        });
        put(Character.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Integer) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Character val = (Character) obj;
                return Any.wrap((int) val);
            }
        });
        put(long.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Long) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Long val = (Long) obj;
                return Any.wrap((long) val);
            }
        });
        put(Long.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Long) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Long val = (Long) obj;
                return Any.wrap((long) val);
            }
        });
        put(float.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Float) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Float val = (Float) obj;
                return Any.wrap((float) val);
            }
        });
        put(Float.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Float) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Float val = (Float) obj;
                return Any.wrap((float) val);
            }
        });
        put(double.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Double) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Double val = (Double) obj;
                return Any.wrap((double) val);
            }
        });
        put(Double.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((Double) obj);
            }

            @Override
            public Any wrap(Object obj) {
                Double val = (Double) obj;
                return Any.wrap((double) val);
            }
        });
        put(String.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                stream.writeVal((String) obj);
            }

            @Override
            public Any wrap(Object obj) {
                String val = (String) obj;
                return Any.wrap(val);
            }
        });
        put(Object.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                if (obj != null && obj.getClass() == Object.class) {
                    stream.writeEmptyObject();
                    return;
                }
                stream.writeVal(obj);
            }

            @Override
            public Any wrap(Object obj) {
                if (obj != null && obj.getClass() == Object.class) {
                    return Any.wrapAnyMap(new HashMap<String, Any>());
                }
                return JsonStream.wrap(obj);
            }
        });

        put(BigDecimal.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                BigDecimal val = (BigDecimal) obj;
                stream.writeVal(val.toString());
            }

            @Override
            public Any wrap(Object obj) {
                return Any.wrap(obj.toString());
            }
        });
        put(BigInteger.class, new Encoder() {
            @Override
            public void encode(Object obj, JsonStream stream) throws IOException {
                BigInteger val = (BigInteger) obj;
                stream.writeVal(val.toString());
            }

            @Override
            public Any wrap(Object obj) {
                return Any.wrap(obj.toString());
            }
        });
    }};

    public static String genWriteOp(String code, Type valueType) {
        if (NATIVE_ENCODERS.containsKey(valueType)) {
            return String.format("stream.writeVal((%s)%s);", getTypeName(valueType), code);
        }

        String cacheKey = TypeLiteral.create(valueType).getEncoderCacheKey();
        Codegen.getEncoder(cacheKey, valueType);
        if (Codegen.canStaticAccess(cacheKey)) {
            return String.format("%s.encode_((%s)%s, stream);", cacheKey, getTypeName(valueType), code);
        } else {
            return String.format("com.jsoniter.output.CodegenAccess.writeVal(\"%s\", (%s)%s, stream);", cacheKey, getTypeName(valueType), code);
        }
    }

    public static String getTypeName(Type fieldType) {
        if (fieldType instanceof Class) {
            Class clazz = (Class) fieldType;
            return clazz.getCanonicalName();
        } else if (fieldType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) fieldType;
            Class clazz = (Class) pType.getRawType();
            return clazz.getCanonicalName();
        } else {
            throw new JsonException("unsupported type: " + fieldType);
        }
    }
    public static String genEnum(Class clazz) {
        ClassDescriptor desc = JsoniterSpi.getEncodingClassDescriptor(clazz, false);
        StringBuilder lines = new StringBuilder();
        append(lines, String.format("public static void encode_(java.lang.Object obj, com.jsoniter.output.JsonStream stream) throws java.io.IOException {", clazz.getCanonicalName()));
        append(lines, "if (obj == null) { stream.writeNull(); return; }");
        append(lines, "stream.write('\"');");
        append(lines, "stream.writeRaw(obj.toString());");
        append(lines, "stream.write('\"');");
        append(lines, "}");
        return lines.toString();
    }

    private static void append(StringBuilder lines, String str) {
        lines.append(str);
        lines.append("\n");
    }
}
