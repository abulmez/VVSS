package note.controller;

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

import static note.utils.Constants.invalidElev;

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
