package base.util;

import base.models.User;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by base on 2016/3/28.
 */
public class TestHelper {

    @Test
    public void testDate() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2016-01-01 00:00:00");
        Date date2 = sdf.parse("2016-01-02 00:00:00");
        Date date3 = sdf.parse("2016-01-01 00:00:00");

        //compareTeam
        System.out.println(DateHelper.compareTeam(date1, date2));//-1
        System.out.println(DateHelper.compareTeam(date1, date3));//0
        System.out.println(DateHelper.compareTeam(date2, date3));//1

        //getDateOfThem
        long datems = DateHelper.getDateOfThem(date1.getTime(), date2.getTime());
        System.out.println(sdf.format(new Date(datems)));//date1 < x < date2

        //date2dateString
        System.out.println(DateHelper.date2dateString(date1));//2016-01-01

        //date2timeString
        System.out.println(DateHelper.date2timeString(date1));//2016-01-01 00:00:00
    }

    @Test
    public void testHttp() throws Exception{
        //getInfoByRequestUrl
        String url = "http://www.baidu.com";
        System.out.println(HttpHelper.getInfoByRequestUrl(url));
    }

    @Test
    public void testJson() {
        User user = new User();
        user.setName("烈风");
        user.setSex('男');
        user.setAge(30);

        //obj2JsonstrByJackson
        System.out.println(JsonHelper.obj2JsonstrByJackson(user));
        System.out.println(JsonHelper.obj2JsonstrByFastjson(user, JsonHelper.CASE_FASTJSON_NOT_SHOW_NULL));
        System.out.println(JsonHelper.obj2JsonstrByFastjson(user, JsonHelper.CASE_FASTJSON_SHOW_NULL));
        System.out.println(JsonHelper.obj2JsonstrByFastjson(user, JsonHelper.CASE_FASTJSON_SHOW_NULL_TO_DEFAULT));

        //jsonstr2ObjByJackson
        String jsonstr = JsonHelper.obj2JsonstrByJackson(user);
        User user1 = JsonHelper.jsonstr2ObjByJackson(jsonstr,User.class);
        User user2 = JsonHelper.jsonstr2ObjByFastjson(jsonstr,User.class);
        System.out.println(user1.toString());
        System.out.println(user2.toString());

        //jsonstr2JsonNodeByJackson
        JsonNode jsonNode = JsonHelper.jsonstr2JsonNodeByJackson(jsonstr);
        System.out.println(jsonNode.get("name").asText());
        //jsonstr2JsonObjectByFastjson
        JSONObject jsonObject = JsonHelper.jsonstr2JsonObjectByFastjson(jsonstr);
        System.out.println(jsonObject.getString("name"));
    }

    @Test
    public void testMD5() {
        //getMD5OfString
        System.out.println(MD5Helper.getMD5OfString("123456", MD5Helper.CASE_LOWER));
        System.out.println(MD5Helper.getMD5OfString("123456", MD5Helper.CASE_UPPER));
    }

    @Test
    public void testRandom() {
        //randomNumbers
        System.out.println(RandomHelper.randomNumbers(3));//202
        System.out.println(RandomHelper.randomNumbers(8));//51232180
        System.out.println(RandomHelper.randomNumbers(12));//132379024259

        //randomLetters
        System.out.println(RandomHelper.randomLetters(8, RandomHelper.CASE_ALL));       //QiJIYOBU
        System.out.println(RandomHelper.randomLetters(8, RandomHelper.CASE_LOWER));//zhyjwlnm
        System.out.println(RandomHelper.randomLetters(8, RandomHelper.CASE_UPPER));   //KWGENXLV
    }

    @Test
    public void testSerialize(){
        User user = new User();
        user.setName("烈风");
        user.setSex('男');
        user.setAge(30);

        //objectToBytes - object must to be implemented Serializable
        byte[] bytes = SerializeHelper.objectToBytes(user);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]+",");
        }
        System.out.println();

        //bytesToObject
        User user1 = (User) SerializeHelper.bytesToObject(bytes);
        System.out.println(user1.toString());
    }

    @Test
    public void testXml() throws Exception{
        User user = new User();
        user.setName("烈风");
        user.setSex('男');
        user.setAge(30);

        //objectToXml - object must to be added annotation `@XmlRootElement`
        String userXml = XmlHelper.objectToXml(user,User.class);
        System.out.println(userXml);

        //xmlToObject
        User user1 = XmlHelper.xmlToObject(userXml, User.class);
        System.out.println(user1.toString());
    }
}
