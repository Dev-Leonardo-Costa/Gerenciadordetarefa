package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.repository.DepartamentoRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class DepartamentoServiceTest {

    public static final Long ID_DEPARTAMENTO = 1L;
    @InjectMocks
    private DepartamentoService departamentoService;

    @Mock
    private DepartamentoRepository departamentoRepository;

//    @Test
//    void deveBuscarPorId() {
//        Departamento departamentoMock = new Departamento();
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.of(departamentoMock));
//
//        Departamento result = departamentoService.buscarPorId(ID_DEPARTAMENTO);
//
//        assertNotNull(result);
//        assertEquals(departamentoMock, result);
//        verify(departamentoRepository, times(1)).findById(ID_DEPARTAMENTO);
//    }


//    @Test
//    void deveBuscarPorIdDepartamentoParaPessoa() {
//        Departamento departamentoMock = new Departamento();
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.of(departamentoMock));
//
//        Departamento result = departamentoService.buscarPorIdDepartamentoParaPessoa(ID_DEPARTAMENTO);
//
//        assertNotNull(result);
//        assertEquals(departamentoMock, result);
//        verify(departamentoRepository, times(1)).findById(ID_DEPARTAMENTO);
//
//    }

//    @Test
//    void deveListarDepartamento() {
//        List<DepartamentoProjection> departamentoProjections = Collections.singletonList(mock(DepartamentoProjection.class));
//        when(departamentoRepository.listarDepartamentosComQuantidadeDePessoasETarefas()).thenReturn(departamentoProjections);
//
//        List<DepartamentoDTO> result = departamentoService.listarDepartamento();
//
//        verify(departamentoRepository, times(1)).listarDepartamentosComQuantidadeDePessoasETarefas();
//        assertNotNull(result);
//    }

//    @Test
//    void buscarPorIdDeveLancarNotFoundExceptionSeDepartamentoNaoExistir() {
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> departamentoService.buscarPorId(ID_DEPARTAMENTO));
//
//        verify(departamentoRepository, times(1)).findById(ID_DEPARTAMENTO);
//    }
//
//    @Test
//    void deveBuscarDepartamentoPorIdComSucesso() {
//        Departamento departamento = new Departamento();
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.of(departamento));
//
//        Departamento result = departamentoService.buscarPorId(ID_DEPARTAMENTO);
//
//        assertNotNull(result);
//        assertEquals(departamento, result);
//    }
//
//    @Test
//    void deveLancarNotFoundExceptionAoBuscarDepartamentoPorIdInexistente() {
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> departamentoService.buscarPorId(ID_DEPARTAMENTO));
//    }
//
//    @Test
//    void deveBuscarDepartamentoPorIdDepartamentoParaPessoaComSucesso() {
//
//        Departamento departamento = new Departamento();
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.of(departamento));
//
//        Departamento result = departamentoService.buscarPorIdDepartamentoParaPessoa(ID_DEPARTAMENTO);
//
//        assertNotNull(result);
//        assertEquals(departamento, result);
//    }
//
//    @Test
//    void deveLancarNotFoundExceptionAoBuscarDepartamentoPorIdDepartamentoParaPessoaInexistente() {
//
//        when(departamentoRepository.findById(ID_DEPARTAMENTO)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> departamentoService.buscarPorIdDepartamentoParaPessoa(ID_DEPARTAMENTO));
//    }
//
//    @Test
//    void deveListarDepartamentosComQuantidadeDePessoasETarefas() {
//        List<DepartamentoProjection> projections = Collections.singletonList(mock(DepartamentoProjection.class));
//        when(departamentoRepository.listarDepartamentosComQuantidadeDePessoasETarefas()).thenReturn(projections);
//
//        List<DepartamentoDTO> result = departamentoService.listarDepartamento();
//
//        assertNotNull(result);
//    }
}