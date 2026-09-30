# Guía paso a paso — Catálogo de Productos con Room

Esta guía está pensada para que Fernando y Daniel puedan montar el proyecto en Android Studio y explicarlo en clase.

## 1. Preparar Android Studio

1. Instala Android Studio.
2. Durante la instalación asegúrate de tener Android SDK y un JDK compatible.
3. Abre Android Studio y espera a que finalice la indexación.
4. Ve a `Tools > SDK Manager`.
5. En `SDK Platforms`, comprueba que esté instalado **Android 15 / API 35**.
6. En `SDK Tools`, comprueba las herramientas de Android necesarias y el Android SDK Build-Tools.

## 2. Abrir el ZIP

1. Descomprime `CatalogoProductosRoom.zip`.
2. Android Studio > `Open`.
3. Selecciona la carpeta raíz `CatalogoProductosRoom`, no la carpeta `app`.
4. Espera la sincronización de Gradle.
5. Acepta cualquier descarga de dependencias que Android Studio solicite.

## 3. Revisar la configuración

En `app/build.gradle` encontrarás:

- `compileSdk 35`
- `minSdk 24`
- `targetSdk 35`
- ViewBinding habilitado.
- Room runtime + Room KTX + Room compiler.
- RecyclerView.
- Material Components.
- Lifecycle ViewModel/LiveData.

No cambies versiones mientras el proyecto esté funcionando.

## 4. Entender Room

### Entity

Archivo:

`app/src/main/java/com/ferdan/catalogoproductos/data/Product.kt`

`Product` representa la tabla `products`.

La propiedad `id` es una clave primaria con autogeneración:

```kotlin
@PrimaryKey(autoGenerate = true)
val id: Int = 0
```

### DAO

Archivo:

`ProductDao.kt`

Aquí se definen las operaciones de la base de datos:

```kotlin
@Query("SELECT * FROM products ORDER BY id DESC")
fun observeAll(): Flow<List<Product>>

@Query("SELECT * FROM products WHERE id = :id LIMIT 1")
suspend fun getById(id: Int): Product?

@Insert
suspend fun insert(product: Product)

@Update
suspend fun update(product: Product)

@Delete
suspend fun delete(product: Product)
```

### Base de datos

Archivo:

`AppDatabase.kt`

Room genera la implementación del DAO en tiempo de compilación. La instancia se mantiene como singleton para evitar crear varias bases de datos.

### Repository

Archivo:

`ProductRepository.kt`

Sirve como intermediario entre ViewModel y DAO.

### ViewModel

Archivo:

`ProductViewModel.kt`

Ejecuta las operaciones fuera del hilo principal usando Coroutines y `Dispatchers.IO`.

## 5. Pantalla principal

Archivo:

`MainActivity.kt`

Responsabilidades:

1. Mostrar los productos.
2. Observar cambios de Room.
3. Filtrar por texto.
4. Abrir el formulario para crear/editar.
5. Pedir confirmación antes de eliminar.

El RecyclerView usa `ListAdapter` y `DiffUtil` para actualizar la lista de manera eficiente.

## 6. Formulario

Archivo:

`ProductFormActivity.kt`

El formulario sirve tanto para crear como para editar.

Si recibe un ID mediante:

`ProductFormActivity.EXTRA_PRODUCT_ID`

el formulario busca ese producto y carga sus datos.

Si no recibe ID, se trata de un registro nuevo.

## 7. Validaciones

El formulario comprueba:

- Nombre: mínimo 3 caracteres.
- Categoría: obligatoria.
- Precio: número mayor que 0.
- Stock: entero igual o mayor que 0.
- Descripción: máximo 250 caracteres.

En caso de error, el mensaje aparece directamente debajo del campo correspondiente.

## 8. Prueba manual completa

### Prueba A — Crear

Nombre: Laptop Lenovo
Categoría: Computadoras
Precio: 850
Stock: 5
Descripción: Laptop para oficina y estudio.

Guarda y confirma que aparece la tarjeta.

### Prueba B — Consultar

Cierra y vuelve a abrir la aplicación. El registro debe continuar allí porque está guardado localmente con Room.

### Prueba C — Buscar

Escribe `Lenovo` en el buscador. Debe quedar visible el producto relacionado.

### Prueba D — Editar

Cambia el stock de 5 a 7 y guarda. La tarjeta debe mostrar 7.

### Prueba E — Eliminar

Pulsa la papelera y confirma. El registro debe desaparecer.

### Prueba F — Validar

Intenta guardar:

- Nombre vacío.
- Categoría vacía.
- Precio `0`.
- Stock `-1`.
- Descripción con más de 250 caracteres.

Los campos deben mostrar mensajes de validación y no se debe guardar el registro.

## 9. Reparto del trabajo

### Fernando

Parte recomendada:

1. `Product.kt`
2. `ProductDao.kt`
3. `AppDatabase.kt`
4. `ProductRepository.kt`
5. Prueba de persistencia local.

Commits sugeridos:

```text
feat(room): crea entidad Product
feat(room): crea ProductDao
feat(room): configura AppDatabase y Repository
```

### Daniel

Parte recomendada:

1. `MainActivity.kt`
2. `ProductFormActivity.kt`
3. `ProductAdapter.kt`
4. XML de las pantallas.
5. Validaciones.
6. README y documentación.

Commits sugeridos:

```text
feat(ui): crea pantalla principal del catalogo
feat(ui): implementa formulario y validaciones
feat(ui): agrega RecyclerView y acciones CRUD
```

El objetivo no es que una sola persona haga todo: ambos deben subir cambios identificables a GitHub.

## 10. Qué explicar en la exposición

Una explicación de 1 a 2 minutos puede seguir este orden:

1. `Product` representa la tabla.
2. `ProductDao` contiene las consultas y operaciones CRUD.
3. `AppDatabase` crea y administra la base de Room.
4. `Repository` separa la fuente de datos de la interfaz.
5. `ViewModel` ejecuta operaciones y conserva el estado durante cambios de configuración.
6. `RecyclerView` lista los productos.
7. El formulario valida los campos antes de insertar o actualizar.
8. Todo se almacena de forma local en el dispositivo.

## 11. Preparar la entrega

1. Crear el repositorio con el nombre exigido por la actividad.
2. Subir el proyecto.
3. Confirmar que aparecen commits de Fernando y Daniel.
4. Agregar al docente indicado en el documento como colaborador.
5. Copiar el enlace del repositorio en un archivo `.txt`.
6. Entregar el `.txt` en Moodle/Plataforma ITCA.
