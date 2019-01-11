package cn.sky999.common.request;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {
	private int w = 110;
	private int h = 50;
	private Random r = new Random(); // {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书",
										// "微软雅黑", "楷体_GB2312"}
	private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	private Color bgColor = new Color(238, 248, 247);
	private String text;

	public void verifyCode(HttpServletRequest request, HttpServletResponse response, String isNum, String isWhite)
			throws IOException {
		if (StringUtils.isNotBlank(isNum) && isNum.equals("1")) {
			codes = "0123456789";
		}
		if (StringUtils.isNotBlank(isWhite) && isWhite.equals("1")) {
			bgColor = new Color(255, 255, 255);
		}
		// 获取一次性验证码图片
		BufferedImage image = getImage();
		// 把文本保存到session中，必须在输出之前保存session
		request.getSession().setAttribute("vCode", text);
		// 该方法必须在getImage()方法之后来调用
		output(image, response.getOutputStream());// 把图片写到指定流中
	}

	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}

	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(6) + 40;
		return new Font(fontName, style, size);
	}

	private void drawLine(BufferedImage image) {
		int num = 3;
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.BLUE);
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return image;
	}

	public BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		StringBuilder sb = new StringBuilder();
		// 向图片中画4个字符
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			float x = i * 1.0F * w / 4;
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, h - 10);// 调整文字显示高度
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

	public void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
}
