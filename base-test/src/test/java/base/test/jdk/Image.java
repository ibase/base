package base.test.jdk;

import base.test.constants.ConstantsHelper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by base on 2016/4/6.
 */
public class Image {

    @Test
    public void testWriteImage() throws Exception{
        InputStream is = new FileInputStream(ConstantsHelper._constant_RES_PATH+"\\img\\jdk\\whitePaper.png");
        BufferedImage bimg = ImageIO.read(is);

        //设置画笔
        Graphics2D gc = bimg.createGraphics();
        Color fontColor = new Color(0x000000);
        gc.setColor(fontColor);//颜色
        String fontName = "罗西钢笔行楷";
        int fontType = 5;
        int fontSize = 18;
        Font font = new Font(fontName,fontType,fontSize);//字体样式，类型(正斜体)，大小
        gc.setFont(font);//字体

        String word = "这是我的个人签名啊";
        gc.drawString(word, 50, 120);//写入的字符串，写入图片的位置

        OutputStream os = new FileOutputStream(ConstantsHelper._constant_RES_PATH+"\\img\\jdk\\whitePaper_.png");
        ImageIO.write(bimg,"png",os);
    }
}
