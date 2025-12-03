# Sistema de Administración - League of Legends Web

## 🔐 Funcionalidades Implementadas

### 1. Autenticación de Administradores
- **Login seguro**: Formulario de login con validación de credenciales
- **Gestión de sesiones**: Control automático de sesiones con timeout de 30 minutos
- **Protección de rutas**: Todas las rutas `/admin/*` están protegidas automáticamente
- **Logout seguro**: Invalidación completa de sesión

### 2. Panel de Administración
- **Dashboard principal**: Vista general con acceso a todas las funcionalidades
- **Gestión de Campeones**: CRUD completo con upload de imágenes
- **Gestión de Administradores**: CRUD completo de usuarios administrativos

### 3. Seguridad Implementada
- **Interceptor de autenticación**: Verifica automáticamente todas las rutas admin
- **Validación de estado**: Administradores desactivados son deslogueados automáticamente
- **Protección contra auto-desactivación**: Un admin no puede desactivar su propia cuenta
- **Validación de último admin**: No permite desactivar el último administrador activo

## 🚀 Instalación y Configuración

### 1. Base de Datos
Ejecutar el script SQL para crear la tabla admin:

```sql
-- Archivo: src/main/resources/init_admin.sql
CREATE TABLE IF NOT EXISTS admin (
    Id_Admin INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL UNIQUE,
    Contrasena VARCHAR(25) NOT NULL,
    Estado TINYINT(1) DEFAULT 1,
    INDEX idx_nombre_estado (Nombre, Estado)
);

-- Administrador por defecto
INSERT INTO admin (Nombre, Contrasena, Estado) 
VALUES ('admin', 'admin123', 1)
ON DUPLICATE KEY UPDATE Nombre = VALUES(Nombre);
```

### 2. Credenciales por Defecto
- **Usuario**: `admin`
- **Contraseña**: `admin123`

⚠️ **IMPORTANTE**: Cambiar estas credenciales después del primer login por seguridad.

## 📱 Cómo Usar el Sistema

### Acceso al Panel Administrativo

1. **Desde el sitio web**: 
   - Ir al footer de cualquier página
   - Hacer clic en "🔐 Admin"

2. **Acceso directo**: 
   - Navegar a: `http://localhost:8080/admin/login`

### Funcionalidades Disponibles

#### 🎯 Gestión de Campeones
- **Crear**: Agregar nuevos campeones con imagen opcional
- **Editar**: Modificar información y cambiar imágenes
- **Activar/Desactivar**: Controlar visibilidad en el sitio público
- **Listar**: Ver todos los campeones (activos e inactivos)

#### 👥 Gestión de Administradores
- **Crear**: Agregar nuevos usuarios administrativos
- **Editar**: Modificar nombre de usuario y contraseña
- **Activar/Desactivar**: Controlar acceso al panel
- **Validaciones**: Previene eliminación del último admin activo

### Rutas Principales

| Ruta | Función |
|------|---------|
| `/admin/login` | Formulario de login |
| `/admin/dashboard` | Panel principal |
| `/admin/campeones` | Gestión de campeones |
| `/admin/admins` | Gestión de administradores |
| `/admin/logout` | Cerrar sesión |

## 🔧 Archivos Creados/Modificados

### Backend (Java)
- `model/Admin.java` - Entidad para administradores
- `repository/AdminRepository.java` - Repositorio con consultas personalizadas
- `service/AdminService.java` - Lógica de negocio para administradores
- `controller/AdminLoginController.java` - Manejo de autenticación
- `controller/AdminManagementController.java` - CRUD de administradores
- `config/AdminAuthInterceptor.java` - Interceptor de seguridad
- `config/WebConfig.java` - Configuración del interceptor

### Frontend (HTML/CSS)
- `templates/admin/login.html` - Formulario de login
- `templates/admin/dashboard.html` - Panel principal
- `templates/admin/admins/index.html` - Lista de administradores
- `templates/admin/admins/form.html` - Formulario de administrador
- `css/admin.css` - Estilos actualizados para nuevas funcionalidades

### Archivos Modificados
- `AdminCampeonController.java` - Agregada verificación de sesión
- `fragments/header.html` - Enlace al panel admin en footer
- `css/base.css` - Estilos para enlace de admin

## 🛡️ Seguridad

### Características de Seguridad Implementadas
- **Autenticación por sesión**: No se usan tokens, sino sesiones del servidor
- **Timeout automático**: Sesiones expiran después de 30 minutos de inactividad
- **Verificación en tiempo real**: Estado de admin verificado en cada request
- **Protección CSRF**: Formularios con protección automática de Spring
- **Validación de entrada**: Sanitización de datos en formularios

### Recomendaciones Adicionales
1. **Cambiar credenciales por defecto** inmediatamente
2. **Usar HTTPS** en producción
3. **Configurar base de datos** con usuario específico de aplicación
4. **Backup regular** de la base de datos
5. **Monitoreo de logs** para detectar accesos sospechosos

## 🔄 Flujo de Autenticación

1. Usuario accede a cualquier ruta `/admin/*`
2. **Interceptor verifica** si existe sesión válida
3. Si no hay sesión → **Redirección a login**
4. En login → **Validación de credenciales** contra BD
5. Si es válido → **Creación de sesión** y redirección a dashboard
6. En cada request → **Verificación de estado** del admin
7. Si admin desactivado → **Invalidación de sesión** automática

## 📞 Soporte

### Solución de Problemas Comunes

**Error de conexión a BD:**
- Verificar configuración en `application.properties`
- Asegurar que la tabla `admin` exista

**No puedo acceder al admin:**
- Verificar que existe el administrador por defecto
- Revisar logs de aplicación para errores

**Sesión expira muy rápido:**
- Modificar timeout en `AdminLoginController.java` línea 50

### Logs Importantes
- **Login exitoso**: `"Login exitoso para: [usuario]"`
- **Login fallido**: `"Login fallido para: [usuario]"`  
- **Acceso protegido**: `"Acceso autorizado a: [ruta] por: [usuario]"`
- **Acceso denegado**: `"Acceso no autorizado a: [ruta]"`

---

✅ **Sistema completamente funcional y listo para usar**