-if class OWM
-keepnames class OWM
-if class OWM
-keep class OWMJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
