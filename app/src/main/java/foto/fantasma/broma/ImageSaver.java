package foto.fantasma.broma;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.core.content.FileProvider;

public class ImageSaver {

    private static final String TAG = "ImageSaver";
    // Define el authority de tu FileProvider. Debe coincidir con el AndroidManifest.xml
    private static final String FILE_PROVIDER_AUTHORITY = "foto.fantasma.broma.fileprovider";


    /**
     * Guarda un Bitmap en la galería de fotos del dispositivo y retorna la Uri del archivo guardado.
     * La forma de guardado varía según la versión de Android para asegurar la compatibilidad
     * y la visibilidad en la galería.
     *
     * @param context El contexto de la aplicación (e.g., this Activity).
     * @param bitmap El Bitmap que se desea guardar.
     * @param filenamePrefix Un prefijo para el nombre del archivo (e.g., "caricator").
     * @return La Uri del archivo guardado si la operación fue exitosa, o null si hubo un error.
     */
    public static Uri saveBitmapToGallery(Context context, Bitmap bitmap, String filenamePrefix) {
        // Genera un nombre de archivo único con sello de tiempo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = filenamePrefix + timeStamp + ".jpg";

        OutputStream fos = null;
        String message = "";
        Uri resultUri = null; // La URI que retornaremos

        // --- Lógica para Android 10 (API 29) y superior (Scoped Storage) ---
        // Para Android Q (API 29) y superiores, se recomienda usar MediaStore API.
        // Esto gestiona automáticamente los permisos y la visibilidad en la galería pública.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            // Se especifica el directorio público de Pictures.
            // Esto es crucial para que aparezca en la galería.
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            try {
                // Inserta una nueva entrada en la base de datos de MediaStore y obtiene una URI
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (imageUri == null) {
                    throw new IOException("Failed to create new MediaStore record.");
                }

                // Abre un OutputStream para escribir los datos del Bitmap en la URI
                fos = resolver.openOutputStream(imageUri);
                if (fos == null) {
                    throw new IOException("Failed to open OutputStream.");
                }

                // Comprime y escribe el Bitmap en el OutputStream
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                message = "Image saved in gallery";
                resultUri = imageUri; // Asigna la URI para retornarla

            } catch (IOException e) {
                // Si ocurre un error, limpia la entrada de MediaStore si se creó
                if (resultUri != null) { // resultUri aquí sería la imageUri si se llegó a crear
                    resolver.delete(resultUri, null, null);
                    resultUri = null; // Reinicia a null si hubo error
                }
                Log.e(TAG, "Error al guardar imagen con MediaStore", e);
                message = "Error saving image: " + e.getMessage();
            } finally {
                // Asegúrate de cerrar el OutputStream
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error al cerrar OutputStream", e);
                }
            }
        } else {
            // --- Lógica para Android 9 (API 28) y anteriores ---
            // Para versiones anteriores, se usa la API de File tradicional.
            // Para que aparezca en la galería, es necesario notificar al MediaScanner.
            File dir;
            // Verifica el estado del almacenamiento externo
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // Directorio público de imágenes. Requiere WRITE_EXTERNAL_STORAGE y escaneo manual.
                dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            } else {
                // Directorio interno de la app como fallback (no es visible en galería)
                dir = context.getFilesDir();
                message = "External storage could not be accessed. Image saved internally (not in gallery).";
            }

            // Asegúrate de que el directorio exista
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, fileName);

            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();

                // Notifica al MediaScanner para que la imagen aparezca en la galería
                galleryAddPic(context, file);

                message = "Image saved in gallery";
                // Para compartir, necesitarás una content:// URI para este File,
                // especialmente si el File está en un directorio privado de la app (si dir = context.getFilesDir()).
                // Incluso para directorios públicos, es mejor usar FileProvider para compartir de forma segura.
                resultUri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, file);

            } catch (FileNotFoundException e) {
                Log.e(TAG, "FileNotFoundException al guardar imagen (Android 9-)", e);
                message = "Error: File not found when saving.";
                resultUri = null;
            } catch (IOException e) {
                Log.e(TAG, "IOException al guardar imagen (Android 9-)", e);
                message = "Error saving image: " + e.getMessage();
                resultUri = null;
            } catch (IllegalArgumentException e) { // Captura si el FileProvider no está configurado
                Log.e(TAG, "IllegalArgumentException: FileProvider no configurado correctamente.", e);
                message = "Error: FileProvider not configured for sharing.";
                resultUri = null;
            } finally {
                // Asegúrate de cerrar el OutputStream
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error al cerrar OutputStream (Android 9-)", e);
                }
            }
        }

        // Muestra un Toast con el resultado
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        if (resultUri != null) {
            Log.d(TAG, "Image saved successfully. URI: " + resultUri.toString());
        } else {
            Log.e(TAG, "Failed to save image. URI is null.");
        }
        return resultUri;
    }

    /**
     * Notifica al MediaScanner para que el archivo sea indexado y aparezca en la galería.
     * Este método es necesario para Android 9 (API 28) y versiones anteriores cuando se guarda
     * en el almacenamiento público.
     *
     * @param context El contexto de la aplicación.
     * @param photoFile El archivo de imagen que se desea escanear.
     */
    private static void galleryAddPic(Context context, File photoFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photoFile);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
        Log.d(TAG, "MediaScanner notificado para: " + photoFile.getAbsolutePath());
    }
}
