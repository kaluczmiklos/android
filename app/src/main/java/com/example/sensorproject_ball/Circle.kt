package com.example.sensorproject_ball

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Circle {
    var leftTopX : Float=0F
    var leftTopY : Float=0F
    var radius:Int=0
    var paint: Paint
    var bounds:RectF
    var speedX :Int=0
    var speedY :Int=0
    var maxX:Int=0
    var maxY:Int=0

    constructor(color: Int) {
        paint = Paint()
        paint.setColor(color)
        bounds = RectF()
    }
    fun setCoordinates(x:Float,y:Float,r:Int){
        leftTopX=x
        leftTopY=y
        this.radius=r
        bounds.set(leftTopX,leftTopY,leftTopX+2*this.radius,leftTopY+2*this.radius)
    }
    fun draw(canvas: Canvas){
        canvas.drawOval(bounds,paint)
        update()
    }
    fun setMax(maxX:Int,maxY:Int){
        this.maxX=maxX
        this.maxY=maxY
    }
    fun update(){
        if(leftTopX+2*radius>maxX && speedX>=0)   //maxX-->right side
            speedX=0               //l/u/d
        else if(leftTopX<0 && speedX<=0)          //minX-->left side
            speedX=0               //r/u/d
        if(leftTopY+2*radius>maxY && speedY>=0)   //maxY-->bottom
            speedY=0               //r/l/u
        else if(leftTopY<0 && speedY<=0)          //minY-->top
            speedY=0               //r/l/d

        leftTopX+=speedX
        leftTopY+=speedY
        bounds.set(leftTopX,leftTopY,leftTopX+2*radius,leftTopY+2*radius)
    }
    fun setSpeed(speedX:Int,speedY:Int){
        this.speedX=speedX      //accelero sides
        this.speedY=speedY      //accelero upDown
    }
}