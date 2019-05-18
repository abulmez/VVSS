package clasaRepositoryMockTest;

import note.main.ClasaException;
import note.model.Corigent;
import note.model.Elev;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ClasaRepositoryMock;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetCorigentiTest {

    private ClasaRepository repository;

    @Before
    public void init() {
        repository = new ClasaRepositoryMock();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.emptyRepository);
        repository.getCorigenti(repository.getClasa());
    }

    @Test
    public void test2() throws ClasaException{
        Elev elev1 = new Elev(1,"Dan");
        Elev elev2 = new Elev(2,"Ana");
        Elev elev3 = new Elev(3,"Marcel");
        Nota nota1 = new Nota(1,"Mate",3);
        Nota nota2 = new Nota(2,"Mate",3);
        Nota nota3 = new Nota(2,"Mate",5);
        Nota nota4 = new Nota(3,"Romana",7);
        List<Elev> listaElevi = Arrays.asList(elev1,elev2,elev3);
        List<Nota> listaNote = Arrays.asList(nota1,nota2,nota3,nota4);
        repository.creazaClasa(listaElevi,listaNote);
        Corigent corigent1 = new Corigent("Ana",1);
        Corigent corigent2 = new Corigent("Dan",1);
        List<Corigent> expectedCorigenti = new ArrayList<Corigent>();
        expectedCorigenti.add(corigent1);
        expectedCorigenti.add(corigent2);
        assertEquals(expectedCorigenti,repository.getCorigenti(repository.getClasa()));
    }
}
