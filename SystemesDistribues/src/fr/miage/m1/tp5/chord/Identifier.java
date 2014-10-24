package fr.miage.m1.tp5.chord;

import java.io.Serializable;

public class Identifier implements Comparable<Identifier>, Serializable {

    private static final long serialVersionUID = 1L;

    protected static final int NB_BITS = 31;

    protected static final int MAX_VALUE = (2 << (NB_BITS - 1)) - 1;

    private final int value;

    public Identifier(int value) {
        if (value < 0 || value > MAX_VALUE) {
            throw new IllegalArgumentException("Invalid identifier value: "
                    + value);
        }

        this.value = value;
    }

    public boolean isBetweenOpenClosed(Identifier a, Identifier b) {
        if (a.compareTo(b) < 0) {
            return this.compareTo(a) > 0 && this.compareTo(b) <= 0;
        } else {
            return this.compareTo(a) > 0 || this.compareTo(b) <= 0;
        }
    }

    public boolean isBetweenOpenOpen(Identifier a, Identifier b) {
        if (a.compareTo(b) < 0) {
            return this.compareTo(a) > 0 && this.compareTo(b) < 0;
        } else {
            return this.compareTo(a) > 0 || this.compareTo(b) < 0;
        }
    }

    @Override
    public int compareTo(Identifier id) {
        return this.value - id.value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Identifier
                && this.value == ((Identifier) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.value).hashCode();
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

}
