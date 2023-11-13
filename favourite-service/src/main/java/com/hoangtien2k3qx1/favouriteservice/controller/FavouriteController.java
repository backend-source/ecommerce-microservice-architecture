package com.hoangtien2k3qx1.favouriteservice.controller;

import com.hoangtien2k3qx1.favouriteservice.constant.AppConstant;
import com.hoangtien2k3qx1.favouriteservice.dto.FavouriteDto;
import com.hoangtien2k3qx1.favouriteservice.dto.response.collection.DtoCollectionResponse;
import com.hoangtien2k3qx1.favouriteservice.domain.id.FavouriteId;
import com.hoangtien2k3qx1.favouriteservice.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouriteController {

    private final FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<FavouriteDto>> findAll() {
        log.info("*** FavouriteDto List, controller; fetch all favourites *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.favouriteService.findAll()));
    }

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDto> findById(@PathVariable("userId") final String userId,
                                                 @PathVariable("productId") final String productId,
                                                 @PathVariable("likeDate") final String likeDate) {
        log.info("*** FavouriteDto, resource; fetch favourite by id *");
        return ResponseEntity.ok(this.favouriteService.findById(
                new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                        LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT)))));
    }

    @GetMapping("/find")
    public ResponseEntity<FavouriteDto> findById(@RequestBody
                                                 @NotNull(message = "Input must not be NULL")
                                                 @Valid final FavouriteId favouriteId) {
        log.info("*** FavouriteDto, resource; fetch favourite by id *");
        return ResponseEntity.ok(this.favouriteService.findById(favouriteId));
    }

    @PostMapping
    public ResponseEntity<FavouriteDto> save(@RequestBody
                                             @NotNull(message = "Input must not be NULL")
                                             @Valid final FavouriteDto favouriteDto) {
        log.info("*** FavouriteDto, resource; save favourite *");
        return ResponseEntity.ok(this.favouriteService.save(favouriteDto));
    }

    @PutMapping
    public ResponseEntity<FavouriteDto> update(@RequestBody
                                               @NotNull(message = "Input must not be NULL")
                                               @Valid final FavouriteDto favouriteDto) {
        log.info("*** FavouriteDto, resource; update favourite *");
        return ResponseEntity.ok(this.favouriteService.update(favouriteDto));
    }

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") final String userId,
                                              @PathVariable("productId") final String productId,
                                              @PathVariable("likeDate") final String likeDate) {
        log.info("*** Boolean, resource; delete favourite by id *");
        this.favouriteService.deleteById(new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT))));
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody
                                              @NotNull(message = "Input must not be NULL")
                                              @Valid final FavouriteId favouriteId) {
        log.info("*** Boolean, resource; delete favourite by id *");
        this.favouriteService.deleteById(favouriteId);
        return ResponseEntity.ok(true);
    }

}
