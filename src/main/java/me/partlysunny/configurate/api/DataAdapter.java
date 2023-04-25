package me.partlysunny.configurate.api;

/**
 * Adapts data from T to U
 * Used to convert data from unsupported types to supported types for saving / loading
 * @param <T> The type of data to adapt from
 * @param <U> The type of data to adapt to
 */
public interface DataAdapter<T, U> {

    /**
     * Deserializes the object
     * @param object The object to deserialize of type U
     * @return The deserialized object of type T
     */
    T deserialize(U object);

    /**
     * Serializes the object
     * @param object The object to serialize of type T
     * @return The serialized object of type U
     */
    U serialize(T object);

}
