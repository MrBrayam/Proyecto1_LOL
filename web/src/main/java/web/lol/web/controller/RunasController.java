package web.lol.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/runas")
public class RunasController {

    @GetMapping
    public String runas(Model model) {
        model.addAttribute("title", "Runas");
        model.addAttribute("description", "Descubre las diferentes categorías de runas y potencia a tus campeones");
        return "runas";
    }

    @GetMapping("/{categoria}")
    public String runasCategoria(@PathVariable String categoria, Model model) {
        Map<String, Object> runaData = obtenerRunasPorCategoria(categoria);
        
        if (runaData == null) {
            return "redirect:/runas";
        }
        
        model.addAttribute("categoria", runaData.get("categoria"));
        model.addAttribute("descripcion", runaData.get("descripcion"));
        model.addAttribute("color", runaData.get("color"));
        model.addAttribute("runasClaves", runaData.get("runasClaves"));
        model.addAttribute("runasSegundaFila", runaData.get("runasSegundaFila"));
        model.addAttribute("runasTerceraFila", runaData.get("runasTerceraFila"));
        model.addAttribute("runasCuartaFila", runaData.get("runasCuartaFila"));
        model.addAttribute("imagenPrincipal", runaData.get("imagenPrincipal"));
        
        return "runas-detalle";
    }

