#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};

#end

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Global executor pools for the whole application.
 *
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 *
 *
 * Creating a class like this is a way for android apps to handle non-trivial threading.
 *
 *
 * Made By Pouya Rezaei
 * Github : pouyarezaei
 * RecyclerView Template Adapter
 */
class AppExecutors private constructor(
    private val diskIO: ExecutorService,
    private val networkIO: ExecutorService,
    private val mainThread: Executor
) {
    fun diskIO(body: () -> Unit) = diskIO.execute(body)

    fun mainThread(body: () -> Unit) = mainThread.execute(body)


    fun networkIO(body: () -> Unit) = networkIO.execute(body)


    private class MainThreadExecutor : Executor {
        private val mainThreadHandler =
            Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppExecutors? = null
        val instance: AppExecutors?
            get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        sInstance = AppExecutors(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(
                                Runtime.getRuntime().availableProcessors() / 2
                            ),
                            MainThreadExecutor()
                        )
                    }
                }
                return sInstance
            }
    }

}
