package com.example.sensorproject_ball

import android.view.View
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color

class BallView : View {
    var maxX :Int = 0
    var maxY :Int = 0
    var ball : Circle
    constructor(context: Context) : super(context) {
        ball=Circle(Color.GRAY)
        ball.setCoordinates((maxX/2).toFloat(), (maxY/2).toFloat(), 100)     //from the middle
        ball.setSpeed(0, 0)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        ball.draw(canvas)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        maxX=w
        maxY=h
        ball.setMax(maxX,maxY)
    }
}