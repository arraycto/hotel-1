package cn.ajiehome.common.utils;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@Log4j
public class BaseUtils {

    @Value("${local.image.url}")
    private  String localUrl;
    @Autowired
    private  SnowFlake snowFlake;
    @Value("${response.image.url}")
    private String responseImageUrl;

    public  String insertImage(MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        assert filename != null;

        String dateUrl = DateUtils.formatDateTime(LocalDate.now());
        String prefix = snowFlake.nextId().toString();
        String suffix =filename.substring(filename.lastIndexOf("."));

        String url = this.localUrl + dateUrl +"/"+prefix+suffix;
        File file = new File(url);

        try {
            if(!file.exists()){
                boolean mkdirs1 = file.getParentFile().mkdirs();
                boolean mkdirs = file.createNewFile();
            }
            //--------------------------------------
//            BufferedImage prevImage = ImageIO.read(multipartFile.getInputStream());
//            BufferedImage bufferedImage = new BufferedImage(700, 450, BufferedImage.TYPE_INT_BGR);
//            Graphics graphics = bufferedImage.createGraphics();
//            graphics.drawImage(prevImage, 0, 0, 700, 450, null);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            ImageIO.write(bufferedImage, Objects.requireNonNull(multipartFile.getContentType()),fileOutputStream);
//
//            fileOutputStream.write(file);
//
//            fileOutputStream.flush();
//            fileOutputStream.close();
            //---------------------------------------------------

            multipartFile.transferTo(file);
            return this.responseImageUrl+dateUrl+"/"+prefix+suffix;
        } catch (IOException e) {
            throw new ApplicationException(CodeType.SERVICE_IO_ERROR, "图片文件创建异常");
        }
    }
}
