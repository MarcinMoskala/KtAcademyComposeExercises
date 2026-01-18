package kt.academy

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object Icons {
    val MilkIcon: ImageVector by lazy {
        ImageVector.Builder(
            name = "Milk",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 512.0f,
            viewportHeight = 512.0f
        ).apply {
            // Milk bottle outline path
            path(
                fill = SolidColor(Color.Black),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(371.891f, 118.434f)
                lineToRelative(-22.593f, -35.358f)
                curveTo(350.313f, 82.232f, 351.375f, 81.435f, 352.313f, 80.482f)
                lineToRelative(-0.015f, 0.023f)
                curveToRelative(7.343f, -7.305f, 11.905f, -17.539f, 11.905f, -28.694f)
                verticalLineTo(40.577f)
                curveToRelative(0.0f, -11.14f, -4.546f, -21.312f, -11.835f, -28.625f)
                lineToRelative(0.32f, 0.298f)
                lineToRelative(-0.359f, -0.36f)
                curveToRelative(-0.016f, 0.0f, -0.016f, -0.007f, -0.016f, -0.007f)
                curveToRelative(-7.234f, -7.242f, -17.296f, -11.765f, -28.327f, -11.867f)
                curveToRelative(-0.016f, 0.0f, -0.125f, 0.0f, -0.187f, 0.0f)
                curveTo(323.768f, 0.008f, 323.721f, 0.0f, 323.674f, 0.0f)
                verticalLineToRelative(0.008f)
                curveToRelative(-0.078f, -0.008f, -0.117f, 0.008f, -0.218f, 0.008f)
                horizontalLineToRelative(-67.451f)
                horizontalLineToRelative(-67.608f)
                curveToRelative(-0.031f, 0.0f, -0.047f, -0.008f, -0.07f, -0.008f)
                curveToRelative(-11.132f, 0.008f, -21.327f, 4.539f, -28.624f, 11.851f)
                lineToRelative(0.305f, -0.312f)
                lineToRelative(-0.329f, 0.336f)
                curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, -0.015f, 0.007f)
                verticalLineToRelative(0.008f)
                curveToRelative(-7.312f, 7.312f, -11.859f, 17.508f, -11.875f, 28.679f)
                verticalLineToRelative(11.234f)
                curveToRelative(0.016f, 11.156f, 4.562f, 21.374f, 11.89f, 28.686f)
                lineToRelative(-0.007f, -0.015f)
                curveToRelative(0.945f, 0.953f, 2.007f, 1.75f, 3.038f, 2.594f)
                lineToRelative(-22.601f, 35.358f)
                curveToRelative(-17.382f, 27.202f, -26.624f, 58.834f, -26.624f, 91.122f)
                verticalLineToRelative(216.993f)
                curveToRelative(0.016f, 47.194f, 38.241f, 85.435f, 85.458f, 85.451f)
                horizontalLineToRelative(114.122f)
                curveToRelative(47.202f, -0.016f, 85.435f, -38.257f, 85.45f, -85.451f)
                verticalLineTo(209.556f)
                curveTo(398.515f, 177.268f, 389.281f, 145.636f, 371.891f, 118.434f)
                close()
                moveTo(364.203f, 426.549f)
                curveToRelative(0.0f, 14.164f, -5.702f, 26.858f, -14.983f, 36.163f)
                curveToRelative(-9.297f, 9.273f, -22.0f, 14.96f, -36.155f, 14.976f)
                horizontalLineTo(198.943f)
                curveToRelative(-14.171f, -0.016f, -26.874f, -5.703f, -36.17f, -14.976f)
                curveToRelative(-9.281f, -9.304f, -14.968f, -21.999f, -14.984f, -36.163f)
                verticalLineTo(209.556f)
                curveToRelative(0.0f, -25.749f, 7.359f, -50.959f, 21.234f, -72.654f)
                lineToRelative(37.389f, -58.537f)
                curveToRelative(2.61f, -4.07f, 2.782f, -9.195f, 0.454f, -13.445f)
                curveToRelative(-2.321f, -4.25f, -6.726f, -6.843f, -11.562f, -6.843f)
                horizontalLineToRelative(-6.938f)
                curveToRelative(-1.782f, -0.016f, -3.258f, -0.688f, -4.437f, -1.844f)
                curveToRelative(-1.141f, -1.172f, -1.821f, -2.649f, -1.828f, -4.422f)
                verticalLineTo(40.577f)
                curveToRelative(0.007f, -1.766f, 0.687f, -3.25f, 1.828f, -4.422f)
                curveToRelative(1.179f, -1.156f, 2.656f, -1.828f, 4.437f, -1.828f)
                horizontalLineToRelative(67.639f)
                horizontalLineToRelative(67.638f)
                curveToRelative(1.765f, 0.0f, 3.25f, 0.672f, 4.414f, 1.828f)
                curveToRelative(1.164f, 1.172f, 1.836f, 2.657f, 1.836f, 4.422f)
                verticalLineToRelative(11.234f)
                curveToRelative(0.0f, 1.773f, -0.672f, 3.25f, -1.836f, 4.422f)
                curveToRelative(-1.164f, 1.156f, -2.649f, 1.828f, -4.414f, 1.844f)
                horizontalLineToRelative(-6.945f)
                curveToRelative(-4.836f, 0.0f, -9.242f, 2.594f, -11.57f, 6.843f)
                curveToRelative(-2.328f, 4.25f, -2.157f, 9.375f, 0.453f, 13.445f)
                lineToRelative(37.405f, 58.537f)
                curveToRelative(13.844f, 21.695f, 21.218f, 46.905f, 21.218f, 72.654f)
                verticalLineTo(426.549f)
                close()
            }
            
            // Milk fill path
            path(
                fill = SolidColor(Color.Black),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(184.74f, 234.509f)
                verticalLineToRelative(192.025f)
                curveToRelative(0.0f, 4.859f, 2.281f, 8.172f, 4.125f, 10.031f)
                curveToRelative(1.922f, 1.898f, 5.218f, 4.172f, 10.078f, 4.179f)
                horizontalLineToRelative(114.09f)
                curveToRelative(4.874f, -0.007f, 8.187f, -2.289f, 10.023f, -4.117f)
                curveToRelative(1.914f, -1.922f, 4.195f, -5.234f, 4.195f, -10.078f)
                verticalLineTo(226.29f)
                curveTo(258.27f, 264.664f, 254.879f, 192.681f, 184.74f, 234.509f)
                close()
            }
        }.build()
    }

    val AppleIcon: ImageVector by lazy {
        ImageVector.Builder(
            name = "Apple",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).path(
            fill = SolidColor(Color.Black),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(10.9579f, 8.51864f)
            curveTo(10.9658f, 8.67799f, 10.9797f, 8.83851f, 10.9999f, 9.0f)
            curveTo(12.7875f, 9.22345f, 14.4571f, 8.67892f, 15.7223f, 7.65278f)
            curveTo(15.9571f, 7.63419f, 16.1896f, 7.63077f, 16.4174f, 7.64315f)
            curveTo(17.6741f, 7.71143f, 18.7111f, 8.24574f, 19.2969f, 9.28479f)
            curveTo(19.7417f, 10.0737f, 19.9999f, 10.9403f, 19.9999f, 12.0f)
            curveTo(19.9999f, 14.0817f, 19.0464f, 16.2523f, 17.6503f, 18.3122f)
            curveTo(16.5964f, 19.8671f, 14.5039f, 20.4164f, 12.5622f, 19.7362f)
            lineTo(12.3305f, 19.655f)
            curveTo(12.1165f, 19.58f, 11.8833f, 19.58f, 11.6693f, 19.655f)
            lineTo(11.4376f, 19.7362f)
            curveTo(9.49579f, 20.4164f, 7.40333f, 19.8671f, 6.34947f, 18.3122f)
            curveTo(4.95334f, 16.2523f, 3.99988f, 14.0817f, 3.99988f, 12.0f)
            curveTo(3.99988f, 10.9712f, 4.24322f, 10.1244f, 4.66059f, 9.3544f)
            curveTo(5.2333f, 8.29785f, 6.27177f, 7.74749f, 7.54043f, 7.66705f)
            curveTo(8.66006f, 7.59607f, 9.8982f, 7.90358f, 10.9579f, 8.51864f)
            close()
            moveTo(12.9999f, 3.58597f)
            verticalLineTo(3.0f)
            curveTo(12.9999f, 2.44772f, 12.5522f, 2.0f, 11.9999f, 2.0f)
            curveTo(11.4476f, 2.0f, 10.9999f, 2.44772f, 10.9999f, 3.0f)
            verticalLineTo(6.30987f)
            curveTo(9.85507f, 5.8274f, 8.60755f, 5.59538f, 7.41388f, 5.67106f)
            curveTo(5.62129f, 5.78471f, 3.87417f, 6.60835f, 2.90229f, 8.40131f)
            curveTo(2.33287f, 9.45181f, 1.99988f, 10.6248f, 1.99988f, 12.0f)
            curveTo(1.99988f, 14.6621f, 3.20202f, 17.2331f, 4.6939f, 19.4343f)
            curveTo(6.33311f, 21.8529f, 9.39734f, 22.5259f, 11.9999f, 21.6575f)
            curveTo(14.6024f, 22.5259f, 17.6667f, 21.8528f, 19.3059f, 19.4343f)
            curveTo(20.7977f, 17.2331f, 21.9999f, 14.662f, 21.9999f, 12.0f)
            curveTo(21.9999f, 10.5813f, 21.6455f, 9.3779f, 21.0391f, 8.30249f)
            curveTo(20.1929f, 6.80168f, 18.8033f, 5.99975f, 17.3096f, 5.73573f)
            curveTo(17.8976f, 4.64129f, 18.1692f, 3.35452f, 17.9999f, 2.0f)
            curveTo(16.0783f, 1.7598f, 14.2931f, 2.40702f, 12.9999f, 3.58597f)
            close()
        }.build()
    }

    val FavoriteIcon: ImageVector by lazy {
        ImageVector.Builder(
            name = "Favorite",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).path(
            fill = SolidColor(Color.Black),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            // This is the SVG path data for the Material Design "Favorite" icon
            moveTo(12.0f, 21.35f)
            lineTo(10.55f, 20.03f)
            curveTo(5.4f, 15.36f, 2.0f, 12.28f, 2.0f, 8.5f)
            curveTo(2.0f, 5.42f, 4.42f, 3.0f, 7.5f, 3.0f)
            curveTo(9.24f, 3.0f, 10.91f, 3.81f, 12.0f, 5.09f)
            curveTo(13.09f, 3.81f, 14.76f, 3.0f, 16.5f, 3.0f)
            curveTo(19.58f, 3.0f, 22.0f, 5.42f, 22.0f, 8.5f)
            curveTo(22.0f, 12.28f, 18.6f, 15.36f, 13.45f, 20.04f)
            lineTo(12.0f, 21.35f)
            close()
        }.build()
    }

    val SomeLogoIcon: ImageVector by lazy {
        ImageVector.Builder(
            name = "ComposeLogo",
            defaultWidth = 200.dp,
            defaultHeight = 200.dp,
            viewportWidth = 200f,
            viewportHeight = 200f
        ).apply {
            // Blue element (top-left)
            path(
                fill = SolidColor(Color(0xFF4285F4)),
                stroke = null,
                strokeLineWidth = 0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(50f, 60f)
                lineTo(90f, 60f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, 10f)
                lineTo(100f, 110f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, 10f)
                lineTo(50f, 120f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, -10f)
                lineTo(40f, 70f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, -10f)
                close()
            }

            // Green element (middle)
            path(
                fill = SolidColor(Color(0xFF34A853)),
                stroke = null,
                strokeLineWidth = 0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(80f, 90f)
                lineTo(120f, 90f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, 10f)
                lineTo(130f, 140f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, 10f)
                lineTo(80f, 150f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, -10f)
                lineTo(70f, 100f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, -10f)
                close()
            }

            // Teal element (bottom-right)
            path(
                fill = SolidColor(Color(0xFF00BCD4)),
                stroke = null,
                strokeLineWidth = 0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(110f, 50f)
                lineTo(150f, 50f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, 10f)
                lineTo(160f, 100f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, 10f)
                lineTo(110f, 110f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, -10f)
                lineTo(100f, 60f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, -10f)
                close()
            }

            // Purple accent element
            path(
                fill = SolidColor(Color(0xFF9C27B0)),
                stroke = null,
                strokeLineWidth = 0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(50f, 130f)
                lineTo(90f, 130f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, 10f)
                lineTo(100f, 160f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, 10f)
                lineTo(50f, 170f)
                arcToRelative(10f, 10f, 0f, false, true, -10f, -10f)
                lineTo(40f, 140f)
                arcToRelative(10f, 10f, 0f, false, true, 10f, -10f)
                close()
            }
        }.build()
    }

    val ComposeLogoIcon: ImageVector by lazy {
        ImageVector.Builder(
            name = "ComposeLogo",
            defaultWidth = 200.0.dp,
            defaultHeight = 200.0.dp,
            viewportWidth = 600.0f,
            viewportHeight = 649.25f
        ).apply {
            // Small detail path 1 - dark navy blue accent
            path(
                fill = SolidColor(Color(0xFF083042)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(217.85f, 372.88f)
                curveTo(217.004f, 371.447f, 216.437f, 369.849f, 216.197f, 368.172f)
                curveTo(216.437f, 369.849f, 217.004f, 371.446f, 217.85f, 372.88f)
                close()
            }

            // Small detail path 2 - dark navy blue accent
            path(
                fill = SolidColor(Color(0xFF083042)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(217.707f, 279.602f)
                horizontalLineToRelative(-0f)
                curveTo(216.909f, 281.503f, 216.377f, 283.54f, 216.172f, 285.673f)
                curveTo(216.377f, 283.54f, 216.909f, 281.503f, 217.707f, 279.602f)
                close()
            }

            // Small detail path 3 - dark navy blue accent
            path(
                fill = SolidColor(Color(0xFF083042)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(219.897f, 375.503f)
                curveTo(220.694f, 376.287f, 221.6f, 376.973f, 222.6f, 377.537f)
                lineTo(290.787f, 416.052f)
                lineTo(222.6f, 377.537f)
                curveTo(221.6f, 376.973f, 220.694f, 376.287f, 219.897f, 375.503f)
                close()
            }

            // Dark navy blue left face - MAIN SHAPE
            path(
                fill = SolidColor(Color(0xFF083042)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(301.274f, 464.738f)
                curveTo(301.092f, 464.743f, 300.91f, 464.759f, 300.728f, 464.76f)
                curveTo(295.744f, 464.801f, 290.743f, 463.554f, 286.255f, 461.02f)
                lineTo(184.531f, 403.561f)
                curveTo(178.559f, 400.2f, 174.852f, 393.899f, 174.785f, 387.052f)
                lineTo(174.783f, 270.219f)
                curveTo(174.733f, 265.233f, 175.719f, 260.426f, 177.742f, 256.191f)
                lineTo(98.209f, 211.634f)
                curveTo(94.86f, 218.633f, 93.23f, 226.578f, 93.312f, 234.82f)
                lineTo(93.315f, 427.671f)
                curveTo(93.425f, 438.973f, 99.545f, 449.374f, 109.402f, 454.921f)
                lineTo(277.313f, 549.764f)
                curveTo(284.721f, 553.948f, 292.975f, 556.005f, 301.202f, 555.937f)
                lineTo(301.204f, 555.801f)
                curveTo(301.393f, 543.776f, 301.333f, 495.262f, 301.274f, 464.738f)
                close()
            }

            // Small detail path 4 - green accent (#3DDB85)
            path(
                fill = SolidColor(Color(0xFF3DDB85)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(381.24f, 277.467f)
                verticalLineToRelative(-0f)
                curveTo(381.049f, 277.151f, 380.855f, 276.837f, 380.647f, 276.532f)
                curveTo(380.183f, 275.849f, 379.677f, 275.193f, 379.134f, 274.566f)
                curveTo(379.677f, 275.192f, 380.183f, 275.849f, 380.647f, 276.532f)
                curveTo(380.855f, 276.837f, 381.049f, 277.151f, 381.24f, 277.467f)
                close()
            }

            // Small detail path 5 - green accent
            path(
                fill = SolidColor(Color(0xFF3DDB85)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(218.641f, 277.617f)
                curveTo(219.057f, 276.883f, 219.523f, 276.179f, 220.04f, 275.511f)
                curveTo(219.523f, 276.179f, 219.056f, 276.882f, 218.641f, 277.617f)
                close()
            }

            // Small detail path 6 - green accent
            path(
                fill = SolidColor(Color(0xFF3DDB85)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(297.365f, 230.757f)
                curveTo(297.823f, 230.72f, 298.282f, 230.696f, 298.742f, 230.691f)
                curveTo(298.282f, 230.696f, 297.823f, 230.72f, 297.365f, 230.757f)
                close()
            }

            // Green top face (main green)
            path(
                fill = SolidColor(Color(0xFF3DDC84)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(177.75f, 256.173f)
                curveTo(179.929f, 251.616f, 183.313f, 247.726f, 187.919f, 245.004f)
                lineTo(283.682f, 188.511f)
                curveTo(292.591f, 183.247f, 303.627f, 183.145f, 312.639f, 188.233f)
                lineTo(409.451f, 242.913f)
                curveTo(413.767f, 245.354f, 417.547f, 248.799f, 420.312f, 252.876f)
                curveTo(420.323f, 252.892f, 420.332f, 252.909f, 420.343f, 252.925f)
                lineTo(498.594f, 206.193f)
                curveTo(494.029f, 199.462f, 487.789f, 193.775f, 480.665f, 189.747f)
                lineTo(320.864f, 99.49f)
                curveTo(305.989f, 91.092f, 287.771f, 91.259f, 273.066f, 99.949f)
                lineTo(114.995f, 193.198f)
                curveTo(107.391f, 197.69f, 101.807f, 204.111f, 98.209f, 211.633f)
                lineTo(177.742f, 256.19f)
                curveTo(177.745f, 256.185f, 177.747f, 256.179f, 177.75f, 256.173f)
                close()
            }

            // Blue right face
            path(
                fill = SolidColor(Color(0xFF4285F4)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(498.594f, 206.194f)
                lineTo(420.343f, 252.926f)
                curveTo(423.34f, 257.344f, 425.164f, 262.483f, 425.217f, 267.85f)
                lineTo(425.215f, 379.033f)
                curveTo(425.309f, 389.385f, 419.883f, 398.994f, 410.975f, 404.249f)
                lineTo(315.211f, 460.751f)
                curveTo(310.905f, 463.296f, 306.101f, 464.609f, 301.273f, 464.738f)
                curveTo(301.332f, 495.262f, 301.391f, 543.776f, 301.204f, 555.803f)
                lineTo(301.202f, 555.939f)
                curveTo(309.475f, 555.872f, 317.735f, 553.681f, 325.11f, 549.322f)
                lineTo(483.182f, 456.057f)
                curveTo(497.887f, 447.382f, 506.843f, 431.522f, 506.688f, 414.434f)
                lineTo(506.69f, 230.91f)
                curveTo(506.604f, 222.018f, 503.573f, 213.503f, 498.594f, 206.194f)
                close()
            }

            // Light blue center hexagon
            path(
                fill = SolidColor(Color(0xFFD6F0FF)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(217.707f, 279.602f)
                curveTo(216.909f, 281.503f, 216.377f, 283.54f, 216.172f, 285.673f)
                curveTo(216.094f, 286.491f, 216.058f, 287.32f, 216.067f, 288.157f)
                lineTo(216.068f, 366.471f)
                curveTo(216.074f, 367.045f, 216.117f, 367.613f, 216.197f, 368.172f)
                curveTo(216.437f, 369.849f, 217.004f, 371.447f, 217.85f, 372.88f)
                curveTo(218.414f, 373.836f, 219.101f, 374.718f, 219.898f, 375.503f)
                curveTo(220.695f, 376.287f, 221.601f, 376.974f, 222.601f, 377.537f)
                lineTo(290.788f, 416.052f)
                curveTo(293.796f, 417.751f, 297.148f, 418.586f, 300.489f, 418.559f)
                curveTo(300.731f, 418.557f, 300.973f, 418.541f, 301.215f, 418.53f)
                curveTo(304.33f, 418.389f, 307.419f, 417.515f, 310.198f, 415.872f)
                lineTo(374.389f, 377.998f)
                curveTo(374.762f, 377.778f, 375.126f, 377.546f, 375.481f, 377.304f)
                curveTo(376.19f, 376.819f, 376.862f, 376.291f, 377.493f, 375.725f)
                curveTo(378.44f, 374.875f, 379.295f, 373.939f, 380.05f, 372.932f)
                curveTo(380.554f, 372.261f, 381.013f, 371.558f, 381.424f, 370.828f)
                curveTo(381.693f, 370.351f, 381.934f, 369.859f, 382.163f, 369.361f)
                curveTo(382.412f, 368.818f, 382.636f, 368.264f, 382.834f, 367.699f)
                curveTo(382.903f, 367.502f, 382.97f, 367.304f, 383.033f, 367.105f)
                curveTo(383.636f, 365.188f, 383.953f, 363.165f, 383.934f, 361.096f)
                lineTo(383.935f, 286.569f)
                curveTo(383.922f, 285.215f, 383.74f, 283.883f, 383.412f, 282.59f)
                curveTo(383.193f, 281.728f, 382.909f, 280.883f, 382.566f, 280.061f)
                curveTo(382.395f, 279.65f, 382.208f, 279.245f, 382.008f, 278.846f)
                curveTo(381.773f, 278.377f, 381.512f, 277.919f, 381.24f, 277.469f)
                curveTo(381.049f, 277.153f, 380.855f, 276.839f, 380.647f, 276.533f)
                curveTo(380.183f, 275.85f, 379.677f, 275.193f, 379.134f, 274.566f)
                curveTo(377.504f, 272.687f, 375.536f, 271.081f, 373.366f, 269.854f)
                lineTo(308.472f, 233.202f)
                curveTo(307.339f, 232.563f, 306.159f, 232.045f, 304.949f, 231.65f)
                curveTo(303.336f, 231.123f, 301.671f, 230.814f, 299.997f, 230.721f)
                curveTo(299.579f, 230.698f, 299.16f, 230.688f, 298.741f, 230.692f)
                curveTo(298.282f, 230.696f, 297.824f, 230.721f, 297.365f, 230.758f)
                curveTo(296.233f, 230.849f, 295.109f, 231.043f, 294.004f, 231.333f)
                curveTo(293.798f, 231.387f, 293.593f, 231.447f, 293.388f, 231.508f)
                curveTo(291.894f, 231.953f, 290.439f, 232.574f, 289.063f, 233.388f)
                lineTo(224.872f, 271.255f)
                curveTo(222.942f, 272.395f, 221.333f, 273.841f, 220.04f, 275.511f)
                curveTo(219.523f, 276.179f, 219.057f, 276.883f, 218.641f, 277.617f)
                curveTo(218.433f, 277.984f, 218.238f, 278.359f, 218.055f, 278.741f)
                curveTo(217.922f, 279.019f, 217.827f, 279.317f, 217.707f, 279.602f)
                close()
            }

            // Dark overlay on left (darker shadow)
            path(
                fill = SolidColor(Color(0xFF041619)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(301.214f, 418.529f)
                curveTo(300.972f, 418.54f, 300.73f, 418.556f, 300.488f, 418.558f)
                curveTo(297.147f, 418.585f, 293.795f, 417.75f, 290.787f, 416.051f)
                lineTo(222.6f, 377.536f)
                curveTo(221.599f, 376.973f, 220.693f, 376.286f, 219.897f, 375.502f)
                curveTo(219.1f, 374.717f, 218.413f, 373.835f, 217.849f, 372.879f)
                curveTo(217.003f, 371.446f, 216.436f, 369.848f, 216.196f, 368.171f)
                curveTo(216.116f, 367.612f, 216.072f, 367.044f, 216.067f, 366.47f)
                lineTo(216.066f, 288.156f)
                curveTo(216.058f, 287.319f, 216.093f, 286.49f, 216.171f, 285.672f)
                curveTo(216.376f, 283.539f, 216.908f, 281.502f, 217.706f, 279.601f)
                lineTo(199.294f, 268.264f)
                lineTo(177.742f, 256.19f)
                curveTo(175.719f, 260.426f, 174.733f, 265.232f, 174.783f, 270.218f)
                lineTo(174.785f, 387.051f)
                curveTo(174.852f, 393.898f, 178.559f, 400.199f, 184.531f, 403.56f)
                lineTo(286.256f, 461.018f)
                curveTo(290.744f, 463.552f, 295.745f, 464.799f, 300.729f, 464.758f)
                curveTo(300.911f, 464.757f, 301.093f, 464.741f, 301.275f, 464.736f)
                curveTo(301.246f, 449.843f, 301.217f, 439.232f, 301.217f, 439.232f)
                lineTo(301.214f, 418.529f)
                close()
            }

            // Green overlay (darker green accent)
            path(
                fill = SolidColor(Color(0xFF37BF6E)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(409.451f, 242.913f)
                lineTo(312.639f, 188.233f)
                curveTo(303.627f, 183.145f, 292.59f, 183.247f, 283.681f, 188.511f)
                lineTo(187.918f, 245.004f)
                curveTo(183.312f, 247.725f, 179.928f, 251.615f, 177.749f, 256.173f)
                curveTo(177.746f, 256.179f, 177.744f, 256.185f, 177.741f, 256.191f)
                lineTo(199.293f, 268.265f)
                lineTo(217.705f, 279.602f)
                curveTo(217.825f, 279.317f, 217.92f, 279.019f, 218.053f, 278.741f)
                curveTo(218.236f, 278.359f, 218.431f, 277.984f, 218.639f, 277.617f)
                curveTo(219.055f, 276.883f, 219.521f, 276.179f, 219.938f, 275.511f)
                curveTo(221.231f, 273.841f, 222.84f, 272.395f, 224.77f, 271.255f)
                lineTo(288.961f, 233.387f)
                curveTo(290.338f, 232.574f, 291.792f, 231.953f, 293.286f, 231.507f)
                curveTo(293.491f, 231.446f, 293.696f, 231.386f, 293.901f, 231.332f)
                curveTo(295.006f, 231.042f, 296.13f, 230.848f, 297.262f, 230.757f)
                curveTo(297.72f, 230.72f, 298.179f, 230.695f, 298.638f, 230.691f)
                curveTo(299.057f, 230.687f, 299.476f, 230.697f, 299.894f, 230.72f)
                curveTo(301.568f, 230.813f, 303.233f, 231.122f, 304.846f, 231.649f)
                curveTo(306.056f, 232.044f, 307.236f, 232.561f, 308.369f, 233.201f)
                lineTo(373.263f, 269.853f)
                curveTo(375.433f, 271.08f, 377.4f, 272.686f, 379.031f, 274.565f)
                curveTo(379.574f, 275.191f, 380.08f, 275.848f, 380.544f, 276.532f)
                curveTo(380.752f, 276.838f, 380.946f, 277.152f, 381.137f, 277.467f)
                lineTo(397.691f, 266.393f)
                lineTo(420.241f, 252.926f)
                curveTo(420.23f, 252.91f, 420.221f, 252.893f, 420.21f, 252.877f)
                curveTo(417.547f, 248.799f, 413.767f, 245.354f, 409.451f, 242.913f)
                close()
            }

            // Blue overlay (darker blue accent)
            path(
                fill = SolidColor(Color(0xFF3870B2)),
                stroke = null,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(381.24f, 277.467f)
                curveTo(381.513f, 277.918f, 381.773f, 278.376f, 382.008f, 278.845f)
                curveTo(382.208f, 279.244f, 382.394f, 279.649f, 382.566f, 280.06f)
                curveTo(382.909f, 280.882f, 383.193f, 281.727f, 383.412f, 282.589f)
                curveTo(383.74f, 283.882f, 383.922f, 285.215f, 383.935f, 286.568f)
                lineTo(383.934f, 361.095f)
                curveTo(383.953f, 363.165f, 383.636f, 365.187f, 383.032f, 367.104f)
                curveTo(382.969f, 367.304f, 382.903f, 367.502f, 382.833f, 367.698f)
                curveTo(382.635f, 368.263f, 382.411f, 368.817f, 382.162f, 369.36f)
                curveTo(381.934f, 369.858f, 381.693f, 370.35f, 381.423f, 370.827f)
                curveTo(381.011f, 371.556f, 380.552f, 372.259f, 380.049f, 372.931f)
                curveTo(379.294f, 373.938f, 378.438f, 374.874f, 377.491f, 375.724f)
                curveTo(376.86f, 376.291f, 376.189f, 376.819f, 375.479f, 377.303f)
                curveTo(375.124f, 377.545f, 374.76f, 377.777f, 374.387f, 377.997f)
                lineTo(310.196f, 415.871f)
                curveTo(307.417f, 417.514f, 304.328f, 418.389f, 301.213f, 418.53f)
                lineTo(301.216f, 439.234f)
                curveTo(301.216f, 439.234f, 301.245f, 449.845f, 301.274f, 464.738f)
                curveTo(306.103f, 464.609f, 310.907f, 463.296f, 315.212f, 460.751f)
                lineTo(410.976f, 404.249f)
                curveTo(419.884f, 398.994f, 425.31f, 389.385f, 425.216f, 379.033f)
                lineTo(425.218f, 267.85f)
                curveTo(425.165f, 262.483f, 423.341f, 257.344f, 420.344f, 252.926f)
                lineTo(397.794f, 266.393f)
                lineTo(381.24f, 277.467f)
                close()
            }
        }.build()
    }
}