package org.example;

import org.example.DataProcessor;
import org.example.DataRecord;

import java.util.stream.Stream;

public class TrimProcessor {
    @DataProcessor(order = 1)
    public Stream<DataRecord> trim(Stream<DataRecord> stream) {
        return stream.map(record -> record.withValue(record.getValue() == null ? null : record.getValue().trim()));
    }
}

