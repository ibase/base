package base.util;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by base on 2016/3/28.
 */
public class XmlHelper {

    /**
     * xml字符串转化为对象
     *
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T xmlToObject(String xml, Class<T> clazz) throws Exception {
        if (ValueHelper.isNone(xml)) {
            return clazz.newInstance();
        }

        StringReader xmlString = new StringReader(xml);
        InputSource source = new InputSource(xmlString);

        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();
        return (T) u.unmarshal(source);
    }

    /**
     * 对象转化为xml字符串
     *
     * @param t
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String objectToXml(T t, Class<T> clazz) throws Exception {
        Writer writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(t, writer);

        return writer.toString();
    }
}
