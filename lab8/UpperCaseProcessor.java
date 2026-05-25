package org.example;

import org.example.DataProcessor;
import org.example.DataRecord;

import java.util.stream.Stream;

public class UpperCaseProcessor {
    @DataProcessor(order = 3)
    public Stream<DataRecord> toUpperCase(Stream<DataRecord> stream) {
        return stream.map(record -> record.withValue(record.getValue().toUpperCase()));
    }
}

