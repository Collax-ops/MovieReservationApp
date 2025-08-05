# 🎬 Movie Reservation App

Una aplicación Android de reservas de entradas de cine, desarrollada como proyecto personal con fines educativos. Implementa autenticación, consulta de películas desde la API de TMDB, selección de asientos, pagos simulados con PayPal y gestión de tickets.

> ⚠️ **Este proyecto es con fines de aprendizaje. No está destinado para uso comercial ni en producción.**
---
## 🎥 Demo
https://github.com/user-attachments/assets/f8895216-e097-4a74-8d63-0e107b37e133
---
## 🚀 Características

- **Autenticación con Firebase** (registro e inicio de sesión).
- **Listado de películas desde TMDB**, con filtro por género.
- **Horarios disponibles por cine**, seleccionables por día.
- **Selección de asientos**, con visualización de ocupados y disponibles.
- **Pago simulado con PayPal Sandbox**, redirigiendo al flujo de PayPal Web.
- **Persistencia local con Room**, para tickets, pagos y asientos.
- **Historial de reservas y pagos** en tiempo real.
- **Pantalla de descarga de ticket (dummy)**, con todos los datos de tu reserva.
---
## 🧱 Arquitectura

El proyecto sigue el enfoque de **Clean Architecture**, desacoplando la lógica de negocio, datos y presentación.
```
📁 data
 ┣ 📂local      → Room DB, DAOs, modelos de base de datos
 ┣ 📂remote     → Retrofit + APIs de TMDB y PayPal
 ┣ 📂repository → Implementaciones concretas de repositorios

📁 domain
 ┣ 📂model      → Modelos de dominio
 ┣ 📂repository → Interfaces de acceso a datos
 ┗ 📂usecase    → Casos de uso

📁 di →  inyección de dependencias

📁 ui
 ┣ 📂screens    → Jetpack Compose Screens (Home, Booking, Payment, etc.)
 ┣ 📂components → Composables reutilizables
 ┗ 📂navigation → Gestión de navegación con Compose
```
---
## 🧰 Tecnologías y Librerías

- **Jetpack Compose** para UI declarativa.
- **Navigation-Compose** para manejo de rutas.
- **Hilt** para inyección de dependencias.
- **Room** para almacenamiento local.
- **Retrofit + Kotlinx Serialization** para llamadas a APIs.
- **Firebase Authentication** para login/register.
- **Datastore** para persistencia ligera de preferencias.
- **PayPal Web Payments SDK** en entorno sandbox.
- **Coil 3** para carga eficiente de imágenes.
- **Kotlinx.datetime** para manipulación de fechas.

---

## 📦 Dependencias principales

- **ViewModel**
  - `androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7`

- **Room (Base de datos local)**
  - `androidx.room:room-runtime:2.6.1`
  - `androidx.room:room-ktx:2.6.1`
  - `androidx.room:room-compiler:2.6.1` (KSP)

- **Hilt (Inyección de dependencias)**
  - `com.google.dagger:hilt-android:2.55`
  - `androidx.hilt:hilt-navigation-compose:1.2.0`
  - `com.google.dagger:hilt-compiler:2.55` (KAPT)

- **Firebase (Auth + Analytics)**
  - `com.google.firebase:firebase-bom:34.0.0` (platform)
  - `com.google.firebase:firebase-analytics`
  - `com.google.firebase:firebase-auth`

- **Kotlinx DateTime**
  - `org.jetbrains.kotlinx:kotlinx-datetime:0.6.2`

- **Retrofit + Serialization**
  - `com.squareup.retrofit2:retrofit:2.9.0`
  - `org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0`
  - `com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0`

- **Coil (Carga de imágenes con Compose)**
  - `io.coil-kt.coil3:coil-compose:3.1.0`
  - `io.coil-kt.coil3:coil-network-okhttp:3.1.0`

- **PayPal (Web Payments SDK - sandbox)**
  - `com.paypal.android:paypal-web-payments:2.0.0`

- **DataStore (Preferencias locales)**
  - `androidx.datastore:datastore-preferences-core:1.1.7`
  - `androidx.datastore:datastore-preferences:1.1.7`

---

## ⚙️ Instalación & Ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/movie-reservation-app.git
cd movie-reservation-app
```

2. Crea o edita un archivo `local.properties` en la raíz del proyecto con tus claves:

```
# TMDB
tmdb.apiKey=TU_TMDB_API_KEY

# PayPal (sandbox)
paypal.clientId=TU_PAYPAL_CLIENT_ID
paypal.secretKey=TU_PAYPAL_SECRET_KEY
```

3. Ejecuta el proyecto en Android Studio (versión 2023.1.1+ recomendada).
 Usar un emulador con servicios de Google
---

## 🌱 Ideas para futuras mejoras

Estas características no se implementaron por razones de tiempo, pero pueden ser añadidas si deseas contribuir:

- 🔔 **Notificaciones push** con Firebase Cloud Messaging (FCM).
- 📲 **QR Code en ticket** que contenga los detalles de la reserva.
- 🧾 **Descarga de ticket en PDF** directamente en el dispositivo.
- 🎫 **Tickets compartibles** a otros usuarios.
- 📆 **Recordatorios de próximas funciones.**

---

## 📄 Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).

---

🙌 Créditos
- API de películas: The Movie Database (TMDB).

- SDK de pagos: PayPal Developer.

- Autenticación: Firebase Authentication.

---
### 🧾 Terceras bibliotecas y licencias

Este proyecto utiliza librerías de terceros con licencias compatibles:

- `kotlinx-datetime` (Apache-2.0): https://github.com/Kotlin/kotlinx-datetime

---
