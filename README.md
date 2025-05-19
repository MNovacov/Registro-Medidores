# Registro Lectura de Medidores 📲

Esta aplicación permite registrar las lecturas de medidores de **agua, luz y gas**, con el objetivo de ayudar a los usuarios a controlar posibles cobros injustificados en los servicios básicos.

---

## 🛠️ Características técnicas implementadas

1. **Programación Orientada a Objetos en Kotlin**  
   - Se modela cada registro como un objeto `Gasto`, con tipo, valor y fecha.

2. **Diseño de 2 pantallas usando Jetpack Compose**  
   - Una pantalla lista (`ListScreen`) para ver las mediciones registradas.  
   - Una pantalla formulario (`FormScreen`) para ingresar nuevos registros.

3. **Gestión de eventos de navegación y guardado**  
   - Botón flotante para ir al formulario.  
   - Botón para guardar y volver a la lista.

4. **Uso de íconos representativos**  
   - Se muestran íconos según el tipo de registro: 💧 Agua, ⚡ Luz, 🔥 Gas.

5. **Soporte multilenguaje (español / inglés)**  
   - Se usan `strings.xml` en carpetas `values/` y `values-en/`.

6. **Uso de ViewModel**  
   - Se emplea `GastoViewModel` para manejar datos y lógica.

7. **Lista dinámica con LazyColumn**  
   - Muestra automáticamente todos los registros guardados.

8. **Navegación entre pantallas**  
   - Se utiliza `NavHost` y `composable()` para la navegación.

9. **Persistencia con ROOM**  
   - Los datos se guardan localmente en una base de datos SQLite.

10. **Uso de corrutinas**  
   - Las operaciones de base de datos se realizan con `viewModelScope.launch`.

---

## 🧪 Estado

✔ Proyecto funcional y probado en Android Studio Meerkat  
✔ Compatible con dispositivos Android API 24+  
