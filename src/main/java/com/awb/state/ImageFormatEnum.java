package com.awb.state;

import org.apache.commons.lang.StringUtils;

public enum ImageFormatEnum {
    BMP("bmp"),
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    ICO("ico");

    private String suffix;

    ImageFormatEnum(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static boolean vaildateImage(String suffix) {
        for (ImageFormatEnum each : ImageFormatEnum.values()) {
            if (StringUtils.equalsIgnoreCase(suffix, each.getSuffix()))
                return true;
        }
        return false;
    }
}
