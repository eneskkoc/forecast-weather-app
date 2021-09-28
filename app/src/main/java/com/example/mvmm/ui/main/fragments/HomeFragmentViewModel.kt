package com.example.mvmm.ui.main.fragments

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvmm.data.AppDataManager
import com.example.mvmm.data.database.ConnectivityInterceptor
import com.example.mvmm.data.database.entity.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
    private var appDataManager: AppDataManager,
    private var connectivityInterceptor: ConnectivityInterceptor,
) :
    ViewModel() {

    val data = MutableLiveData<State>()
    val progressVisible = ObservableField(false)
    val image = ObservableField<String>()
    var mCurrent: CurrentEntity? = null
    var mWeatherr: WeatherEntity? = null
    var mCoord: CoordiEntity? = null
    var mError: String? = null
    private val timestamp: Long = (System.currentTimeMillis()) / 1000


    @SuppressLint("CheckResult")
    fun coordinate(q: String) {
        progressVisible.set(true)
        appDataManager.geo(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({//success

                //kotlin sistem saati ve tarihi kaydetme
                val coordiEntity =
                    CoordiEntity(it.first().lat, it.first().lon, it.first().name, timestamp)
                saveDatabase(coordiEntity)//kaydetme tarihi ekle
                mCoord = coordiEntity
                data.postValue(State.OnCompleted)
                forecast(it.first().lat, it.first().lon)
            }, {//error
                progressVisible.set(false)
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })
    }

    @SuppressLint("CheckResult")
    fun forecast(lat: Double?, lon: Double?) {
        progressVisible.set(true)
        appDataManager.forecast(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val weather = WeatherEntity(
                    it.current?.weather?.first()?.description, it.current?.weather?.first()?.icon
                )
                it.daily?.forEach { daily -> // kaç tane değer varsa ona göre kaydediyor
                    val temp = TempEntity(daily.temp?.max)
                    daily.weather?.forEach { weatherX ->
                        val weatherXentity = WeatherXentity(weatherX.description, weatherX.icon)
                        val dailyEntity = DailyEntity(
                            daily.dt,
                            temp,
                            listOf(weatherXentity),
                            daily.humidity
                        )
                        saveDaily(dailyEntity)
                        saveWeatherx(weatherXentity)
                    }
                    saveTemp(temp)
                }
                val current = CurrentEntity(
                    it.current?.dt,
                    it.current?.temp,
                    listOf(weather),
                    it.current?.humidity
                )
                val forecastEntity = ForecastEntity(current)//dailentity kaldıralacak
                saveForecast(forecastEntity)
                saveCurrent(current)
                saveWeather(weather)
                image.set(it.current?.weather?.first()?.icon)
                mCurrent = current
                mWeatherr = weather
                data.postValue(State.OnCompleted)
                progressVisible.set(false)
            }
    }

    @SuppressLint("CheckResult")
    fun saveDatabase(coordinate: CoordiEntity) {
        appDataManager.database().subscribe { db ->
            coordinate.name?.let { db.coordinateDao().deleteCoordinate(it) }
            db.coordinateDao().insertCoordi(coordinate)
        }
    }

    @SuppressLint("CheckResult")
    fun saveForecast(forecast: ForecastEntity) {
        appDataManager.database().subscribe { db ->
            db.forecastDao().insertForecast(forecast)
        }
    }

    @SuppressLint("CheckResult")
    fun saveCurrent(current: CurrentEntity) {
        appDataManager.database().subscribe { db ->
            db.currentDao().insertCurrent(current)
        }
    }

    @SuppressLint("CheckResult")
    fun saveDaily(daily: DailyEntity) {
        appDataManager.database().subscribe { db ->
            db.dailyDao().insertDaily(daily)
        }
    }

    @SuppressLint("CheckResult")
    fun saveTemp(temp: TempEntity) {
        appDataManager.database().subscribe { db ->
            db.tempDao().insertTemp(temp)
        }
    }

    @SuppressLint("CheckResult")
    fun saveWeather(weather: WeatherEntity) {
        appDataManager.database().subscribe { db ->
            db.weatherDao().insertWeather(weather)
        }
    }

    @SuppressLint("CheckResult")
    fun saveWeatherx(weatherX: WeatherXentity) {
        appDataManager.database().subscribe { db ->
            db.weatherXdao().insertWeatherX(weatherX)
        }
    }

    //hepsini silme parametresiz
    @SuppressLint("CheckResult")
    fun deleteDatabase() {
        appDataManager.database().subscribe { db ->
            db.coordinateDao().deleteAll()
            db.currentDao().deleteAll()
            db.dailyDao().deleteAll()
            db.forecastDao().deleteAll()
            db.tempDao().deleteAll()
            db.weatherDao().deleteAll()
            db.weatherXdao().deleteAll()
        }
    }

    @SuppressLint("CheckResult")
    fun getName(name: String) {
        appDataManager.database().subscribe({ db ->
            val mData = db.coordinateDao().getName(name).lastOrNull()
            if (mData != null) {// && ile tarih değeri kontrol edilecek
                mCoord = mData
                data.postValue(State.OnCompleted)
                getCurrent()
            } else {
                if (connectivityInterceptor.isConnectionOn()) {
                    coordinate(name)
                } else {
                    val error = "İnternet Bağlantınızı Kontrol Ediniz"
                    mError = error
                    data.postValue(State.OnMessage)
                }
            }
        }, {
            data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
        })
    }

    @SuppressLint("CheckResult")
    fun getCurrent() {
        progressVisible.set(true)
        appDataManager.database().subscribe({ db ->
            val mData = db.currentDao().getCurrent().lastOrNull()
            val mWeather = db.weatherDao().getWeather().lastOrNull()
            val coord = db.coordinateDao().getLat().lastOrNull()
            if (mData != null && mWeather != null) {
                image.set(mWeather.icon)
                mCurrent = mData
                mWeatherr = mWeather
                mCoord = coord
                data.postValue(State.OnCompleted)
                if(connectivityInterceptor.isConnectionOn()){
                    update()
                }
                //data.postValue(State.OnCurrent(mData, mWeather))
                //data.postValue(State.OnCompleted(coord!!))
            }
            progressVisible.set(false)
        }, {
            data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
        })
    }

    @SuppressLint("CheckResult")
    fun update() {
        appDataManager.database().subscribe({ db ->
            val times = db.coordinateDao().getLat().lastOrNull()?.time
            val coord = db.coordinateDao().getLat().lastOrNull()
            if (times != null) {
                if ((timestamp) - (times) >= 3600) {
                    forecast(coord?.lat, coord?.lon)
                    deleteDb()
                    }
                }
        }, {})
    }

    @SuppressLint("CheckResult")
    fun deleteDb() {
        appDataManager.database().subscribe { db ->
            db.currentDao().deleteAll()
            db.dailyDao().deleteAll()
            db.forecastDao().deleteAll()
            db.tempDao().deleteAll()
            db.weatherDao().deleteAll()
            db.weatherXdao().deleteAll()
        }
    }
/*
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<WeatherApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

 */
    sealed class State {
        object OnCompleted : State()
        object OnMessage : State()

        //data class OnCurrent(val dt: CurrentEntity, val weather: WeatherEntity) : State() //parametre göndermek için data class
        data class OnError(val error: String) : State() //parametre göndermek için data class

        //object OnCompletedObj:State() //parametre yoksa object
    }
}