package org.example;

public class DataRecord {
    private final Long id;
    private final String value;

    public DataRecord(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public DataRecord withValue(String newValue) {
        return new DataRecord(id, newValue);
    }

    @Override
    public String toString() {
        return "DataRecord{id=" + id + ", value='" + value + "'}";
    }
}