    private Map<String, Object> obtenerRunasPorCategoria(String categoria) {
        Map<String, Object> data = new HashMap<>();
        
        switch (categoria.toLowerCase()) {
            case "precision":
                data.put("categoria", "Precisión");
                data.put("descripcion", "Mejora tus ataques básicos y sustentabilidad en combate prolongado");
                data.put("color", "#C8AA6E");
                data.put("imagenPrincipal", "/img/1_Runas/Presicion/runas-precision-league-of-legends.jpg");
                
                data.put("runasClaves", Arrays.asList(
                    Map.of("nombre", "Conquistador", "descripcion", "Ganas poder de ataque adaptativo al luchar contra campeones", "imagen", "/img/1_Runas/Presicion/conqueror-55x.png"),
                    Map.of("nombre", "Tempo Letal", "descripcion", "Ganas velocidad de ataque que supera el límite", "imagen", "/img/1_Runas/Presicion/lethal-tempo-55x.png"),
                    Map.of("nombre", "Ataque Concentrado", "descripcion", "Tus ataques contra el mismo objetivo se vuelven más letales", "imagen", "/img/1_Runas/Presicion/press-the-attack-55x.png"),
                    Map.of("nombre", "Pies Veloces", "descripcion", "Ganas velocidad de movimiento y curación", "imagen", "/img/1_Runas/Presicion/fleet-footwork-55x.png")
                ));
                
                data.put("runasSegundaFila", Arrays.asList(
                    Map.of("nombre", "Presencia Mental", "descripcion", "Restaura maná cuando dañas a campeones enemigos", "imagen", "/img/1_Runas/Presicion/presence-of-mind-55x.png"),
                    Map.of("nombre", "Triunfo", "descripcion", "Las eliminaciones restauran salud", "imagen", "/img/1_Runas/Presicion/triumph-55x.png"),
                    Map.of("nombre", "Absorber Vida", "descripcion", "Curas cuando dañas a campeones enemigos", "imagen", "/img/1_Runas/Presicion/absorb-life-55x.png")
                ));
                
                data.put("runasTerceraFila", Arrays.asList(
                    Map.of("nombre", "Leyenda: Presteza", "descripcion", "Ganas velocidad de ataque permanente por eliminación única", "imagen", "/img/1_Runas/Presicion/legend-alacrity-55x.png"),
                    Map.of("nombre", "Leyenda: Torrente de Vida", "descripcion", "Ganas robo de vida permanente por eliminación única", "imagen", "/img/1_Runas/Presicion/legend-bloodline-55x.png"),
                    Map.of("nombre", "Leyenda: Tenacidad", "descripcion", "Ganas reducción de enfriamiento de habilidades permanente", "imagen", "/img/1_Runas/Presicion/legend-haste-55x.png")
                ));
                
                data.put("runasCuartaFila", Arrays.asList(
                    Map.of("nombre", "Golpe de Gracia", "descripcion", "Infliges daño extra a enemigos con poca vida", "imagen", "/img/1_Runas/Presicion/coup-de-grace-55x.png"),
                    Map.of("nombre", "Derribar", "descripcion", "Infliges daño extra a enemigos con más vida máxima que tú", "imagen", "/img/1_Runas/Presicion/cut-down-55x.png"),
                    Map.of("nombre", "Última Resistencia", "descripcion", "Infliges más daño basado en tu vida perdida", "imagen", "/img/1_Runas/Presicion/last-stand-55x.png")
                ));
                break;
                
            case "dominacion":
                data.put("categoria", "Dominación");
                data.put("descripcion", "Aumenta tu poder explosivo y capacidades de eliminación instantánea");
                data.put("color", "#DA1E37");
                data.put("imagenPrincipal", "/img/1_Runas/Dominacion/runas-dominacion.jpg");
                
                data.put("runasClaves", Arrays.asList(
                    Map.of("nombre", "Electrocutar", "descripcion", "Inflige daño extra tras golpear a un enemigo con 3 ataques o habilidades separadas", "imagen", "/img/1_Runas/Dominacion/electrocute-55x.png"),
                    Map.of("nombre", "Lluvia de Hojas", "descripcion", "Las eliminaciones y asistencias descargan proyectiles que infligen daño", "imagen", "/img/1_Runas/Dominacion/hail-of-blades-55x.png"),
                    Map.of("nombre", "Cosecha Oscura", "descripcion", "Inflige daño adaptativo extra a campeones con poca vida", "imagen", "/img/1_Runas/Dominacion/dark-harvest-55x.png")
                ));
                
                data.put("runasSegundaFila", Arrays.asList(
                    Map.of("nombre", "Golpe Bajo", "descripcion", "Inflige daño verdadero extra a campeones con movilidad reducida", "imagen", "/img/1_Runas/Dominacion/cheap-shot-55x.png"),
                    Map.of("nombre", "Sabor de Sangre", "descripcion", "Cura vida cuando dañas a un campeón enemigo", "imagen", "/img/1_Runas/Dominacion/taste-of-blood-55x.png"),
                    Map.of("nombre", "Impacto Repentino", "descripcion", "Ganas penetración tras usar un dash, salto o teletransporte", "imagen", "/img/1_Runas/Dominacion/sudden-impact-55x.png")
                ));
                
                data.put("runasTerceraFila", Arrays.asList(
                    Map.of("nombre", "Guardián Vigilante", "descripcion", "Recibes visión cuando un enemigo entra en un arbusto cercano", "imagen", "/img/1_Runas/Dominacion/sixth-sense-55x.png"),
                    Map.of("nombre", "Vigía Profundo", "descripcion", "Ganas velocidad de movimiento cerca de wards y cuando las destruyes", "imagen", "/img/1_Runas/Dominacion/deep-ward-55x.png"),
                    Map.of("nombre", "Recuerdos Macabros", "descripcion", "Recolecta recuerdos de campeones enemigos para ganar poder adaptativo", "imagen", "/img/1_Runas/Dominacion/grisly-mementos-55x.png")
                ));
                
                data.put("runasCuartaFila", Arrays.asList(
                    Map.of("nombre", "Cazador Definitivo", "descripcion", "Reduce el enfriamiento de tu definitiva por eliminación única", "imagen", "/img/1_Runas/Dominacion/ultimate-hunter-55x.png"),
                    Map.of("nombre", "Cazador Incansable", "descripcion", "Ganas velocidad de movimiento fuera de combate", "imagen", "/img/1_Runas/Dominacion/relentless-hunter-55x.png"),
                    Map.of("nombre", "Cazador de Tesoros", "descripcion", "Ganas oro extra por eliminación única", "imagen", "/img/1_Runas/Dominacion/treasure-hunter-55x.png")
                ));
                break;
                
            case "brujeria":
                data.put("categoria", "Brujería");
                data.put("descripcion", "Potencia tus habilidades mágicas y utilidad de combate");
                data.put("color", "#4E7FCC");
                data.put("imagenPrincipal", "/img/1_Runas/Brujeria/runas-brujeria.jpg");
                
                data.put("runasClaves", Arrays.asList(
                    Map.of("nombre", "Cometa Arcano", "descripcion", "Inflige daño extra tras dañar a un campeón con una habilidad", "imagen", "/img/1_Runas/Brujeria/arcane-comet-55x.png"),
                    Map.of("nombre", "Invocación de Aery", "descripcion", "Tus ataques y habilidades envían a Aery para dañar enemigos o proteger aliados", "imagen", "/img/1_Runas/Brujeria/summon-aery-55x.png"),
                    Map.of("nombre", "Paso Etéreo", "descripcion", "Ganas velocidad de movimiento al usar hechizos de invocador", "imagen", "/img/1_Runas/Brujeria/phase-rush-55x.png")
                ));
                
                data.put("runasSegundaFila", Arrays.asList(
                    Map.of("nombre", "Banda de Flujo de Maná", "descripcion", "Golpear a un campeón enemigo con una habilidad permanentemente aumenta tu maná o regeneración", "imagen", "/img/1_Runas/Brujeria/manaflow-band-55x.png"),
                    Map.of("nombre", "Capa de Nimbo", "descripcion", "Ganas velocidad de movimiento tras usar un hechizo de invocador", "imagen", "/img/1_Runas/Brujeria/nimbus-cloak-55x.png"),
                    Map.of("nombre", "Trascendencia", "descripcion", "Ganas reducción de enfriamiento de habilidades al subir de nivel", "imagen", "/img/1_Runas/Brujeria/transcendence-55x.png")
                ));
                
                data.put("runasTerceraFila", Arrays.asList(
                    Map.of("nombre", "Foco Absoluto", "descripcion", "Ganas poder de habilidad extra cuando tienes mucho maná", "imagen", "/img/1_Runas/Brujeria/absolute-focus-55x.png"),
                    Map.of("nombre", "Celeridad", "descripcion", "Ganas poder de ataque o poder de habilidad basado en tu velocidad de movimiento extra", "imagen", "/img/1_Runas/Brujeria/celerity-55x.png"),
                    Map.of("nombre", "Chamuscar", "descripcion", "Tu siguiente habilidad quema al enemigo objetivo", "imagen", "/img/1_Runas/Brujeria/scorch-55x.png")
                ));
                
                data.put("runasCuartaFila", Arrays.asList(
                    Map.of("nombre", "Caminar Sobre Agua", "descripcion", "Ganas velocidad de movimiento cuando estás en el río", "imagen", "/img/1_Runas/Brujeria/waterwalking-55x.png"),
                    Map.of("nombre", "Tormenta Creciente", "descripcion", "Ganas poder de ataque adaptativo cada 10 minutos", "imagen", "/img/1_Runas/Brujeria/gathering-storm-55x.png"),
                    Map.of("nombre", "Arcanista Axiomático", "descripcion", "Tras obtener una eliminación, reduces el enfriamiento de tu definitiva", "imagen", "/img/1_Runas/Brujeria/axiom-arcanist-55x.png")
                ));
                break;
                
            case "valor":
                data.put("categoria", "Valor");
                data.put("descripcion", "Incrementa tu resistencia y capacidades defensivas");
                data.put("color", "#00C851");
                data.put("imagenPrincipal", "/img/1_Runas/Valor/runas-valor.jpg");
                
                data.put("runasClaves", Arrays.asList(
                    Map.of("nombre", "Guardián", "descripcion", "Protege a aliados cercanos de daño explosivo y les otorga velocidad de movimiento", "imagen", "/img/1_Runas/Valor/guardian-55x.png"),
                    Map.of("nombre", "Réplica", "descripcion", "Tras recibir daño, tu siguiente ataque básico contra un campeón inflige daño extra", "imagen", "/img/1_Runas/Valor/aftershock-55x.png"),
                    Map.of("nombre", "Agarre del Inmortal", "descripcion", "Cada 4 segundos en combate, tu siguiente ataque contra un campeón te otorga vida", "imagen", "/img/1_Runas/Valor/grasp-of-the-undying-55x.png")
                ));
                
                data.put("runasSegundaFila", Arrays.asList(
                    Map.of("nombre", "Demoler", "descripcion", "Tras estar cerca de una torreta enemiga, cargas un ataque poderoso contra ella", "imagen", "/img/1_Runas/Valor/demolish-55x.png"),
                    Map.of("nombre", "Fuente de Vida", "descripcion", "Inmovilizar a un campeón enemigo marca a ese objetivo", "imagen", "/img/1_Runas/Valor/font-of-life-55x.png"),
                    Map.of("nombre", "Golpe de Escudo", "descripcion", "Cuando ganas un escudo, tu siguiente ataque básico inflige daño extra", "imagen", "/img/1_Runas/Valor/shield-bash-55x.png")
                ));
                
                data.put("runasTerceraFila", Arrays.asList(
                    Map.of("nombre", "Acondicionamiento", "descripcion", "Ganas resistencias mágica y de armadura extra", "imagen", "/img/1_Runas/Valor/conditioning-55x.png"),
                    Map.of("nombre", "Segundo Viento", "descripcion", "Tras recibir daño, regeneras vida durante un tiempo", "imagen", "/img/1_Runas/Valor/second-wind-55x.png"),
                    Map.of("nombre", "Placas Óseas", "descripcion", "Tras recibir daño, bloqueas los próximos 3 ataques o habilidades", "imagen", "/img/1_Runas/Valor/bone-plating-55x.png")
                ));
                
                data.put("runasCuartaFila", Arrays.asList(
                    Map.of("nombre", "Sobrecrecimiento", "descripcion", "Ganas vida máxima permanente cuando mueren súbditos cerca", "imagen", "/img/1_Runas/Valor/overgrowth-55x.png"),
                    Map.of("nombre", "Revitalizar", "descripcion", "La curación y los escudos que otorgas o recibes son más fuertes cuando tienes poca vida", "imagen", "/img/1_Runas/Valor/revitalize-55x.png"),
                    Map.of("nombre", "Inquebrantable", "descripcion", "Ganas resistencia a ralentizaciones cuando usas un hechizo de invocador", "imagen", "/img/1_Runas/Valor/unflinching-55x.png")
                ));
                break;
                
            case "inspiracion":
                data.put("categoria", "Inspiración");
                data.put("descripcion", "Ofrece utilidades únicas y opciones creativas de juego");
                data.put("color", "#42A5F5");
                data.put("imagenPrincipal", "/img/1_Runas/Inspiracion/runas-inspiracion.jpg");
                
                data.put("runasClaves", Arrays.asList(
                    Map.of("nombre", "Primer Golpe", "descripcion", "Cuando atacas primero a un campeón enemigo, infliges daño verdadero extra", "imagen", "/img/1_Runas/Inspiracion/first-strike-55x.png"),
                    Map.of("nombre", "Libro de Hechizos Liberado", "descripcion", "Cambias periódicamente uno de tus hechizos de invocador por uno diferente", "imagen", "/img/1_Runas/Inspiracion/unsealed-spellbook-55x.png"),
                    Map.of("nombre", "Aumento Glacial", "descripcion", "Inmovilizar a un campeón enemigo comienza un proceso que ralentiza a enemigos cercanos", "imagen", "/img/1_Runas/Inspiracion/glacial-augment-55x.png")
                ));
                
                data.put("runasSegundaFila", Arrays.asList(
                    Map.of("nombre", "Calzado Mágico", "descripcion", "Recibes botas gratis ligeramente mejoradas", "imagen", "/img/1_Runas/Inspiracion/magical-footwear-55x.png"),
                    Map.of("nombre", "Entrega de Galletas", "descripcion", "Recibes galletas gratuitas que restauran vida y maná", "imagen", "/img/1_Runas/Inspiracion/biscuit-delivery-55x.png"),
                    Map.of("nombre", "Devolución de Efectivo", "descripcion", "Obtienes oro extra al comprar objetos", "imagen", "/img/1_Runas/Inspiracion/cash-back-55x.png")
                ));
                
                data.put("runasTerceraFila", Arrays.asList(
                    Map.of("nombre", "Hexflash", "descripcion", "Puedes usar el hechizo Flash mientras canalizas la vuelta a la base", "imagen", "/img/1_Runas/Inspiracion/hextech-flashtraption-55x.png"),
                    Map.of("nombre", "Tónico Temporal", "descripcion", "Las pociones y elixires duran más tiempo", "imagen", "/img/1_Runas/Inspiracion/time-warp-tonic-55x.png"),
                    Map.of("nombre", "Velocidad de Acercamiento", "descripcion", "Ganas velocidad de movimiento al acercarte a aliados dañados o enemigos con movilidad reducida", "imagen", "/img/1_Runas/Inspiracion/approach-velocity-55x.png")
                ));
                
                data.put("runasCuartaFila", Arrays.asList(
                    Map.of("nombre", "Perspicacia Cósmica", "descripcion", "Ganas reducción de enfriamiento en objetos activos y hechizos de invocador", "imagen", "/img/1_Runas/Inspiracion/cosmic-insight-55x.png"),
                    Map.of("nombre", "Jack de Todos los Oficios", "descripcion", "Ganas poder de ataque adaptativo por cada tipo diferente de objeto legendario", "imagen", "/img/1_Runas/Inspiracion/jack-of-all-trades-55x.png"),
                    Map.of("nombre", "Tónico Triple", "descripcion", "Cuando usas una poción, otras dos se activan con menor potencia", "imagen", "/img/1_Runas/Inspiracion/triple-tonic-55x.png")
                ));
                break;
                
            default:
                return null;
        }
        
        return data;
    }
}