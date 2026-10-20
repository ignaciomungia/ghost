# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\rfinistrosa\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:
# -dontoptimize estaba aquí antes: desactivaba la optimización de R8 aunque
# minifyEnabled estuviera a true, que es justo lo que Google Play pide mejorar
# (ofuscación/reducción de código DEX por debajo del 25%). Lo hemos quitado.

# Fix del crash "Failed to create an instance of androidx.work.impl.WorkDatabase"
# (visto al arrancar otra app de la misma cuenta tras activar la optimización).
# WorkManager (una librería que arrastra el propio SDK de AdMob, no la usamos
# directamente) guarda sus tareas en una base de datos Room interna llamada
# WorkDatabase. Room crea esa base de datos en tiempo de ejecución buscando por
# reflexión una clase generada en compilación llamada "WorkDatabase_Impl". Sin
# esta regla, R8 puede eliminar o renombrar esa clase generada al no detectar
# que se usa (porque solo se referencia por reflexión), y entonces la app falla
# justo al arrancar. Con este -keep evitamos que R8 toque esa clase y cualquier
# otra base de datos Room que pueda arrastrar alguna librería.
-keep class * extends androidx.room.RoomDatabase
-keep class androidx.work.impl.WorkDatabase
-keep class androidx.work.impl.WorkDatabase_Impl { *; }

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
