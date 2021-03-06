// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.NullPointerException
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptySet
import kotlin.text.buildString

public class OWMJsonAdapter(
  moshi: Moshi
) : JsonAdapter<OWM>() {
  private val options: JsonReader.Options = JsonReader.Options.of("coord", "weather", "base",
      "main", "visibility", "wind", "clouds", "dt", "sys", "timezone", "id", "name", "cod")

  private val nullableCoordsAdapter: JsonAdapter<Coords?> = moshi.adapter(Coords::class.java,
      emptySet(), "coord")

  private val nullableListOfWeatherAdapter: JsonAdapter<List<Weather>?> =
      moshi.adapter(Types.newParameterizedType(List::class.java, Weather::class.java), emptySet(),
      "weather")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "base")

  private val nullableMainAdapter: JsonAdapter<Main?> = moshi.adapter(Main::class.java, emptySet(),
      "main")

  private val nullableIntAdapter: JsonAdapter<Int?> = moshi.adapter(Int::class.javaObjectType,
      emptySet(), "visibility")

  private val nullableWindAdapter: JsonAdapter<Wind?> = moshi.adapter(Wind::class.java, emptySet(),
      "wind")

  private val nullableCloudsAdapter: JsonAdapter<Clouds?> = moshi.adapter(Clouds::class.java,
      emptySet(), "clouds")

  private val nullableSysAdapter: JsonAdapter<Sys?> = moshi.adapter(Sys::class.java, emptySet(),
      "sys")

  public override fun toString(): String = buildString(25) {
      append("GeneratedJsonAdapter(").append("OWM").append(')') }

  public override fun fromJson(reader: JsonReader): OWM {
    var coord: Coords? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int? = null
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: String? = null
    var sys: Sys? = null
    var timezone: Int? = null
    var id: Int? = null
    var name: String? = null
    var cod: Int? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> coord = nullableCoordsAdapter.fromJson(reader)
        1 -> weather = nullableListOfWeatherAdapter.fromJson(reader)
        2 -> base = nullableStringAdapter.fromJson(reader)
        3 -> main = nullableMainAdapter.fromJson(reader)
        4 -> visibility = nullableIntAdapter.fromJson(reader)
        5 -> wind = nullableWindAdapter.fromJson(reader)
        6 -> clouds = nullableCloudsAdapter.fromJson(reader)
        7 -> dt = nullableStringAdapter.fromJson(reader)
        8 -> sys = nullableSysAdapter.fromJson(reader)
        9 -> timezone = nullableIntAdapter.fromJson(reader)
        10 -> id = nullableIntAdapter.fromJson(reader)
        11 -> name = nullableStringAdapter.fromJson(reader)
        12 -> cod = nullableIntAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return OWM(
        coord = coord,
        weather = weather,
        base = base,
        main = main,
        visibility = visibility,
        wind = wind,
        clouds = clouds,
        dt = dt,
        sys = sys,
        timezone = timezone,
        id = id,
        name = name,
        cod = cod
    )
  }

  public override fun toJson(writer: JsonWriter, value_: OWM?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("coord")
    nullableCoordsAdapter.toJson(writer, value_.coord)
    writer.name("weather")
    nullableListOfWeatherAdapter.toJson(writer, value_.weather)
    writer.name("base")
    nullableStringAdapter.toJson(writer, value_.base)
    writer.name("main")
    nullableMainAdapter.toJson(writer, value_.main)
    writer.name("visibility")
    nullableIntAdapter.toJson(writer, value_.visibility)
    writer.name("wind")
    nullableWindAdapter.toJson(writer, value_.wind)
    writer.name("clouds")
    nullableCloudsAdapter.toJson(writer, value_.clouds)
    writer.name("dt")
    nullableStringAdapter.toJson(writer, value_.dt)
    writer.name("sys")
    nullableSysAdapter.toJson(writer, value_.sys)
    writer.name("timezone")
    nullableIntAdapter.toJson(writer, value_.timezone)
    writer.name("id")
    nullableIntAdapter.toJson(writer, value_.id)
    writer.name("name")
    nullableStringAdapter.toJson(writer, value_.name)
    writer.name("cod")
    nullableIntAdapter.toJson(writer, value_.cod)
    writer.endObject()
  }
}
