### Algoritmo de Visibilidad

#### Problematica

Cuando se pinta una parrilla de productos en los frontales web de las tiendas de comercio electrónico es necesario filtrar aquellos productos que se han quedado sin stock para facilitar al usuario el encontrar productos que pueda comprar.
Para ello se deben comprobar todas las tallas de cada producto para ver si tienen stock, y en caso de que ninguna talla tenga stock, ese producto no deberá mostrarse en la parrilla.
Existen dos casuísticas especiales:
• La primera es cuando una talla está marcada como back soon, en este caso, aunque la talla no tenga stock, el producto debe mostrarse igual, puesto que es un producto que va a volver a estar a la venta cuando vuelva a entrar stock.
• La segunda es cuando un producto tiene tallas “especiales”, en este caso el producto solo estará visible si al menos una talla especial y una no especial tiene stock (o está marcada como back soon). Si solo tienen stock (o están marcadas como back soon) tallas de uno de los dos grupos el producto no debe mostrarse. Esta casuística se utiliza en productos compuestos, por ejemplo, un cojín que consta de un relleno y una funda, solo se muestra si hay stock tanto del relleno como de la funda, si no hay stock de ninguno o solo del relleno o solo de la funda, entonces el cojín no se muestra.
Se pide desarrollar un algoritmo que lea tres ficheros en formato csv que simulan las tablas en base de datos:
- product.csv: fichero con los siguientes campos:
  o id: identificador de producto.
  o sequence: posición del producto en la parrilla.
- size.csv: fichero con los siguientes campos:
  o id: identificador de talla.
  o productId: identificador de producto.
  o backSoon: true si la talla es back soon o false en caso contrario.
  o special: true si la talla es especial o false en caso contrario.
- stock.csv: fichero con los siguientes campos:
  o sizeId: identificador de talla.
  o quantity: unidades disponibles en almacén de dicha talla.

Y que ofrezca como salida la lista de identificadores de producto, ordenados por el campo sequence,
que cumplan las condiciones de visibilidad explicadas anteriormente y separados por comas.


### Consideraciones de la solucion planteada

#### API Product Controller
Se agrega un controller con visibilidad desde swagger para disponer de una interfaz grafica y poder ejecutar el servicio que resuelve el algoritmo de visibilidad de productos.

#### Lectura de los archivos .csv
Se considera que la aplicacion es dueña de las tablas de la base de datos y que ya tenemos la informacion disponible en ellas de los productos, talles y stock. Por este motivo se lee los archivos .csv desde nuestro archivo de migracion de flyway.

#### Servicio de base de datos
##### El servicio de productService.getAll
Este servicio trae todos los datos de las tablas considerando que existen pocos productos en la base de datos.
En el caso de que existan muchos productos y talles, este servicio deberia modificarse para que utilice una paginacion y asi poder manejar grandes volumenes de datos.

#### Estructuras de datos utilizadas en el algoritmo

La clase **Size** contiene los mismos campos que contiene la tabla de la base de datos con el mismo nombre, ademas tambien tiene el campo *quantity* que hace referencia al campo del mismo nombre de la tabla **stock** debido a que esta tabla comparte el mismo *id* que Size.

La clase **Product** ademas del campo *id* y *sequence*, contiene ademas la coleccion de datos **Size**.

Para presentar el listado de productos se utilizó la estructura de List debido a que el retorno de la solucion debe tener un orden.


#### Complejidad temporal del algoritmo
##### Una vez resuelto el algoritmo de visibilidad. ¿Qué complejidad temporal expresada en notación “O” crees que tiene?

```
Producto{                   -> O(n)
    isCommonSize            -> O(m)
        commonSize          -> O(m)
    else                    
        specialSize         -> O(2.m)
}
sortProducts                -> O(nlog(n))
convertListToString         -> O(n)
```

La complejidad que tiene el algoritmo es O(3m.n + n.log(n) + n)

##### ¿Consideras que se podría mejorar de alguna manera?

Si, se puede realizar un metodo que cumpla el requerimiento de forma que solamente se recorra solo una vez el listado de talles por cada producto.

```java
boolean isVisibleProduct (Set<Size> sizes){
        boolean hasSpecialSize= false;
        boolean hasCommonStock=false;
        boolean hasSpecialStock=false;

        for(Size size :sizes) {
            hasSpecialSize = hasSpecialSize || size.isSpecial();
            hasSpecialStock = hasSpecialStock || (hasSpecialSize && (size.hasStock() || size.isBackSoon()));
            hasCommonStock = hasCommonStock || (!size.isSpecial() && (size.hasStock() || size.isBackSoon()));
            if (hasSpecialStock &&   hasCommonStock) {
                return true;
            }
        }
        return !hasSpecialSize && hasCommonStock ;
    }
```

La complejidad que tendria el algoritmo aqui seria O(m.n + n.log(n) + n)

La performance mejora, pero con esto nos arriesgamos a que en el futuro el requerimiento cambie (por ejemplo: un nuevo tipo de producto o una condicion de visibilidad nueva) y sea mas complicado de integrar esta nueva condicion a nuestro algoritmo, es decir, no es escalable.

