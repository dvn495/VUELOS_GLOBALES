# *VUELOS_GLOBALES*

## TECNOLOGÍAS USADAS:

#### **JAVA** :  Es un lenguaje de programación orientado a objetos y una plataforma utilizada para desarrollar aplicaciones. En este proyecto que maneja, Java se utiliza para escribir el código que interactúa con la base de datos, gestionar la lógica y controlar el flujo de la aplicación.

##### 	Ejemplo de Uso:

- **Conexión a la Base de Datos:** Utilización de JDBC (Java Database Connectivity) para conectar y ejecutar consultas en la base de datos MySQL.
- **Servicios y Repositorios:** Implementación de servicios para manejar la lógica  y repositorios para la gestión de la persistencia de datos.

#### **MySQL**: Es un sistema de gestión de bases de datos relacional de código abierto que utiliza SQL (Structured Query Language) para gestionar los datos. 

##### Ejemplo de Uso:

- **Creación de Esquemas y Tablas:** Definición de estructuras de datos mediante sentencias DDL (Data Definition Language).
- **Consultas y Transacciones:** Realización de consultas para insertar, actualizar, eliminar y seleccionar datos mediante sentencias DML (Data Manipulation Language).
- **Integridad de Datos:** Uso de restricciones como claves primarias, claves foráneas, índices y restricciones de unicidad.

#### **MAVEN**: es una herramienta de gestión y comprensión de proyectos que proporciona un modelo de gestión de proyectos basado en POM (Project Object Model). Maven gestiona dependencias, compila código, ejecuta pruebas, empaqueta el proyecto y más.

##### Ejemplo de Uso:

- **Gestión de Dependencias:** Declaración de bibliotecas externas y plugins necesarios en el archivo `pom.xml`.
- **Construcción del Proyecto:** Uso de comandos de Maven para compilar, ejecutar pruebas y empaquetar la aplicación.
- **Gestión de Perfiles:** Configuración de perfiles para diferentes entornos (desarrollo, pruebas, producción).

## ORGANIZACIÓN:

#### **Arquitectura Hexagonal**: La arquitectura hexagonal  es un patrón de diseño de software que enfatiza la separación de preocupaciones y la independencia de la infraestructura. En esta arquitectura, la lógica de negocio se mantiene en el núcleo (dominio) y las interacciones con el mundo exterior (interfaces de usuario, bases de datos, servicios externos) se manejan mediante puertos y adaptadores.

##### Ejemplo de Uso:

- **Dominio:** Definición de entidades, agregados y servicios de dominio que representan la lógica de negocio.
- **Aplicación:** Servicios de aplicación que coordinan las operaciones del dominio.
- **Adaptadores:** Implementación de adaptadores para interfaces de usuario, persistencia, y otros servicios externos.
- **Interfaces:** Las interfaces definen los contratos que los adaptadores deben cumplir. 

#### **VERTICAL SLICING: **es una técnica de desarrollo que divide una aplicación en capas o componentes completos de funcionalidad, que abarcan todas las capas de la arquitectura (desde la interfaz de usuario hasta la base de datos). Cada "slice" representa una funcionalidad completa e independiente.

##### Ejemplo de Uso:

- **Feature Completa:** Implementación de una funcionalidad específica (por ejemplo, gestión de vuelos) que incluye cambios en la base de datos, lógica de negocio, y interfaz de usuario.
- **Implementación Iterativa:** Desarrollo de funcionalidades de manera iterativa, asegurando que cada slice sea completamente funcional antes de pasar al siguiente.

#### **SOLID:** es un conjunto de principios de diseño de software que busca hacer el software más comprensible, flexible y mantenible. Los principios son:

1. **Single Responsibility Principle (SRP):** Una clase debe tener una sola responsabilidad.
2. **Open/Closed Principle (OCP):** Las entidades de software deben estar abiertas para extensión pero cerradas para modificación.
3. **Liskov Substitution Principle (LSP):** Los objetos de una clase base deben poder ser reemplazados por objetos de una clase derivada sin afectar el funcionamiento del programa.
4. **Interface Segregation Principle (ISP):** Los clientes no deben verse obligados a depender de interfaces que no utilizan.
5. **Dependency Inversion Principle (DIP):** Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones.

### QUE PERMITE:

Este programa permite registrar y manejar una base de datos de una aerolínea, manejar un CRUD por cada entidad establecida para el correcto funcionamiento de dicha base de datos, usando las formas normales hasta su 4ta forma normal; Este programa permite manejar gracias a las entidades distintos casos de uso que se presentan dependiendo del rol que tenga el usuario y sus distintas responsabilidades.

### COMO USARLO:



