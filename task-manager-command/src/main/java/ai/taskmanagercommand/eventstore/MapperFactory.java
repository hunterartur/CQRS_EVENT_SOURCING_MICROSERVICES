package ai.taskmanagercommand.eventstore;

import java.text.MessageFormat;

public final class MapperFactory {

    private MapperFactory() {
    }

    public static <D, S> DomainMapper<D, S> getMapper(
            DomainMapper<D, S> mapper,
            Class<DomainMapper<D, S>> mapperClass
    ) {
        try {
            return mapperClass.cast(mapper);
        } catch (Exception e) {
            throw new RuntimeException(
                    MessageFormat.format("Невозможно привести маппер к типу = {0}",
                            mapper.getClass().getCanonicalName()),
                    e);
        }
    }
}
