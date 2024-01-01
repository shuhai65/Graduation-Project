package edu.scau.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.List;

public class Graphics2DUtil {
    /**
     * 获取文本的长度，字体大小不同，长度也不同
     *
     * @param font
     * @param content
     * @return
     */
    public static int[] getContentSize(Font font, String content) {
        int[] contentSize = new int[2];
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Rectangle rec = font.getStringBounds(content, frc).getBounds();
        contentSize[0] = (int) rec.getWidth();
        contentSize[1] = (int) rec.getHeight();
        return contentSize;
    }

    /**
     * 获取图片的宽和高
     *
     * @param img
     * @return
     */
    public static int[] getImgSize(BufferedImage img) {
        int[] imgSize = new int[2];
        imgSize[0] = img.getWidth(null); // 得到源图宽
        imgSize[1] = img.getHeight(null); // 得到源图高
        return imgSize;
    }

    public static void addTextWaterMark(BufferedImage targetImg, Color textColor, int fontSize, List<String> text, String outPath) {
        try {
            int width = targetImg.getWidth(); //图片宽
            int height = targetImg.getHeight(); //图片高
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(targetImg, 0, 0, width, height, null);
            g.setColor(textColor); //水印颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
            // 水印内容放置在右下角
            int x = 0;
            int y = height - fontSize * 2 * text.size();
//            g.drawString(text, x, y);
            for (int i = 0; i < text.size(); i++) {
                y = y + i * fontSize * 2;
                g.drawString(text.get(i), x, y);
            }
            FileOutputStream outImgStream = new FileOutputStream(outPath);
            ImageIO.write(bufferedImage, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}