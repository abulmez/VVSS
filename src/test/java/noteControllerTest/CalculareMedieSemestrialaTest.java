package noteControllerTest;

import note.controller.NoteController;
import note.main.ClasaException;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.EleviRepository;
import note.repository.NoteRepository;
import note.repository.NoteRepositoryMock;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CalculareMedieSemestrialaTest {

    private NoteController controller;

    @Before
    public void init() {
        controller = new NoteController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidElev);
        controller.calculareMedieSemestriala("", 1,
                Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Test
    public void test2() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidElev);
        Elev elev = new Elev(2, "dan");
        controller.calculareMedieSemestriala("", 1,
                Collections.EMPTY_LIST, Collections.singletonList(elev));
    }

    @Test
    public void test3() throws ClasaException {
        Elev elev = new Elev(1, "dan");
        Medie result = controller.calculareMedieSemestriala("", 1,
                Collections.EMPTY_LIST, Collections.singletonList(elev));
        Medie expectedResult = new Medie(elev, 0.0);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test4() throws ClasaException {
        Elev elev = new Elev(1, "dan");
        Nota nota = new Nota(2, "mate", 8);
        Medie result = controller.calculareMedieSemestriala("", 1,
                Collections.singletonList(nota), Collections.singletonList(elev));
        Medie expectedResult = new Medie(elev, 0.0);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test5() throws ClasaException {
        Elev elev = new Elev(1, "dan");
        Nota nota = new Nota(1, "mate", 8);
        Medie result = controller.calculareMedieSemestriala("", 1,
                Collections.singletonList(nota), Collections.singletonList(elev));
        Medie expectedResult = new Medie(elev, 8.0);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test6() throws ClasaException {
        Elev elev1 = new Elev(4, "ana");
        Elev elev2 = new Elev(1, "dan");
        Nota nota1 = new Nota(1, "mate", 8);
        Nota nota2 = new Nota(1, "info", 6);
        Medie result = controller.calculareMedieSemestriala("", 1,
                new ArrayList<Nota>(Arrays.asList(nota1, nota2)),
                new ArrayList<Elev>(Arrays.asList(elev1, elev2)));
        Medie expectedResult = new Medie(elev2, 7.0);
        assertEquals(expectedResult, result);
    }
}
