package ai.taskmanagerquery.eventstore;

import ai.taskmanagerquery.exception.DeserializeObjectException;
import ai.taskmanagerquery.exception.SerializeObjectException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public final class SerializerUtil {

    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new Jdk8Module())
            .addModule(new ParameterNamesModule())
            .addModule(new JavaTimeModule())
            .build();

    private SerializerUtil() {
    }

    public static byte[] serializeToJsonBytes(final Object object) {
        try {
            return MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new SerializeObjectException(e.getMessage(), e);
        }
    }

    public static <T> T deserializeToJsonBytes(final byte[] jsonBytes, Class<T> tClass) {
        try {
            return MAPPER.readValue(jsonBytes, tClass);
        } catch (IOException e) {
            throw new DeserializeObjectException(e.getMessage(), e);
        }
    }

    public static HashMap<String, byte[]> deserializeEventsMetadata(final byte[] metaData) {
        final var typeReference = new TypeReference<HashMap<String, byte[]>>() {
        };
        try {
            return MAPPER.readValue(metaData, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] serializeEventsMetadata(final HashMap<String, byte[]> metaData) {
        try {
            final var valueAsString = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(metaData);
            return valueAsString.getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
