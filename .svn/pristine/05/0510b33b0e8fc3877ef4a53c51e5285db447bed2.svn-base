package com.qbao.store.controller.app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import com.bqiong.usercenter.util.QnUtils;

/**
 * controller 基类
 * @author yewenhai
 *
 */
public class BaseController {
    /** 校验器 **/
    protected static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /** 有效的图片后缀 **/
    protected final String[] imgSuffixs = new String[] { "jpeg", "jpg", "gif", "png", "bmp", "JPEG", "JPG", "GIF", "PNG", "BMP" };

    /**
     * 数据校验
     * @param obj
     * @return
     */
    protected <T> String dataValid(T obj) {
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        StringBuilder errorMsg = new StringBuilder();
        if ((null != set) && (0 != set.size())) {
            for (ConstraintViolation<T> violation : set) {
                errorMsg.append("," + violation.getMessage());
            }
        }
        String customerMsg = this.customerValid(obj); // 自定义校验器
        if (StringUtils.isNotEmpty(customerMsg)) {
            errorMsg.append("," + customerMsg);
        }
        if (StringUtils.isNotEmpty(errorMsg.toString())) {
            return errorMsg.substring(1);
        }
        return null;
    }

    /**
     * 自定义校验
     * @return
     */
    protected <T> String customerValid(T obj) {

        return null;
    }

    /**
     * 上传文件到7牛，并返图片url路径
     * @param fileName 文件名
     * @param file 文件
     * @param suffix 文件后缀
     * @return
     * @throws IOException 
     */
    protected String fileUploadToQn(String fileName, MultipartFile file, String suffix) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        BufferedImage bufferedFile = ImageIO.read(file.getInputStream());
        ImageIO.write(bufferedFile, suffix, byteArray);
        return QnUtils.upload(fileName, byteArray.toByteArray());
    }

    /**
     * 图片格式校验
     * @param fileName
     * @return
     */
    protected String checkPicFormat(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()); // 获取文件后缀
        if (!Arrays.asList(this.imgSuffixs).contains(suffix.toLowerCase())) {
            return "图片格式只能是jpeg,gif,jpg,png,bmp";
        }
        return null;
    }

   
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
