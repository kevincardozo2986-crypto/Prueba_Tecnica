Aplicación de consola para crear, consultar, actualizar y eliminar productos. Los datos se conservan en productos.json y cada producto contiene nombre, precio, stock y categoría.

Como instalar el proyecto:
git clone <https://github.com/kevincardozo2986-crypto/Prueba_Tecnica>
cd proyecto_tecnico
mvn clean package

Los atributos que se utilizaron;:
int id, String nombre, double precio, int stock, String categoria

El menú numérico guía las cuatro operaciones del CRUD, el archivo json se crea automáticamente si no existe.

Como utilizarlo
1. Puedes abrir el programa crear un producto y cerrar el programa.
2. Abrirlo nuevamente y comprobar que el producto continúa allí.
3. Actualizarlo y despuyes consultarlo por ID y eliminarlo.
4. Intentar crear productos con precio o stock negativos para comprobar las validaciones.

JUSTIFICAION
Elegí Java porque  con ese lenguaje  me siento mas comodo y seguro adaemas es el lenguaje que yo mas se utilizar.

Usé tres clases: 
1. Main contiene el menú
2. Producto  guarda los datos 
3. ProductoService contiene las operaciones y escribe el archivo JSON.
Así el código queda ordenado sin agregar una estructura difícil de entender.

Validé que el nombre y la categoría no estén vacíos, y que el precio y el stock no sean negativos. El reto principal fue guardar y volver a leer los productos desde el archivo pero se logro con los metodos cargar y guardar.

Fue pan comido.
