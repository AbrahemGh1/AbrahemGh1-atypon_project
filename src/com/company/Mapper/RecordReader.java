package com.company.Mapper;

import java.io.Closeable;
import java.io.IOException;

interface RecordReader<K, V> extends Closeable {
    /**
     * Returns true if this RecordReader has another Record.
     *
     * @return true iff a key/value was read, false if at EOF.
     * @throws IOException if this file is closed
     */
    boolean hasNext() throws IOException;

    /**
     * Reads the next Key.
     *
     * @return a new Key object.
     */
    K getKey();

    /**
     * Reads the next Value.
     *
     * @return a new Value object.
     */
    V getValue() throws IOException;

    /**
     * Close this {InputSplit} Object.
     *
     * @throws IOException
     */
    @Override
    void close() throws IOException;
}
