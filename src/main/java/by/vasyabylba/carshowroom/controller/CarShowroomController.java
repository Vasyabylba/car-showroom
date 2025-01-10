package by.vasyabylba.carshowroom.controller;


import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomResponse;
import by.vasyabylba.carshowroom.service.CarShowroomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carshowrooms")
@RequiredArgsConstructor
@Validated
public class CarShowroomController {

    private final CarShowroomService carShowroomService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarShowroomResponse> getAll() {
        return carShowroomService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarShowroomResponse getOne(@PathVariable("id") @Valid @NotBlank UUID id) {
        return carShowroomService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarShowroomResponse create(@RequestBody @Valid CarShowroomRequest carShowroomRequest) {
        return carShowroomService.create(carShowroomRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarShowroomResponse update(@PathVariable("id") @Valid @NotBlank UUID id,
                                      @RequestBody @Valid CarShowroomRequest carShowroomRequest) {
        return carShowroomService.update(id, carShowroomRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Valid @NotBlank UUID id) {
        carShowroomService.delete(id);
    }

    @GetMapping("/{showroomId}/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCars(@PathVariable("showroomId") @Valid @NotBlank UUID showroomId) {
        return carShowroomService.getCarsByShowroom(showroomId);
    }

}

