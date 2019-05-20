package note.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import note.main.ClasaException;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ClasaRepositoryMock;
import note.repository.EleviRepository;
import note.repository.EleviRepositoryMock;
import note.repository.NoteRepository;
import note.repository.NoteRepositoryMock;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static note.utils.Constants.invalidElev;

@Component
class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD"); // also added header to allow POST, GET method to be available
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Allow", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        filterChain.doFilter(request, response);
    }
}

@Component
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {
    private NoteRepository note;
    private ClasaRepository clasa;
    private EleviRepository elevi;

    public NoteController() {
        note = new NoteRepositoryMock();
        clasa = new ClasaRepositoryMock();
        elevi = new EleviRepositoryMock();
    }

    public NoteController(NoteRepository note,ClasaRepository clasa,EleviRepository elevi) {
        this.note = note;
        this.clasa = clasa;
        this.elevi = elevi;
    }

    public void addNota(Nota nota) throws ClasaException {
        note.addNota(nota);
    }

    public void addElev(Elev elev) {
        elevi.addElev(elev);
    }

    public void creeazaClasa(List<Elev> elevi, List<Nota> note) {
        clasa.creazaClasa(elevi, note);
    }

    public List<Medie> calculeazaMedii() throws ClasaException {
        return clasa.calculeazaMedii();

    }

    public List<Nota> getNote() {
        return note.getNote();
    }

    public List<Elev> getElevi() {
        return elevi.getElevi();
    }

    public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
        return clasa.getClasa();
    }

    public void afiseazaClasa() {
        clasa.afiseazaClasa();
    }

    public void readElevi(String fisier) throws ClasaException{
        elevi.readElevi(fisier);
    }

    public void readNote(String fisier) {
        note.readNote(fisier);
    }

    public List<Corigent> getCorigenti() throws ClasaException{
        return clasa.getCorigenti(clasa.getClasa());
    }

    public Medie calculareMedieSemestriala(String nume, int numarMatricol,List<Nota> note,List<Elev> elevi) throws ClasaException {
        Elev elevCautat = null;
        for (int i = 0; i < elevi.size(); i++) {
            if (elevi.get(i).getNrmatricol() == numarMatricol) {
                elevCautat = elevi.get(i);
            }
        }
        if (elevCautat != null) {
            double sumaNote = 0.0;
            int numarNote = 0;
            for (int i = 0; i < note.size(); i++) {
                if (note.get(i).getNrmatricol() == numarMatricol) {
                    sumaNote += note.get(i).getNota();
                    numarNote++;
                }
            }
            if (numarNote > 0) {
                return new Medie(elevCautat, sumaNote / numarNote);
            } else {
                return new Medie(elevCautat, 0);
            }

        } else {
            throw new ClasaException(invalidElev);
        }
    }
}
