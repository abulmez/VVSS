package noteRepositoryMockTest;

import note.main.ClasaException;
import note.model.Nota;
import note.repository.NoteRepository;
import note.repository.NoteRepositoryMock;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class AddNotaTest {

    NoteRepository repositoryMock;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init(){
        repositoryMock = new NoteRepositoryMock();
    }

    @Test
    public void test1() throws ClasaException {
        Nota nota = new Nota(2, "matematica", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test2() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidMateria);
        Nota nota = new Nota(2, "materiefoartefoartefoartelunga", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test3() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidMateria);
        Nota nota = new Nota(2, "info", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test4() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidNrmatricol);
        Nota nota = new Nota(0, "matematica", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test5() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidNrmatricol);
        Nota nota = new Nota(1001, "matematica", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test6() throws ClasaException {
        Nota nota = new Nota(4, "mater", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test7() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidMateria);
        Nota nota = new Nota(4, "mate", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test8() throws ClasaException {
        Nota nota = new Nota(4, "materi", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test9() throws ClasaException {
        Nota nota = new Nota(4, "materiecu20decaracte", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test10() throws ClasaException {
        Nota nota = new Nota(4, "materiecu19decaract", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test11() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidMateria);
        Nota nota = new Nota(4, "materiecu21decaracter", 5);
        repositoryMock.addNota(nota);
    }

    @Test
    public void test12() throws ClasaException {
        Nota nota = new Nota(1, "matematica", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test13() throws ClasaException {
        Nota nota = new Nota(2, "matematica", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test14() throws ClasaException {
        Nota nota = new Nota(1000, "matematica", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

    @Test
    public void test15() throws ClasaException {
        Nota nota = new Nota(999, "matematica", 5);
        repositoryMock.addNota(nota);
        assertEquals(1,repositoryMock.getNote().size());
    }

}
