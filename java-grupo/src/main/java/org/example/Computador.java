package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processador.ProcessadorCacheLoader;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.NetworkIF;
import oshi.hardware.HWDiskStore;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Computador {
    private final Integer idMaquina;
    private final String hostname;
    private final Boolean ativo;
    private final Integer empresa;
    private final SystemInfo system = new SystemInfo();
    private final OperatingSystem os = system.getOperatingSystem();
    private final HardwareAbstractionLayer hal = system.getHardware();
    private final NetworkIF networkIfs;
    private final SystemInfo si;
    private final HWDiskStore hwDiskStore;
    private final OSFileStore osFileStore;
    private final Sistema sistema;
    private final Memoria memoria;
    private final Processador processador;
    private final ProcessadorCacheLoader processadorCacheLoader;
    private final RedeInterface redeInterface;
    private final RedeParametros redeParametros;
    private final DiscoGrupo grupoDeDiscos;
    private final Disco disco;
    private final Volume volume;
    private final ServicoGrupo servicoGrupo;
    private final ProcessoGrupo processoGrupo;

    public Computador(Integer idMaquina, String hostname, Boolean ativo, Integer empresa) {
        this.idMaquina = idMaquina;
        this.hostname = hostname;
        this.ativo = ativo;
        this.empresa = empresa;

        this.si = new SystemInfo();
        this.networkIfs = hal.getNetworkIFs().get(0);
        this.hwDiskStore = hal.getDiskStores().get(0);
        this.osFileStore = os.getFileSystem().getFileStores().get(0);

        //Fixos
        this.sistema = new Sistema();
        this.redeParametros = new RedeParametros(si);

        //Variaveis
        this.memoria = new Memoria();
        this.processadorCacheLoader = new ProcessadorCacheLoader();
        this.servicoGrupo = new ServicoGrupo();

        //duplo
        this.processador = new Processador();
        this.redeInterface = new RedeInterface(networkIfs);
        this.grupoDeDiscos = new DiscoGrupo(); // volume, disco,
        this.processoGrupo = new ProcessoGrupo();

        this.disco = new Disco(hwDiskStore);
        this.volume = new Volume(osFileStore);
    }

    public Sistema getSistema() {
        return sistema;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Processador getProcessador() {
        return processador;
    }

    public ProcessadorCacheLoader getProcessadorCacheLoader() {
        return processadorCacheLoader;
    }

    public RedeInterface getRedeInterface() {
        return redeInterface;
    }

    public RedeParametros getRedeParametros() {
        return redeParametros;
    }

    public DiscoGrupo getGrupoDeDiscos() {
        return grupoDeDiscos;
    }

    public Disco getDisco() {
        return disco;
    }

    public Volume getVolume() {
        return volume;
    }

    public ServicoGrupo getServicoGrupo() {
        return servicoGrupo;
    }

    public ProcessoGrupo getProcessoGrupo() {
        return processoGrupo;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public String getHostname() {
        return hostname;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    @Override
    public String toString() {
//        SERVIÇÕES E PROCESSOS SÃO OS MESMO MAS COM INFOS DIFERENTES
        return "Computador{" +
                "  \nsistema=" + sistema +
                ", \nmemoria=" + memoria +
                ", \nprocessador=" + processador +
                ", \nUso: (processadorCacheLoader)=" + processadorCacheLoader.getUso() +
                ", \nredeInterface=" + redeInterface +
                ", \nredeParametros=" + redeParametros +
                ", \nDisco=" + grupoDeDiscos.getDiscos() +
                ", \nVolume=" + grupoDeDiscos.getVolumes() +
                ", \nQuantidade de Discos=" + grupoDeDiscos.getQuantidadeDeDiscos() +
                ", \nQuantidade de Volumes=" + grupoDeDiscos.getQuantidadeDeVolumes() +
                ", \nTamanho total (disco)=" + grupoDeDiscos.getTamanhoTotal() +
                ", \nTotal Serviços=" + servicoGrupo.getTotalDeServicos() +
                ", \nTotal Serviços Inativos=" + servicoGrupo.getTotalServicosInativos() +
                ", \nTotal Serviços Inativos=" + servicoGrupo.getTotalServicosAtivos() +
                ", \nServições ativos=" + servicoGrupo.getServicosAtivos() +
                ", \nServições inativos=" + servicoGrupo.getServicosInativos() +
                ", \nProcessos=" + processoGrupo +
//                ", \nTotal de processos=" + processoGrupo.getTotalProcessos() +
//                ", \nTotal de Thereads=" + processoGrupo.getTotalThreads() +
//                ", \nProcessos=" + processoGrupo.getProcessos() +
                "  \n}";
    }

    public void buscarInfos(Integer primeiro) {
        if (primeiro == 0) {
            cadastrarPrimeiro();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
            cadastrar();
            buscarInfos(1);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void cadastrar(){

        Conexao con = new Conexao();

//        SISTEMA
        String tempoAtividadeSistema = Conversor.formatarSegundosDecorridos(this.getSistema().getTempoDeAtividade());

        var querySistema = """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 1, current_timestamp(),'tempoAtividade', '%s');
                           """.formatted(
                                   idMaquina,
                tempoAtividadeSistema
        );

        con.executarQuery(querySistema);


//        MEMORIA
        Long emUsoMemoria = (this.getMemoria().getEmUso() / 10000000);

        var queryMemoria= """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 2, current_timestamp(),'emUso', '%s');
                           """.formatted(
                idMaquina,
                emUsoMemoria
        );

        con.executarQuery(queryMemoria);
    }

    public void cadastrarPrimeiro(){
        Conexao con = new Conexao();

        //            SISTEMA
        String modeloSistema = this.getRedeParametros().getHostName();
        Instant inicializadoSistema = this.getSistema().getInicializado();

        String querySistema = """
                        INSERT INTO componentes VALUES
                                                (1, %d, 1, 'modelo', '%s', null),
                                                (2, %d, 1, 'inicializado', '%s', null);
                    """.formatted(
                idMaquina,
                modeloSistema,
                idMaquina,
                inicializadoSistema
        );

        con.executarQuery(querySistema);


//            Memoria
        Long totalMemoria = (this.getMemoria().getTotal() / 1000000000);

        String queryMemoria = """
                        INSERT INTO componentes VALUES
                                                (3, %d, 2, 'total', '%s', 'total de memoria do computador')
                    """.formatted(
                idMaquina,
                totalMemoria
        );

        con.executarQuery(queryMemoria);


//            Processador
        String nomeProcessador = this.getProcessador().getNome();
        Integer nmrPacotesFisicosProcessador = this.getProcessador().getNumeroPacotesFisicos();
        Integer nmrCpusFisicosProcessador = this.getProcessador().getNumeroCpusFisicas();
        Integer nmrCpusLogicasProcessador = this.getProcessador().getNumeroCpusLogicas();

        String queryProcessador = """
                        INSERT INTO componentes VALUES
                                                (4, %d, 3, 'nome', '%s', 'Nome do Processador'),
                                                (5, %d, 3, 'nmrPacotesFisicos', '%s', 'Numero de pacotes físicos do processador'),
                                                (6, %d, 3, 'nmrCpusFisicos', '%s', 'Numero de CPUs físicas do processador'),
                                                (7, %d, 3, 'nmrCpusLogicas', '%s', 'Numero de CPUs Logicas do processador')
                    """.formatted(
                idMaquina,
                nomeProcessador,
                idMaquina,
                nmrPacotesFisicosProcessador,
                idMaquina,
                nmrCpusFisicosProcessador,
                idMaquina,
                nmrCpusLogicasProcessador
        );

        con.executarQuery(queryProcessador);


//            Rede
        String nomeRede = this.getRedeInterface().getNome();
        String nomeExibicaoRede = this.getRedeInterface().getNomeExibicao();
        List enderecoIPv4Rede = this.getRedeInterface().getEnderecoIpv4();
        List enderecoIPv6Rede = this.getRedeInterface().getEnderecoIpv6();
        String enderecoMACRede = this.getRedeInterface().getEnderecoMac();
        String hostnameRede = this.getRedeParametros().getHostName();
        List servidoresDNSRede = this.getRedeParametros().getServidoresDns();

        String queryRede = """
                        INSERT INTO componentes VALUES
                                                (8, %d, 4, 'nome', '%s', 'Nome da rede'),
                                                (9, %d, 4, 'nomeExibicao', '%s', 'Nome de exibição da rede'),
                                                (10, %d, 4, 'enderecoIPv4', '%s', 'Endereço IPv4 da rede'),
                                                (11, %d, 4, 'enderecoIPv6', '%s', 'Endereço IPv6 da rede'),
                                                (12, %d, 4, 'enderecoMAC', '%s', 'Endereço MAC da rede'),
                                                (13, %d, 4, 'hostname', '%s', 'hostname da rede'),
                                                (14, %d, 4, 'servidoresDNS', '%s', 'servidores DNS da rede')
                    """.formatted(
                idMaquina,
                nomeRede,
                idMaquina,
                nomeExibicaoRede,
                idMaquina,
                enderecoIPv4Rede,
                idMaquina,
                enderecoIPv6Rede,
                idMaquina,
                enderecoMACRede,
                idMaquina,
                hostnameRede,
                idMaquina,
                servidoresDNSRede
        );

        con.executarQuery(queryRede);


//            DISCO
        Integer qtdDiscosDisco = this.getGrupoDeDiscos().getQuantidadeDeDiscos();
        String nomeDisco = this.getDisco().getNome();
        Long tamanhoDisco = (this.getDisco().getTamanho() / 1000000000);

        String queryDisco = """
                        INSERT INTO componentes VALUES
                                                (15, %d, 3, 'qtdDiscos', '%s', 'Quantidade de disco no computador'),
                                                (16, %d, 3, 'nome', '%s', 'Nome do disco'),
                                                (17, %d, 3, 'tamanho', '%s', 'tamanho do disco')
                    """.formatted(
                idMaquina,
                qtdDiscosDisco,
                idMaquina,
                nomeDisco,
                idMaquina,
                tamanhoDisco
        );

        con.executarQuery(queryDisco);


//            VOLUME
        Integer qtdVolumesVolume = this.getGrupoDeDiscos().getQuantidadeDeDiscos();
        String UUIDVolume = this.getVolume().getUUID();
        String nomeVolume = this.getVolume().getVolume();
        Long totalVolume = this.getVolume().getTotal();
        Long disponivelVolume = this.getVolume().getDisponivel();
        String tipoVolume = this.getVolume().getTipo();

        String queryVolume = """
                        INSERT INTO componentes VALUES
                                                (18, %d, 4, 'qtdVolumes', '%s', 'quantidade de volumes no computador'),
                                                (19, %d, 4, 'UUID', '%s', 'UUID do volume'),
                                                (20, %d, 4, 'nome', '%s', 'nome do volume'),
                                                (21, %d, 4, 'total', '%s', 'tamanho total do volume'),
                                                (22, %d, 4, 'disponivel', '%s', 'tamanho disponivel do volume'),
                                                (23, %d, 4, 'tipo', '%s', 'tipo do volume')
                    """.formatted(
                idMaquina,
                qtdVolumesVolume,
                idMaquina,
                UUIDVolume,
                idMaquina,
                nomeVolume,
                idMaquina,
                totalVolume,
                idMaquina,
                disponivelVolume,
                idMaquina,
                tipoVolume
        );

        con.executarQuery(queryVolume);
    }
}
