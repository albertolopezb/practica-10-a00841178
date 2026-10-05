package mx.tec.avisos

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import mx.tec.avisos.notificaciones.Notificador
import mx.tec.avisos.trabajo.RevisarAvisosWorker
import javax.inject.Inject

@HiltAndroidApp
class AvisosApplication : Application(), Configuration.Provider, SingletonImageLoader.Factory {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    @Inject lateinit var imageLoader: ImageLoader

    @Inject lateinit var notificador: Notificador

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    override fun newImageLoader(context: PlatformContext): ImageLoader = imageLoader

    override fun onCreate() {
        super.onCreate()
        notificador.crearCanal()
        RevisarAvisosWorker.programar(this)
    }
}