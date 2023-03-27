package com.example.qrcode;

/**
 * Created by pat on 27-Mar-23 - 8:49 AM
 *
 * @author pat
 * @project qrcode
 */

import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
public class QRCodeController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @GetMapping("/")
    public String getQRCode(){
        String cmcati="https://sec.cmcati.vn/";
        String github="https://github.com/tuanpa-tpa";

        byte[] image = new byte[0];
        try {
            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(cmcati,250,250);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        return qrcode;
    }
}
