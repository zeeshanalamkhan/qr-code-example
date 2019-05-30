package com.zeeshan.qrreader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeReader {

	private static String decodeQRCode(File qrCodeImage) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
		LuminanceSource luminaceSource = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminaceSource));
		try {

			Result result = new MultiFormatReader().decode(binaryBitmap);
			return result.getText();

		} catch (NotFoundException e) {
			System.out.println("There is no QR code in the image");
			return null;
		}

	}

	public static void main(String[] args) {

		try {
			File file = new File("MyQRCode.png");
			String decodedText = decodeQRCode(file);
			if (decodedText == null) {
				System.out.println("No QR Code found in the image");
			} else {
				System.out.println("Decoded text = " + decodedText);
			}
		} catch (IOException e) {
			System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
		}
	}
}
