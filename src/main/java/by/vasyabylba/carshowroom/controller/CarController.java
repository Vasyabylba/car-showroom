package by.vasyabylba.carshowroom.controller;

import by.vasyabylba.carshowroom.dto.car.CarRequest;
import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.filter.CarFilter;
import by.vasyabylba.carshowroom.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@Validated
public class CarController {

    public static final String DEFAULT_SORT_CAR_FIELD = "price";

    private final CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Window<CarResponse> getAll(@Valid @ModelAttribute("carFilter") CarFilter carFilter,
                                      @PageableDefault(page = 0, size = 20)
                                      @SortDefault(sort = DEFAULT_SORT_CAR_FIELD, direction = Sort.Direction.ASC)
                                      Pageable pageable) {
        return carService.getAll(carFilter, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getOne(@PathVariable("id") @Valid @NotBlank UUID id) {
        return carService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse create(@RequestBody @Valid CarRequest carRequest) {
        return carService.create(carRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse update(@PathVariable("id") @Valid @NotBlank UUID id,
                              @RequestBody @Valid CarRequest carRequest) {
        return carService.update(id, carRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Valid @NotBlank UUID id) {
        carService.delete(id);
    }

    @PostMapping("/{carId}/showroom")
    @ResponseStatus(HttpStatus.OK)
    public void addCarToShowroom(@PathVariable("carId") @Valid @NotBlank UUID carId,
                                 @RequestParam("showroomId") @Valid @NotBlank UUID showroomId) {
        carService.assignCarToShowroom(carId, showroomId);
    }

}

