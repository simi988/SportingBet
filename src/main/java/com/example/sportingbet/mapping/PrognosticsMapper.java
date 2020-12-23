package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.model.Prognostics;

public class PrognosticsMapper {
    public Prognostics mapToDto(PrognosticsDO prognosticsDO) {
        Prognostics prognostics = new Prognostics();
        prognostics.setId(prognosticsDO.getId());
        prognostics.setOdd(prognosticsDO.getOdd());
        prognostics.setValue(prognosticsDO.getValue());
        return prognostics;
    }

    public PrognosticsDO mapToDO(Prognostics prognostics) {
        PrognosticsDO prognosticsDO = new PrognosticsDO();
        prognosticsDO.setOdd(prognostics.getOdd());
        prognosticsDO.setValue(prognostics.getValue());
        return prognosticsDO;
    }
}
