package base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by base on 2016/3/28.
 */
public class JsonHelper {

    /** Jackson*/
    private static ObjectMapper mapper = new ObjectMapper();

    public static String obj2JsonstrByJackson(Object object){
        try {
            String value_str = mapper.writeValueAsString(object);
            return value_str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T>T jsonstr2ObjByJackson(String json, Class<T> type){
        try {
            T t = mapper.readValue(json, type);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode jsonstr2JsonNodeByJackson(String jsonstr){
        try {
            JsonNode jsonNode = mapper.readTree(jsonstr);
            return jsonNode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final byte _CASE_FASTJSON_NOT_SHOW_NULL = 0x01;
    public static final byte _CASE_FASTJSON_SHOW_NULL = 0x02;
    public static final byte _CASE_FASTJSON_SHOW_NULL_TO_DEFAULT = 0x03;

    /** Fastjson*/

    /**
     *
     * @param object
     * @param _case null值处理方式
     * @return
     */
    public static String obj2JsonstrByFastjson(Object object, byte _case) {
        switch (_case){
            case JsonHelper._CASE_FASTJSON_SHOW_NULL:
                return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
            case JsonHelper._CASE_FASTJSON_SHOW_NULL_TO_DEFAULT:
                SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullBooleanAsFalse,
                        SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullListAsEmpty};
                return JSON.toJSONString(object,features);
            default:
                break;
        }
        return JSON.toJSONString(object);
    }

    public static  <T>T  jsonstr2ObjByFastjson(String jsonstr,Class<T> type) {
        T t = JSON.parseObject(jsonstr, type);
        return t;
    }

    public static JSONObject jsonstr2JsonObjectByFastjson(String jsonstr) {
        return JSON.parseObject(jsonstr);
    }
}
