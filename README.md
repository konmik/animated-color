
# AnimatedColor

This is a color animator that has a correct interpolation in the HSV 3D space.

The algorithm makes a standard 3D math interpolation between
two 3D points and thus it allows to make a visually perfect color shift.

The usual direct interpolation of HSV values results in a stranger behavior,
in example you can see a blink of red color while you're interpolating from
blue to white.

# Usage

Just copy/paste the class into your project:
[AnimatedColor.java](https://github.com/konmik/animated-color/blob/master/animated-color/src/main/java/info/android15/color3d/AnimatedColor.java)

``` java
    AnimatedColor color = new AnimatedColor(Color.RED, Color.WHITE);
    int resultColor = color.with(0.5f);
```

# A notice

While this algorithm is OK for usage in a usual Android application,
it is not as efficient as is should be for usage in games.
You may probably want to make use of temporary static variables
instead of allocating small `float[]` buffers every time `with` is called.
