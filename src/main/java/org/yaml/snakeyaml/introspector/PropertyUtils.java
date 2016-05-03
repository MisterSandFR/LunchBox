package org.yaml.snakeyaml.introspector;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.yaml.snakeyaml.error.YAMLException;

public class PropertyUtils {

    private final Map propertiesCache = new HashMap();
    private final Map readableProperties = new HashMap();
    private BeanAccess beanAccess;
    private boolean allowReadOnlyProperties;
    private boolean skipMissingProperties;

    public PropertyUtils() {
        this.beanAccess = BeanAccess.DEFAULT;
        this.allowReadOnlyProperties = false;
        this.skipMissingProperties = false;
    }

    protected Map getPropertiesMap(Class type, BeanAccess bAccess) throws IntrospectionException {
        if (this.propertiesCache.containsKey(type)) {
            return (Map) this.propertiesCache.get(type);
        } else {
            LinkedHashMap properties;
            boolean inaccessableFieldsExist;

            properties = new LinkedHashMap();
            inaccessableFieldsExist = false;
            Class c;
            Field[] arr$;
            int len$;
            int i$;
            Field field;
            int modifiers;

            label84:
            switch (PropertyUtils.SyntheticClass_1.$SwitchMap$org$yaml$snakeyaml$introspector$BeanAccess[bAccess.ordinal()]) {
            case 1:
                c = type;

                while (true) {
                    if (c == null) {
                        break label84;
                    }

                    arr$ = c.getDeclaredFields();
                    len$ = arr$.length;

                    for (i$ = 0; i$ < len$; ++i$) {
                        field = arr$[i$];
                        modifiers = field.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !properties.containsKey(field.getName())) {
                            properties.put(field.getName(), new FieldProperty(field));
                        }
                    }

                    c = c.getSuperclass();
                }

            default:
                PropertyDescriptor[] apropertydescriptor = Introspector.getBeanInfo(type).getPropertyDescriptors();
                int i = apropertydescriptor.length;

                for (len$ = 0; len$ < i; ++len$) {
                    PropertyDescriptor propertydescriptor = apropertydescriptor[len$];
                    Method method = propertydescriptor.getReadMethod();

                    if (method == null || !method.getName().equals("getClass")) {
                        properties.put(propertydescriptor.getName(), new MethodProperty(propertydescriptor));
                    }
                }

                for (c = type; c != null; c = c.getSuperclass()) {
                    arr$ = c.getDeclaredFields();
                    len$ = arr$.length;

                    for (i$ = 0; i$ < len$; ++i$) {
                        field = arr$[i$];
                        modifiers = field.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                            if (Modifier.isPublic(modifiers)) {
                                properties.put(field.getName(), new FieldProperty(field));
                            } else {
                                inaccessableFieldsExist = true;
                            }
                        }
                    }
                }
            }

            if (properties.isEmpty() && inaccessableFieldsExist) {
                throw new YAMLException("No JavaBean properties found in " + type.getName());
            } else {
                this.propertiesCache.put(type, properties);
                return properties;
            }
        }
    }

    public Set getProperties(Class type) throws IntrospectionException {
        return this.getProperties(type, this.beanAccess);
    }

    public Set getProperties(Class type, BeanAccess bAccess) throws IntrospectionException {
        if (this.readableProperties.containsKey(type)) {
            return (Set) this.readableProperties.get(type);
        } else {
            Set properties = this.createPropertySet(type, bAccess);

            this.readableProperties.put(type, properties);
            return properties;
        }
    }

    protected Set createPropertySet(Class type, BeanAccess bAccess) throws IntrospectionException {
        TreeSet properties = new TreeSet();
        Collection props = this.getPropertiesMap(type, bAccess).values();
        Iterator i$ = props.iterator();

        while (i$.hasNext()) {
            Property property = (Property) i$.next();

            if (property.isReadable() && (this.allowReadOnlyProperties || property.isWritable())) {
                properties.add(property);
            }
        }

        return properties;
    }

    public Property getProperty(Class type, String name) throws IntrospectionException {
        return this.getProperty(type, name, this.beanAccess);
    }

    public Property getProperty(Class type, String name, BeanAccess bAccess) throws IntrospectionException {
        Map properties = this.getPropertiesMap(type, bAccess);
        Object property = (Property) properties.get(name);

        if (property == null && this.skipMissingProperties) {
            property = new MissingProperty(name);
        }

        if (property != null && ((Property) property).isWritable()) {
            return (Property) property;
        } else {
            throw new YAMLException("Unable to find property \'" + name + "\' on class: " + type.getName());
        }
    }

    public void setBeanAccess(BeanAccess beanAccess) {
        if (this.beanAccess != beanAccess) {
            this.beanAccess = beanAccess;
            this.propertiesCache.clear();
            this.readableProperties.clear();
        }

    }

    public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
        if (this.allowReadOnlyProperties != allowReadOnlyProperties) {
            this.allowReadOnlyProperties = allowReadOnlyProperties;
            this.readableProperties.clear();
        }

    }

    public void setSkipMissingProperties(boolean skipMissingProperties) {
        if (this.skipMissingProperties != skipMissingProperties) {
            this.skipMissingProperties = skipMissingProperties;
            this.readableProperties.clear();
        }

    }

    static class SyntheticClass_1 {

        static final int[] $SwitchMap$org$yaml$snakeyaml$introspector$BeanAccess = new int[BeanAccess.values().length];

        static {
            try {
                PropertyUtils.SyntheticClass_1.$SwitchMap$org$yaml$snakeyaml$introspector$BeanAccess[BeanAccess.FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

        }
    }
}
