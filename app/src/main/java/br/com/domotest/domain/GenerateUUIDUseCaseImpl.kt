package br.com.domotest.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import java.security.MessageDigest
import java.util.UUID
import kotlin.random.Random

class GenerateUUIDUseCaseImpl(
    private val applicationContext: Context,
): GenerateUUIDUseCase {

    private lateinit var sensorManager: SensorManager
    private var accelerometerListener: SensorEventListener? = null
    private var gyroscopeListener: SensorEventListener? = null
    private var lastAccelerometerData: FloatArray? = null
    private var lastGyroscopeData: FloatArray? = null

    override suspend fun invoke(
        onGenerateFinished: (generatedUUID: String?) -> Unit
    ) {
        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer != null) {
            accelerometerListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.let {
                        lastAccelerometerData = it.copyOf()
                        checkAndGenerateUUID(onGenerateFinished)
                    }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            sensorManager.registerListener(accelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            lastAccelerometerData = floatArrayOf(0f, 0f, 0f)
            checkAndGenerateUUID(onGenerateFinished)
        }

        val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if (gyroscope != null) {
            gyroscopeListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.let {
                        lastGyroscopeData = it.copyOf()
                        checkAndGenerateUUID(onGenerateFinished)
                    }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    // Não é necessário implementar para este caso
                }
            }
            sensorManager.registerListener(gyroscopeListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            // Se o giroscópio não estiver disponível, tenta gerar o UUID sem ele
            lastGyroscopeData = floatArrayOf(0f, 0f, 0f) // Valor padrão
            checkAndGenerateUUID(onGenerateFinished)
        }
    }

    private fun stopSensorCollection() {
        accelerometerListener?.let { sensorManager.unregisterListener(it) }
        gyroscopeListener?.let { sensorManager.unregisterListener(it) }
        accelerometerListener = null
        gyroscopeListener = null
    }

    private fun checkAndGenerateUUID(
        onGenerateFinished: (generatedUUID: String?) -> Unit
    ) {
        if (lastAccelerometerData != null && lastGyroscopeData != null) {
            stopSensorCollection()
            generateCustomUUID(onGenerateFinished)
        }
    }

    private fun generateCustomUUID(
        onGenerateFinished: (generatedUUID: String?) -> Unit
    ) {
        val accData = lastAccelerometerData
        val gyroData = lastGyroscopeData

        if (accData == null || gyroData == null) {
            return onGenerateFinished.invoke(null)
        }

        val sensorDataString = "${accData[0]},${accData[1]},${accData[2]};" +
                "${gyroData[0]},${gyroData[1]},${gyroData[2]}"

        val hardwareInfo = "${Build.MANUFACTURER};${Build.MODEL};${Build.VERSION.SDK_INT}"

        // 3. Combinação dos dados
        // Adiciona um pouco de aleatoriedade inicial para garantir que mesmo dispositivos idênticos
        // que iniciam com valores de sensor muito próximos, tenham uma chance maior de gerar UUIDs diferentes.
        // Isso é um "sal" inicial, não uma semente para bibliotecas de UUID.
        val initialEntropy = Random.nextLong().toString()

        val combinedString = "$initialEntropy;$sensorDataString;$hardwareInfo"

        // 4. Hashing da string combinada para um identificador de tamanho fixo
        val hashedBytes = MessageDigest.getInstance("SHA-256").digest(combinedString.toByteArray())
        val customUUID = UUID.nameUUIDFromBytes(hashedBytes).toString()

        onGenerateFinished.invoke(customUUID)
    }

}