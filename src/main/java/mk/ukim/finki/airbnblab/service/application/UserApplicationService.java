package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.CreateUserDto;
import mk.ukim.finki.airbnblab.DTO.DisplayUserDto;
import mk.ukim.finki.airbnblab.DTO.LoginResponseDTO;
import mk.ukim.finki.airbnblab.DTO.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDTO> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
