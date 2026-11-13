# Proguard & R8 rules for Salim Trading Challenge Tracker

# Keep MainActivity, util classes, and JavaScript bridge
-keep class com.sami.tradingchallengetracker.MainActivity { *; }
-keep class com.sami.tradingchallengetracker.MainActivity$** { *; }
-keep class com.sami.tradingchallengetracker.util.** { *; }

# Keep JavaScript Interface methods for WebView
-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep WebViewAssetLoader
-keep class androidx.webkit.** { *; }

# Keep annotations
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

# Firebase Messaging
-dontwarn com.google.firebase.**
-keep class com.google.firebase.messaging.** { *; }

# Coroutines
-dontwarn kotlinx.coroutines.**
