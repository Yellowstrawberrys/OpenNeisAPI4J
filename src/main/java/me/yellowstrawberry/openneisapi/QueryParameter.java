package me.yellowstrawberry.openneisapi;

public class QueryParameter {

    private final String key;
    private final String value;

    private QueryParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static QueryParameter of(String key, String value) {
        return new QueryParameter(key, value);
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
