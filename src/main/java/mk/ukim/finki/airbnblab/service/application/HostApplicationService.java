package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.CreateHostDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayHostDTO;
import mk.ukim.finki.airbnblab.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {


    List<DisplayHostDTO> findAll();
    Optional<DisplayHostDTO> findById(Long id);
    Optional<DisplayHostDTO> save(CreateHostDTO hostDTO);
    Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO);
    void deleteById(Long id);

}
