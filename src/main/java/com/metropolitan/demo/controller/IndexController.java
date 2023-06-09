package com.metropolitan.demo.controller;
import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.repository.VoziloRepository;
import com.metropolitan.demo.service.VoziloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final VoziloService voziloService;
    private final VoziloRepository voziloRepository;

    @GetMapping("/")
    public String getAllVozila(Model model) {
        List<Vozilo> vozila = voziloService.findAll();
        model.addAttribute("vozila", vozila);
        return "index";

    }
    @GetMapping("/sortiraj")
    public String sortirajPoCeni(Model model) {
        List<Vozilo> vozila = voziloService.findAll();
        vozila = voziloService.sortByPrica(vozila);
        model.addAttribute("vozila", vozila);
        return "index";
    }

    @GetMapping("/pretraga/{marka}")
    public String pronadjiVozilaPoMarci(@RequestParam("marka") String marka, Model model) {

       List<Vozilo> listaVozila = (List<Vozilo>) voziloRepository.findByMarkaIgnoreCase(marka);
       model.addAttribute("listaVozila", listaVozila);

        return "index";
    }

    @GetMapping("/pretraga/{boja}")
    public String pronadjiVozilaPoBoji(@RequestParam("boja") String boja, Model model) {
        List<Vozilo> vozila = (List<Vozilo>) voziloRepository.findByBoja(boja);
        model.addAttribute("vozila", vozila);
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){

        return "/login";
    }

    @GetMapping("/kontakt")
    public String openAdminPanel(){
        return "/kontakt";
    }


    @GetMapping("/detalji")
    public String prikaziDetaljeVozila(@RequestParam("voziloId") Integer voziloId, Model model) {
        Vozilo vozilo = voziloService.findById(voziloId);
        model.addAttribute("vozilo", vozilo);
        return "vozilo/detalji";
    }


}