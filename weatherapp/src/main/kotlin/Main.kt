import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

//  Основной класс для обработки JSON-ответа
@JsonClass(generateAdapter = true)
data class OWM(
    @Json(name = "coord")
    val coord: Coords?,
    @Json(name="weather")
    val weather: List<Weather>?,
    @Json(name="base")
    val base: String?,
    @Json(name="main")
    val main: Main?,
    @Json(name="visibility")
    val visibility: Int?,
    @Json(name="wind")
    val wind: Wind?,
    @Json(name="clouds")
    val clouds: Clouds?,
    @Json(name="dt")
    val dt: String?,
    @Json(name="sys")
    val sys: Sys?,
    @Json(name="timezone")
    val timezone: Int?,
    @Json(name="id")
    val id: Int?,
    @Json(name="name")
    val name: String?,
    @Json(name="cod")
    val cod: Int?
)

@JsonClass(generateAdapter = true)
data class Coords(
    @Json(name="lon")
    val lon: Double?,
    @Json(name="lat")
    val lat: Double?
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name="id")
    val id: Int?,
    @Json(name="main")
    val main: String?,
    @Json(name="description")
    val description: String?,
    @Json(name="icon")
    val icon: String?
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name="temp")
    val temp: Double?,
    @Json(name="feels_like")
    val feelsLike: Double?,
    @Json(name="temp_min")
    val tempMin: Double?,
    @Json(name="temp_max")
    val tempMax: Double?,
    @Json(name="pressure")
    val pressure: Int?,
    @Json(name="humidity")
    val humidity: Int?,
    @Json(name="sea_level")
    val seaLevel: Int?,
    @Json(name="grnd_level")
    val grndLevel: Int?
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name="speed")
    val speed: Double?,
    @Json(name="deg")
    val deg: Double?,
    @Json(name="gust")
    val gust: Double?
)

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name="all")
    val all: Int?
)

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name="country")
    val country: String?,
    @Json(name="sunrise")
    val sunrise: String?,
    @Json(name="sunset")
    val sunset: String?
)

fun getAPIKey(): String? {
    System.getenv("WEATHER_API_KEY")
    val APIKey = System.getenv("WEATHER_API_KEY")
    return APIKey
}

//  Запись температуры в файл/вывод температуры в консоль
fun getTemperature(args:Array<String>, json:String?, jsonAdapter: JsonAdapter<OWM>){
    if (json != null) {
        /* JSON to Model */
        val modelWeather = jsonAdapter.fromJson(json)

        //  вывод в консоль
        if (args.size<3) {
            println("${modelWeather?.main?.temp}")
        }

        //  вывод в файл
        else if (args[2]=="-f"){
            if (args.size<4){
                throw Exception("Wrong command line arguments about file path. Expected '-c LATITUDE,LONGITUDE [-f PATH]'")
            }
            else{
                try {
                    val illegalCurrentPathFileCharacters=setOf('\\')

                    //  создаем файл в указанной директории
                    if (args[3].any(illegalCurrentPathFileCharacters::contains)) {
                        val directory: String = args[3].substringBeforeLast('\\')
                        val filename: String = args[3].substringAfterLast('\\')
                        val file = File(directory,filename)
                        FileOutputStream(file).use {
                            it.write("${modelWeather?.main?.temp}".toByteArray())
                        }
                    }

                    //  создаем файл в директории проекта
                    else{
                        val filename: String = args[3]
                        val file = File(filename)
                        FileOutputStream(file).use {
                            it.write("${modelWeather?.main?.temp}".toByteArray())
                        }
                    }
                }
                catch (e: Exception){
                    throw Exception("Something wrong with file creation!")
                }
            }
        }
        else if (args[2]!="-f") {
            throw Exception("Wrong command line arguments about file path. Expected '-c LATITUDE,LONGITUDE [-f PATH]'")
        }
    }
    else {
        throw Exception("HTTP-API error: Json is null!")
    }
}

fun getJsonObject(client: OkHttpClient, request: Request, lat: String, lon: String, args: Array<String>){
    client.newCall(request).execute().use { response ->
        if (lat.toDouble() < -90 || lat.toDouble() > 90 || lon.toDouble() < -180 || lon.toDouble() > 180) {
            throw Exception("Wrong coordinates are entered: latitude should be less than 90 and more than -90 and longitude should be less than 180 and more than -180")
        }
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        else {
            if (response.body != null) {
                val json: String? = response.body?.string()
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<OWM> = moshi.adapter(OWM::class.java)
                getTemperature(args, json, jsonAdapter)
            } else {
                throw Exception("HTTP error: response is null!")
            }
        }
    }
}

fun buildOkhttpRequest(lat: String, lon: String, APIKey: String, args: Array<String>){
    val client: OkHttpClient = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build()
    val request = Request.Builder()
        .url("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$APIKey&units=metric")
        .build()
    getJsonObject(client, request,lat,lon,args)
}

fun main(args: Array<String>) {
    val APIKey = getAPIKey()
    val legalCoordCharacter = setOf(',')
    if (args.isEmpty()){
        throw Exception("No command line arguments. Expected '-c LATITUDE,LONGITUDE [-f PATH]'")
    }
    else {
        if (args[0] == "-c" && args[1].any(legalCoordCharacter::contains)) {
            val lat: String = args[1].split(",")[0]
            val lon: String = args[1].split(",")[1]
            if (APIKey != null) {
                buildOkhttpRequest(lat, lon, APIKey, args)
            } else {
                throw Exception("API key is not founded!")
            }
        } else {
            throw Exception("Wrong command line arguments about coordinates. Expected '-c LATITUDE,LONGITUDE [-f PATH]'")
        }
    }
}