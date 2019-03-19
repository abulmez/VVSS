package note.ui;

import note.controller.NoteController;
import note.main.ClasaException;
import note.model.Corigent;
import note.model.Medie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UI {

    private NoteController ctrl = new NoteController();
    private String elevFileName;
    private String noteFileName;

    public UI(String elevFileName,String noteFileName){
        this.elevFileName = elevFileName;
        this.noteFileName = noteFileName;
    }

    public void showUI() throws ClasaException {
        List<Medie> medii = new LinkedList<Medie>();
        List<Corigent> corigenti = new ArrayList<Corigent>();
        ctrl.readElevi(elevFileName);
        ctrl.readNote(noteFileName);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        boolean gasit = false;
        while(!gasit) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Iesire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
            try {
                int option = Integer.parseInt(br.readLine());
                switch(option) {
                    case 1: break;
                    case 2: medii = ctrl.calculeazaMedii();
                        for(Medie medie:medii)
                            System.out.println(medie);
                        break;
                    case 3: corigenti = ctrl.getCorigenti();
                        for(Corigent corigent:corigenti)
                            System.out.println(corigent);
                        break;
                    case 4: gasit = true;
                        break;
                    default: System.out.println("Introduceti o optiune valida!");
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
