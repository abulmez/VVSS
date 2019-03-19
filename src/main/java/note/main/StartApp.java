package note.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import note.main.ClasaException;
import note.model.Corigent;
import note.model.Medie;

import note.controller.NoteController;
import note.ui.UI;

//functionalitati
//F01.	 adaugarea unei note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat şi alfabetic dupa nume.


public class StartApp {

    /**
     * @param args
     * @throws ClasaException
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UI ui = new UI(args[0],args[1]);
        try {
            ui.showUI();
        } catch (ClasaException e) {
            e.printStackTrace();
        }
    }

}
