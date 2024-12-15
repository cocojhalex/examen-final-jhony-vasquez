package pe.edu.cibertec.examen_final_jhony_vasquez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDetailDto;
import pe.edu.cibertec.examen_final_jhony_vasquez.dto.CarDto;
import pe.edu.cibertec.examen_final_jhony_vasquez.entity.Car;
import pe.edu.cibertec.examen_final_jhony_vasquez.repository.CarRepository;
import pe.edu.cibertec.examen_final_jhony_vasquez.service.ManageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            cars.add(new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear()));
        });
        return cars;
    }

    @Override
    public Optional<CarDetailDto> getCarById(int carId) throws Exception {

        Optional<Car> optional = carRepository.findById(carId);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            carRepository.save(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean detailCarById(int carId) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCarById(int car_id) throws Exception {

        Optional<Car> optional = carRepository.findById(car_id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDetailDto.carId());
        if (optional.isEmpty()) {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            carRepository.save(car);
            return true;
        }
        return false;
    }
}
