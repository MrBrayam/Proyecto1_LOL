/**
 * Contact Form Handler para WebLOL
 * Maneja el envío de emails usando EmailJS
 */

// Configuración EmailJS para Brayam - WebLOL
(function() {
	console.log('Inicializando EmailJS...');
	
	// Verificar que EmailJS esté disponible
	if (typeof emailjs === 'undefined') {
		console.error('EmailJS no está disponible globalmente');
		return;
	}
	
	try {
		emailjs.init("7xlkX0mmfz1rJY4M1"); // Public Key de Brayam
		console.log('EmailJS inicializado correctamente con Public Key: 7xlkX0mmfz1rJY4M1');
		
		// Verificar que la inicialización fue exitosa
		console.log('EmailJS object:', emailjs);
		console.log('EmailJS methods available:', Object.getOwnPropertyNames(emailjs));
		
	} catch (error) {
		console.error('Error en inicialización de EmailJS:', error);
	}
})();

// Configuración del servicio EmailJS
const EMAILJS_CONFIG = {
	serviceId: 'service_o4dhbfa',
	templateId: 'template_k0lj9q8', // Template ID correcto del dashboard
	publicKey: '7xlkX0mmfz1rJY4M1'
};

/**
 * Inicializa el formulario de contacto
 */
function initContactForm() {
	const form = document.getElementById('contactForm');
	const statusElement = document.getElementById('status');

	if (!form || !statusElement) {
		console.error('Elementos del formulario no encontrados');
		return;
	}

	form.addEventListener('submit', function(e) {
		e.preventDefault();
		
		console.log('Formulario enviado, iniciando proceso...');
		
		// Validar que EmailJS esté disponible
		if (!window.emailjs) {
			console.error('EmailJS no está disponible');
			statusElement.innerHTML = 'Error: EmailJS no cargado correctamente.';
			statusElement.style.color = '#e74c3c';
			return;
		}
		
		// Mostrar estado de envío
		statusElement.innerHTML = 'Enviando mensaje al soporte...';
		statusElement.style.color = '#f0b90b';
		statusElement.style.fontWeight = 'bold';
		
		// Deshabilitar botón durante el envío
		const submitBtn = form.querySelector('.btn');
		submitBtn.disabled = true;
		submitBtn.textContent = 'Enviando...';

		// Obtener fecha actual para el template
		const currentDate = new Date().toLocaleString('es-ES', {
			year: 'numeric',
			month: 'long',
			day: 'numeric',
			hour: '2-digit',
			minute: '2-digit',
			timeZone: 'America/Mexico_City'
		});

		// Parámetros personalizados para el template de Brayam
		const templateParams = {
			from_name: document.getElementById('name').value,
			from_email: document.getElementById('email').value,
			email: 'brayamaristafrndz@gmail.com', // Para el {{email}} del template
			message: document.getElementById('message').value,
			current_date: currentDate,
			website_name: 'WebLOL',
			admin_name: 'Brayam'
		};
		
		console.log('Parámetros del template:', templateParams);
		console.log('Configuración EmailJS:', EMAILJS_CONFIG);
		
		// Verificar que todos los campos requeridos están presentes
		const requiredFields = ['from_name', 'from_email', 'message'];
		const missingFields = requiredFields.filter(field => !templateParams[field] || templateParams[field].trim() === '');
		
		if (missingFields.length > 0) {
			console.error('Campos requeridos faltantes:', missingFields);
			statusElement.innerHTML = `Faltan campos requeridos: ${missingFields.join(', ')}`;
			statusElement.style.color = '#e74c3c';
			submitBtn.disabled = false;
			submitBtn.textContent = 'Enviar mensaje';
			return;
		}
		
		console.log('Todos los campos requeridos están presentes');
		console.log('Intentando enviar email...');
		console.log('Service ID:', EMAILJS_CONFIG.serviceId);
		console.log('Template ID:', EMAILJS_CONFIG.templateId);

		// Enviar email usando EmailJS con template personalizado
		emailjs.send(EMAILJS_CONFIG.serviceId, EMAILJS_CONFIG.templateId, templateParams)
			.then(function(response) {
				console.log('Email enviado exitosamente a Brayam:', response);
				console.log('Estado de la respuesta:', response.status);
				console.log('Texto de la respuesta:', response.text);
				statusElement.innerHTML = '¡Mensaje enviado exitosamente!<br>El Soporte te responderá pronto.';
				statusElement.style.color = '#00d084';
				form.reset();
				
				// Mensaje adicional después de 3 segundos
				setTimeout(() => {
					statusElement.innerHTML = '¡Gracias por contactar WebLOL!<br>Que tengas buen día invocador.';
					statusElement.style.color = '#c8aa6e';
				}, 3000);
			})
			.catch(function(error) {
				console.error('Error al enviar email:', error);
				console.error('Tipo de error:', typeof error);
				console.error('Error completo:', JSON.stringify(error, null, 2));
				
				// Intentar extraer más información del error
				const errorDetails = {
					message: error.message || 'Mensaje no disponible',
					status: error.status || 'Status no disponible',
					text: error.text || 'Texto no disponible',
					statusText: error.statusText || 'StatusText no disponible',
					name: error.name || 'Nombre no disponible'
				};
				
				console.error('Detalles del error:', errorDetails);
				
				let errorMessage = 'Error al enviar el mensaje.<br>Por favor, inténtalo de nuevo.';
				
				// Mensajes de error específicos
				if (error.status === 400) {
					errorMessage += '<br><small>Error 400: Datos inválidos o Template ID incorrecto</small>';
				} else if (error.status === 401) {
					errorMessage += '<br><small>Error 401: Public Key incorrecto</small>';
				} else if (error.status === 402) {
					errorMessage += '<br><small>Error 402: Límite de emails alcanzado</small>';
				} else if (error.status === 403) {
					errorMessage += '<br><small>Error 403: Service ID incorrecto o acceso denegado</small>';
				} else if (error.status === 404) {
					errorMessage += '<br><small>Error 404: Template o Service no encontrado</small>';
				} else if (!error.status) {
					errorMessage += '<br><small>Posible problema de red o CORS</small>';
				}
				
				errorMessage += `<br><small>Status: ${errorDetails.status} | Mensaje: ${errorDetails.message}</small>`;
				
				statusElement.innerHTML = errorMessage;
				statusElement.style.color = '#e74c3c';
			})
			.finally(function() {
				// Rehabilitar botón
				submitBtn.disabled = false;
				submitBtn.textContent = 'Enviar mensaje';
			});
	});

	// Mejora de UX: Limpiar estado cuando el usuario comience a escribir de nuevo
	const inputs = form.querySelectorAll('input, textarea');
	inputs.forEach(input => {
		input.addEventListener('input', function() {
			if (statusElement.style.color === 'rgb(231, 76, 60)') { // Si había error
				statusElement.textContent = '';
			}
		});
	});
}

// Auto-inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', initContactForm);

/**
 * Función para actualizar la configuración de EmailJS
 * Útil para cambiar el Template ID sin recargar la página
 */
function updateEmailJSConfig(newTemplateId) {
	EMAILJS_CONFIG.templateId = newTemplateId;
	console.log('Template ID actualizado a:', newTemplateId);
}

// Exportar funciones para uso global
window.ContactForm = {
	initContactForm,
	updateEmailJSConfig,
	EMAILJS_CONFIG
};