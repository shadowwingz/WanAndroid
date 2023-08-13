package com.shadowwingz.wanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.extension.dp

/**
 * 带外边框的 TextView
 */
@SuppressLint("AppCompatCustomView")
class BorderTextView @JvmOverloads constructor(
  context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attr, defStyleAttr) {

  init {
    setWillNotDraw(false)
  }

  private val cornerRadius = 2f.dp
  private val lineWidth = 2f.dp
  private var rect: RectF? = null

  private val rootView: RelativeLayout by lazy {
    LayoutInflater.from(context).inflate(R.layout.layout_border_text_view, this, true) as RelativeLayout
  }

  private val tvContent: TextView by lazy {
    rootView.findViewById(R.id.tv_content)
  }

  private val paintColor = context.resources.getColor(R.color.color_text_border)

  private val paint = Paint().apply {
    color = paintColor
    strokeWidth = lineWidth
    isAntiAlias = true
    style = Paint.Style.STROKE
  }

  var content: String = ""
    set(value) {
      field = value
      tvContent.apply {
        text = value
        setTextColor(paintColor)
      }
    }

  override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
    rect = RectF(
      lineWidth / 2,
      lineWidth / 2,
      width.toFloat() - lineWidth / 2,
      height.toFloat() - lineWidth / 2
    )
  }

  override fun onDraw(canvas: Canvas) {
    rect?.let { canvas.drawRoundRect(it, cornerRadius, cornerRadius, paint) }
    super.onDraw(canvas)
  }
}