package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextureFactory implements ITextureFactory{

    private final Map<String, Image> textureCached = new ConcurrentHashMap<>();

    @Override
    public Image createTexture(String type, String number, int width, int height) {
        String path = "src/resources/img/cards/texture_" + type + "_" + number + ".png";
        if (!textureCached.containsKey(path)){
            loadTexture(path,width,height);
        }
        return textureCached.get(path);
    }

    @Override
    public Image createTexture(String type, String number, int index, int width, int height) {
        String path = "src/resources/img/cards/texture_" + type + "_" + number + "_" + index + "_perspective.png";
        if (!textureCached.containsKey(path)){
            loadTexture(path,width,height);
        }
        return textureCached.get(path);
    }

    private void loadTexture(String path, int width, int height){
        BufferedImage imageIO;
        Image imgEsc;
        try {
            imageIO = ImageIO.read(new File(path));
            imgEsc = imageIO.getScaledInstance(width,height,Image.SCALE_SMOOTH);
            textureCached.put(path, imgEsc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
