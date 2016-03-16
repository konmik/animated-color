package info.android15.color3d;

import android.graphics.Color;

import org.junit.Test;

import static android.graphics.Color.alpha;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimatedColorTest {

    @Test
    public void testToHSV() throws Exception {
        assertArrayEquals(new float[]{0, 0, 0}, AnimatedColor.toHSV(Color.BLACK), 0);
        assertArrayEquals(new float[]{0, 1, 1}, AnimatedColor.toHSV(Color.RED), 0);
    }

    @Test
    public void testTo3D() throws Exception {
        assertArrayEquals(new float[]{0, 0, 0}, AnimatedColor.toVector(new float[]{0, 0, 0}), 0.00001f);
        assertArrayEquals(new float[]{1, 0, 0}, AnimatedColor.toVector(new float[]{0, 1, 0}), 0.00001f);
        assertArrayEquals(new float[]{1, 0, 1}, AnimatedColor.toVector(new float[]{0, 1, 1}), 0.00001f);
        assertArrayEquals(new float[]{-1, 0, 1}, AnimatedColor.toVector(new float[]{180, 1, 1}), 0.00001f);
    }

    @Test
    public void testFrom3DToHSV() throws Exception {
        assertArrayEquals(new float[]{0, 0, 0}, AnimatedColor.toHSV(new float[]{0, 0, 0}), 0.00001f);
        assertArrayEquals(new float[]{0, 1, 0}, AnimatedColor.toHSV(new float[]{1, 0, 0}), 0.00001f);
        assertArrayEquals(new float[]{0, 1, 1}, AnimatedColor.toHSV(new float[]{1, 0, 1}), 0.00001f);
        assertArrayEquals(new float[]{180, 1, 1}, AnimatedColor.toHSV(new float[]{-1, 0, 1}), 0.00001f);
    }

    @Test
    public void testWith() throws Exception {
        assertEquals(Color.BLACK, new AnimatedColor(Color.BLACK, Color.BLACK).with(0));
        assertEquals(Color.RED, new AnimatedColor(Color.BLACK, Color.RED).with(1));
        assertEquals(0xFF808080, new AnimatedColor(Color.BLACK, Color.WHITE).with(0.5f));
        assertEquals(0xFF803F3F, new AnimatedColor(0xFFFF0000, 0xFF000000).with(0.5f));
        assertEquals(0xFFFF7FFF, new AnimatedColor(0xFFFF0000, 0xFF0000FF).with(0.5f));
        assertEquals(0xFFFFFFFF, new AnimatedColor(0xFFFFFF00, 0xFF0000FF).with(0.5f));
        assertEquals(0xFFFFFF7F, new AnimatedColor(0xFFFFFF00, 0xFF0000FF).with(0.25f));
        assertEquals(0xFF8080FF, new AnimatedColor(0xFFFFFF00, 0xFF0000FF).with(0.75f));
    }

    @Test
    public void testSpecialCases() throws Exception {
        int c = new AnimatedColor(0xFF00FF00, 0xFF0000FF).with(0.90f);
        assertTrue(alpha(c) == 0xFF);
        assertTrue(red(c) < 0x30);
        assertTrue(green(c) > 0x10 && green(c) < 0x80);
        assertTrue(blue(c) > 0xE0);
    }
}
