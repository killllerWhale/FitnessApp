
import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fitnessapp.R

class YourJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        val context = this
        val notificationManager = NotificationManagerCompat.from(context)
        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.icon_notification)
            .setContentTitle("Заголовок уведомления")
            .setContentText("Текст уведомления")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Показ уведомления
        notificationManager.notify(1, builder.build())

        return false // задание не продолжается после вызова этого метода
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true // возвращаем true, чтобы выполнить эту работу позже
    }
}
