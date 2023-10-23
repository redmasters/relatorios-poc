package io.red.relatoriospoc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class ReportImageUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReportImageUtil.class);
    public BufferedImage getBufferedImage(String path) throws IOException {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var iconStream = classLoader.getResourceAsStream(path);
        if(iconStream == null) {
            throw new IOException("Imagem não encontrada no caminho do recurso: " + path);
        }

        logger.info("iconStream: {}", iconStream);
        return ImageIO.read(iconStream);
    }
}
