package com.wemeCity.components.image.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 生成二维码
 *
 * @author Xiang xiaowen
 * @since JDK1.7
 * @history 2015年7月8日 创建
 */
public class QRCodeUtil {

	private static Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 生成二维码
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 * @Author Xiang XiaoWen 2015年7月8日 创建
	 */
	public static BufferedImage genQRCode(String content, int width, int height) {
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
				}
			}
			return image;
		} catch (Exception e) {
			logger.error("生成二维码错误！", e);
		}
		return null;
	}

	/**
	 * 生成无白边二维码
	 *
	 * @param content
	 * @param width
	 * @param height
	 * @Author dengjunjun 2015年12月27日 创建
	 */
	public static BufferedImage getQRCodeWithoutWhite(String content, int width, int height) {
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			BitMatrix resMatrix = deleteWhite(bitMatrix);
			width = resMatrix.getWidth();
			height = resMatrix.getHeight();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, resMatrix.get(x, y) ? BLACK : WHITE);
				}
			}
			return image;
		} catch (Exception e) {
			logger.error("生成二维码错误！", e);
		}
		return null;
	}

	/**
	 * 去除二维码白边
	 *
	 * @param bitMatrix
	 * @Author dengjunjun 2015年12月27日 创建
	 */
	public static BitMatrix deleteWhite(BitMatrix bitMatrix){
		int[] rec = bitMatrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (bitMatrix.get(i + rec[0], j + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}

	/**
	 * 二维码绘制logo
	 *
	 * @param qrCodeImg 二维码图片文件
	 * @param logoImg   logo图片文件
	 * @Author dengjunjun 2015年12月27日 创建
	 */
	public static BufferedImage encodeImgLogo(BufferedImage qrCodeImg, File logoImg) {
		BufferedImage qrCodeLogoImg = qrCodeImg;
		try {
			if (!logoImg.isFile()) {
				logger.error("输入的Logo非图片");
				return null;
			}
			//获取画笔
			Graphics2D graphics2D = qrCodeLogoImg.createGraphics();
			//读取logo图片
			BufferedImage logo = ImageIO.read(logoImg);
			//设置二维码大小，太大，会覆盖二维码，此处20%
			int logoWidth = logo.getWidth(null) > qrCodeLogoImg.getWidth() * 2 / 10 ? (qrCodeLogoImg.getWidth() * 2 / 10) : logo.getWidth(null);
			int logoHeight = logo.getHeight(null) > qrCodeLogoImg.getHeight() * 2 / 10 ? (qrCodeLogoImg.getHeight() * 2 / 10) : logo.getHeight(null);
			//设置logo图片放置位置中心
			int x = (qrCodeLogoImg.getWidth() - logoWidth) / 2;
			int y = (qrCodeLogoImg.getHeight() - logoHeight) / 2;
//			// 右下角，15为调整值
//          int x = qrCodeLogoImg.getWidth()  - logoWidth-15;
//          int y = qrCodeLogoImg.getHeight() - logoHeight-15;
			//开始合并绘制图片
			graphics2D.drawImage(logo, x, y, logoWidth, logoHeight, null);
			graphics2D.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
			//logo边框大小
			graphics2D.setStroke(new BasicStroke(2));
			//logo边框颜色
			graphics2D.setColor(Color.WHITE);
			graphics2D.drawRect(x, y, logoWidth, logoHeight);
			graphics2D.dispose();
			logo.flush();
			qrCodeLogoImg.flush();
		} catch (Exception e) {
			logger.error("二维码绘制logo失败", e);
		}
		return qrCodeLogoImg;
	}

}
