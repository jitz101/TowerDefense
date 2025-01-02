package main;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Text {
    public Text() {
    }

    public Font pixelFont(int size) {
        Font pixelFont;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf")))
                    .deriveFont((float)size);
        } catch (FontFormatException | IOException e) {
            System.err.println("Font Error: " + e.getMessage());
            pixelFont = new Font("Monospaced", Font.PLAIN, size);
        }
        return pixelFont;
    }
}
