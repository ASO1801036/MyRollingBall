package jp.ac.asojuku.myrollingball

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),SensorEventListener, SurfaceHolder.Callback {

    private var surfaceWidth:Int = 0
    private var surfaceHeight:Int = 0

    private val radius = 30.0f
    private val r = 40.0f
    private val coef = 100.0f

    private var ballX:Float = 0f
    private var ballY:Float = 0f
    private var vx:Float = 0f
    private var vy:Float = 0f
    private var time:Long = 0L

    private var holeX:Float = 0f
    private var holeY:Float = 0f

    private var rectcolor:Paint =Paint().apply{
        this.color = Color.BLUE
    }

    private val rect1l:Float =250f
    private val rect1t:Float = 150f
    private val rect1r:Float = 290f
    private val rect1b:Float = 380f

    private val rect2l:Float =0f
    private val rect2t:Float = 530f
    private val rect2r:Float = 120f
    private val rect2b:Float = 580f

    private val rect3l:Float =160f
    private val rect3t:Float = 730f
    private val rect3r:Float = 360f
    private val rect3b:Float = 880f

    private val rect4l:Float =610f
    private val rect4t:Float = 300f
    private val rect4r:Float = 810f
    private val rect4b:Float = 615f

    private val rect5l:Float =260f
    private val rect5t:Float = 1200f
    private val rect5r:Float = 710f
    private val rect5b:Float = 1280f


    private val goalcolor:Paint =Paint().apply{
        this.color = Color.WHITE
    }

    private val goalcx:Float = 900f
    private val goalcy:Float = 1275f
    private val goalr:Float = 35f


    private var flag = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)

        val holder = surfaceView.holder
        holder.addCallback(this)

        button.setOnClickListener{onbtn()}
    }

    fun onbtn(){
        imageView.setImageResource(R.drawable.fight)
        textView.setText(R.string.start)
        //ボールの初期位置を保存しておく
        this.ballX = 50.toFloat()
        this.ballY = 50.toFloat()
        flag = 1
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        if(event == null){return;}

        if(time == 0L){time = System.currentTimeMillis()}

        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val x = event.values[0]*-1
            val y = event.values[1]

            var t = ((System.currentTimeMillis()) - time).toFloat()

            time = System.currentTimeMillis()
            t /= 1000.0f

            val dx = (vx*t) + (x*t*t)/2.0f
            val dy = (vy*t) + (y*t*t)/2.0f
            ballX +=  (dx*coef)
            ballY +=  (dy*coef)

            this.vx += (x*t)
            this.vy += (y*t)

            //横軸
            if((this.ballX-radius<0) && vx<0){
                vx = (vx*-1)/1.5f
                this.ballX = this.radius
            }else if((this.ballX+radius>this.surfaceWidth) && vx>0){
                vx = (vx*-1)/1.5f
                this.ballX = (this.surfaceWidth - this.radius)
            } else if(ballX-radius<=rect1r&&ballX+radius>=rect1l&&ballY+radius>rect1t&&ballY-radius<rect1b){
                vx = (-1 * vx)
                flag = 0
                fail()
                if (ballX + radius == rect1b){
                    ballX = radius - rect1b
                }else if(ballX - radius == rect1l){
                    ballX = rect1l + radius
                }
            } else if(ballX-radius<=rect2r&&ballX+radius>=rect2l&&ballY+radius>rect2t&&ballY-radius<rect2b){
                vx = (-1 * vx)
                flag = 0
                fail()
                if (ballX + radius == rect2b){
                    ballX = radius - rect2b
                }else if(ballX - radius == rect2l){
                    ballX = rect2l + radius
                }
            } else if(ballX-radius<=rect3r&&ballX+radius>=rect3l&&ballY+radius>rect3t&&ballY-radius<rect3b){
                vx = (-1 * vx)
                flag = 0
                fail()
                if (ballX + radius == rect3b){
                    ballX = radius - rect3b
                }else if(ballX - radius == rect3l){
                    ballX = rect3l + radius
                }
            } else if(ballX-radius<=rect4r&&ballX+radius>=rect4l&&ballY+radius>rect4t&&ballY-radius<rect4b){
                vx = (-1 * vx)
                flag = 0
                fail()
                if (ballX + radius == rect4b){
                    ballX = radius - rect4b
                }else if(ballX - radius == rect4l){
                    ballX = rect4l + radius
                }
            } else if(ballX-radius<=rect5r&&ballX+radius>=rect5l&&ballY+radius>rect5t&&ballY-radius<rect5b){
                vx = (-1 * vx)
                flag = 0
                fail()
                if (ballX + radius == rect5b){
                    ballX = radius - rect5b
                }else if(ballX - radius == rect5l){
                    ballX = rect5l + radius
                }
            }

            //縦軸
            if((this.ballY-radius<0) && vy<0){
                vy = (vy*-1)/1.5f
                this.ballY = this.radius
            }else if((this.ballY+radius>this.surfaceHeight) && vy>0){
                vy = (vy*-1)/1.5f
                this.ballY = (this.surfaceHeight - this.radius)
            }else if(ballX+radius>=rect1l&&ballY+radius>=rect1t&&ballX-radius<=rect1r&&ballY-radius<=rect1b){
                vy = (-1 * vy)
                flag = 0
                fail()
                if (ballY - radius == rect1t){
                    ballY = radius + rect1t
                }else if(ballY + radius == rect1r){
                    ballY = rect1r - radius
                }
            }else if(ballX+radius>=rect2l&&ballY+radius>=rect2t&&ballX-radius<=rect2r&&ballY-radius<=rect2b){
                vy = (-1 * vy)
                flag = 0
                fail()
                if (ballY - radius == rect2t){
                    ballY = radius + rect2t
                }else if(ballY + radius == rect2r){
                    ballY = rect2r - radius
                }
            }
            else if(ballX+radius>=rect3l&&ballY+radius>=rect3t&&ballX-radius<=rect3r&&ballY-radius<=rect3b){
                vy = (-1 * vy)
                flag = 0
                fail()
                if (ballY - radius == rect3t){
                    ballY = radius + rect3t
                }else if(ballY + radius == rect3r){
                    ballY = rect3r - radius
                }
            }else if(ballX+radius>=rect4l&&ballY+radius>=rect4t&&ballX-radius<=rect4r&&ballY-radius<=rect4b){
                vy = (-1 * vy)
                flag = 0
                fail()
                if (ballY - radius == rect4t){
                    ballY = radius + rect4t
                }else if(ballY + radius == rect4r){
                    ballY = rect4r - radius
                }
            }else if(ballX+radius>=rect5l&&ballY+radius>=rect5t&&ballX-radius<=rect5r&&ballY-radius<=rect5b){
                vy = (-1 * vy)
                flag = 0
                fail()
                if (ballY - radius == rect5t){
                    ballY = radius + rect5t
                }else if(ballY + radius == rect5r){
                    ballY = rect5r - radius
                }
            }


            //ゴール判定
            if ((goalcx - goalr < ballX && ballX < goalcx + goalr) &&
                (goalcy - goalr < ballY && ballY < goalcy + goalr) &&
                flag == 1) {
                ballX = goalcx
                ballY = goalcy
                vx = 0f
                vy = 0f
                clear()
            }


            this.drawCanvas()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        this.surfaceWidth = width
        this.surfaceHeight = height

        this.ballX = 50.toFloat()
        this.ballY = 50.toFloat()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        val sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager.unregisterListener(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        val sensorManager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            this,
            accSensor,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    private fun drawCanvas(){
        val canvas = surfaceView.holder.lockCanvas()
        canvas.drawColor(Color.DKGRAY)
        canvas.drawRect(
            this.rect1l,
            this.rect1t,
            this.rect1r,
            this.rect1b,
            this.rectcolor
        )
        canvas.drawRect(
            this.rect2l,
            this.rect2t,
            this.rect2r,
            this.rect2b,
            this.rectcolor
        )
        canvas.drawRect(
            this.rect3l,
            this.rect3t,
            this.rect3r,
            this.rect3b,
            this.rectcolor
        )
        canvas.drawRect(
            this.rect4l,
            this.rect4t,
            this.rect4r,
            this.rect4b,
            this.rectcolor
        )
        canvas.drawRect(
            this.rect5l,
            this.rect5t,
            this.rect5r,
            this.rect5b,
            this.rectcolor
        )

        if(flag == 1){
            canvas.drawCircle(
                this.goalcx,
                this.goalcy,
                this.goalr,
                this.goalcolor
            )
            canvas.drawText(
                "G",
                872f,
                1302f,
                Paint().apply{
                    this.color = Color.RED
                    textSize = 80f
                }
            )
        }

        canvas.drawCircle(
            this.ballX,
            this.ballY,
            this.radius,
            Paint().apply{
                this.color = Color.RED
            }
        )

        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    //clear
    private fun clear(){
        imageView.setImageResource(R.drawable.clear)
        textView.setText(R.string.clear)
    }

    //fail
    private fun fail(){
        imageView.setImageResource(R.drawable.fail)
        textView.setText(R.string.fail)
    }
}
