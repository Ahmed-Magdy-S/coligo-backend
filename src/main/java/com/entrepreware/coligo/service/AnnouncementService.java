package com.entrepreware.coligo.service;

import com.entrepreware.coligo.dto.announcement.AnnouncementCreateDto;
import com.entrepreware.coligo.dto.announcement.AnnouncementResponseDto;
import com.entrepreware.coligo.dto.announcement.AnnouncementUpdateDto;
import com.entrepreware.coligo.entity.Announcement;
import com.entrepreware.coligo.exception.AnnouncementNotFoundException;
import com.entrepreware.coligo.exception.AnnouncementServiceBusinessException;
import com.entrepreware.coligo.repository.AnnouncementRepository;
import com.entrepreware.coligo.utils.ValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository _announcementRepository;

    public AnnouncementResponseDto createNewAnnouncement(AnnouncementCreateDto announcementCreateDto) throws AnnouncementServiceBusinessException {
        AnnouncementResponseDto announcementResponseDto;

        try {
            log.info("AnnouncementService:createNewAnnouncement execution started.");
            Announcement announcement = ValueMapper.convertToEntity(announcementCreateDto);
            log.debug("AnnouncementService:createNewAnnouncement request parameters {}", ValueMapper.jsonAsString(announcementCreateDto));

            Announcement savedAnnouncementResult = _announcementRepository.save(announcement);

            announcementResponseDto = ValueMapper.convertToDTO(savedAnnouncementResult);
            log.debug("AnnouncementService:createNewAnnouncement received response from database {}", ValueMapper.jsonAsString(savedAnnouncementResult));
        } catch (Exception e) {
            log.error("Exception occurred while persisting announcement to database , exception message {}", e.getMessage());
            throw new AnnouncementServiceBusinessException("Exception occurred while create a new announcement");
        }

        log.info("AnnouncementService:createNewAnnouncement execution ended");
        return announcementResponseDto;

    }

    public List<AnnouncementResponseDto> getAllAnnouncements() throws AnnouncementServiceBusinessException {

        List<AnnouncementResponseDto> announcementResponseDtoList = new ArrayList<>();

        try {
            log.info("AnnouncementService:getAllAnnouncements method execution started");
            Iterable<Announcement> announcements = _announcementRepository.findAll();
            for (var announcement : announcements) {
                announcementResponseDtoList.add(ValueMapper.convertToDTO(announcement));
            }

            log.debug("AnnouncementService:getAllAnnouncements retrieved announcements from database {}", ValueMapper.jsonAsString(announcementResponseDtoList));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving announcements from database , Exception message {}", e.getMessage());
            throw new AnnouncementServiceBusinessException("Exception occurred while fetch all announcements from Database");
        }
        log.info("AnnouncementService:getAllAnnouncements execution ended");
        return announcementResponseDtoList;
    }

    public AnnouncementResponseDto getAnnouncementById(int announcementId) throws AnnouncementServiceBusinessException {
        AnnouncementResponseDto announcementResponseDto;

        try {
            log.info("AnnouncementService:getAnnouncementById execution started");
            Announcement announcement = _announcementRepository.findById(announcementId).orElseThrow(() -> new AnnouncementNotFoundException("Cannot find the announcement with the id: " + announcementId));
            announcementResponseDto = ValueMapper.convertToDTO(announcement);
            log.debug("An announcement with id: " + announcementId + " has been retrieved successfully from database {}", ValueMapper.jsonAsString(announcementResponseDto));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving announcement with id {} from database , Exception message {}", announcementId, e.getMessage());
            throw new AnnouncementServiceBusinessException("Error occurred when fetching the announcement with id " + announcementId + " from database");
        }

        log.info("AnnouncementService:getAnnouncementById execution ended");
        return announcementResponseDto;
    }

    public AnnouncementResponseDto updateAnnouncement(int announcementId, AnnouncementUpdateDto announcementUpdateDto) throws AnnouncementServiceBusinessException{
        AnnouncementResponseDto announcementResponseDto;
        try {
            log.info("AnnouncementResponseDto:updateAnnouncement execution started");
            Announcement announcement = _announcementRepository.findById(announcementId).orElseThrow(()->new AnnouncementNotFoundException("Cannot find the announcemen with the id " +announcementId));
            announcement.setDescription(announcementUpdateDto.getDescription());
            _announcementRepository.save(announcement);
            announcementResponseDto = ValueMapper.convertToDTO(announcement);
            log.debug("A announcement with id {} has been saved successfully {}", announcement.getId(), ValueMapper.jsonAsString(announcementResponseDto));
        }
        catch (Exception ex){
            log.error("Error occurred while updating the announcement with the id {}",announcementId);
            throw new AnnouncementServiceBusinessException("Failed to update announcement with the id " + announcementId);
        }

        log.info("AnnouncementService:updateAnnouncement execution ended");
        return announcementResponseDto;
    }

    public void deleteAnnouncementById(int announcementId) throws AnnouncementServiceBusinessException{
        log.info("AnnouncementService:deleteAnnouncementById method has been started");

        try{
            _announcementRepository.deleteById(announcementId);
            log.debug("An announcement with id {} has been deleted successfully", announcementId);
        }
        catch (Exception ex){
            log.error("Exception occurred while trying to remove a announcement from database with the id {}", announcementId);
            throw new AnnouncementServiceBusinessException("Error occurred while deleting an announcement from database with the id " + announcementId);
        }

        log.info("AnnouncementService:deleteAnnouncementById execution ended");

    }
}
