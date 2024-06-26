package com.vuelos_globales.entities.TripCrew.application;

import com.vuelos_globales.entities.TripCrew.infrastructure.TripCrewRepository;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import java.util.List;
import java.util.Optional;

public class TripCrewService {
    private final TripCrewRepository tripCrewRepository;


    public TripCrewService(TripCrewRepository tripCrewRepository){
        this.tripCrewRepository = tripCrewRepository;
    }

    public void createTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public void updateTripCrew(TripCrew tripCrew){
        tripCrewRepository.update(tripCrew);
    }

    public Optional<TripCrew> getTripCrewById(String id){
        return tripCrewRepository.findById(id);

    }

    public void deleteTripCrew(String id){
        tripCrewRepository.delete(id);
    }

    public List<TripCrew> getAllTripCrews(){
        return tripCrewRepository.findAll();
    }
}
