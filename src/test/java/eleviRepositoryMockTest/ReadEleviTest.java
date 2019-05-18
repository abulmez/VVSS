package eleviRepositoryMockTest;

import note.main.ClasaException;
import note.model.Elev;
import note.repository.EleviRepository;
import note.repository.EleviRepositoryMock;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class ReadEleviTest {

    private String VALID_FILE_NAME = "eleviTest.txt";
    private EleviRepository repo;
    private Elev mockElev1 = new Elev(1,"Ana");
    private Elev mockElev2 = new Elev(2,"are");
    private Elev mockElev3 = new Elev(3,"mere");

    @Before
    public void init(){
        repo = new EleviRepositoryMock();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws ClasaException {
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.eleviFileNotFound);
        repo.readElevi("fileNotFound");
    }

    @Test
    public void test2() throws ClasaException {
        repo.readElevi(VALID_FILE_NAME);
        assertEquals(repo.getElevi().size(),3);
        assertEquals(repo.getElevi().get(0),mockElev1);
        assertEquals(repo.getElevi().get(1),mockElev2);
        assertEquals(repo.getElevi().get(2),mockElev3);
    }


}
