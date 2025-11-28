/**
 * Componentes reutilizables para WebLOL
 * Genera dinámicamente header, footer y otros elementos comunes
 */

// Configuración de rutas según la ubicación del archivo
const PAGE_CONFIG = {
    // Detecta si estamos en la raíz o en la carpeta HTML
    isInSubfolder: window.location.pathname.includes('/HTML/'),
    
    // Rutas base según ubicación
    get basePath() {
        return this.isInSubfolder ? '../' : '';
    },
    
    get imgPath() {
        return this.basePath + 'img/';
    },
    
    get htmlPath() {
        return this.isInSubfolder ? '' : 'HTML/';
    }
};

/**
 * Genera el HTML del header común para todas las páginas
 */
function generateHeader() {
    const { basePath, imgPath, htmlPath } = PAGE_CONFIG;
    
    return `
        <header class="site-header inicio">
            <div class="contenedor contenido-header">
                <div class="barra">
                    <a href="${basePath}Index.html">
                        <img src="${imgPath}LOLLogo.png" alt="Logo League of Legends" class="logo1">
                    </a>
                    <nav class="navegacion">
                        <a href="${basePath}Index.html">Inicio</a>
                        <a href="${htmlPath}campeones.html">Campeones</a>
                        <a href="${htmlPath}runas.html">Runas</a>
                        <a href="${htmlPath}contacto.html">Contacto</a>
                    </nav>
                </div>
            </div> 
        </header>
    `;
}

/**
 * Genera el HTML del footer común para todas las páginas
 */
function generateFooter() {
    return `
        <footer class="site-footer">
            <div class="contenedor">
                <p>&copy; 2025 WebLOL. Todos los derechos reservados.</p>
            </div>
        </footer>
    `;
}

/**
 * Genera la estructura de hero section personalizable
 */
function generateHeroSection(title, description) {
    return `
        <section class="hero-inicio">
            <div class="hero-content">
                <h1 class="titulo-pagina">${title}</h1>
                <p class="descripcion-inicio">${description}</p>
            </div>
        </section>
    `;
}

/**
 * Inicializa los componentes cuando el DOM está listo
 */
function initializeComponents() {
    // Insertar header al inicio del body
    const headerContainer = document.querySelector('[data-component="header"]');
    if (headerContainer) {
        headerContainer.innerHTML = generateHeader();
    }
    
    // Insertar footer al final del body
    const footerContainer = document.querySelector('[data-component="footer"]');
    if (footerContainer) {
        footerContainer.innerHTML = generateFooter();
    }
    
    // Insertar hero section si se especifica
    const heroContainer = document.querySelector('[data-component="hero"]');
    if (heroContainer) {
        const title = heroContainer.getAttribute('data-title') || 'Título por defecto';
        const description = heroContainer.getAttribute('data-description') || 'Descripción por defecto';
        heroContainer.innerHTML = generateHeroSection(title, description);
    }
}

// Auto-inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', initializeComponents);

// También exportar funciones para uso manual si es necesario
window.WebLOLComponents = {
    generateHeader,
    generateFooter,
    generateHeroSection,
    initializeComponents,
    PAGE_CONFIG
};