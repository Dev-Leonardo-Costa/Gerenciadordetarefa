package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.api.controller.impl.DepartamentoControllerImpl;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.*;

@Profile("test")
@SpringBootTest
class DepartamentoControllerTest {

    private DepartamentoService mockService;
    private DepartamentoControllerImpl departamentoController;

    @BeforeEach
    public void setUp() {
        mockService = mock(DepartamentoService.class);
        departamentoController = new DepartamentoControllerImpl(mockService);
    }

//    @Test
//    public void deveListarDepartamentosComQuantidadeDePessoasETarefas() {
//        List<DepartamentoDTO> departamentosDTO = Arrays.asList(
//                new DepartamentoDTO("TI", 10, 20),
//                new DepartamentoDTO("RH", 15, 25)
//        );
//
//        when(mockService.listarDepartamento()).thenReturn(departamentosDTO);
//
//        ResponseEntity<List<DepartamentoDTO>> response = departamentoController.listarDepartamentosComQuantidadeDePessoasETarefas();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(departamentosDTO, response.getBody());
//
//        verify(mockService, times(1)).listarDepartamento();
//        verifyNoMoreInteractions(mockService);
//    }
}