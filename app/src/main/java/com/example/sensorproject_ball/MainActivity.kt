package com.example.sensorproject_ball

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var ballView: BallView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ballView=BallView(this)
        setContentView(ballView)
        ballView.setBackgroundColor(Color.BLACK)

        setUpSensorStuff()
    }

    private fun setUpSensorStuff(){
        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also{
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
       if(event?.sensor?.type==Sensor.TYPE_ACCELEROMETER){
           var sides=event.values[0]
           var upDown=event.values[1]

           var color=Color.BLACK
           if(upDown.toInt()==0 && sides.toInt()==0) color=Color.GRAY

           else if(upDown.toInt()<0 && sides.toInt()==0) color=Color.RED
           else if(upDown.toInt()>0 && sides.toInt()==0) color=Color.YELLOW

           else if(sides.toInt()>0 && upDown.toInt()==0) color=Color.GREEN
           else if(sides.toInt()<0 && upDown.toInt()==0) color=Color.BLUE

           else color=Color.WHITE
           /*
           else if(upDown.toInt()<0 && sides.toInt()>0) color=Color.CYAN
           else if(upDown.toInt()<0 && sides.toInt()<0) color=Color.MAGENTA
           else if(upDown.toInt()>0 && sides.toInt()>0) color=Color.LTGRAY
           else if(upDown.toInt()>0 && sides.toInt()<0) color=Color.LTGRAY
           */
           sides*=(-20)
           upDown*=20
           ballView.ball.setSpeed(sides.toInt(), upDown.toInt())
           ballView.ball.paint.setColor(color)
       }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}