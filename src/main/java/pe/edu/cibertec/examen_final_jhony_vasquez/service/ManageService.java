package pe.edu.cibertec.examen_final_jhony_vasquez.service;

import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDetailDto;
import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {
    List<CarDto> getAllCars() throws Exception;
    Optional<CarDetailDto> getCarById(int carId) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;
    boolean detailCarById(int carId) throws Exception;
    boolean deleteCarById(int car_id) throws Exception;
    boolean addCar(CarDetailDto carDetailDto) throws Exception;

}
