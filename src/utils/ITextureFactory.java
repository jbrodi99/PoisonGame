package utils;

import java.awt.*;

public interface ITextureFactory {
    Image createTexture(String type, String number, int width, int height);
    Image createTexture(String type, String number, int index, int width, int height);
    Image createTexture(String path,int width, int height);
}
