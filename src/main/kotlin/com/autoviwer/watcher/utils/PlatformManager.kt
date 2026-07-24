package com.autoviwer.watcher.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.random.Random

data class PlatformStats(
    val views: Int = 0,
    val successRate: Float = 0f,
    val runtime: Long = 0,
    val speed: Float = 0f
)

class PlatformManager(private val platform: String) {
    
    companion object {
        private const val DATASTORE_NAME = "auto_watcher_prefs"
        val PREFS_LINK = stringPreferencesKey("link_$platform")
        val PREFS_VIEWS = intPreferencesKey("views_$platform")
    }
    
    private var isRunning = false
    private var viewsCount = 0
    private var successCount = 0
    private var startTime = 0L
    
    // Original traffic simulation
    private val userAgents = listOf(
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.0 Mobile/15E148 Safari/604.1",
        "Mozilla/5.0 (Linux; Android 13; SM-G991B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
    )
    
    private val referrers = listOf(
        "https://www.google.com/",
        "https://www.bing.com/",
        "https://www.yahoo.com/",
        "https://www.facebook.com/",
        "https://www.instagram.com/",
        "https://www.reddit.com/",
        "https://twitter.com/",
        "https://www.linkedin.com/",
        "https://www.tiktok.com/",
        "direct"
    )
    
    fun setLink(link: String) {
        // Temporary storage
    }
    
    fun clearLink() {
        // Clear temporary storage
    }
    
    suspend fun getSavedLink(context: Context): String? {
        val dataStore = context.preferencesDataStore(name = DATASTORE_NAME)
        val prefs = dataStore.data.map { it[PREFS_LINK] }
        return prefs.getOrNull(0)
    }
    
    suspend fun saveLink(context: Context, link: String) {
        val dataStore = context.preferencesDataStore(name = DATASTORE_NAME)
        dataStore.edit { prefs ->
            prefs[PREFS_LINK] = link
        }
    }
    
    fun startViewCounter(context: Context, link: String, targetViews: Int) {
        isRunning = true
        viewsCount = 0
        successCount = 0
        startTime = System.currentTimeMillis()
        
        Thread {
            while (isRunning && viewsCount < targetViews) {
                try {
                    generateOriginalView(link)
                    viewsCount++
                    successCount++
                    
                    // Random delay between views (original human behavior)
                    val randomDelay = Random.nextLong(1000, 5000)
                    Thread.sleep(randomDelay)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
    
    fun stopViewCounter() {
        isRunning = false
    }
    
    private fun generateOriginalView(link: String) {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
        
        try {
            val request = Request.Builder()
                .url(link)
                .header("User-Agent", userAgents.random())
                .header("Referer", referrers.random())
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Accept-Encoding", "gzip, deflate")
                .header("DNT", "1")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Cache-Control", "max-age=0")
                .build()
            
            val response = httpClient.newCall(request).execute()
            
            // Simulate watching time (2-10 seconds)
            val watchTime = Random.nextLong(2000, 10000)
            Thread.sleep(watchTime)
            
            response.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun getStats(): Flow<PlatformStats> {
        return Flow {
            val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
            val successRate = if (viewsCount > 0) (successCount.toFloat() / viewsCount) * 100 else 0f
            val speed = if (elapsedSeconds > 0) viewsCount.toFloat() / elapsedSeconds else 0f
            
            emit(PlatformStats(
                views = viewsCount,
                successRate = successRate,
                runtime = elapsedSeconds,
                speed = speed
            ))
        }
    }
}
