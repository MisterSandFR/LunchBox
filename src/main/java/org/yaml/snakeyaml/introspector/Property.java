package org.yaml.snakeyaml.introspector;

public abstract class Property implements Comparable {

    private final String name;
    private final Class type;

    public Property(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public Class getType() {
        return this.type;
    }

    public abstract Class[] getActualTypeArguments();

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.getName() + " of " + this.getType();
    }

    public int compareTo(Property o) {
        return this.name.compareTo(o.name);
    }

    public boolean isWritable() {
        return true;
    }

    public boolean isReadable() {
        return true;
    }

    public abstract void set(Object object, Object object1) throws Exception;

    public abstract Object get(Object object);

    public int hashCode() {
        return this.name.hashCode() + this.type.hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Property)) {
            return false;
        } else {
            Property p = (Property) other;

            return this.name.equals(p.getName()) && this.type.equals(p.getType());
        }
    }
}
