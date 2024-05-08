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
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.NetworkIF;
import oshi.hardware.HWDiskStore;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Computador {
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

    public Computador() {
        this.si  = new SystemInfo();
        this.networkIfs = hal.getNetworkIFs().get(0);
        this.hwDiskStore  = hal.getDiskStores().get(0);
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

    public void buscarInfos(){
        Computador computador = new Computador();
        String sistemaFixo = computador.getSistema().toString();
        System.out.println(computador);

        try{
            TimeUnit.SECONDS.sleep(10);
            buscarInfos();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
