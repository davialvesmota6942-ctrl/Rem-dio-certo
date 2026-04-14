package com.remedio.certo;

import com.remedio.certo.service.MedicamentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicamentoServiceTest {

    @Test
    void deveAdicionarMedicamentoCorretamente() {
        MedicamentoService service = new MedicamentoService();
        service.adicionarMedicamento("Dipirona", "500mg", "08:00");

        Assertions.assertEquals(1, service.listarMedicamentos().size());
        Assertions.assertEquals("Dipirona", service.listarMedicamentos().get(0).getNome());
    }

    @Test
    void naoDevePermitirNomeVazio() {
        MedicamentoService service = new MedicamentoService();

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.adicionarMedicamento("", "500mg", "08:00")
        );
    }

    @Test
    void deveRemoverMedicamentoCorretamente() {
        MedicamentoService service = new MedicamentoService();
        service.adicionarMedicamento("Dipirona", "500mg", "08:00");
        service.removerMedicamento(0);

        Assertions.assertEquals(0, service.listarMedicamentos().size());
    }

    @Test
    void deveMarcarMedicamentoComoTomado() {
        MedicamentoService service = new MedicamentoService();
        service.adicionarMedicamento("Losartana", "50mg", "12:00");
        service.marcarComoTomado(0);

        Assertions.assertTrue(service.listarMedicamentos().get(0).isTomado());
    }
}
