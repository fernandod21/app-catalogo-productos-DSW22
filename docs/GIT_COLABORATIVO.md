# Trabajo colaborativo con GitHub

## Configuración inicial de cada integrante

Cada persona debe configurar su identidad de Git en su propia computadora:

```bash
git config --global user.name "NOMBRE REAL"
git config --global user.email "CORREO_DE_GITHUB"
```

## Flujo diario recomendado

```bash
git pull origin main
git status
git add .
git commit -m "tipo: descripción clara"
git push origin main
```

Para evitar conflictos, es preferible que Fernando y Daniel trabajen en ramas separadas y hagan Pull Request.

Ejemplo:

```bash
git checkout -b feature/room-fernando
git checkout -b feature/ui-daniel
```

Los nombres son solamente una propuesta.

## Evidencia de colaboración

Antes de entregar:

```bash
git log --oneline --all --decorate --graph
```

Tomen una captura donde aparezcan commits de los dos integrantes.
