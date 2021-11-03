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
           val sides=event.values[0]*-20
           val upDown=event.values[1]*20

           ballView.ball.setSpeed(sides.toInt(), upDown.toInt())
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