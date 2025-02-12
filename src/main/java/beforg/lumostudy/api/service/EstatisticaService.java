package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.estatisticas.EstatisticaDTO;
import beforg.lumostudy.api.domain.registro.Rees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {
    @Autowired
    private ReesService reesService;

    public List<EstatisticaDTO> getPeriodo(String cod, String data, String periodo) {

        int totalTempo = 0;
        int totalSessoes = 0;

        switch (periodo.toLowerCase()) {
            case "mes":
                // Calculate total time and sessions for the month
                totalTempo = getTempoTotal(null);
                totalSessoes = 0;
                break;
            case "semana":
                // Calculate total time and sessions for the week
                totalTempo = getTempoTotal(null);
                totalSessoes = 0;
                break;
            case "ano":
                // Calculate total time and sessions for the year
                totalTempo = getTempoTotal(null);
                totalSessoes = 0;
                break;
            default:
                throw new IllegalArgumentException("Período inválido: " + periodo);
        }
        return List.of(new EstatisticaDTO(data, totalTempo, totalSessoes));
    }

    private int getTempoTotal(List<Rees> rees) {
        return 0;
    }
}
