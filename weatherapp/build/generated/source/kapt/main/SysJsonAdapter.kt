// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class SysJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Sys>() {
  private val options: JsonReader.Options = JsonReader.Options.of("country", "sunrise", "sunset")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "country")

  public override fun toString(): String = buildString(25) {
      append("GeneratedJsonAdapter(").append("Sys").append(')') }

  public override fun fromJson(reader: JsonReader): Sys {
    var country: String? = null
    var sunrise: String? = null
    var sunset: String? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> country = nullableStringAdapter.fromJson(reader)
        1 -> sunrise = nullableStringAdapter.fromJson(reader)
        2 -> sunset = nullableStringAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Sys(
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Sys?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("country")
    nullableStringAdapter.toJson(writer, value_.country)
    writer.name("sunrise")
    nullableStringAdapter.toJson(writer, value_.sunrise)
    writer.name("sunset")
    nullableStringAdapter.toJson(writer, value_.sunset)
    writer.endObject()
  }
}
