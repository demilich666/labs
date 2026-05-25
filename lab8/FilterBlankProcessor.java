package org.example;

import org.example.DataProcessor;
import org.example.DataRecord;

import java.util.stream.Stream;

public class FilterBlankProcessor {
    @DataProcessor(order = 2)
    public Stream<DataRecord> filter(Stream<DataRecord> stream) {
        return stream.filter(record -> record.getValue() != null && !record.getValue().isBlank());
    }
}

