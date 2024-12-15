package pe.edu.cibertec.examen_final_jhony_vasquez.response;

import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDetailDto;

public record FindCarResponse(String code,
                              String error,
                              CarDetailDto user) {

}
