package de.paesserver.frames;

public class StringPair {
    final String prefix;
    String suffix;

    public StringPair(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringPair(String prefix) {
        this.prefix = prefix;
        this.suffix = "n/a";
    }

    @Override
    public String toString() {
        return prefix + ":\n" + suffix + "\n";
    }

}
