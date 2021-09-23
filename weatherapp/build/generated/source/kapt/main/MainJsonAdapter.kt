// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class MainJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Main>() {
  private val options: JsonReader.Options = JsonReader.Options.of("temp", "feels_like", "temp_min",
      "temp_max", "pressure", "humidity", "sea_level", "grnd_level")

  private val nullableDoubleAdapter: JsonAdapter<Double?> =
      moshi.adapter(Double::class.javaObjectType, emptySet(), "temp")

  private val nullableIntAdapter: JsonAdapter<Int?> = moshi.adapter(Int::class.javaObjectType,
      emptySet(), "pressure")

  public override fun toString(): String = buildString(26) {
      append("GeneratedJsonAdapter(").append("Main").append(')') }

  public override fun fromJson(reader: JsonReader): Main {
    var temp: Double? = null
    var feelsLike: Double? = null
    var tempMin: Double? = null
    var tempMax: Double? = null
    var pressure: Int? = null
    var humidity: Int? = null
    var seaLevel: Int? = null
    var grndLevel: Int? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> temp = nullableDoubleAdapter.fromJson(reader)
        1 -> feelsLike = nullableDoubleAdapter.fromJson(reader)
        2 -> tempMin = nullableDoubleAdapter.fromJson(reader)
        3 -> tempMax = nullableDoubleAdapter.fromJson(reader)
        4 -> pressure = nullableIntAdapter.fromJson(reader)
        5 -> humidity = nullableIntAdapter.fromJson(reader)
        6 -> seaLevel = nullableIntAdapter.fromJson(reader)
        7 -> grndLevel = nullableIntAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Main(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        grndLevel = grndLevel
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Main?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("temp")
    nullableDoubleAdapter.toJson(writer, value_.temp)
    writer.name("feels_like")
    nullableDoubleAdapter.toJson(writer, value_.feelsLike)
    writer.name("temp_min")
    nullableDoubleAdapter.toJson(writer, value_.tempMin)
    writer.name("temp_max")
    nullableDoubleAdapter.toJson(writer, value_.tempMax)
    writer.name("pressure")
    nullableIntAdapter.toJson(writer, value_.pressure)
    writer.name("humidity")
    nullableIntAdapter.toJson(writer, value_.humidity)
    writer.name("sea_level")
    nullableIntAdapter.toJson(writer, value_.seaLevel)
    writer.name("grnd_level")
    nullableIntAdapter.toJson(writer, value_.grndLevel)
    writer.endObject()
  }
}