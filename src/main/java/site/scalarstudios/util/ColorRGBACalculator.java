package site.scalarstudios.util;

import net.minecraft.util.ColorRGBA;

public class ColorRGBACalculator {
    // Gravel and Sand Vanilla Values
    private static final ColorRGBA gravelDustColor = new ColorRGBA(-8356741); // 0x80807B
    private static final ColorRGBA sandDustColor = new ColorRGBA(14406560);   // 0xDBD3A0

    // Approximate Base Colors for Stone Variants
    public static ColorRGBA cobblestoneBase = new ColorRGBA(8224125); // 0x7D7D7D
    public static ColorRGBA blackstoneBase = new ColorRGBA(3099439);  // 0x2F2B2F

    /**
     * Averages a stone base RGBA color with the gravel and sand dust colors to create a layered road color.
     *
     * @param stoneBase The base color of the stone.
     * @return The resulting layered road color.
     */
    public static ColorRGBA generateLayeredRoadColorRGBA(ColorRGBA stoneBase) {
        int baseRGB = stoneBase.rgba() & 0xFFFFFF;
        int gravelRGB = gravelDustColor.rgba() & 0xFFFFFF;
        int sandRGB = sandDustColor.rgba() & 0xFFFFFF;

        int red = (((baseRGB >> 16) & 0xFF) + ((gravelRGB >> 16) & 0xFF) + ((sandRGB >> 16) & 0xFF)) / 3;
        int green = (((baseRGB >> 8) & 0xFF) + ((gravelRGB >> 8) & 0xFF) + ((sandRGB >> 8) & 0xFF)) / 3;
        int blue = ((baseRGB & 0xFF) + (gravelRGB & 0xFF) + (sandRGB & 0xFF)) / 3;

        int averagedRGB = (red << 16) | (green << 8) | blue;
        return new ColorRGBA(averagedRGB);
    }
}
