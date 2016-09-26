package com.lawrence.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lawrence on 9/26/2016.
 */

public class MessageUtil {
    public static Map<String,String> parseXML(HttpServletRequest request) throws Exception
    {
        Map<String,String> map = new HashMap<String,String>();

        InputStream in = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);

        Element root = document.getRootElement();

        List<Element> elementList = root.elements();

        for(Element e:elementList){
            map.put(e.getName(),e.getText());
        }

        in.close();

        return map;
    }
}
