package com.tiantian.controller;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.result.CommonMap;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.util.RedisUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author qi_bingo
 */
@RestController
@ResponseResult
public class ImageCodeController {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 字体格式
     */
    private Font[] fonts = { new Font("Arial", Font.BOLD, 36), new Font("Verdana", Font.BOLD, 36),
            new Font("Tahoma", Font.BOLD, 36), new Font("Times New Roman", Font.BOLD, 36),
            new Font("宋体", Font.BOLD, 36), new Font("Serif", Font.BOLD, 36), new Font("simhei", Font.BOLD, 36) };
    /** 干扰线的长度=1.414*lineWidth */
    private int lineWidth = 3;
    /** 定义图形大小 */
    private int width = 168;
    /** 定义图形大小 */
    private int height = 50;
    /** 干扰线数量 */
    private int count = 155;

    /**
     * 描述：
     *
     * @param fc 描述：
     * @param bc 描述：
     * @return 描述：
     */
    private Color getRandColor(int fc, int bc) { // 取得给定范围随机颜色
        Random random = new Random();
        int two = 255;
        if (fc > two) {
            fc = 255;
        }
        if (bc > two) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * @description: 随机生成中文
     * @param: []
     * @return: java.lang.String
     */
    public String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = new Random(System.currentTimeMillis());
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (new Integer(highPos)).byteValue();
        b[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 描述：
     *
     * @param response 描述：
     * @throws IOException
     * @throws ServletException 描述：
     * @throws IOException      描述：
     */
    @GetMapping("/imgcode")
    public CommonMap code(HttpServletResponse response) throws IOException {
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        // 在内存中创建图象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 生成随机类
        Random random = new Random(System.currentTimeMillis());
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(fonts[random.nextInt(7)]);
        // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < count; i++) {
            g.setColor(getRandColor(75, 200));
            // 保证画在边框之内
            int x = random.nextInt(width - lineWidth - 1) + 1;
            int y = random.nextInt(height - lineWidth - 1) + 1;
            int xl = random.nextInt(lineWidth);
            int yl = random.nextInt(lineWidth);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String code = this.equation(g, 1);
        // 图象生效
        g.dispose();
        String key = Guid.newGuid();
        // 将验证码放入缓存中
        redisUtil.set(key, code);
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", data);
        CommonMap result = new CommonMap();
        result.put("key", key);
        result.put("image", Base64.encodeBase64String(data.toByteArray()));
        return result;
    }

    /**
     * @description: 算法验证码
     * @param: [g, complexity]
     * @return: java.lang.String
     */
    private String equation(Graphics2D g, int complexity) {
        Random random = new Random(System.currentTimeMillis());
        int num1 = random.nextInt(10 * complexity);
        int num2 = random.nextInt(10 * complexity);
        String pic;
        int result;
        int three = 3;
        int choice = random.nextInt(3);
        if (choice % three == 0) {
            result = num1 + num2;
            pic = num1 + "+" + num2 + "=?";
        } else if (choice % three == 1) {
            int bigger = Math.max(num1, num2);
            int smaller = Math.min(num1, num2);
            result = bigger - smaller;
            pic = bigger + "-" + smaller + "=?";
        } else {
            result = num1 * num2;
            pic = num1 + "x" + num2 + "=?";
        }
        // 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
        g.setColor(new Color(20 + random.nextInt(130), 20 + random.nextInt(130), 20 + random.nextInt(130)));
        g.drawString(pic, 24, 36);
        return result + "";
    }

}
