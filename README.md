# 🔥 Sistema de Gestión de Incendios - INF-131

![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-Java%20GUI%20Framework-blueviolet?style=for-the-badge)

**Sistema desarrollado por Eynar-Cast** como parte del curso **INF-131 (Estructuras de Datos)**.  
Este proyecto presenta una **simulación interactiva de un sistema de gestión de incendios**, con soporte para la toma de decisiones, asignación de recursos y visualización de eventos, utilizando **estructuras de datos personalizadas** e interfaz gráfica construida con **JFrame/Swing en Java** bajo el entorno **NetBeans**.

---

## 🎯 Objetivo del Proyecto

Crear una aplicación que permita gestionar incendios en una ciudad ficticia, simulando:
- Registro y categorización de alertas
- Asignación dinámica de unidades de emergencia
- Registro de personas afectadas y recursos
- Visualización por zonas y prioridades

---

## 🧠 Estructuras de Datos Implementadas

Las estructuras se diseñaron de forma **genérica (`<T>`)** y adaptada al contexto real del sistema. A continuación, se detalla su aplicación:

| 🧱 **Estructura de Datos** | 🛠️ **Uso en el Sistema** |
|---------------------------|--------------------------|
| `LSimple<T>`              | Gestión de pérdidas materiales, zonas afectadas y asignaciones de unidades |
| `LDoble<T>`               | Registro y seguimiento de personas afectadas en emergencias |
| `Pila<T>`                 | Manejo de recursos de emergencia (equipos, herramientas, brigadas) |
| `NodoS<T>`                | Nodo soporte para `LSimple` y `Pila` |
| `NodoD<T>`                | Nodo soporte para `LDoble` |

> Todas las estructuras fueron implementadas de forma reutilizable, con orientación a objetos.

---

## 🖼️ Interfaz Gráfica

Desarrollada con:

- **Java Swing**
- **JFrame**
- GUI Builder de **NetBeans**

### Funcionalidades clave:
- Registro de incendios y emergencias
- Asignación de recursos a zonas afectadas
- Visualización de alertas activas y resueltas
- Seguimiento de personas afectadas
- Panel de control de recursos

---

## 📁 Estructura del Proyecto

/src
├── interfaz/
│ ├── MainFrame.java
│ ├── PanelAlerta.java
│ ├── PanelUnidad.java
├── estructuras/
│ ├── LSimple.java
│ ├── LDoble.java
│ ├── Pila.java
│ ├── NodoS.java
│ ├── NodoD.java
├── logica/
│ ├── GestorIncendios.java
│ ├── Alerta.java
│ ├── Unidad.java
│ ├── Zona.java
---

## ▶️ Cómo ejecutar

1. Abrir el proyecto en **NetBeans IDE**
2. Compilar todo el proyecto
3. Ejecutar `MainFrame.java` desde el paquete `interfaz`
4. Usar el panel principal para interactuar con el sistema

---

## 🧰 Tecnologías y Herramientas

| Herramienta | Función |
|------------|---------|
| ![Java](https://img.shields.io/badge/-Java-007396?logo=java&logoColor=white&style=flat) | Lenguaje de desarrollo |
| ![NetBeans](https://img.shields.io/badge/-NetBeans-1B6AC6?logo=apache-netbeans-ide&logoColor=white&style=flat) | IDE utilizado |
| ![Swing](https://img.shields.io/badge/-Swing-blueviolet?style=flat) | Librería para la interfaz gráfica |

---

## 👤 Autor

**Eynar-Cast**  
🎓 Estudiante de INF-131  
📘 Facultad de Ciencias y Tecnología  
🔧 Enfoque en estructuras de datos aplicadas y desarrollo orientado a objetos  
🌐 GitHub: [github.com/Eynar-Cast](https://github.com/Eynar-Cast)

---

## 📄 Documentación Adicional

📥 Informe técnico del sistema: `./documentacion/Informe-Estructuras-Incendios.pdf`  
📥 Manual de usuario (GUI): `./manual/ManualUsuario.pdf`

---

## ⚠️ Licencia

Este trabajo es de uso **académico**. Puede ser utilizado como referencia citando a su autor.

---
