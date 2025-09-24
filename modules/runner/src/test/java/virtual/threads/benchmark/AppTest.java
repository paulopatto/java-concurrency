package virtual.threads.benchmark;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAMainMethod() {
        // Verifica se o método main pode ser chamado sem exceções
        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }
}
