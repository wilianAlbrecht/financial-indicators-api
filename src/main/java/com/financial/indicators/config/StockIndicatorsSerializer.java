package com.financial.indicators.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.financial.indicators.models.StockIndicators;

public class StockIndicatorsSerializer extends JsonSerializer<StockIndicators> {

    private static final DecimalFormat DOUBLE_FORMAT =
            new DecimalFormat("0.############################");

    @Override
    public void serialize(StockIndicators value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        gen.writeStartObject();

        Field[] fields = getAllFields(value.getClass());

        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();

            try {
                Object fieldValue = field.get(value);

                if (fieldValue == null) {
                    gen.writeNullField(name);
                    continue;
                }

                // ===============================
                // BIGDECIMAL → STRING PLAIN
                // ===============================
                if (fieldValue instanceof BigDecimal bigDecimal) {
                    gen.writeStringField(name, bigDecimal.toPlainString());
                    continue;
                }

                // ===============================
                // DOUBLE → STRING SEM E NOTAÇÃO
                // ===============================
                if (fieldValue instanceof Double d) {

                    // Usa DecimalFormat para evitar notação científica
                    String formatted = DOUBLE_FORMAT.format(d);

                    gen.writeStringField(name, formatted);
                    continue;
                }

                // ===============================
                // OUTROS NÚMEROS → STRING NORMAL
                // ===============================
                if (fieldValue instanceof Float ||
                    fieldValue instanceof Long ||
                    fieldValue instanceof Integer ||
                    fieldValue instanceof Short) {

                    gen.writeStringField(name, fieldValue.toString());
                    continue;
                }

                // ===============================
                // OBJETOS NORMAIS → JSON NORMAL
                // ===============================
                gen.writeObjectField(name, fieldValue);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        gen.writeEndObject();
    }


    // -------------------------------
    // Suporte para campos herdados
    // -------------------------------
    private Field[] getAllFields(Class<?> type) {
        Field[] result = new Field[0];
        Class<?> current = type;

        while (current != null && current != Object.class) {
            result = concat(result, current.getDeclaredFields());
            current = current.getSuperclass();
        }

        return result;
    }

    private Field[] concat(Field[] a, Field[] b) {
        Field[] r = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
}
