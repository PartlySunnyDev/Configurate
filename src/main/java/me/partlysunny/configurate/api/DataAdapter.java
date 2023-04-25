package me.partlysunny.configurate.api;

public interface DataAdapter<T, U> {

    T deserialize(U object);

    U serialize(T object);

}
