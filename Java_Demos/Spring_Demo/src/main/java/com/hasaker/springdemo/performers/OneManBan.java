package com.hasaker.springdemo.performers;

import com.hasaker.springdemo.interfaces.Instrument;
import com.hasaker.springdemo.interfaces.Performer;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

public class OneManBan implements Performer {

    private Collection<Instrument> instruments = null;
    private Map<String, Instrument> instrumentsMap = null;
    private Properties instrumentsPro = null;

    public void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setInstrumentsMap(Map<String, Instrument> instrumentsMap) {
        this.instrumentsMap = instrumentsMap;
    }

    public void setInstrumentsPro(Properties instrumentsPro) {
        this.instrumentsPro = instrumentsPro;
    }

    public void perform() {
        if (instruments != null) {
            for (Instrument instrument : instruments) {
                instrument.play();
            }
        } else if (instrumentsMap != null) {
            for (Map.Entry<String, Instrument> entry : instrumentsMap.entrySet()) {
                entry.getValue().play();
            }
        } else {
            for (Object key : instrumentsPro.keySet()) {
                System.out.println(key + ": " + instrumentsPro.getProperty(key.toString()));
            }
        }
    }
}
