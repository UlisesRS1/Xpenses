# Xpenses

Aplicación móvil desarrollada en Android Studio utilizando el lenguaje Java.

## Requisitos

- Android Studio instalado
- JDK 8 o superior
- Git (opcional, para clonar el repositorio)
- Plugin Loombok

## Clonar el repositorio

```bash
git clone git@github.com:UlisesRS1/Xpenses.git
```
## Contribuir al repositorio

Para contribuir al repositorio de forma organizada, se recomienda trabajar con ramas independientes para cada nueva funcionalidad o corrección.

### Pasos para contribuir:
1. Asegúrate de tener la última versión del proyecto:
```bash
git pull origin main
```

2. Crea una nueva rama con un nombre descriptivo:
```bash
git checkout -b <branch_name>
```

3. Realiza los cambios necesarios en el proyecto.
4. Guarda y confirma los cambios:
```bash
git add .
git commit -m "Descripcion de los cambios"
```
5. Envía tu rama al repositorio remoto:
```bash
git push origin main
```
6. Crea un Pull Request desde GitHub para que tus cambios sean revisados e integrados a la rama principal.

### ¿Cuándo crear una nueva rama?

✅ **Crea una nueva rama cuando:**
- Vas a trabajar en una nueva funcionalidad (ej. `feature/login-screen`)
- Estás corrigiendo un error específico (ej. `bugfix/crash-on-startup`)
- Quieres probar una idea sin afectar el código estable
- Estás colaborando con otras personas y deseas evitar conflictos

❌ **Puedes evitar crear una nueva rama si:**
- Estás trabajando solo en el proyecto y haciendo cambios menores
- Ya estás en una rama que corresponde a una tarea específica y continuarás en ella
- Estás haciendo ajustes rápidos o personales que no se integrarán directamente al repositorio principal

> **Nota:** Mantener una rama por cada cambio importante ayuda a organizar mejor el proyecto y facilita la revisión

## Volver a un Estado Estable Anterior

En caso de que necesites revertir los cambios recientes y volver a un punto donde todo estaba funcionando correctamente y de forma estable, sigue estos pasos:

### 1. **Identificación del Cambio**
   Determina el momento exacto en que los cambios comenzaron a afectar la estabilidad del sistema. Si tienes un registro de commits o un historial de cambios (como Git), busca la última versión estable antes de que comenzara el problema.

### 2. **Uso de Control de Versiones**
   Si estás utilizando un sistema de control de versiones como Git, puedes revertir los cambios a una versión estable con los siguientes comandos:

   ```bash
   # Regresar a un commit específico
   git checkout <commit-hash>
   
   # Revertir un commit sin perder historial
   git revert <commit-hash>
  ```
  El `commit-hash` es un identificador único para cada commit en Git. Para encontrar el `commit-hash` de una versión anterior estable, puedes usar el siguiente comando:

   ```bash
   git log
  ```
  Un ejemplo de commit-hash seria el siguiente:
  ```bash
  commit d34db33f93a74f5f89618bba25f2ac7f0f34370b
  ```

## Comandos de Git Más Usados

| **Comando**                                     | **Descripción**                                                                                             |
|-------------------------------------------------|-----------------------------------------------------------------------------------------------------------  |
| `git log`                                       | Muestra el historial de commits. Incluye detalles como el autor, fecha y el `commit-hash`.                  |
| `git checkout <commit-hash>`                    | Cambia a un commit específico usando su `commit-hash`. Permite regresar a una versión anterior.             |
| `git revert <commit-hash>`                      | Crea un nuevo commit que revierte los cambios realizados en el commit especificado. No altera el historial. |
| `git reset --hard <commit-hash>`                | Resetea el repositorio al estado de un commit específico, eliminando los cambios locales no confirmados.    |
| `git status`                                    | Muestra el estado actual del repositorio: archivos modificados, no confirmados, y no rastreados.            |
| `git diff`                                      | Muestra las diferencias entre el estado actual del archivo y el commit anterior.                            |
| `git commit -m "Mensaje del commit"`            | Realiza un commit de los cambios con un mensaje descriptivo.                                                |
| `git push`                                      | Empuja los cambios confirmados al repositorio remoto.                                                       |
| `git pull`                                      | Trae los cambios del repositorio remoto y los fusiona con la rama actual.                                   |
| `git clone <url>`                               | Clona un repositorio remoto a tu máquina local.                                                             |
| `git branch`                                    | Muestra las ramas actuales del repositorio.                                                                 |
| `git branch <nombre-rama>`                      | Crea una nueva rama en el repositorio.                                                                      |
| `git merge <nombre-rama>`                       | Fusiona los cambios de una rama a la rama activa.                                                           |
| `git stash`                                     | Guarda los cambios no confirmados en un área temporal (stash) y los elimina del área de trabajo.            |
| `git stash pop`                                 | Recupera los cambios guardados en el stash y los aplica en el área de trabajo.                              |
| `git fetch`                                     | Descarga cambios desde el repositorio remoto sin fusionarlos con la rama actual.                            |
| `git rebase <nombre-rama>`                      | Aplica los cambios de una rama sobre otra. A diferencia de `merge`, reescribe el historial de commits.      |

> **Nota:** Cuando usas el comando git merge o git rebase, Git intenta fusionar el contenido de la rama en la que te encuentras con otra rama. Si los cambios son compatibles, Git los combinará automáticamente. Sin embargo, si hay conflictos (por ejemplo, si dos ramas han modificado la misma línea de un archivo), Git te pedirá que resuelvas los conflictos manualmente.

## Autores

- [Ulises] (https://github.com/UlisesRS1)
- [Cesar] (https://github.com/CesarE301)
- [Angel Noe] (https://github.com/AngelN22)
- [Jairo Jaffet] (https://github.com/JairoMtz28)
- [Cristopher] (Pendiente)
