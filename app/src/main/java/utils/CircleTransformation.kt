package utils

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransformation : Transformation {
    override fun key(): String = "circle"

    override fun transform(source: Bitmap): Bitmap? {
        val size = Math.min(source.width, source.height)
        val width = (source.width - size) /2
        val height = (source.height - size) /2
        val squareBitmap = Bitmap.createBitmap(source, width, height, size, size)
        if(squareBitmap != source) {
            source.recycle()
        }
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squareBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true
        val range = size / 2.1f
        canvas.drawCircle(range, range, range, paint)
        squareBitmap.recycle()
        return bitmap
    }
}