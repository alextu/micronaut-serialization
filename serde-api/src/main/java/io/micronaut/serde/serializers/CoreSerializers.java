package io.micronaut.serde.serializers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import io.micronaut.context.annotation.Factory;
import io.micronaut.core.type.Argument;
import io.micronaut.core.util.ArrayUtils;
import io.micronaut.serde.Encoder;
import io.micronaut.serde.Serializer;
import io.micronaut.serde.exceptions.SerdeException;
import jakarta.inject.Singleton;

/**
 * Factory class for core serializers.
 */
@Factory
public class CoreSerializers {

    /**
     * Generic array serializer.
     * @return A serializer for object array
     */
    @Singleton protected Serializer<Object[]> arraySerializer() {
        return new Serializer<Object[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  Object[] value,
                                  Argument<? extends Object[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                // TODO: need better generics handling in core for arrays
                final Argument<?> componentType = Argument.of(type.getType().getComponentType());
                final Serializer<Object> componentSerializer =
                        (Serializer<Object>) context.findSerializer(componentType);
                for (Object v : value) {
                    componentSerializer.serialize(
                            arrayEncoder,
                            context,
                            v,
                            componentType
                    );
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(Object[] value) {
                return ArrayUtils.isEmpty(value);
            }
        };
    }

    /**
     * A serializer for the date type.
     *
     * @return A date serializer
     */
    @Singleton protected Serializer<Date> dateSerializer() {
        return (encoder, context, value, type, generics) -> {
            context.findSerializer(Argument.LONG)
                    .serialize(
                            encoder,
                            context, value.getTime(), Argument.LONG
                    );
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.CharSequence}.
     *
     * @return A char sequence serializer
     */
    @Singleton protected Serializer<CharSequence> charSequenceSerializer() {
        return (encoder, context, value, type, generics) -> {
            encoder.encodeString(value.toString());
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.Long}.
     *
     * @return A long serializer
     */
    @Singleton protected Serializer<Long> longSerializer() {
        return (encoder, context, value, type, generics) -> {
            encoder.encodeLong(value);
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.Double}.
     *
     * @return A double serializer
     */
    @Singleton protected Serializer<Double> doubleSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeDouble(value);
    }

    /**
     * A serializer for all instances of {@link java.lang.Float}.
     *
     * @return A float serializer
     */
    @Singleton protected Serializer<Float> floatSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeFloat(value);
    }

    /**
     * A serializer for all instances of {@link java.lang.Short}.
     *
     * @return A short serializer
     */
    @Singleton protected Serializer<Short> shortSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeShort(value);
    }

    /**
     * A serializer for all instances of {@link java.lang.Character}.
     *
     * @return A Character serializer
     */
    @Singleton protected Serializer<Character> charSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeChar(value);
    }

    /**
     * A serializer for all instances of {@code char[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<char[]> charArraySerializer() {
        return new Serializer<char[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  char[] value,
                                  Argument<? extends char[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (char i : value) {
                    arrayEncoder.encodeChar(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(char[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.Byte}.
     *
     * @return A byte serializer
     */
    @Singleton protected Serializer<Byte> byteSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeByte(value);
    }

    /**
     * A serializer for all instances of {@code byte[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<byte[]> byteArraySerializer() {
        return new Serializer<byte[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  byte[] value,
                                  Argument<? extends byte[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (byte i : value) {
                    arrayEncoder.encodeByte(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(byte[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.Integer}.
     *
     * @return A integer serializer
     */
    @Singleton protected Serializer<Integer> intSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeInt(value);
    }

    /**
     * A serializer for all instances of {@code int[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<int[]> intArraySerializer() {
        return new Serializer<int[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  int[] value,
                                  Argument<? extends int[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (int i : value) {
                    arrayEncoder.encodeInt(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(int[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@code short[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<short[]> shortArraySerializer() {
        return new Serializer<short[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  short[] value,
                                  Argument<? extends short[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (short i : value) {
                    arrayEncoder.encodeShort(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(short[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@code boolean[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<boolean[]> booleanArraySerializer() {
        return new Serializer<boolean[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  boolean[] value,
                                  Argument<? extends boolean[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (boolean i : value) {
                    arrayEncoder.encodeBoolean(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(boolean[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@code long[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<long[]> longArraySerializer() {
        return new Serializer<long[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  long[] value,
                                  Argument<? extends long[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (long i : value) {
                    arrayEncoder.encodeLong(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(long[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@code float[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<float[]> floatArraySerializer() {
        return new Serializer<float[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  float[] value,
                                  Argument<? extends float[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (float i : value) {
                    arrayEncoder.encodeFloat(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(float[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@code double[]}.
     *
     * @return An array serializer
     */
    @Singleton protected Serializer<double[]> doubleArraySerializer() {
        return new Serializer<double[]>() {
            @Override
            public void serialize(Encoder encoder,
                                  EncoderContext context,
                                  double[] value,
                                  Argument<? extends double[]> type,
                                  Argument<?>... generics) throws IOException {
                final Encoder arrayEncoder = encoder.encodeArray();
                for (double i : value) {
                    arrayEncoder.encodeDouble(i);
                }
                arrayEncoder.finishStructure();
            }

            @Override
            public boolean isEmpty(double[] value) {
                return value == null || value.length == 0;
            }
        };
    }

    /**
     * A serializer for all instances of {@link java.lang.Boolean}.
     *
     * @return A boolean serializer
     */
    @Singleton protected Serializer<Boolean> booleanSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeBoolean(value);
    }

    /**
     * A serializer for all instances of {@link java.math.BigInteger}.
     *
     * @return A bit integer serializer
     */
    @Singleton protected Serializer<BigInteger> bitIntSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeBigInteger(value);
    }

    /**
     * A serializer for all instances of {@link java.math.BigDecimal}.
     *
     * @return A bit decimal serializer
     */
    @Singleton protected Serializer<BigDecimal> bigDecimalSerializer() {
        return (encoder, context, value, type, generics) -> encoder.encodeBigDecimal(value);
    }

    /**
     * A serializer for all instances of {@link java.math.BigDecimal}.
     *
     * @return A bit decimal serializer
     */
    @Singleton protected <T> Serializer<Iterable<T>> iterableSerializer() {
        return (encoder, context, value, type, generics) -> {
            final Encoder childEncoder = encoder.encodeArray();
            if (ArrayUtils.isEmpty(generics)) {
                throw new SerdeException("Serializing raw iterables is not supported for value: " + value);
            }
            final Argument<T> generic = (Argument<T>) generics[0];
            final Serializer<T> componentSerializer = (Serializer<T>) context.findSerializer(generic);
            for (T t : value) {
                componentSerializer.serialize(
                        childEncoder,
                        context,
                        t,
                        generic,
                        generic.getTypeParameters()
                );
            }
            encoder.finishStructure();
        };
    }
}
