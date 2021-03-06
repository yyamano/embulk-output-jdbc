package org.embulk.output.jdbc;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ToStringMap
        extends HashMap<String, String>
{
    @JsonCreator
    ToStringMap(Map<String, ToString> map)
    {
        super(Maps.transformValues(map, new Function<ToString, String>() {
            public String apply(ToString value)
            {
                if (value == null) {
                    return "null";
                } else {
                    return value.toString();
                }
            }
        }));
    }

    public Properties toProperties()
    {
        Properties props = new Properties();
        props.putAll(this);
        return props;
    }
}
