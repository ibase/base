package base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by base on 2016/3/28.
 */
public class SerializeHelper {

    /**
     * 对象序列化为字节数组
     *
     * @param obj
     * @return
     */
    public static byte[] objectToBytes(Object obj) {
        byte[] result = null;
        ByteArrayOutputStream byteOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            byteOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteOutputStream);

            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

            result = byteOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != objectOutputStream) {
                try {
                    objectOutputStream.close();
                    byteOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 字节数组反序列化为对象
     *
     * @param bytes
     * @return
     */
    public static Object bytesToObject(byte[] bytes) {
        Object obj = null;
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream byteInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteInputStream);
            obj = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != objectInputStream) {
                try {
                    objectInputStream.close();
                    byteInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
