package com.entrepreware.coligo.controller;

import com.entrepreware.coligo.dto.APIResponse;
import com.entrepreware.coligo.dto.announcement.AnnouncementCreateDto;
import com.entrepreware.coligo.dto.announcement.AnnouncementResponseDto;
import com.entrepreware.coligo.dto.announcement.AnnouncementUpdateDto;
import com.entrepreware.coligo.service.AnnouncementService;
import com.entrepreware.coligo.utils.ValueMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/announcements")
@CrossOrigin
public class AnnouncementController {
    
    private final AnnouncementService _announcementService;
    public static final String SUCCESS = "Success";


    @Operation(summary = "Create a new announcement")
    @PostMapping
    public ResponseEntity<APIResponse<AnnouncementResponseDto>> createNewAnnouncement(@RequestBody @Valid AnnouncementCreateDto announcementCreateDto){
        log.info("AnnouncementController:createNewAnnouncement has been executed");
        log.info("AnnouncementController:createNewAnnouncement request parameters {}", ValueMapper.jsonAsString(announcementCreateDto));

        AnnouncementResponseDto announcementResponseDto = _announcementService.createNewAnnouncement(announcementCreateDto);

        APIResponse<AnnouncementResponseDto> responseDto = APIResponse
                .<AnnouncementResponseDto>builder()
                .status(SUCCESS)
                .results(announcementResponseDto)
                .build();
        log.info("AnnouncementController:createNewAnnouncement response {}", ValueMapper.jsonAsString(responseDto));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all announcements")
    @GetMapping
    public ResponseEntity<APIResponse<List<AnnouncementResponseDto>>> getAllAnnouncements() {
        log.info("AnnouncementController:getAllAnnouncements has been executed");
        List<AnnouncementResponseDto> announcementResponseDtoList = _announcementService.getAllAnnouncements();
        APIResponse<List<AnnouncementResponseDto>> responseDTO = APIResponse
                .<List<AnnouncementResponseDto>>builder()
                .status(SUCCESS)
                .results(announcementResponseDtoList)
                .build();

        log.info("AnnouncementController::getAllAnnouncements response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get an announcement by its id")
    @GetMapping("/{announcementId}")
    public ResponseEntity<APIResponse<AnnouncementResponseDto>> getAnnouncementById(@PathVariable int announcementId) {
        log.info("AnnouncementController:getAnnouncementById has been executed");
        log.info("AnnouncementController:getAnnouncementById announcementId {}", announcementId);


        AnnouncementResponseDto announcementResponseDto = _announcementService.getAnnouncementById(announcementId);
        APIResponse<AnnouncementResponseDto> responseDTO = APIResponse
                .<AnnouncementResponseDto>builder()
                .status(SUCCESS)
                .results(announcementResponseDto)
                .build();

        log.info("AnnouncementController::getAnnouncementById {} response {}", announcementId,ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "update an announcement with specific id")
    @PutMapping("/{announcementId}")
    public ResponseEntity<APIResponse<AnnouncementResponseDto>> updateAnnouncement(@PathVariable int announcementId, @RequestBody @Valid AnnouncementUpdateDto announcementUpdateDto) {
        log.info("AnnouncementController:updateAnnouncement has been executed");
        log.info("AnnouncementController:updateAnnouncement with request body {}", ValueMapper.jsonAsString(announcementUpdateDto));

        AnnouncementResponseDto announcementResponseDto = _announcementService.updateAnnouncement(announcementId,announcementUpdateDto);
        APIResponse<AnnouncementResponseDto> responseDTO = APIResponse
                .<AnnouncementResponseDto>builder()
                .status(SUCCESS)
                .results(announcementResponseDto)
                .build();

        log.info("AnnouncementController::updateAnnouncement response {}", ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "delete an Announcement with specific id")
    @DeleteMapping({"/{announcementId}"})
    public ResponseEntity<APIResponse<String>> deleteAnnouncement(@PathVariable int announcementId) {
        log.info("AnnouncementController:deleteAnnouncement has been executed");
        log.info("AnnouncementController:deleteAnnouncement with id {}", announcementId);

        _announcementService.deleteAnnouncementById(announcementId);
        APIResponse<String> responseDTO = APIResponse
                .<String>builder()
                .status(SUCCESS)
                .results("Announcement with id " + announcementId + " has been deleted successfully")
                .build();

        log.info("AnnouncementController::deleteAnnouncement response {}", ValueMapper
                .jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
