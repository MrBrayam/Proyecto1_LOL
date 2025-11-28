package web.lol.web.service;

import org.springframework.stereotype.Service;
import web.lol.web.model.Campeon;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampeonService {
    
    private List<Campeon> campeones;
    
    public CampeonService() {
        inicializarCampeones();
    }
    
    public List<Campeon> obtenerTodosCampeones() {
        return new ArrayList<>(campeones);
    }
    
    public Map<String, List<Campeon>> obtenerCampeonesPorLetra() {
        return campeones.stream()
                .collect(Collectors.groupingBy(Campeon::getLetra, TreeMap::new, Collectors.toList()));
    }
    
    private void inicializarCampeones() {
        campeones = Arrays.asList(
            // Letra A
            new Campeon("Aatrox", "La Espada de los Mundos Sombríos", "/img/Aatrox/OriginalAatrox.jpg", "A"),
            new Campeon("Ahri", "La Vastaya de Nueve Colas", "/img/Ahri/OriginalAhri.jpg", "A"),
            new Campeon("Akali", "La asesina sin maestro", "/img/Akali/OriginalAkali.jpg", "A"),
            new Campeon("Akshan", "El Centinela Rebelde", "/img/Akshan/OriginalAkshan.jpg", "A"),
            new Campeon("Alistar", "El Minotauro", "/img/Alistar/OriginalAlistar.jpg", "A"),
            new Campeon("Ambessa", "La Matriarca de la Guerra", "/img/Ambessa/OriginalAmbessa.jpg", "A"),
            new Campeon("Amumu", "La Momia Triste", "/img/Amumu/OriginalAmumu.jpg", "A"),
            new Campeon("Anivia", "La Criofénix", "/img/Anivia/OriginalAnivia.jpg", "A"),
            new Campeon("Annie", "La Niña Sombría", "/img/Annie/OriginalAnnie.jpg", "A"),
            new Campeon("Aphelios", "El Arma de los Fieles", "/img/Aphelios/OriginalAphelios.jpg", "A"),
            new Campeon("Ashe", "La Arquera de Hielo", "/img/Ashe/OriginalAshe.jpg", "A"),
            new Campeon("Aurelion Sol", "El Forjador de Estrellas", "/img/Aurelion_Sol/OriginalAurelion Sol.jpg", "A"),
            new Campeon("Aurora", "La Bruja Entre Mundos", "/img/Aurora/OriginalAurora.jpg", "A"),
            new Campeon("Azir", "El Emperador de las Arenas", "/img/Azir/OriginalAzir.jpg", "A"),
            
            // Letra B
            new Campeon("Bard", "El Guardián Errante", "/img/Bard/OriginalBard.jpg", "B"),
            new Campeon("Bel'Veth", "La Emperatriz del Vacío", "/img/Bel'Veth/OriginalBel'Veth.jpg", "B"),
            new Campeon("Blitzcrank", "El Gran Golem de Vapor", "/img/Blitzcrank/OriginalBlitzcrank.jpg", "B"),
            new Campeon("Brand", "La Venganza Ardiente", "/img/Brand/OriginalBrand.jpg", "B"),
            new Campeon("Braum", "El Corazón de Freljord", "/img/Braum/OriginalBraum.jpg", "B"),
            new Campeon("Briar", "La Hambruna Desatada", "/img/Briar/OriginalBriar.jpg", "B"),
            
            // Letra C
            new Campeon("Caitlyn", "La sheriff de Piltover", "/img/Caitlyn/OriginalCaitlyn.jpg", "C"),
            new Campeon("Camille", "La Sombra de Acero", "/img/Camille/OriginalCamille.jpg", "C"),
            new Campeon("Cassiopeia", "El Abrazo de la Serpiente", "/img/Cassiopeia/OriginalCassiopeia.jpg", "C"),
            new Campeon("Cho'Gath", "El Terror del Vacío", "/img/Cho'Gath/OriginalCho'Gath.jpg", "C"),
            new Campeon("Corki", "El As Audaz", "/img/Corki/OriginalCorki.jpg", "C"),
            
            // Letra D
            new Campeon("Darius", "La Mano de Noxus", "/img/Darius/OriginalDarius.jpg", "D"),
            new Campeon("Diana", "El Desdén de la Luna", "/img/Diana/OriginalDiana.jpg", "D"),
            new Campeon("Dr.Mundo", "El Loco de Zaun", "/img/Dr.Mundo/OriginalDr. Mundo.jpg", "D"),
            new Campeon("Draven", "El Ejecutor Glorioso", "/img/Draven/OriginalDraven.jpg", "D"),
            
            // Solo incluyo algunos ejemplos para que funcione el demo
            // Puedes agregar todos los demás campeones siguiendo el mismo patrón
            new Campeon("Ekko", "El Niño que Fragmentó el Tiempo", "/img/Ekko/OriginalEkko.jpg", "E"),
            new Campeon("Ezreal", "El Explorador Pródigo", "/img/Ezreal/OriginalEzreal.jpg", "E"),
            new Campeon("Garen", "El Poder de Demacia", "/img/Garen/OriginalGaren.jpg", "G"),
            new Campeon("Jinx", "La Bala Perdida", "/img/Jinx/OriginalJinx.jpg", "J"),
            new Campeon("Lux", "La Dama de la Luz", "/img/Lux/OriginalLux.jpg", "L"),
            new Campeon("Yasuo", "El Imperdonado", "/img/Yasuo/OriginalYasuo.jpg", "Y"),
            new Campeon("Zed", "El Maestro de las Sombras", "/img/Zed/OriginalZed.jpg", "Z")
        );
    }
}