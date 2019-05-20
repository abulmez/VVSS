package note.controller;

import com.google.gson.Gson;
import note.main.ClasaException;
import note.model.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class NoteRestController {

    @Autowired
    private NoteController controller;
    @Autowired(required = false)
    ApplicationArguments appArgs;
    Gson gson = new Gson();

    @PostConstruct
    public void init() {
        try {
            controller.readElevi(appArgs.getSourceArgs()[0]);
            controller.readNote(appArgs.getSourceArgs()[1]);
        } catch (ClasaException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hallo " + name + " !";
    }

    @RequestMapping("/note")
    public List<Nota> getNote() {
        return controller.getNote();
    }

    @PostMapping("/addNota")
    public void signUp(@RequestBody String notaJson) throws ClasaException {
        controller.addNota(gson.fromJson(notaJson, Nota.class));
    }


}
