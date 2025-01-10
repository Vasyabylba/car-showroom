package by.vasyabylba.carshowroom.controller;

import by.vasyabylba.carshowroom.dto.review.ReviewRequest;
import by.vasyabylba.carshowroom.dto.review.ReviewResponse;
import by.vasyabylba.carshowroom.filter.ReviewFilter;
import by.vasyabylba.carshowroom.service.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAll(@ModelAttribute ReviewFilter reviewFilter) {
        return reviewService.getAll(reviewFilter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse getOne(@PathVariable("id") @Valid @NotBlank UUID id) {
        return reviewService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse create(@RequestBody @Valid ReviewRequest reviewRequest) {
        return reviewService.create(reviewRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse update(@PathVariable("id") @Valid @NotBlank UUID id,
                                 @RequestBody @Valid ReviewRequest reviewRequest) {
        return reviewService.update(id, reviewRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Valid @NotBlank UUID id) {
        reviewService.delete(id);
    }

}

