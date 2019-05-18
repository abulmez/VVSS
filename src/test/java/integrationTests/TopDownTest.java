package integrationTests;

import note.controller.NoteController;
import note.main.ClasaException;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TopDownTest {

    private ClasaRepository clasaRepository;
    private EleviRepository eleviRepository;
    private NoteRepository noteRepository;
    private NoteController controller;
    private String VALID_FILE_NAME = "eleviTest.txt";
    private Elev mockElev1 = new Elev(1,"Ana");
    private Elev mockElev2 = new Elev(2,"are");
    private Elev mockElev3 = new Elev(3,"mere");

    @Before
    public void init() {
        clasaRepository = new ClasaRepositoryMock();
        eleviRepository = new EleviRepositoryMock();
        noteRepository = new NoteRepositoryMock();
        controller = new NoteController(noteRepository,clasaRepository,eleviRepository);
    }

    //Unit test for module A
    @Test
    public void test1() throws ClasaException {
        Nota nota = new Nota(4, "mater", 5);
        noteRepository.addNota(nota);
        assertEquals(1,noteRepository.getNote().size());
    }

    //Unit test for module B
    @Test
    public void test2() throws ClasaException {
        eleviRepository.readElevi(VALID_FILE_NAME);
        assertEquals(eleviRepository.getElevi().size(),3);
        assertEquals(eleviRepository.getElevi().get(0),mockElev1);
        assertEquals(eleviRepository.getElevi().get(1),mockElev2);
        assertEquals(eleviRepository.getElevi().get(2),mockElev3);
    }

    //Unit test for module C
    @Test
    public void test3() throws ClasaException{
        Elev elev1 = new Elev(1,"Dan");
        Elev elev2 = new Elev(2,"Ana");
        Elev elev3 = new Elev(3,"Marcel");
        Nota nota1 = new Nota(1,"Mate",3);
        Nota nota2 = new Nota(2,"Mate",3);
        Nota nota3 = new Nota(2,"Mate",5);
        Nota nota4 = new Nota(3,"Romana",7);
        List<Elev> listaElevi = Arrays.asList(elev1,elev2,elev3);
        List<Nota> listaNote = Arrays.asList(nota1,nota2,nota3,nota4);
        clasaRepository.creazaClasa(listaElevi,listaNote);
        Corigent corigent1 = new Corigent("Ana",1);
        Corigent corigent2 = new Corigent("Dan",1);
        List<Corigent> expectedCorigenti = new ArrayList<Corigent>();
        expectedCorigenti.add(corigent1);
        expectedCorigenti.add(corigent2);
        assertEquals(expectedCorigenti,clasaRepository.getCorigenti(clasaRepository.getClasa()));
    }

    //Integration test P->A
    @Test
    public void test4() throws ClasaException{
        Nota nota1 = new Nota(1,"VVSS++",10);
        Nota nota2 = new Nota(2,"Calcul numeric",5);
        Nota nota3 = new Nota(1,"Analiza",3);
        controller.addNota(nota1);
        controller.addNota(nota2);
        controller.addNota(nota3);
        assertEquals(3,controller.getNote().size());
        assertEquals(nota1,controller.getNote().get(0));
        assertEquals(nota2,controller.getNote().get(1));
        assertEquals(nota3,controller.getNote().get(2));
    }

    //Integration test P->A->B
    @Test
    public void test5() throws ClasaException{
        Nota nota1 = new Nota(1, "matematica", 8);
        Nota nota2 = new Nota(1, "informatica", 6);
        controller.addNota(nota1);
        controller.addNota(nota2);
        Elev elev1 = new Elev(4, "ana");
        Elev elev2 = new Elev(1, "dan");
        controller.addElev(elev1);
        controller.addElev(elev2);
        Medie result = controller.calculareMedieSemestriala("", 1,
                Arrays.asList(nota1, nota2),
                Arrays.asList(elev1, elev2));
        Medie expectedResult = new Medie(elev2, 7.0);
        assertEquals(expectedResult, result);
    }

    //Integration test P->A->B->C
    @Test
    public void test6() throws ClasaException{
        Nota nota1 = new Nota(1,"Matematica",3);
        Nota nota2 = new Nota(2,"Matematica",3);
        Nota nota3 = new Nota(2,"Matematica",5);
        Nota nota4 = new Nota(3,"Romana",7);
        controller.addNota(nota1);
        controller.addNota(nota2);
        controller.addNota(nota3);
        controller.addNota(nota4);
        Elev elev1 = new Elev(1,"Dan");
        Elev elev2 = new Elev(2,"Ana");
        Elev elev3 = new Elev(3,"Marcel");
        controller.addElev(elev1);
        controller.addElev(elev2);
        controller.addElev(elev3);
        controller.creeazaClasa(controller.getElevi(),controller.getNote());
        assertEquals(3,controller.getClasa().keySet().size());
        Corigent corigent1 = new Corigent("Ana",1);
        Corigent corigent2 = new Corigent("Dan",1);
        List<Corigent> expectedCorigenti = new ArrayList<Corigent>();
        expectedCorigenti.add(corigent1);
        expectedCorigenti.add(corigent2);
        assertEquals(expectedCorigenti,controller.getCorigenti());
    }

    @Test
    public void topDown() throws ClasaException{
        test1(); //Unit test for module A
        init();
        test2(); //Unit test for module B
        init();
        test3(); //Unit test for module C
        init();
        test4(); //Integration test P->A
        init();
        test5(); //Integration test P->A->B
        init();
        test6(); //Integration test P->A->B->C
    }


}
