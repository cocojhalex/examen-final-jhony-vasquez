package pe.edu.cibertec.examen_final_jhony_vasquez.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDetailDto;
import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDto;
import pe.edu.cibertec.examen_final_jhony_vasquez.response.*;
import pe.edu.cibertec.examen_final_jhony_vasquez.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageCarApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCarsd() {

        try {
            List<CarDto> Cars = manageService.getAllCars();
            if (!Cars.isEmpty())
                return new FindCarsResponse("01", null, Cars);
            else
                return new FindCarsResponse("02", "Cars not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "An error ocurred, please try again", null);

        }

    }

    @GetMapping("/detail")
    public FindCarResponse findCar(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
            return optional.map(Car ->
                    new FindCarResponse("01", null, Car)
            ).orElse(
                    new FindCarResponse("02", "Car not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarResponse("99", "An error ocurred, please try again", null);

        }

    }

    @PutMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto CarDto) {

        try {
            if (manageService.updateCar(CarDto))
                return new UpdateCarResponse("01", null);
            else
                return new UpdateCarResponse("02", "Car not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "An error ocurred, please try again");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarResponse deleteCar(@PathVariable String id) {

        try {
            if (manageService.deleteCarById(Integer.parseInt(id)))
                return new DeleteCarResponse("01", null);
            else
                return new DeleteCarResponse("02", "Car not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "An error ocurred, please try again");

        }

    }

    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarDetailDto CarDetailDto) {

        try {
            if (manageService.addCar(CarDetailDto))
                return new CreateCarResponse("01", null);
            else
                return new CreateCarResponse("02", "Car already exists");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "An error ocurred, please try again");

        }

    }

}