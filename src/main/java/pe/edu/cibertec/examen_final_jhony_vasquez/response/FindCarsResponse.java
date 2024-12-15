package pe.edu.cibertec.examen_final_jhony_vasquez.response;

import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {

}

