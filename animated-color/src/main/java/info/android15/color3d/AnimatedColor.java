package info.android15.color3d;

import android.graphics.Color;

/**
 * This is a color animator that has a correct interpolation in HSV 3D space.
 *
 * The algorithm makes a standard 3D math interpolation between
 * two 3D points and thus it allows to make a visually perfect color shift.
 *
 * The usual direct interpolation of HSV values makes stranger behavior,
 * in example you can see red color while you're interpolating from
 * blue to white.
 */
public class AnimatedColor {

    private static final float ERROR = 0.001f;

    private final int start;
    private final int end;
    private final float[] vector0;
    private final float[] vector1;

    public AnimatedColor(int start, int end) {
        this.start = start;
        this.end = end;
        this.vector0 = toVector(toHSV(start));
        this.vector1 = toVector(toHSV(end));
    }

    public int with(float delta) {
        if (delta <= 0)
            return start;
        if (delta >= 1)
            return end;
        return Color.HSVToColor(toHSV(move(vector0, vector1, delta)));
    }

    public static float[] move(float[] vector0, float[] vector1, float delta) {
        float[] vector = new float[3];
        vector[0] = (vector1[0] - vector0[0]) * delta + vector0[0];
        vector[1] = (vector1[1] - vector0[1]) * delta + vector0[1];
        vector[2] = (vector1[2] - vector0[2]) * delta + vector0[2];
        return vector;
    }

    public static float[] toHSV(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv;
    }

    public static float[] toVector(float[] hsv) {
        float[] vector = new float[3];
        double rad = Math.PI * hsv[0] / 180;
        vector[0] = (float) Math.cos(rad) * hsv[1];
        vector[1] = (float) Math.sin(rad) * hsv[1];
        vector[2] = hsv[2];
        return vector;
    }

    public static float[] toHSV(float[] vector) {
        float[] hsv = new float[3];
        hsv[1] = (float) Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        hsv[0] = hsv[1] < ERROR ? 0 :
            (float) (Math.atan2(vector[1] / hsv[1], vector[0] / hsv[1]) * 180 / Math.PI);
        if (hsv[0] < 0)
            hsv[0] += 360f;
        hsv[2] = vector[2];
        return hsv;
    }
}
