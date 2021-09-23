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
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class WindJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Wind>() {
  private val options: JsonReader.Options = JsonReader.Options.of("speed", "deg", "gust")

  private val nullableDoubleAdapter: JsonAdapter<Double?> =
      moshi.adapter(Double::class.javaObjectType, emptySet(), "speed")

  public override fun toString(): String = buildString(26) {
      append("GeneratedJsonAdapter(").append("Wind").append(')') }

  public override fun fromJson(reader: JsonReader): Wind {
    var speed: Double? = null
    var deg: Double? = null
    var gust: Double? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> speed = nullableDoubleAdapter.fromJson(reader)
        1 -> deg = nullableDoubleAdapter.fromJson(reader)
        2 -> gust = nullableDoubleAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Wind(
        speed = speed,
        deg = deg,
        gust = gust
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Wind?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("speed")
    nullableDoubleAdapter.toJson(writer, value_.speed)
    writer.name("deg")
    nullableDoubleAdapter.toJson(writer, value_.deg)
    writer.name("gust")
    nullableDoubleAdapter.toJson(writer, value_.gust)
    writer.endObject()
  }
}