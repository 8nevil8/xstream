package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.awt.*;
import java.util.Map;

public class FontConverter implements Converter {

    public boolean canConvert(Class type) {
        // String comparison is used here because Font.class loads the class which in turns instantiates AWT,
        // which is nasty if you don't want it.
        return type.getName().equals("java.awt.Font");
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Font font = (Font) source;
        Map attributes = font.getAttributes();
        writer.startNode("attributes"); // <attributes>
        context.convertAnother(attributes);
        writer.endNode(); // </attributes>
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown(); // into <attributes>
        Map attributes = (Map) context.convertAnother(null, Map.class);
        reader.moveUp(); // out of </attributes>
        return Font.getFont(attributes);
    }
}
